package br.ufc.crateus.util;

public class Edge {
	
	public final Vertex dest;
	public final int peso;
	
	public Edge(Vertex dest, int peso) {
		this.dest = dest;
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return dest + "," + peso;
	}
	
}
