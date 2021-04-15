package br.ufc.crateus;

public class ErroSintatico extends Exception {
	
	String erro;

	public ErroSintatico(String erro) {
		this.erro = erro;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println(erro);
		super.printStackTrace();
	}
	
}
