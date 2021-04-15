package questoes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import tries.RwTries;

public class Qtd16 {
	public void listaDeFilmes(RwTries<String> rwt, String str) {
		Queue<String> keysWithPrefix = (Queue<String>) rwt.keysWithPrefix(str);
		
		String longestPrefixOf = rwt.longestPrefixOf(str);
		
		str = str + "?"; // PARA A BUSCA DE CHAVES COM PADRAO, UMA VEZ QUE EM ARQUIVO E ADICIONADO (?) NO FINAL DA LINHA
		Queue<String> keysThatMatch = (Queue<String>) rwt.keysThatMatch(str);
		
		Set<String> movies = new TreeSet<>(keysWithPrefix);
		movies.add(longestPrefixOf);
		movies.addAll(keysThatMatch);
		
		for(String m : movies) {
			if(rwt.get(m)!=null)
				System.out.println(rwt.get(m));
		}
		
	}

	public RwTries<String> insertionArq() throws IOException {
		BufferedReader movies = new BufferedReader(new InputStreamReader (new FileInputStream ("Arquivos\\movies.txt"), "ISO-8859-1"));
		RwTries<String> rwt = new RwTries<String>();
		
		String lines = movies.readLine().substring(3); // NO PRIMEIRO CASO APRESENTA CARACTERES ESTRANHOS, QUE SAO RETIRADOS COM O SUBSTRING
		while (lines != null) {
			String nome_filme = lines.split("\\(", 2)[0];
			String temp = "";
			
			StringBuilder filter = new StringBuilder(nome_filme);
			
			
			temp = nome_filme;
			nome_filme = this.tratamentoString(filter);
			rwt.put(nome_filme, temp);
			lines = movies.readLine();
		}
		
		movies.close();
		
		return rwt;
	}
	
	private String tratamentoString(StringBuilder filter) {
		for(int i = 0; i < filter.length(); i++) {
			char c = filter.charAt(i);
			if(c >= 'A' && c <= 'Z') c = (char) (c + 32);
			if((c >= 0 && c <= 47 && c != '.') || (c >= 58 && c <= 64) ||  (c >= 91 && c <= 96) 
					|| (c >= 155 && c <= 159) || (c >= 166 && c <= 255)) 
				c = '?';
			if(c == 128 || c == 135) c = 'c';
			if(c == 129) c = 'u';
			if((c >= 131 && c <= 134) || (c >= 142 && c <= 143) || c == 160) c = 'a';
			if((c >= 136 && c <= 138) || (c >= 144 && c <= 146)) c = 'e';
			if((c >= 139 && c <= 141) || c == 161) c = 'i';
			if((c >= 147 && c <= 149) || c == 153 || c == 162) c = 'o';
			if((c >= 150 && c <= 151) || c == 154 || c == 163) c = 'u';
			if(c == 152) c = 'y';
			if(c >= 164 && c <= 165) c = 'n';
			
			filter.setCharAt(i, c);
		}
		
		return filter.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nome do Filme: (Use [.] apenas para casamento de padrões)"); // COMO DIFERENCIAR O (.) CORINGA E UM 
		String res = sc.nextLine();														// (.) RELATIVO A UM FILME? SOLUCAO:
		Qtd16 resposta = new Qtd16();													// OS FILMES NAO DEVE POSSUI O (.);
		
		StringBuilder aux = new StringBuilder(res);
		
		System.out.println("----- Filmes Encontrados: -----");
		resposta.listaDeFilmes(resposta.insertionArq(), resposta.tratamentoString(aux));
																					
		sc.close();
	}
	
	/*
		 ---- RESULTADOS ----
		 PALAVRA: anD
		 	Nome do Filme: (Use [.] apenas para casamento de padrões)
			anD
			----- Filmes Encontrados: -----
			And God Created Woman 
			And Now for Something Completely Different 
			And Now the Screaming Starts! 
			And Soon the Darkness 
			And the Band Played On 
			And the Sea Will Tell 
			And Then There Were None 
			And You Thought Your Parents Were Weird 
			And Your Name Is Jonah 
			Anderson Tapes, The 
			Andre 
			Android 
			Android Affair, The 
			Andromeda Strain, The 
			Andromina: The Pleasure Planet
			
		PALAVRA: x.men
			Nome do Filme: (Use [.] apenas para casamento de padrões)
			x.men
			----- Filmes Encontrados: -----
			X-Men 
		
		PALAVRA: 3 ninjas
			Nome do Filme: (Use [.] apenas para casamento de padrões)
			3 ninjas
			----- Filmes Encontrados: -----
			3 Ninjas 
			3 Ninjas: High Noon at Mega Mountain 
			3 Ninjas Kick Back 
			3 Ninjas Knuckle Up
			
		PALAVRA: A.ro.s ..e S.. of ..m.
			Nome do Filme: (Use [.] apenas para casamento de padrões)
			A.ro.s ..e S.. of ..m.
			----- Filmes Encontrados: -----
			Across the Sea of Time 
			
		PALAVRA: MY NAME
			Nome do Filme: (Use [.] apenas para casamento de padrões)
			MY NAME
			----- Filmes Encontrados: -----
			My Name Is Joe 
		
		PALAVRA: $1,000,000
			Nome do Filme: (Use [.] apenas para casamento de padrões)
			$1,000,000
			----- Filmes Encontrados: -----
			$1,000,000 Duck 

	*/

}