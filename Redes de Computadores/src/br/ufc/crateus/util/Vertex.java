package br.ufc.crateus.util;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{
	
	public List<Edge> adj;
	public String label;
	public int index;

	public List<Vertex> path;
	public int minCusto = Integer.MAX_VALUE;
//	public Vertex previous;
	
	Vertex(String label, int index) {
        this.label = label;
        this.index = index;
        adj = new ArrayList<Edge>();
        path = new ArrayList<Vertex>();
    }
	
	@Override
	public String toString() {
		return label;
	}

	@Override
	public int compareTo(Vertex other) {
		return Integer.compare(minCusto, other.minCusto);
	}
	
	public boolean isAdjacent(Vertex other) {
		Iterable<Edge> n = adj;
		for (Edge e : n)
			if(e.dest.equals(other)) return true;
		
		return false;
	}
	
	public int pesoEdge(Vertex other) {
		Iterable<Edge> n = adj;
		for (Edge e : n)
			if(e.dest.equals(other)) return e.peso;
		
		return -1;
	}
	
	public int indexEdge(Vertex other) {
		for (int i = 0; i < adj.size(); i++) {
			if(adj.get(i).dest.equals(other)) {
				return i;
			}
		}
		return -1;
	}
}
