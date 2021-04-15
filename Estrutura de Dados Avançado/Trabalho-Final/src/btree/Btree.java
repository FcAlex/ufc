package btree;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import dtypes.DataType;

class BTree<K extends Comparable<K>, V> {

	int ordem;
	private Serializable<K, V> serializable;
	Page<K, V> root;
	

    public BTree(int ordem, String path, DataType<K> keyDT, DataType<V> valueDT) throws Exception {
    	File file = new File(path);
    	serializable = new Serializable<K, V>(file, keyDT, valueDT, ordem);
    	root = new Page<K, V>(ordem, serializable, Serializable.ROOT_OFFSET);
		this.ordem = ordem;
		if(ordem%2 != 0) throw new IllegalArgumentException(" A ordem deve ter tamanho par!!!");
    	
    }

	public V get(K key) throws Exception {
		V tst = get(root, key);
		return tst;
    }

	private V get(Page<K, V> r, K key) throws Exception {
		int i = 0;
		
		while(i < r.size()-1 && key.compareTo(r.keys.get(i)) > 0)
			i = i + 1;
		
		if(i <= r.size()-1 && key.compareTo(r.keys.get(i)) == 0)
			return r.values.get(i);
		
		if (r.isLeaf())
			return null;
		
		if(key.compareTo(r.keys.get(r.size()-1)) > 0) {
			long offset = r.children.get(i+1);//.getOffset();
			Page<K, V> temp = serializable.read(offset);
			return get(temp, key);
		}
	
		long offset = r.children.get(i);//.getOffset();
		Page<K, V> temp = serializable.read(offset);
		
		return get(temp, key);
	}

	
	public void put(K key, V val) throws Exception {
		
		if(root.keys.isEmpty()){
			root.keys.add(key);
			root.values.add(val);
			serializable.write(Serializable.ROOT_OFFSET, root);
		} else {
			Page<K, V> temp = root;
			int i = -1;
			while(i < 0){
				i = 0;
				while(i<temp.size() && temp.keys.get(i).compareTo(key)<0)
					i++;
					
				if(!temp.isLeaf()){
//					temp = temp.children.get(i);
					temp = temp.next(i);
					i = -1;
				}
			}
			
			temp.keys.add(i, key);
			temp.values.add(i, val);
			
			Page<K, V> pg = temp;
			
			if(temp.size() == ordem) {
				pg = split(temp, false);
			}
			
			serializable.write(pg.getOffset(), pg);
			
		}	
    }
    
	private Page<K, V> split(Page<K, V> n, boolean splitAgain) throws Exception{
		Page<K, V> p;
    	int i = 0;
    	long size;
    	if(n.getParent()==null){ 
    		p = new Page<K, V>(ordem, serializable, n.getOffset());
    		root = p;
    		n.setParent(p);
    		size = (serializable.pageSize())*serializable.getNumberOfPage();
    		n.setOffset(n.getOffset() + size);
    		p.children.add(i, n.getOffset());  // adiciona n com filho de p (nova raiz vazia)
    		serializable.pageInserted();
    	} else{
    		p = n.getParent();
    		i = p.children.indexOf(n.getOffset());
    	}
    	size = 4+serializable.getNumberOfPage()*serializable.pageSize();
    	Page<K, V> temp = new Page<K, V>(ordem, serializable, size); // cria pagina temporaria
    	p.children.add(i+1, temp.getOffset()); // adiciona em p, como filho a direita temp
    	p.keys.add(i, n.keys.get(ordem/2)); // adiciona a chave central em p
    	p.values.add(i, n.values.get(ordem/2)); // adiciona a valor central em p
    	temp.setParent(p);
    	
    	int half = ordem/2;
    	
    	for(int j = 0;j<(ordem/2)-1;j++){ // adicionar na segunda metade chave e valor em temp
    		temp.keys.add(j, n.keys.get(j+half+1));
    		temp.values.add(j, n.values.get(j+half+1));
    	}
    	
    	for(int j = 0;j<half;j++){ // remove os elementos de n
    		n.keys.remove(half);
    		n.values.remove(half);
    	}
    	
    	if(!n.isLeaf()){ // se n tiver filhos
    		for(int j = 0;j<half;j++) // adiciona a segunda metade filhos em temp
    			temp.children.add(j, n.children.get(j+half+1));
    			
    		for(int j = 0;j<half;j++) // remove os filhos ate a metade
    			n.children.remove(half+1);
    			
    		for(int j = 0;j<half;j++) {
    			Page<K, V> pg = serializable.read(temp.children.get(j));
    			serializable.writeParent(pg, temp);
    		} 
    		
    		for(int j = 0;j<half;j++) {
    			Page<K, V> pg = serializable.read(n.children.get(j));
    			serializable.writeParent(pg, n);
    		}
    	}
    
    	
    	
    	
    	serializable.write(n.getOffset(), n);
    	if(splitAgain) temp.setLeaf(false);
		serializable.write(temp.getOffset(), temp);
    	
    	serializable.pageInserted();
    	
    	if(p.size()==ordem) {
    		p = split(p, true);
    	}
    	
    	p.setLeaf(false);
			
		return p;
    }
	
	public Iterable<K> keys() throws Exception {
		Queue<K> queue = new LinkedList<>();
		collect(root, queue);
		return queue;
	}

	private void collect(Page<K, V> r, Queue<K> queue) throws Exception {
		
		if (r.isLeaf()) {
			for (K key : r.keys) queue.add(key);
		} else {
			for(int i = 0; i < r.keys.size()+1; i++) {
//				collect(r.children.get(i), queue);
				collect(serializable.read(r.children.get(i)), queue);
				
				try {
					queue.add(r.keys.get(i));
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public int height() throws Exception {
    		if(root == null)
    			return -1;
    		else{
    			int h=0;
    			Page<K, V> temp = root;
    			while(!temp.children.isEmpty()){
    				h++;
    				temp= serializable.read(temp.children.get(0));
    			}
    			
    			return h;
    		}
    }
	
	// vê:  https://github.com/samarthaggarwal/B-Trees/blob/master/myfiles/BTree.java para fazer o remove
	
}
