package tries;

import java.util.LinkedList;
import java.util.Queue;

public class RwTries<V> {
	
	private static final int r = 256;
	private Node root = new Node();
	
	private static class Node{
		Object value;
		Node[] next;
		
		public Node () {
			next = new Node[r];
		}
	}
	
	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}
	
	private Node put(Node r, String str, V value, int i) {
		if(r == null) r = new Node();
		if(i == str.length()) {
			r.value = value; 
			return r;
		} else {
			char ch = str.charAt(i);
			r.next[ch]= put(r.next[ch], str, value, i+1);
		}
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public V get(String key) {
		Node r = get(root, key, 0);
		if(r == null) return null;
		return (V) r.value;
	}
	
	private Node get(Node r, String key, int d) {
		if (r == null) return null;
		if (d == key.length())  return r;
		else {
			char ch = key.charAt(d);
			return get(r.next[ch], key, d + 1);
		}
	}
	
	public Iterable<String> keys() {
		Queue<String> queue = new LinkedList<String>();
		collect(root, "", queue);
		return queue;
	}
	
	private void collect(Node r, String str, Queue<String> queue) {
		if (r != null) {
			if(r.value != null) queue.add(str);
			for(char i = 0; i < r.next.length; i++)
				collect(r.next[i], str + i, queue);
		}
	}
	
	public Iterable<String> keysWithPrefix(String str){
		Node r = get(root, str, 0);
		Queue<String> queue = new LinkedList<String>();
		collect(r, str, queue);
		return queue;
	}
	
	public String longestPrefixOf(String str){
		int tam = longestPrefixOf(root, str, 0, 0);
		return str.substring(0, tam);
		
	}
	
	private int longestPrefixOf(Node r, String query, int d, int tam) {
		if(r == null) return tam;
		if(r.value != null) tam = d;
		if(query.length() == d) return tam;
		char ch = query.charAt(d);
		
		return longestPrefixOf(r.next[ch], query, d+1, tam);
	}
	
	public Iterable<String> keysThatMatch(String s) {
        Queue<String> queue = new LinkedList<String>();
        keysThatMatch(root, "", s, queue);
        return queue;
    }
	
	private void keysThatMatch(Node r, String prefix, String s, Queue<String> queue) {
        if (r == null) return;
        if (prefix.length() == s.length() && r.value != null) queue.add(prefix);
        if (prefix.length() == s.length()) return;
        char c = s.charAt(prefix.length());
        if (c == '.') {
            for (char ch = 0; ch < RwTries.r; ch++) 
            	keysThatMatch(r.next[ch], prefix + ch, s, queue);
        } else {
        	prefix = prefix + c;
        	keysThatMatch(r.next[c], prefix, s, queue);
        }
    }
	
}
