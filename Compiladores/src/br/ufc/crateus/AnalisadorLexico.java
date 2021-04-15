package br.ufc.crateus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AnalisadorLexico {
	
	BufferedReader br;
	TabelaSimbolo ts;
	
	private enum Casos {
		LINE, MULTLINE, NOCOMMENT, CHAR, STRING;
	}
	
	public AnalisadorLexico(String path) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(path));
		ts = new TabelaSimbolo();
	}
	
	public void construirTabelaSimbolo() {
		try {
			br = new BufferedReader(new FileReader("arq.txt"));
			int posLinha = 0, casos = 0;
			boolean comentario = false;
			Casos cm;
			while (br.ready()) {
				boolean caracter = false;
				posLinha++;
				String linha = br.readLine().replace("\t", "").replace("\'", " \' ").replace("\"", " \" ");
				
				if(comentario) {
					cm = tratarComentario(linha);
					if (cm.equals(Casos.NOCOMMENT)) {
						linha = linha.replaceAll("\\*/", "*/\n");
						comentario = false;
					}
				}
				
				String[] lexemas = linha.split(" ");
				
				boolean literal = false;
				for (String str : lexemas) {
					
					if(literal) {
						literal = false;
						casos++;
						if(casos == 2) {
							casos = 0;
						} else {
							String lexema = str;
							ts.append(Padrao.LITERAL, lexema, posLinha);
							System.out.println("Literal -- " + lexema);
							continue;
						}
					}
					
					if(caracter) {
						ts.append(Padrao.LITERAL, str, posLinha);
						System.out.println("Literal -- " + str);
						caracter = false;
						continue;
					}
					
					char[] letras = str.toCharArray();
					
					cm = constroiTabela(letras, posLinha);
					if(cm.equals(Casos.LINE)) break;
					if(cm.equals(Casos.MULTLINE)) {
						comentario = true;
						break;
					}
					
					if(cm.equals(Casos.CHAR)) {
						caracter = true;
						continue;
					}
					
					if(cm.equals(Casos.STRING)) {
						literal = true;
						continue;
					}
					
				}
				
				if(casos == 1) {
					throw new Exception("Erro lexico");
				}
				
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	private Casos constroiTabela(char[] letras, int posLinha) throws Exception {
		String lexema = "";
		
		for (int i = 0; i < letras.length; i++) {
			
			if(i+1 < letras.length && new String(letras[i]+""+letras[i+1]).equals("//")) {
				return Casos.LINE;
			}
			
			if(i+1 < letras.length && new String(letras[i]+""+letras[i+1]).equals("/*")) {
				return Casos.MULTLINE;
			}
			
			if(letras[i] == '\'') {
				ts.append(Padrao.DELIMITADOR, letras[i]+"", posLinha);
				System.out.println("Delimitador -- " + letras[i]);
		
				return Casos.CHAR;
			}
			
			if(letras[i] == '\"') {
				ts.append(Padrao.DELIMITADOR, letras[i]+"", posLinha);
				System.out.println("Delimitador -- " + letras[i]);
		
				return Casos.STRING;
				
			}
			
			if(i < letras.length && Character.isLetter(letras[i])) {
				lexema = "";
				while(i < letras.length && Character.isLetter(letras[i])) {
					lexema += letras[i];
					i++;
				}
				
				if(Padrao.isPalavraChave(lexema)) {
					ts.append(Padrao.PALAVRA_CHAVE, lexema, posLinha);
					System.out.println("Palavra Chave -- " + lexema);
				} else if(Padrao.isTipoDados(lexema)) {
					ts.append(Padrao.TIPOS_DADOS, lexema, posLinha);
					System.out.println("Tipo de dados -- " + lexema);
				} else {
					while(i < letras.length && Character.isLetterOrDigit(letras[i])) {
						lexema += letras[i];
						i++;
					}
					ts.append(Padrao.IDENTIFICADOR, lexema, posLinha);
					System.out.println("Identificador -- " + lexema);
				}
			}
			
			if(i < letras.length && Padrao.isDemilitador(letras[i]+"")) {
				ts.append(Padrao.DELIMITADOR, letras[i]+"", posLinha);
				System.out.println("Delimitador -- " + letras[i]);
			}
			
			if(i < letras.length && Padrao.isOpAritmetico(letras[i]+"")) {		
				ts.append(Padrao.OP_ARITMETICO, letras[i]+"", posLinha);
				System.out.println("Operador Aritmetico -- " + letras[i]);
			}
			
			if(i < letras.length && Padrao.isOpAtribuicao(letras[i]+"")) {
				lexema = letras[i]+"";
				if(i+1 < letras.length && Padrao.isOpRelacional(lexema+letras[i+1])) { // ==
					lexema += letras[++i];
					ts.append(Padrao.OP_RELACIONAL, lexema, posLinha);
					System.out.println("Operador Relacional -- " + lexema);
				} else {
					ts.append(Padrao.OP_ATRIBUICAO, letras[i]+"", posLinha);
					System.out.println("Operador Atribuição -- " + letras[i]);
				}
			}
			
			if(i < letras.length && letras[i] == '<') {
				lexema = letras[i]+"";
				if(i+1 < letras.length && Padrao.isOpRelacional(lexema+letras[i+1])) { // <=
					lexema += letras[++i];
				}
				ts.append(Padrao.OP_RELACIONAL, letras[i]+"", posLinha);
				System.out.println("Operador Relacional -- " + lexema);
			} 
			
			if(i < letras.length && letras[i] == '>') {
				lexema = letras[i]+"";
				if(i+1 < letras.length && Padrao.isOpRelacional(lexema+letras[i+1])) { // >=
					lexema += letras[++i];
				}
				ts.append(Padrao.OP_RELACIONAL, letras[i]+"", posLinha);
				System.out.println("Operador Relacional -- " + lexema);
			}
			
			if(i < letras.length && letras[i] == '!') {
				lexema = letras[i]+"";
				if(i+1 < letras.length && Padrao.isOpRelacional(lexema+letras[i+1])) { // !=
					lexema += letras[++i];
					ts.append(Padrao.OP_RELACIONAL, lexema, posLinha);
					System.out.println("Operador Relacional -- " + lexema);
				} else {
					ts.append(Padrao.OP_LOGICO, letras[i]+"", posLinha);
					System.out.println("Operador Logico -- " + lexema);
				}
			}
			
			if(i < letras.length && Character.isDigit(letras[i])) {
				lexema += letras[i];
				while(i+1 < letras.length && Character.isDigit(letras[i+1])) {
					lexema += letras[i+1];
					i++;
				}
				
				if(Padrao.isNumero(lexema)) {
					ts.append(Padrao.NUMERO, lexema, posLinha);
					System.out.println("Numero -- " + lexema);
				}
			}
			
			if(i < letras.length && letras[i] == '&') {
				if(i+1 < letras.length && letras[i+1] == '&') {
					lexema = letras[i]+""+letras[i+1];
					ts.append(Padrao.OP_LOGICO, lexema, posLinha);
					System.out.println("Operador Logico -- " + lexema);
					i++;
				}
			}
			
			if(i < letras.length && letras[i] == '|') {
				if(i+1 < letras.length && letras[i+1] == '|') {
					lexema = letras[i]+""+letras[i+1];
					ts.append(Padrao.OP_LOGICO, lexema, posLinha);
					System.out.println("Operador Logico -- " + lexema);
					i++;
				}
			}
	
			lexema = "";
		}
		return Casos.NOCOMMENT;
	}
	
	private Casos tratarComentario(String str) {
		int i = 0;
		while(i+1 < str.length()) {
			if(str.substring(i, i+2).equals("*/")) {
				return Casos.NOCOMMENT;
			}
			i++;
		}
		return Casos.MULTLINE;
	}
	
	public void imprimirTabelaSimbolos() {
		System.out.println(ts);
	}
	
	public boolean salvar(String path) {
		try {
			ts.saveArq(path);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
