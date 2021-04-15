package questoes;

import java.util.Random;

import hash.SeparateChainingHashMap;

public class Qtd10<K, V> {
	public int N;
	
	public Qtd10(int N) {
		this.N = N;
	}	
	
	public void lowAndHighList(int[] nums) {
		SeparateChainingHashMap<Integer, Integer> num = new SeparateChainingHashMap<Integer, Integer>(N/100);
		for(int i = 0; i < N; i++) num.put(nums[i], i);
		
		int[] len = new int[num.size()+1];
		len =  num.nodeLength();
		
		int maior = 0;
		Integer menor = Integer.MAX_VALUE;
		
		System.out.println("Tamanho da tabela hash: " + len.length);
		for(int i = 0; i < len.length; i++) {
			if(maior < len[i]) maior = len[i];
		}
		
		for(int i = 0; i < len.length; i++) {
			if(menor > len[i]) menor = len[i];
		}
		
		System.out.println("Menor Lista: " + menor);
		System.out.println("Maior Lista: " + maior);
		
	}
	
	public static void main(String[] args) {
		int n = (int) Math.pow(10, 6);
		Qtd10<Integer, Integer> q = new Qtd10<Integer, Integer>(n);
		Random random = new Random();
		int[] k = new int[n];
		for(int i = 0; i < k.length; i++) k[i] = random.nextInt(1000);
		q.lowAndHighList(k);
		
		
		// PARA RANDOM (NUMEROS INTEIROS GERADOS PSEDO-ALEATORIAMENTE) TEMOS O LIMITE DE 1000
		// ------------ PARA 10^3
		// RESULTADO: 
		// Tamanho da tabela hash: 97
		// Menor Lista: 8
		// Maior Lista: 604
		// ------------ PARA 10^4
		// Tamanho da tabela hash: 97
		// Menor Lista: 11
		// Maior Lista: 11
		// ------------ PARA 10^5
		// Tamanho da tabela hash: 1000
		// Menor Lista: 1
		// Maior Lista: 1000

		// ------------ PARA 10^6
		// Tamanho da tabela hash: 10000
		// Menor Lista: 1
		// Maior Lista: 1000
		

	}
}
