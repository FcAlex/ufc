package br.ufc.crateus;

public enum Padrao {
	
	TIPOS_DADOS, IDENTIFICADOR, OP_LOGICO, OP_RELACIONAL, OP_ARITMETICO, OP_ATRIBUICAO, DELIMITADOR, PALAVRA_CHAVE, NUMERO, LITERAL;
	
	static boolean isTipoDados(String str) {
		return str.equals("int") || str.equals("float") || str.equals("void") || str.equals("char");
	}
	
	static boolean isIdentificador(String str) {
		return str.matches("[0-9]?([a-zA-Z])+");
	}
	
	static boolean isLiteral(String str) {
		return str.matches("([a-zA-Z])+");
	}
	
	static boolean isOpLogico(String str) {
		return str.equals("&&") || str.equals("||") || str.equals("!");
	}
	
	static boolean isOpRelacional(String str) {
		return str.equals(">") || str.equals("<") || str.equals("<=") || str.equals(">=") || str.equals("!=") || str.equals("==");
	}
	
	static boolean isOpAritmetico(String str) {
		return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%");
	}
	
	static boolean isOpAtribuicao(String str) {
		return str.equals("=");
	}
	
	static boolean isDemilitador(String str) {
		return str.equals(";") || str.equals(".") || str.equals("(") || str.equals(")") || str.equals("#") || str.equals("[") || str.equals("]") || str.equals("{") || str.equals("}") || str.equals("\"") || str.equals("\'");
	}
//	
	static boolean isPalavraChave(String str) {	
		return str.equals("scanf")  || str.equals("printf") || str.equals("struct") || str.equals("if") || str.equals("else") || str.equals("while") || str.equals("return");
	}
	
	static boolean isNumero(String str) {
		return str.matches("[0-9]+");
	}
	
	static boolean isComentario(String str) {
		return str.equals("//") || str.equals("/*") || str.equals("*/");
	}
	
	@Override
	public String toString() {
		if(this == TIPOS_DADOS) {
			return "Tipo de Dados";
		} else if(this == IDENTIFICADOR) {
			return "Identificador";
		} else if(this == OP_LOGICO) {
			return "Operador Logico";
		} else if(this == OP_RELACIONAL) {
			return "Operador Relacional";
		} else if(this == OP_ARITMETICO) {
			return "Operador Aritmetico";
		} else if(this == OP_ATRIBUICAO) {
			return "Operador Atribuição";
		} else if(this == DELIMITADOR) {
			return "Delimitador";
		} else if(this == PALAVRA_CHAVE) {
			return "Palavra Chave";
		} else if(this == NUMERO) {
			return "Numero";
		}
		return super.toString();
	}
	
}
