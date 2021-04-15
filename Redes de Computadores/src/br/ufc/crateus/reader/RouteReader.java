package br.ufc.crateus.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import br.ufc.crateus.exception.FileValueInvalidException;
import br.ufc.crateus.util.Grafos;
import br.ufc.crateus.util.Vertex;

public class RouteReader {
	
	private BufferedReader br;
	private String src, dest;

	public RouteReader(String path) {
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Grafos lerGrafos() {
		Grafos grafo = new Grafos();
		try {
			String line[] = br.readLine().split(":");
			
			
			// ---------- DEVICES (VERTICES) ---------- //
			
			if(!line[0].equals("DEVICES")) throw new FileValueInvalidException();
			String values[] = line[1].split(",");
			for(String str : values) {
				grafo.addVertex(str);
			}
			
			// ---------- LINKS (ARESTAS) ---------- //
			
			line = br.readLine().split(":");
			if(!line[0].equals("LINKS")) throw new FileValueInvalidException();
			values = line[1].split("/\\(|(\\),\\()|\\)/");
			for (String str : values) {
				
				str = str.replace("(", "").replace(")", "");
				String options[] = str.split(",");
				
				if(options.length > 3) throw new FileValueInvalidException();
				
				int src = grafo.index(options[0]);
				int dest = grafo.index(options[1]);
				int peso = Integer.parseInt(options[2]);
				
				if(src == -1 || dest == -1) {
					System.out.println(options[1]);
					throw new FileValueInvalidException();
				}
				
				grafo.addEdge(src, dest, peso);
			}
			
			// ---------- ROUTES (ROTA ORIGEM DESTINO) ---------- //
			
			line = br.readLine().split(":");
			if(!line[0].equals("ROUTES") || 
					line[1].indexOf("(") != 0 || 
					line[1].indexOf(")") != line[1].length()-1) throw new FileValueInvalidException();
			else line[1] = line[1].substring(1, line[1].length()-1);
			
			String router[] = line[1].split(",");
			
			if(router.length != 2) throw new FileValueInvalidException();
			
			this.src = router[0];
			this.dest = router[1];
			
		} catch (IOException e) {
			System.err.println("Arquivo Invalido");
			e.printStackTrace();
			System.exit(0);
		} catch (NumberFormatException e) {
			System.err.println("Valor invalido para o custo do link");
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Nao existe esta maquina!");
			e.printStackTrace();
		} catch (FileValueInvalidException e) {
			System.err.println("Arquivo com valores Invalido");
			e.printStackTrace();
			System.exit(0);
		}
		
		return grafo;
		
	}
	
	public String getSrc() {
		return src;
	}

	public String getDest() {
		return dest;
	}
	
	public void roteamento(PrintStream out, Grafos grafo) {
		out.printf("(" + src + "," + dest + ")\n");
		grafo.dijkstra(src);
		
	}
	
	public void rota(PrintStream out, Grafos grafo) {
		out.print("Rota: ");
		for (Vertex v: grafo.getVertex(dest).path) {
			out.print(v + " ");
		}
		out.print(dest + "\n");
	}

	public static void infoVertexRoute(Grafos grafo) {
		for(Vertex v : grafo.getVertices()){
			String custo = (v.minCusto == Integer.MAX_VALUE) ? "Infinity" : v.minCusto + "";
			System.out.print("Vertice - "+v+" , Custo Total - "+ custo + " , Caminho - ");
			for(Vertex pathvert:v.path) {
				System.out.print(pathvert+" ");
			}
			System.out.println(""+v);
		}
		System.out.println();
	}
}
