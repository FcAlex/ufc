package br.ufc.crateus.eda.test;

import br.ufc.crateus.eda.st.AVLTreeMap;

public class TestLinkedList {
	public static void main(String[] args) {
		 
		AVLTreeMap<Integer, String> maps = new AVLTreeMap<>();
		
		maps.put(1, "Francisco");
		maps.put(2, "Alex");
		maps.put(3, "Sousa");
		maps.put(4, "Anchieta");
		
		maps.remove(4);
		
		System.out.println(maps);
		
	}
}
