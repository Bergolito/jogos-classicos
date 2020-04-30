package br.com.mvbos.lgj.lines;

public class LinhaColuna {

	private int linha;
	private int coluna;
	
	public LinhaColuna(int l, int c) {
		super();
		this.linha = l;
		this.coluna = c;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
}
