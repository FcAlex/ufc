package br.ufc.crateus.eda.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class AdjacencyMatrixGraph<T> implements Graph<T>{
	
	// IMPLEMENTAÇÃO ERRADA
	
	class Vertice {
		int vertice;
	    T label;
	    
	    Vertice(T label, int vertice) {
	        this.label = label;
	        this.vertice = vertice;
	    }
	}
	
	private Map<Vertice, Map<Vertice, Boolean>> adjMatrix;
	private int numVertice;
	private int numEdges;
	public boolean ok;

	public AdjacencyMatrixGraph() {
		this.adjMatrix = new HashMap<Vertice, Map<Vertice, Boolean>>();
		this.numVertice = 0;
	}
	
	public AdjacencyMatrixGraph(List<T> labels) {
		this();
		for(T lb : labels) addVertice(lb);
	}
	
	public void addVertice(T label) {
		adjMatrix.put(new Vertice(label, numVertice), new HashMap<Vertice, Boolean>());
		numVertice += 1;
	}
	
	@Override
	public int countVertices() {
		return adjMatrix.size();
	}

	@Override
	public int countEdges() {
		return numEdges;
	}

	@Override
	public int index(T v) {
		for(Vertice vt : adjMatrix.keySet())
			if(vt.label.equals(v)) return vt.vertice;
		return -1;	
	}

	@Override
	public T label(int index) {
		for(Vertice vt : adjMatrix.keySet())
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
		
		for(Vertice vt : adjMatrix.keySet()) {
			if(vt.label.equals(v1)) vt1 = vt;
			if(vt.label.equals(v2)) vt2 = vt;
		}
//		
		if(vt1 != null && vt2 != null) {
			adjMatrix.get(vt1).put(vt2, true);
			adjMatrix.get(vt2).put(vt1, true);
		    numEdges = numEdges + 1;
		}
	}

	@Override
	public Iterable<T> adjacents(T v) {
		Queue<T> queue = new LinkedList<T>();
		
		for(Vertice vt : adjMatrix.keySet()) {
			if(vt.label.equals(v)) {
				for(Vertice vt2 : adjMatrix.get(vt).keySet())
					queue.add(vt2.label);
			}
		}
		
		return queue;
	}

	@Override
	public int degree(T v) {
		for(Vertice vt : adjMatrix.keySet())
			if(vt.label.equals(vt)) return adjMatrix.get(vt).size();
		return 0;
	}
	
	public static void main(String[] args) {
		ArrayList<String> ar = new ArrayList<String>();
		
		ar.add("Alex");
		ar.add("Alan");
		ar.add("Gerson");
		ar.add("Jéssica");
		
		AdjacencyMatrixGraph<String> g = new AdjacencyMatrixGraph<String>(ar);
		
		
		g.addEdge("Alex", "Alan");
		g.addEdge("Alex", "Gerson");
		g.addEdge("Alex", "Jéssica");
//		
		System.out.println(g.adjacents("Alex"));
	}
	
}
