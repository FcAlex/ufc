package br.ufc.crateus;


public class Main {
	public static void main(String[] args) {
		try {
//			AnalisadorLexico al = new AnalisadorLexico("arq.txt");
//			al.construirTabelaSimbolo();
//			System.out.println("\n");
//			al.salvar("saida.txt");
			AnalisadorSintatico as = new AnalisadorSintatico("arq.txt");
			System.out.println("\n\n\n\n");
			
			//as.imprimirTabela();
			//as.expAritmetica();
			//as.expLogica();
			//as.atribuicao();
			//as.declaracoes();
			as.condicional();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
