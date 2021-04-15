package tries;

public class TST<V> {
	private Node root;
	
	private static class Node {
		char ch;
		Object value;
		Node left;
		Node mid;
		Node rigth;
		
		Node (char ch) {
			this.ch = ch;
		}
	}
	
	@SuppressWarnings("unchecked")
	public V get (String key) {
		return (V) get(root, key, 0).value;
	}
	
	private Node get(Node r, String key, int d) {
		char ch = key.charAt(d);
		if(r == null) return null;
		
		if(r.ch < ch) return get(r.left, key, d);
		else if(r.ch > ch) get(r.rigth, key, d); 
		if(d == key.length()-1) return r;
		return get(r.mid, key, d+1);
		
	}
	
	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}
	
	private Node put(Node r, String key, V value, int d) {
		char ch = key.charAt(d);
		
		if(r == null) {
			r = new Node(ch);
		}
		
		if(r.ch < ch) r.left =  put(r.left, key, value, d);
		else if(r.ch > ch) r.rigth = put(r.rigth, key, value, d); 
		else {
			if(d == key.length()-1) {
				r.value = value;
				return r;
			}
			r.mid = put(r.mid, key, value, d+1);
		}
		
		return r;
	}

}
