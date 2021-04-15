package hash;

public class SeparateChainingHashMap<K,V> implements Map<K, V> {

	private Node[] table;
	private int size;
	

	private static class Node {
		Object key;
		Object value;
		Node next;
		
		public Node(Object key, Object value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public SeparateChainingHashMap() {
		this(97);
	}
	
	public SeparateChainingHashMap(int len) {
		this.table = new Node[len];
		this.size = 0;
	}
	
	@Override
	public void put(K key, V value) {
		Node n = getNode(key);
		if(n == null) {
			int i = hash(key);
			table[i] = new Node(key,value,table[i]);
			size++;
			
			if(this.size/table.length >= 8) resize(table.length * 2);
		}
		else {
			n.value = value;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public V get(K key) {
		Node n = getNode(key);
		return (n!= null)? (V) n.value : null;
	}
	
	private Node getNode(K key) {
		int i = hash(key);
		for(Node n = table[i]; n !=null; n = n.next) {
			if(key.equals(n.key)) return n;
		}
		return null;
	}
	
	private int hash(K key) {
		return (Math.abs(key.hashCode() & 0x7fffffff)) % table.length;
	}

	@Override
	public void remove(K key) {
		int i = hash(key);
		Node tmp = new Node(null,null,table[i]);
		
		for(Node n = tmp; n.next != null;) {
			if(key.equals(tmp.next.key)) 
				n.next = n.next.next;
				size--;
				
				if (this.size/table.length <= 2) resize(table.length/2);
			break;
		}
		table[i] = tmp.next;
	}
	
	public void delete(K key) {
		
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newLength) {
		SeparateChainingHashMap<K, V> tmp = new SeparateChainingHashMap<K, V>();
		for(int i = 0; i < table.length; i++) {
			for(Node n = table[i]; n != null; n = n.next)
				tmp.put((K) n.key, (V) n.value);
		table = tmp.table;
		}
	}

	@Override
	public Iterable<K> keys() {
		return null;
	}

	public int size() {
		return size;
	}
	
	public int[] nodeLength() {
		int[] len = new int[table.length];
		int c = 0;
		
		for(int i = 0; i < table.length; i++) {
			for(Node n = table[i]; n != null; n = n.next)
				c = c+1;
			len[i] = c;
		}
		
		return len;
	}
	
	
	
	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}

}




