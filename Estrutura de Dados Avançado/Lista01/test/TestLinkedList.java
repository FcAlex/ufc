package br.ufc.crateus.eda.test;

import java.util.ArrayList;
import java.util.List;

import br.ufc.crateus.eda.st.BinarySearchTreeMap;
import br.ufc.crateus.eda.st.LinkedListMap;
import br.ufc.crateus.eda.st.Map;

public class TestLinkedList {
	public static void main(String[] args) {
		
		LinkedListMap<Integer, String> map = new LinkedListMap<>(); 
		map.put(0, "Alex");
		map.put(3, "Alan");
		map.put(5, "Lucimar");
		map.put(3, "Juliana");
		map.put(7, "Gilvan");
		
		 /* 
		 * map.remove("Tereza");
		 * 
		 * System.out.println(map.get("Paulo"));
		 * 
		 * for (String key : map.keys()) System.out.println(key);
		 */
		
		BinarySearchTreeMap<Integer, String> tree = new BinarySearchTreeMap<Integer, String>();
		//tree.convertToTree(map);
		tree.put(8, "Alex");
		tree.put(5, "Alan");
		tree.put(3, "Lucimar");
		tree.put(7, "Juliana");
		tree.put(2, "Gilvan");
//		System.out.println(map.size());
		
		tree.balancearArvore();
		
		
	}
}
