package btree;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Page<K extends Comparable<K>, V> {
	private int ordem;
	protected ArrayList<K> keys; // conjunto de chaves
	protected ArrayList<V> values; // conjunto de valores
//	protected ArrayList<Page<K, V>> children; // conjunto de filhos
	protected LinkedList<Long> children; // endereço dos filhos
	private Page<K, V> parent; // pagina pai (root possui pai null)
	private Serializable<K, V> serializable; 
	private long offset; // posicao no arquivo
	private boolean leaf;

	public Page(int n, Serializable<K, V> serializable, long offset){
		this.ordem = n;
		this.keys = new ArrayList<K>();
		this.values = new ArrayList<V>();
//		this.children = new ArrayList<Page<K, V>>();
		this.parent = null;
		this.offset = offset;
		this.serializable = serializable;
		this.leaf = true;
		this.children = new LinkedList<Long>();
	}
	
	public Page<K, V> getParent() {
		return parent;
	}
	
	public void setParent(Page<K, V> parent) {
		this.parent = parent;
	}
	
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public boolean isLeaf() {
		return leaf;
	}
	
	public long adressParent() {
		if(parent == null) return -1;
		return parent.offset;
	}

	public int size() {
		return keys.size();
	}

	public Page<K, V> fillPage(List<K> keys, List<V> values) {
		if(keys.size() != values.size()) 
			throw new IllegalArgumentException(" O tamanho da chave e valor devem ser iguais!!!");
		
		Page<K, V> res = new Page<K, V>(ordem, serializable, offset);
		res.keys.addAll(keys);
		res.values.addAll(values);
		return res;

	}

	public Iterable<Long> children() {
		LinkedList<Long> queue = new LinkedList<Long>();
		
		if(isLeaf()) 
			for(int i = 0; i < ordem; i++)
				queue.add(-1L);
		else {
			for (Long pg : children) {
				queue.add(pg);
			}
		}
		return queue;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public Page<K, V> next(int i) throws Exception {
		RandomAccessFile fileStore = new RandomAccessFile(serializable.file, "rw");
		long inicio = 1 + 8 + 4;
		long pos = serializable.sizeKeys() + serializable.sizeValues() + 8;
		fileStore.seek(pos*i + inicio + this.offset);
		Page<K, V> k = serializable.read(fileStore.readLong());
		k.setParent(this);
		fileStore.close();
		return k;
	}

}
