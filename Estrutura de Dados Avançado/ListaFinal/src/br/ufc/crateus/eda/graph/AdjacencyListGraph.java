package br.ufc.crateus.eda.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdjacencyListGraph<T> implements Graph<T> {
	
	class Vertice {
		int vertice;
	    T label;
	    
	    Vertice(T label, int vertice) {
	        this.label = label;
	        this.vertice = vertice;
	    }
	}
	
	private Map<Vertice, List<Vertice>> adjVertices;
	private int numEdges;
	private int numVertice;
	
	public AdjacencyListGraph() {
		this.adjVertices = new HashMap<Vertice, List<Vertice>>();
		this.numEdges = 0;
		this.numVertice = 0;
	}
	
	public AdjacencyListGraph(List<T> labels) {
		this();
		for(T lb : labels) addVertice(lb);
	}
	
	public void addVertice(T label) {
		adjVertices.put(new Vertice(label, numVertice), new ArrayList<>());
		numVertice = numVertice + 1;
	}
	
	@Override
	public int countVertices() {
		return adjVertices.size();
	}

	@Override
	public int countEdges() {
		return numEdges;
	}

	@Override
	public int index(T v) {
		for(Vertice vt : adjVertices.keySet())
			if(vt.label.equals(v)) return vt.vertice;
		return -1;	
	}

	@Override
	public T label(int index) {
		for(Vertice vt : adjVertices.keySet())
			if(vt.vertice == index) return vt.label;
		return null;
	}

	@Override
	public boolean contains(T v) {
		return index(v) != -1;
	}

	@Override
	public void addEdge(T v1, T v2) {
		
		Vertice vt1 = null;
		Vertice vt2 = null;
		
		for(Vertice vt : adjVertices.keySet()) {
			if(vt.label.equals(v1)) vt1 = vt;
			if(vt.label.equals(v2)) vt2 = vt;
		}
		
		if(vt1 != null && vt2 != null) {
		    adjVertices.get(vt1).add(vt2);
		    adjVertices.get(vt2).add(vt1);
		    numEdges = numEdges + 1;
		}
		
	}

	@Override
	public Iterable<T> adjacents(T v) {
		Queue<T> queue = new LinkedList<T>();
		
		for(Vertice vt : adjVertices.keySet()) {
			if(vt.label.equals(v)) {
				for(Vertice vt2 : adjVertices.get(vt))
					queue.add(vt2.label);
			}
		}
		
		return queue;
	}
	
	@Override
	public int degree(T v) {
			for(Vertice vt : adjVertices.keySet())
				if(vt.label.equals(vt)) return adjVertices.get(vt).size();
			return 0;

	}
	
	public static void main(String[] args) {
		ArrayList<String> ar = new ArrayList<String>();
		
		ar.add("Alex");
		ar.add("Alan");
		ar.add("Gerson");
		ar.add("J�ssica");
		
		AdjacencyListGraph<String> g = new AdjacencyListGraph<String>(ar);
		
		
		g.addEdge("Alex", "Alan");
		g.addEdge("Alex", "Gerson");
		g.addEdge("Alex", "J�ssica");
		
		System.out.println(g.adjacents("Alex"));
	}
	
}