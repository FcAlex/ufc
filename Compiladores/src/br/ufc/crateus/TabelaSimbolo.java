package br.ufc.crateus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TabelaSimbolo {
	
	private List<Token> tokens;
	
	public TabelaSimbolo() {
		tokens = new ArrayList<Token>();
	}
	
	public void append(Token tk) {
		tokens.add(tk);
	}
	
	public void append(Padrao pd, String valor, int linha) {
		tokens.add(new Token(pd, valor, linha));
	}
	
	public void saveArq(String path) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		PrintWriter pw = new PrintWriter(bw);
		
		pw.print("( Token, Lexema, Linha )\n");
		for (Token tk : tokens) {
			pw.print(tk + "\n");
		}
		
		pw.close();
	}
	
	public ArrayList<Token> getTokens() {
		return new ArrayList<>(tokens);
	}
}
