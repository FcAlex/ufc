package br.ufc.crateus.eda.graph;

public interface Graph<T> {
	
	int countVertices(); // retorna o numero de vertices do grafo
	int countEdges(); // retorna o numero de arestas do grafo
	int index(T v); // retorna o indice do vertice v, que indica, na sequencias de insercoes, a posicao de insercao do vertice
	T label(int index); // retorna o rotulo do vertice cujo indice e index
	boolean contains(T v); // verifica se existe vertice com rotulo v
	void addEdge(T v1, T v2); // adiciona uma aresta entre os vertices v1 e v2
	Iterable<T> adjacents(T v); // retorna os vertices adjacentes a v
	int degree(T v); // retorna o grau do vertice v
	
}
