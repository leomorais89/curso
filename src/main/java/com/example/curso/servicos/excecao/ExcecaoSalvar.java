package com.example.curso.servicos.excecao;

public class ExcecaoSalvar extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcecaoSalvar(String msg) {
		super(msg);
	}
}
