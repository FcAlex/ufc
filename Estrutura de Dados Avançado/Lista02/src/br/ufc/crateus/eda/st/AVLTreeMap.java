package br.ufc.crateus.eda.st;

public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {
	
	protected class AVL_Node extends Node {
		int altura;
		AVL_Node left;
		AVL_Node right;
		
		public AVL_Node(K key, V value) {
			super(key, value);
			altura = 0;
		}

		@Override
		Node left() {
			return left;
		}

		@Override
		Node right() {
		 	return right;
		}
		
	}
	
	private AVL_Node root;
	
	public AVL_Node getRoot() {
		return root;
	}
	
	private AVL_Node rotateLeft(AVL_Node h) {
		AVL_Node x = h.right;
		h.right = x.left;
		x.left = h;
		
		h.count = count(h.left) + count(h.right) + 1;
		x.count = h.count + count(x.right) + 1;
		return x;
	}

	private AVL_Node rotateRight(AVL_Node h) {
		AVL_Node x = h.left;
		h.left = x.right;
		x.right = h;
		
		h.count = count(h.left) + count(h.right) + 1;
		x.count = h.count + count(x.right) + 1;
		return x;
	}
	
	private int fatorB(AVL_Node r) {
		if (r == null) return 0;
		
		if(r.right == null) {
			if(r.left == null) return 0;
			else return r.left.altura;
		} else if (r.left == null) {
			return -r.right.altura;
		} else {
			return r.left.altura - r.right.altura; 
		}
	}
	
	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	
	protected AVL_Node put(AVL_Node r, K key, V value) {
		if (r == null) return new AVL_Node(key, value);
		if (key.compareTo(r.key) < 0) r.left = put(r.left, key, value);
		else if (key.compareTo(r.key) > 0) r.right = put(r.right, key, value);
		else r.value = value;

		if(fatorB(r) == -2) {
			if (fatorB(r.left) > 0) r = rotateRight(r.left);
			r = rotateLeft(r);
		}
		
		if(fatorB(r) == 2) {
			if (fatorB(r.right) < 0) rotateLeft(r.right);
			r = rotateRight(r);
		}
		
		r.altura++;

		return r;		
	}
	
	private AVL_Node sucessor(AVL_Node n, AVL_Node pai) {
		if (n.right != null) {
			AVL_Node noDireita = n.right;
			while (noDireita.left != null) {
				noDireita = noDireita.left;
			}
			return noDireita;
		} else {
			AVL_Node noPai = pai;
			while (noPai != null && n == pai.right) {
				n = noPai;
				noPai = pai;
			}
			return noPai;
		}
	}
	
	@Override
	public void remove (K key) {
		root = remove(root, null, key);
	}
	
	protected AVL_Node remove(AVL_Node r, AVL_Node pai, K key) {
		
		if (r == null) return null;
		if (key.compareTo(r.key) < 0) r.left = remove(r.left, r, key);
		else if (key.compareTo(r.key) > 0) r.right = remove(r.right, r, key);
		else {
			AVL_Node temp;
			AVL_Node temp2;
			
			System.out.println("OK");
			
			if(r.left == null || r.right == null) {
				if(pai == null) {
					this.root = null;
					r = null;
				}
				temp = null;
			} else {
				temp = sucessor(r, pai);
				r.key = temp.key;
			}
			
			if(temp.left == null) temp2 = temp.right;
			else temp2 = temp.left;
			
			if(pai == null) {
				this.root = temp2;
			} else {
				if(temp == pai.left) pai.left = temp2;
				else pai.right = temp2;
			}
			temp = null;
		}

		if(fatorB(r) == -2) {
			if (fatorB(r.left) > 0) r = rotateRight(r.left);
			r = rotateLeft(r);
		}
		
		if(fatorB(r) == 2) {
			if (fatorB(r.right) < 0) rotateLeft(r.right);
			r = rotateRight(r);
		}
		
		r.altura--;

		return r;		
	}
	
	@Override
	public String toString() {
		int ce = (int) root.right.right.key;
		String chave = "" + ce;
		return chave;
	}
}
