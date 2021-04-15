package br.ufc.crateus.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Grafos{
	
	private ArrayList<Vertex> vertices;
	private int numVertex, numEdge;
	
	public Grafos() {
		vertices = new ArrayList<>();
		numVertex = 0;
	}
	
	public Grafos(int numVertex) {
		this.numVertex = numVertex;
		vertices = new ArrayList<>();
		for (int i = 0; i < numVertex; i++) {
			addVertex("v"+i);
		}
	}
	
	public void addEdge(int src, int dest, int peso) {
		Vertex vs = getVertices().get(src);
		Vertex vd = getVertices().get(dest);
		
		Edge es = new Edge(getVertices().get(dest), peso);
		Edge ed = new Edge(getVertices().get(src), peso);
		
		vs.adj.add(es);
		vd.adj.add(ed);
		numEdge++;
	}
	
	public void addEdge(String src, String dest, int peso) {
		int s = index(src);
		int d = index(dest);
		
		if(s >= 0 && d >= 0) addEdge(s, d, peso);
	}
	
	public int index(String str) {
		for (Vertex v : getVertices()) {
			if(v.label.equals(str)) return v.index;
		}
		return -1;
	}

	public void addVertex(String label) {
		getVertices().add(new Vertex(label, numVertex));
		numVertex++;
	}
	
	public void dijkstra(String label) throws IndexOutOfBoundsException {
		Vertex source = getVertices().get(index(label));
		
		if(source == null) return;
		
		source.minCusto = 0;
		PriorityQueue<Vertex> heap = new PriorityQueue<Vertex>();
		heap.add(source);
		
		while(!heap.isEmpty()){
			
			Vertex u = heap.poll();
		
			for(Edge n : u.adj){
				int newDist = u.minCusto + n.peso;
				
				if(n.dest.minCusto > newDist){
					heap.remove(n.dest);
					n.dest.minCusto = newDist;
					
					n.dest.path = new LinkedList<Vertex>(u.path);
					n.dest.path.add(u);
					
					heap.add(n.dest);					
				}
			}
		}
	}

	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public int numEdge() {
		return numEdge;
	}
	
	public int numVertex() {
		return numVertex;
	}
}