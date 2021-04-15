package questoes;

import java.util.Arrays;
import java.util.Scanner;
import hash.LinearProbingHashMap;
import hash.SeparateChainingHashMap;

import java.io.*;

@SuppressWarnings("unused")
public class Qtd4 {
	public static void main(String[] args) throws IOException {
		BufferedReader dicionario = new BufferedReader(new InputStreamReader (new FileInputStream ("Arquivos\\dicionario.txt")));
		BufferedReader dic_pessoal = new BufferedReader(new InputStreamReader (new FileInputStream ("Arquivos\\dic_pessoal.txt")));
		BufferedReader texto = new BufferedReader(new InputStreamReader (new FileInputStream ("Arquivos\\texto.txt")));
		
		String lines = dicionario.readLine();
		String dic = "";
		while (lines != null) {
			dic = dic.concat("\n").concat(lines);
			lines = dicionario.readLine();
		}
		
		
		String[] dic_palavras = dic.split("\n");
		
		String pessoal = dic_pessoal.readLine();
		String[] pessoal_palavras = pessoal.split("\n");
		SeparateChainingHashMap<String, String> hash = new SeparateChainingHashMap<String, String>();
		
		for (int i = 0; i < dic_palavras.length; i++) {
			hash.put(dic_palavras[i], dic_palavras[i]);
		}
		
		for (int i = 0; i < pessoal_palavras.length; i++) {
			hash.put(pessoal_palavras[i], pessoal_palavras[i]);
		}
		
		lines = texto.readLine();
		String txt = "";
		while (lines != null) {
			txt = txt.concat(lines).concat(" + ");
			lines = texto.readLine();
		}
		
		txt = txt.replaceAll("\\,",  " .."); // NESSE CASO A (,) É EXCLUIDA, ENTAO USA-SE (..) PARA REPRESENTA-LO, PARA DEPOIS VOLTAR PARA
		txt = txt.replaceAll("\\.",  " .");  // O VALOR ORIGINAL
		txt = txt.replaceAll("\\!",  " !");
		txt = txt.replaceAll("\\?", " ?");
		txt = txt.replaceAll("\\:", " :");
		txt = txt.replaceAll("\\;", " ;");
		txt = txt.replaceAll("\\)", " )");
		txt = txt.replaceAll("\\(", "( ");
		txt = txt.replaceAll("\\- ", " -");
		
		
		String[] txt_palavras = txt.split(" ");
		int[] posicao_palavras = new int[txt_palavras.length];
		
		StringBuilder conteudo = new StringBuilder();
	    conteudo.append(txt);
	    
	    int c = 0;
	    for(int i = 0; i < posicao_palavras.length; i++) {
	    	posicao_palavras[i] = conteudo.indexOf(txt_palavras[i])-1;
	    }
	    

		
		dicionario.close();
		dic_pessoal.close();
		texto.close();
		
		for (int i = 0; i < txt_palavras.length; i++) {
			char[] palavraChar = txt_palavras[i].toCharArray();
			if (!hash.contains(txt_palavras[i])) {
				if (palavraChar.length > 1) {
					for (int j = 0; j < palavraChar.length - 1; j++) {
						char temp;
						
						temp = palavraChar[j];
						palavraChar[j] = palavraChar[j + 1];
						palavraChar[j + 1] = temp;
						
						String palavra = String.valueOf(palavraChar);
						palavraChar = txt_palavras[i].toCharArray();
						
						if (hash.contains(palavra)) {
							System.out.println("(TROCA DE ADJACENTES) Palavra: (" + txt_palavras[i] + ") Posição: (" + posicao_palavras[i] + ")");
							txt_palavras[i] = palavra;
							break;
						}
					}
				}
			}
		}
		
		for (int i = 0; i <= txt_palavras.length - 1; i++) {
			for (int separador = 0; separador <= txt_palavras[i].length(); separador++) {
				if (!hash.contains(txt_palavras[i])) {
					String palavra;
					for (char a = 'a'; a <= 'z'; a++) {
						palavra = "";
						palavra = txt_palavras[i].substring(0, separador) + a + txt_palavras[i].substring(separador);
						if (hash.contains(palavra)) {
							System.out.println("(ADIÇÃO) Palavra: (" + txt_palavras[i] + ") Posição: (" + posicao_palavras[i] + ")");
							txt_palavras[i] = palavra;
							break;
						}
					}
				}
			}
		}
		
		for (int i = 0; i <= txt_palavras.length - 1; i++) {
			char[] palavraChar = txt_palavras[i].toCharArray();
			if (!hash.contains(txt_palavras[i])) {
				if (palavraChar.length > 1) {
					for (int j = 0; j < palavraChar.length; j++) {
						palavraChar = txt_palavras[i].toCharArray();
						palavraChar[j] = ' ';
						String palavra = String.valueOf(palavraChar);
						palavra = palavra.replaceAll(" ", "");
						if (hash.contains(palavra)) {
							System.out.println("(REMOÇÃO) Palavra: (" + txt_palavras[i] + ") Posição: (" + posicao_palavras[i] + ")");
							txt_palavras[i] = palavra;
							break;
						}
					}
				}
			}
		}
		
		String novoTexto = Arrays.toString(txt_palavras);
		novoTexto = novoTexto.substring(1, novoTexto.length() - 1);
		novoTexto = novoTexto.replaceAll(",", "");
		
		novoTexto = novoTexto.replaceAll(" \\..", ",");
		novoTexto = novoTexto.replaceAll(" \\.", ".");
		novoTexto = novoTexto.replaceAll(" \\!", "!");
		novoTexto = novoTexto.replaceAll(" \\?", "?");
		novoTexto = novoTexto.replaceAll(" \\:", ":");
		novoTexto = novoTexto.replaceAll(" \\;", ";");
		novoTexto = novoTexto.replaceAll(" \\)", ")");
		novoTexto = novoTexto.replaceAll("\\( ", "(");
		novoTexto = novoTexto.replaceAll(" \\- ", "-");
		novoTexto = novoTexto.replaceAll(" \\+ ", "\n"); // USASSE (+) COMO CORINGA PARA (\n)
		novoTexto = novoTexto.substring(0, novoTexto.length()-1); // EXCLUIR ULTIMO (+)
		
		System.out.println("\nTexto corrigido: \n" + novoTexto);
	}
}


