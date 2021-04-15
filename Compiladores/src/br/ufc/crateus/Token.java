package br.ufc.crateus;

public class Token {
	
	private Padrao pd;
	private String lexema;
	private int linha;

	public Token(Padrao pd, String lexema, int linha) {
		this.pd = pd;
		this.lexema = lexema;
		this.linha = linha;
	}
	
	@Override
	public String toString() {
		return "( " + pd + ", " + lexema + ", " + linha + " )";
	}
	
	public Padrao getPadrao() {
		return pd;
	}
	
	public String getLexema() {
		return lexema;
	}
	
	public boolean isEqualLexeme(String lexema) {
		return this.lexema.equals(lexema);
	}

}
