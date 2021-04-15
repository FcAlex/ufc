package br.ufc.crateus;

import br.ufc.crateus.reader.RouteReader;
import br.ufc.crateus.util.Grafos;
import br.ufc.crateus.util.Vertex;

public class Main {
	public static void main(String[] args) {
		RouteReader rr = new RouteReader("input.txt");
		Grafos t = rr.lerGrafos();
		
//		t.addVertex("u");
//		t.addVertex("v");
//		t.addVertex("x");
//		t.addVertex("w");
//		t.addVertex("y");
//		t.addVertex("z");
//		t.addEdge("u", "v", 2);
//		t.addEdge("u", "x", 1);
//		t.addEdge("u", "w", 5);
//		t.addEdge("x", "v", 2);
//		t.addEdge("x", "w", 3);
//		t.addEdge("x", "y", 1);
//		t.addEdge("v", "w", 3);
//		t.addEdge("y", "w", 1);
//		t.addEdge("y", "z", 2);
//		t.addEdge("w", "z", 5);
		
//		t.addEdge("x", "y", 2);
//		t.addEdge("x", "z", 7);
//		t.addEdge("y", "z", 1);
		
//		t.dijkstra("x");
		
		rr.roteamento(System.out, t);
		
		
		rr.rota(System.out, t);
//		for(Vertex v : t.getVertices()){
//			System.out.print("Vertice - "+v+" , Custo Total - "+ v.minCusto + " , Caminho - ");
//			for(Vertex pathvert:v.path) {
//				System.out.print(pathvert+" ");
//			}
//			System.out.println(""+v);
//		}
	}
}