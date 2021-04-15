package br.ufc.crateus.eda.heap;

public class Heap<T extends Comparable<T>> { // Heap Minima
	
	private T[] values;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Heap(int n) {
		this.values = (T[]) new Object[n+1];
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T min() {
		return (isEmpty()) ? null : values[1];
	}
	
	public void insert(T value) {
		if(size >= values.length) return;
		
		values[size] = value;
		upHeap(size);
	}
	
	public T remove() {
		T v = values[1];
		values[1] = values[size ];
		values[size] = null ;
		size --;
		downHeap(1);
		return v;

	}
	
	private void downHeap(int i) {
		while (2*i <= size) {
			int j = i*2;
			if (j<size && smaller(j+1, j)) j++;
			if (smaller(i, j)) break;
			swap(i, j);
			i = j;
		}
	}

	private void upHeap(int i) {
		while(i>1 && smaller(i, i/2)) {
			swap(i, i/2);
			i = i/2;
		}
		
	}

	private void swap(int i, int j) {
		T tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	private boolean smaller(int i, int j) {
		return values[i].compareTo(values[j]) < 0;
	}
}
