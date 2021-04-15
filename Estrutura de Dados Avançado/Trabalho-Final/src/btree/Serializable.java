package btree;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;

import dtypes.LongDT;
import dtypes.DataType;

public class Serializable<K extends Comparable<K>, V> {
	
	private DataType<K> keyDT;
	private DataType<V> valueDT;
	private LongDT adressDT;
	private int ordem;
	protected File file; 

	static final int N_KEYS_OFFSET = 0;
	static final long ROOT_OFFSET = 4;

	private int nPage;
	
	public Serializable(File file, DataType<K> keyDT, DataType<V> valueDT, int ordem) throws IOException {
		this.file = file;
		this.keyDT = keyDT;
		this.valueDT = valueDT;
		this.adressDT = new LongDT();
		this.ordem = ordem;
		
		if (file.exists()) file.delete();
		RandomAccessFile fileStore = new RandomAccessFile(file, "rw");
		fileStore.seek(0);
		fileStore.writeInt(1);
		this.nPage = 1;
		fileStore.close();
	}
	
	public void writeDefault(long offset) throws Exception {
		if(offset > file.length()) return;
		RandomAccessFile fileStore = new RandomAccessFile(file, "rw");
		fileStore.seek(offset);
		keyDT.write(keyDT.getSentinel(), fileStore);
		valueDT.write(valueDT.getSentinel(), fileStore);
		fileStore.close();
	}
	
	public int sizeKeys() {
		return keyDT.size();
	}
	
	public int sizeValues() {
		return valueDT.size();
	}
	
	public int pageSize() {
		return 1 + 8 + 4 + ordem * (keyDT.size() + valueDT.size() + 8);  
	}
	
	public void write(long offset, Page<K, V> page) throws Exception {
		RandomAccessFile fileStore = new RandomAccessFile(file, "rw");
		fileStore.seek(offset);
		fileStore.writeBoolean(page.isLeaf());
		fileStore.writeLong(page.adressParent());
		fileStore.writeInt(page.size());
		LinkedList<Long> son = (LinkedList<Long>) page.children();
		int i = 0;
		
		while(i < page.size()) {
			adressDT.write(son.get(i), fileStore);
			keyDT.write(page.keys.get(i), fileStore);
			valueDT.write(page.values.get(i), fileStore);
			i++;
		}
		
		adressDT.write(son.get(i), fileStore);
			
		fileStore.close();
	}
	
	public void writeParent(Page<K, V> son, Page<K, V> parent) throws Exception {
		RandomAccessFile fileStore = new RandomAccessFile(file, "rw");
		fileStore.seek(son.getOffset()+1);
		fileStore.writeLong(parent.getOffset());	
		
		fileStore.close();
		
	}
	
	public Page<K, V> read(long offset) throws Exception {
		RandomAccessFile fileStore = new RandomAccessFile(file, "r");
		fileStore.seek(offset+1+8); 
		int size = fileStore.readInt();
		
		List<K> lKeys = new LinkedList<K>();
		List<V> lValues = new LinkedList<V>();
		List<Long> lChildren = new LinkedList<Long>();
		
		for(int i = 0; i < size; i++) {
			long child = adressDT.read(fileStore);
			K key = keyDT.read(fileStore);
			V value = valueDT.read(fileStore);
			lChildren.add(child);
			lKeys.add(key);
			lValues.add(value);
		}
		
		lChildren.add(adressDT.read(fileStore));
		
		
		fileStore.close();
		
		Page<K, V> page = new Page<>(ordem, this, offset);
		page = page.fillPage(lKeys, lValues);
		
		if(lChildren.get(0) != -1) {
			page.setLeaf(false);
			for(int i = 0; i <= size; i++) {
				Page<K, V> aux = read(lChildren.get(i));
				page.children.add(aux.getOffset());
			}
		} else page.children = new LinkedList<Long>();
		return page;
	}
	
	public void pageInserted() throws IOException {
		nPage += 1;
		RandomAccessFile fileStore = new RandomAccessFile(file, "rw");
		fileStore.seek(0);
		fileStore.writeInt(nPage);
		fileStore.close();
	}
	
	public int getNumberOfPage() {
		return nPage;
	}

	
	
}