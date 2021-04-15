package br.ufc.crateus;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AnalisadorSintatico {

	private class Tabela {
		ArrayList<Token> tokens;
		int i = 0;

		public Tabela(String entrada) throws FileNotFoundException {
			AnalisadorLexico al = new AnalisadorLexico(entrada);
			al.construirTabelaSimbolo();
			tokens = al.ts.getTokens();
		}
		
		Token next() {
			if(!hasNext()) return null;
			return tokens.get(i++);
		}

		public boolean hasNext() {
			return i+1 < tokens.size();
		}
		
		@Override
		public String toString() {
			String res = "";
			for (Token token : tokens) {
				res += token.toString() + "\n"; 
			}
			return res;
		}
		
	}
	
	Tabela tb;
	Token atual;

	public AnalisadorSintatico(String entrada) throws FileNotFoundException {
		tb = new Tabela(entrada);
	}
	
	// ===================== EXPRESSAO ARITMETICA ===================== //

	public void expAritmetica() throws ErroSintatico {
		atual = tb.next();
		fator();
		atual = tb.next();
		expAritmetica_();
	}
	
	private void expAritmetica_() throws ErroSintatico {
		if(atual == null) {
			atual = tb.tokens.get(tb.tokens.size()-1);
			return;
		}
		
		if(Padrao.isOpAritmetico(atual.getLexema())) {
			atual = tb.next();
			fator();
			atual = tb.next();
			expAritmetica_();
		} else {
			if(tb.hasNext() || atual.isEqualLexeme(")"))
				return;
			
			throw new ErroSintatico("Aplicação Inválida");
		}
	}

	private void fator() throws ErroSintatico {
		if(atual == null) return;
		if(atual.isEqualLexeme("(")) {
			expAritmetica();
			if(!atual.isEqualLexeme(")"))
				throw new ErroSintatico("Espera-se ')'");
			return;
		} else if(Padrao.isNumero(atual.getLexema())) {
			return;
		} else if(Padrao.isIdentificador(atual.getLexema())) {
			return;
		}
		throw new ErroSintatico("Aplicação Inválida");
	}
	
	// ===================== EXPRESSAO ARITMETICA ===================== //
	
	// ===================== EXPRESSAO LOGICA ===================== //
	
	public void expLogica() throws ErroSintatico {
		atual = tb.next();
		expressaoLogica();
	}
	
	private void expressaoLogica() throws ErroSintatico {
		if(atual.isEqualLexeme("(")) {
			expressoes();
			if(atual == null || !atual.isEqualLexeme(")"))
				throw new ErroSintatico("Espera-se ')'");
			
			atual = tb.next();
	
			expLogica_();
		} else {
			if(tb.hasNext())
				return;
			
			throw new ErroSintatico("Aplica��o inv�lida");
		}
	}

	private void expLogica_() throws ErroSintatico {
		if(atual == null) return;
		
		if(Padrao.isOpLogico(atual.getLexema())) { // voltar aqui
			//atual = tb.next();
//			expressoes();
//			atual = tb.next();
			expLogica();
		} else throw new ErroSintatico("Aplicação Inválida");
	}

	private void expressoes() throws ErroSintatico {
		atual = tb.next();
		termo();
		atual = tb.next();
		expressoes_();
		
	}

	private void expressoes_() throws ErroSintatico {
		if(atual == null) {
			atual = tb.tokens.get(tb.tokens.size()-1);
			return;
		}
		
		if(Padrao.isOpRelacional(atual.getLexema())) {
			atual = tb.next();
			termo();
			atual = tb.next();
			expressoes_();
		} else {
			if(tb.hasNext() || atual.isEqualLexeme(")")) 
				return;
			
			throw new ErroSintatico("Aplicação Inválida");
		}
	}

	private void termo() throws ErroSintatico {
		if(atual == null) return;
		if(atual.isEqualLexeme("(")) {
			expressoes();
			if(atual == null || !atual.isEqualLexeme(")"))
				throw new ErroSintatico("Espera-se ')'");
			atual = tb.next();
			expLogica_();
			return;
		} else if(Padrao.isNumero(atual.getLexema())) {
			return;
		} else if(Padrao.isIdentificador(atual.getLexema())) {
			return;
		}
		
		expAritmetica();
	}
	
	// ===================== EXPRESSAO LOGICA ===================== //
	
	// ===================== ATRIBUICAO ===================== //
	
	public void atribuicao() throws ErroSintatico {
		
		atual = tb.next();
		if(Padrao.isIdentificador(atual.getLexema())) {
			atual = tb.next();

			if(Padrao.isOpAtribuicao(atual.getLexema())) {
				atual = tb.next();
				System.out.println(atual.getLexema());
				expAtribuicao();
				return;
			}
			else 
				throw new ErroSintatico("Aplicação Inválida");
			
		}else {
			throw new ErroSintatico("Aplicação Inválida");
		}
						
	}
	
	private void expAtribuicao() throws ErroSintatico  {
		System.out.println(atual.getLexema());
		if(atual == null)
			return;
		
		if(Padrao.isIdentificador(atual.getLexema())) {
			System.out.println(atual.getLexema());

			atual = tb.next();
			if(Padrao.isOpAtribuicao(atual.getLexema())) {
				atual = tb.next();
				expAtribuicao();
			}
		}else {
			//System.out.println("aqui");
			primario();
		}		
	}
	
	private void primario() throws ErroSintatico {
		//System.out.println(atual.getLexema());
		if(atual == null)
			return;
		
		if(!(Padrao.isIdentificador(atual.getLexema())) || (Padrao.isNumero(atual.getLexema()))) 
			expAritmetica_();
		
		System.out.println("Atual is "+ atual.getLexema());
		
		if((!atual.isEqualLexeme(";")) || (atual == null) ) 
			throw new ErroSintatico("Espera-se ';'");
		
	}
	
	// ===================== ATRIBUICAO ===================== //
	
	// ===================== DECLARACOES ===================== //
	
	public void declaracoes() throws ErroSintatico {
		
		atual = tb.next();
		if(Padrao.isTipoDados(atual.getLexema())) {
			atual = tb.next();
			if(Padrao.isIdentificador(atual.getLexema())) {
				atual = tb.next();
				System.out.println(atual.getLexema());
				declaracao();
			}
		}else
			throw new ErroSintatico("É esperado o tipo de dado");
	}
	
	private void declaracao() throws ErroSintatico {
		
		if(atual == null)
			throw new ErroSintatico("Aplicação Inválida");  //Pode ser incompleta
		if(atual.isEqualLexeme(",")) {
			if(Padrao.isIdentificador(atual.getLexema())) {
				atual = tb.next();
				declaracao();
			}else
				throw new ErroSintatico("Espera-se um Identificador");
			declaracao();
		}else
			termoFinal();

	}
	
	
	private void termoFinal() throws ErroSintatico {
		
		if(atual == null)
			return;
		
		if(Padrao.isIdentificador(atual.getLexema())) {
			atual = tb.next();
			if(!atual.isEqualLexeme(";"))
				throw new ErroSintatico("Espera-se ';'");
				
		}else if(atual.isEqualLexeme("=")) {
			atual = tb.next();
			expAritmetica_();
		
			if(!atual.isEqualLexeme(";"))
				throw new ErroSintatico("Espera-se ';'");
		}else
			throw new ErroSintatico("Aplicação inválida"); //Pode ser Incompleta
	}
	
	// ===================== DECLARACOES ===================== //
	
	// ===================== CONDICIONAL ===================== //
	
	public void condicional() throws ErroSintatico { 
		atual = tb.next();
		condicional_();
	}
	
	private void condicional_() throws ErroSintatico {  //Separado pois os outros métodos precisam desse e o token não é null
		
		if(atual == null) //Se for null, representa erro 
			throw new ErroSintatico("Aplicação inválida");
		
		if(atual.isEqualLexeme("if")) {
			atual = tb.next();
			if(atual.isEqualLexeme("(")) {
				atual = tb.next();
				corpo();					 //Não faz o atual = tb.next(); pois em expLogica tem
				atual = tb.next();
				segundaCondicional();
				return;
			}else 
				throw new ErroSintatico("Esperado o termo '('"); 
		}
		throw new ErroSintatico("Esperado o termo '('"); 
	}
	
	
	private void segundaCondicional() throws ErroSintatico {
	
		if(atual == null) return;  //optativo, pois pode não existir else
		
		if(atual.isEqualLexeme("else")){
			if(atual.isEqualLexeme("("))
				corpo();
			else if(atual.isEqualLexeme("if"))
				condicional_();
			else
				throw new ErroSintatico("Esperado o termo '('"); 
		}else
			throw new ErroSintatico("Esperado o termo 'else'"); 
		 
	}
	
	
	private void corpo() throws ErroSintatico { //Método adicional 
		
		expressaoLogica();
		
		atual = tb.next();

		if(atual.isEqualLexeme(")")) {
			atual = tb.next();
			if(atual.isEqualLexeme("{")) {
				atual = tb.next();
				comandos();
				atual = tb.next();
				if(!atual.isEqualLexeme("}"))
					throw new ErroSintatico("Esperado o termo '}'"); 
				return;
			}else
				throw new ErroSintatico("Esperado o termo '{'"); 
		}else
			throw new ErroSintatico("Esperado o termo ')'"); 
	}
	
	
	// ===================== CONDICIONAL ===================== //
	
	// ===================== PRINCIPAL ===================== //
	
	public void main() throws ErroSintatico {
		atual = tb.next();
		if(atual.isEqualLexeme("void")) {
			atual = tb.next();
			if(atual.isEqualLexeme("main")) {
				atual = tb.next();
				if(atual.isEqualLexeme("(")) {
					atual = tb.next();
					if(atual.isEqualLexeme(")")) {
						atual = tb.next();
						if(atual.isEqualLexeme("{")) {
							comandos();
							atual = tb.next();
							if(atual.isEqualLexeme("return")) {
								atual = tb.next();
								if(atual.isEqualLexeme("0")) {
									if(atual.isEqualLexeme(";")) {
										if(atual.isEqualLexeme("}")) {
											return;
										}
									}
								}
							}
							
							if(atual.isEqualLexeme("}")) {
								return;
							}
							
							throw new ErroSintatico("Aplica��o Inv�lida");
						}
					}
				}
			}
		}
	}
	
	// ===================== PRINCIPAL ===================== //
	
	private void comandos() {
		
	}

	public void imprimirTabela() {
		System.out.println(tb);
	}
	
}
