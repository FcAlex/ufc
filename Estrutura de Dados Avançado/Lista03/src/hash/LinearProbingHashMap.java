package hash;

public class LinearProbingHashMap<K, V> implements Map<K, V> {
	
	private K[] keys;
	private V[] values;
	int size;
	
	@SuppressWarnings("unchecked")
	public LinearProbingHashMap(int len) {
		this.keys = (K[]) new Object[len];
		this.values = (V[]) new Object[len];
		size = 0;
	}
	
	public LinearProbingHashMap() {
		this(397);
	}
	
	@SuppressWarnings("unused")
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % keys.length;
	}
	
	@Override
	public void put(K key, V value) {
		int p = hash(key);
		while(keys[p] != null && !keys.equals(keys[p])) p = (p+1) % keys.length;
		if(values[p] == null) size++;
		keys[p] = key;
		values[p] = value;
		
		if((double) size / keys.length == 0.5) resize(2 * keys.length);
	}

	@Override
	public V get(K key) {
		int p = hash(key);
		while(keys[p] != null && !keys.equals(keys[p])) p = (p+1) % keys.length;
		return values[p];
	}

	@Override
	public void remove(K key) {
		int p = hash(key);
		while(keys[p] != null && !keys.equals(keys[p])) p = (p+1) % keys.length;
		if(values[p] != null) size--;
		values[p] = null;
		
		if((double) size/keys.length == 0.125) resize(keys.length/2);
	}
	
	private void resize(int newSize) {
		LinearProbingHashMap<K, V> tmp = new LinearProbingHashMap<K, V>(newSize);
		for(int i = 0; i < keys.length; i++) {
			if (keys[i] != null && values[i] != null)
				tmp.put(keys[i], values[i]);
		}
		this.keys = tmp.keys;
		this.values = tmp.values;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}


}
