package br.ufc.crateus.matrizes;

import java.util.Random;


public class Matriz {
	private int matriz[][];
	private int qCol;
	private int qLine;
	
	public Matriz (int qLine, int qCol) {
		matriz = new int[qLine][qCol];
		this.qLine = qLine;
		this.qCol = qCol;
	}
	
	public Matriz (int order) {
		this(order, order);
	}
	
	public void setValue(int line, int col, int value) {
		matriz[line][col] = value;
	}
	
	public int getValue(int line, int col) {
		return matriz[line][col];
	}
	
	public static Matriz identidade(int order) {
		Matriz id = new Matriz(order);
		for(int i = 0; i < order; i++)
			id.setValue(i, i, 1);
		return id;
	}
	
	public static Matriz gerarMatriz(int qLine, int qCol, int limit) {
		Random gerador = new Random();
		Matriz mt = new Matriz(qLine, qCol);
		for(int i = 0; i < qLine; i++)
			for(int j = 0; j < qCol; j++)
				mt.setValue(i, j, gerador.nextInt(limit));
		
		return mt;
	}
	
	public Matriz somar(Matriz parcela) {
		if(this.qLine != parcela.qLine || this.qCol != parcela.qCol) return null;
		Matriz res = new Matriz(qLine, qCol);
		for(int i = 0; i < qLine; i++)
			for(int j = 0; j < qCol; j++)
				res.setValue(i, j, this.getValue(i, j) + parcela.getValue(i, j));
		
		return res;
		
	}
	
	public Matriz multiplicarEscalar(int escalar) {
		Matriz res = new Matriz(qLine, qCol);
		for(int i = 0; i < qLine; i++)
			for(int j = 0; j < qCol; j++)
				res.setValue(i, j, this.getValue(i, j)*escalar);
		return res;
	}
	
	public Matriz subtrair(Matriz subtraendo) {
		return somar(subtraendo.multiplicarEscalar(-1));
	}
	
	public Matriz multiplicar(Matriz fator) {
		if(this.qCol != fator.qLine) return null;
		int acc;
		Matriz produto = new Matriz(this.qLine, fator.qCol);
		for(int i = 0; i < produto.qLine; i++) { // itera as linhas do primeiro fator
			for(int j = 0; j < produto.qCol; j++) { // itera as colunas do segundo fator
				acc = 0;
				for(int k = 0; k < produto.qCol; k++) // realiza a operacao da coluna do segundo fator
					acc = this.getValue(i, k)*fator.getValue(k, j)+acc;
				produto.setValue(i, j, acc);
			}
		}
		return produto;
	}
	
	public Matriz matrizTransposta() {
		Matriz res = new Matriz(this.qCol, this.qLine);
		for(int i = 0; i < this.qLine; i++) {
			for(int j = 0; j < this.qCol; j++) {
				res.setValue(j, i, this.getValue(i, j));
			}
		}
		return res;
	}
	
	public Matriz potencia(int expoente) {  // teste
		Matriz res = this;
		for(int j = 1; j < expoente; j++) {
			res = this.multiplicar(res);
		}
		return res;
	}
	
	
	public int /* Matriz */ menorComplementar(int line, int col) {
		Matriz res = new Matriz(this.qCol-1);
		int k = 0, l= 0;
		for(int i = 0; i < this.qLine; i++) {
			if(i == line) continue;
			for(int j = 0; j < this.qCol; j++) {
				if(j == col) continue;
				res.setValue(k, l, this.getValue(i, j));
				l++;
			}
			l = 0;
			k++;
		}
		return res.determinante();
	}
		
	public int cofator(int line, int col) {
		return (int) (menorComplementar(line, col) * Math.pow(-1, line+col));
	}
	
	public Matriz matrizCofatora() {
		Matriz res = new Matriz(this.qCol); // == qLine
		for(int i = 0; i < this.qLine; i++) {
			for(int j = 0; j < this.qCol; j++) {
				res.setValue(i, j, this.cofator(i, j));
			}
		}
		
		return res;
	}
	
	public Matriz matrizAdjunta() {
		return this.matrizCofatora().matrizTransposta();
	}
	
	private int determinanteOrdemDois() {
		return ((this.matriz[0][0] * this.matriz[1][1]) - (this.matriz[0][1] * this.matriz[1][0]));
	}
	
	public int determinante() {
		if(this.qCol == 2) return this.determinanteOrdemDois();
		return 0;  // por sistemas
	}
	
	public void showMatrix() {  // para testes
		for(int i = 0; i < this.qLine; i++) {
			for(int j = 0; j < qCol; j++) {
				System.out.print(this.matriz[i][j] + "\t");
			}
			System.out.println();
		}
			
	}
	
	public static void main(String[] args) {
		Matriz m = new Matriz(3);
		
		m.setValue(0, 0, 2);
		m.setValue(0, 1, -5);
		m.setValue(0, 2, 0);
		m.setValue(1, 0, 1);
		m.setValue(1, 1, 3);
		m.setValue(1, 2, -1);
		m.setValue(2, 0, -4);
		m.setValue(2, 1, -2);
		m.setValue(2, 2, 1);
		
		Matriz z = m.matrizAdjunta();
				

		m.showMatrix();
		System.out.println();
		z.showMatrix();
		
	}
	
}
