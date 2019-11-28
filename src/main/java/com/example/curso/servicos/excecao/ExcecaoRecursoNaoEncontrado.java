package com.example.curso.servicos.excecao;

public class ExcecaoRecursoNaoEncontrado extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcecaoRecursoNaoEncontrado(Integer id) {
		super("Recurso não encontrado id " + id);
	}
}
