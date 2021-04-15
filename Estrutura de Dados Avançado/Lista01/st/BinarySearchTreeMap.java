package br.ufc.crateus.eda.st;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {
	
	private class Node {
		K key; 
		V value;
		Node left;
		Node right;
		int count;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.count = 1;
		}
	}
	
	Node root;
	
	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	
	private Node put(Node r, K key, V value) {
		if (r == null) return new Node(key, value);
		if (key.compareTo(r.key) < 0) {
			r.left = put(r.left, key, value);
		}
		else if (key.compareTo(r.key) > 0) {
			r.right = put(r.right, key, value);
		}
		else r.value = value;
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}
	
	private int count(Node n) {
		return (n != null) ? n.count : 0; 
	}

	@Override
	public V get(K key) {
		
		return null;
	}
	
	@Override
	public int size() {
		return count(root);
	}

	private Node remove(Node r, K key) {
		if(r == null) return null;
		int cmp = key.compareTo(r.key);
		
		if(cmp < 0) r.left = remove(r.left, key);
		else if(cmp > 0) r.right = remove(r.right, key);
		else {
			if(r.left == null) return r.right;
			if(r.right == null) return r.left;
			Node tmp = r;
			r = minNode(r.right);
			r.left = tmp.left;
			r.right = removeMin(tmp.right);
		}
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K min() {
		return minNode(root).key;
	}
	
	private Node minNode(Node r) {
		if (r == null) return null;
		if(r.left == null) return r;
		return minNode(r.left);
	}
	
	public void removeMin() {
		root = removeMin(root);
	}
	
	public void removeMax() {
		root = removeMax(root);
	}
	
	private Node removeMin(Node r) {
		if(r == null) return null;
		if(r.left == null) return r.right;
		r.left = removeMin(r.left);
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}
	
	private Node removeMax(Node r) {
		if(r == null) return null;
		if(r.right == null) return r.left;
		r.right = removeMax(r.right);
		r.count = count(r.left) + count(r.right) + 1;
		return r;
	}

	@Override
	public K max() {
		return maxNode(root).key;
	}
	
	private Node maxNode(Node r) {
		if (r == null) return null;
		if(r.right == null) return r;
		return minNode(r.right);
	}
	

	@Override
	public K ceiling(K val) {
		return ceiling(root, val);
	}

	@Override
	public K floor(K val) {		
		return floor(root, val);
	}
	
	private K floor(Node r, K val) {
		if(r == null) return null;
		int cmp = val.compareTo(r.key);
		if(cmp < 0) return floor(r.left, val);
		else if(cmp > 0) {
			K tmp = floor(r.right, val);
			return (tmp != null) ? tmp : r.key;
		}
		return r.key;
	}
	
	private K ceiling(Node r, K val) {
		if(r == null) return null;
		int cmp = val.compareTo(r.key);
		if(cmp > 0) return floor(r.left, val);
		else if(cmp < 0) {
			K tmp = floor(r.right, val);
			return (tmp != null) ? tmp : r.key;
		}
		return r.key;
	}
	
	@Override
	public void remove(K key) {
		root = remove(root, key);
	}

	@Override
	public int rank(K val) {
		return rank(root, val);
	}
	
	private int rank(Node r, K val) {
		if(r == null) return 0;
		int cmp = val.compareTo(r.key);
		if(cmp < 0) rank(r.left, val);
		if(cmp > 0) return count(r.left) + rank(r.right, val) + 1;
		return count(r.left) + 1;
	}

	@Override
	public int size(K lo, K hi) {
		return height(root);
	}
	
	private int height(Node r) {
		if(r == null) return -1;
		int hleft = height(r.left);
		int hright = height(r.right);
		
		if(hleft > hright) return hleft + 1;
		return hright + 1;
	}
	
	private Node convertToTree(LinkedListMap<K, V> ls, Node node) {
		int half = ls.size()/2;
		LinkedList<K> tmp = (LinkedList<K>) ls.keys();
		K chave = tmp.get(half);
		node = new Node(chave, ls.get(chave));
		for(int i = 0; i < ls.size(); i++) {
			chave = tmp.get(i);
			put(chave, ls.get(chave));
		}
		
		return node;
	}
	
	public void convertToTree(LinkedListMap<K, V> ls) {
		convertToTree(ls, root);
	}
	
	public void balancearArvore() {
		LinkedListMap<K, V> lsm = new LinkedListMap<K, V>();
		lsm = toLinkedListInOrder(root, lsm);
		root = convertToTree(lsm, root);
	}
	
	private LinkedListMap<K, V> toLinkedListInOrder(Node node, LinkedListMap<K, V> ls) {
		if(node != null) {
			toLinkedListInOrder(node.right, ls);
			ls.put(node.key, node.value);
			toLinkedListInOrder(node.left, ls);
		}
		return ls;
	}
	
}
