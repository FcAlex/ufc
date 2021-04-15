package br.ufc.crateus.eda.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GraphUtils {
	
	private static ArrayList<Integer> distTo;
	
	public static <T> boolean shortestPath(Graph<T> g, T v1, T v2) {
		
		return false;
	}
	
    public static <T> void BFS(Graph<T> g, T s)  { 
    	distTo = new ArrayList<Integer>();
        boolean marked[] = new boolean[g.countVertices()]; 

        LinkedList<T> queue = new LinkedList<T>(); 
        marked[g.index(s)]=true; 
        queue.add(s); 
  
        while (!queue.isEmpty())  { 
            s = queue.remove();
            distTo.add(g.index(s));
//            System.out.print(s+" "); 
            Iterator<T> i = g.adjacents(s).iterator();
            while (i.hasNext()) { 
                T n = i.next(); 
                if (!marked[g.index(n)]) { 
                	marked[g.index(n)] = true; 
                    queue.add(n); 
                } 
            } 
        } 
    } 
	
	static Graph<String> readFromFile(InputStream is, String sep) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		AdjacencyListGraph<String> g = new AdjacencyListGraph<String>();
		
		String lines = bf.readLine();
		while (lines != null) {
			ArrayList<String> st = new ArrayList<String>(Arrays.asList(lines.split(",")));
			for(String w : st) g.addVertice(w);
		
			for(int i = 1; i < st.size(); i++) g.addEdge(st.get(0), st.get(i));
			
			lines = bf.readLine();		
		}
		
		return g;
	}
	
	public static void main(String[] args) throws Exception {
		ArrayList<String> ar = new ArrayList<String>();
		
		ar.add("Alex"); // 0
		ar.add("Alan"); // 1
		ar.add("Gerson"); // 2
		ar.add("Jéssica"); // 3
		
		Graph<String> g = new AdjacencyListGraph<String>(ar);
		
		g.addEdge("Alex", "Alan");
		g.addEdge("Alex", "Gerson");
		g.addEdge("Alex", "Jéssica");
		g.addEdge("Alan", "Jéssica");
		
//		GraphUtils tst = new GraphUtils();
		
		GraphUtils.shortestPath(g, "Alex", "Alan");
		
//		InputStream is = new FileInputStream(new File("arquivo.txt"));
		
//		Graph<String> gs =  GraphUtils.readFromFile(is, "/");
		
//		GraphUtils.BFS(g, "Alex");
		
//		System.out.println(gs.adjacents("Alex"));
		
//		for(int i = 0; i < 10; i++) 
//			System.out.print(tst.distTo[i] + " ");
		
//		System.out.println(tst.distTo);
//		System.out.println(tst.edgeTo);
//		System.out.println(tst.marked);
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		System.out.println(a);
	}
}
