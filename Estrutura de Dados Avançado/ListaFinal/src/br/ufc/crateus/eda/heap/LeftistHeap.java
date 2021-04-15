package br.ufc.crateus.eda.heap;

public class LeftistHeap<T extends Comparable<T>> {
	
	class Node {
		T element;
		Node left;
		Node right;
		int dist; // menor comprimento do caminho ate NULL
		
		public Node(T element) {
			this.element = element;
			this.left = null;
			this.right = null;
			this.dist = 0;
		}
	}
	
	private Node root;
	
	public LeftistHeap() {
		this.root = null;
	}
	
	public void insert(T val) {
		 root = merge(new Node(val), root); 
	}
	
	public T getMin() {
		return root.element;
	}
	
	public void deleteMin() { 
	    root = merge(root.left, root.right);
	} 
	
	public void merge(LeftistHeap<T> lheap) {
		if (this.equals(lheap)) 
	        return; 
	    root = merge(root, lheap.root); 
	    lheap.root = null; 
	}

	private Node merge(Node h1, Node h2) {
		if (h1 == null) 
	        return h2; 
	    if (h2 == null) 
	        return h1; 
	    if (h1.element.compareTo(h2.element) < 0) 
	        return mergeHeap(h1, h2); 
	    else
	        return mergeHeap(h2, h1); 
	}

	private Node mergeHeap(Node h1, Node h2) {
		if (h1.left == null) 
	        h1.left = h2; 
	    else
	    { 
	        h1.right = merge(h1.right, h2); 
	        if (h1.left.dist < h1.right.dist) 
	            swap(h1); 
	        h1.dist = h1.right.dist + 1; 
	    } 
	    return h1;
	}

	private void swap(Node h) {
		Node tmp = h.left; 
	    h.left = h.right; 
	    h.right = tmp; 
	}
}
