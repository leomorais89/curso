package com.example.curso.servicos.excecao;

public class ExcecaoBancoDados extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcecaoBancoDados(String msg) {
		super(msg);
	}
}
