package com.example.curso.recursos.excecao;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.curso.servicos.excecao.ExcecaoBancoDados;
import com.example.curso.servicos.excecao.ExcecaoRecursoNaoEncontrado;
import com.example.curso.servicos.excecao.ExcecaoSalvar;

@ControllerAdvice
public class ManipuladorExcecoes {

	@ExceptionHandler(ExcecaoRecursoNaoEncontrado.class)
	public ResponseEntity<ModeloErros> recursoNaoEncontrado(ExcecaoRecursoNaoEncontrado e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ModeloErros erro = new ModeloErros(Instant.now(), status.value(), "Recurso não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(ExcecaoBancoDados.class)
	public ResponseEntity<ModeloErros> excecaoBandoDados(ExcecaoBancoDados e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ModeloErros erro = new ModeloErros(Instant.now(), status.value(), "Erro no banco", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(ExcecaoSalvar.class)
	public ResponseEntity<ModeloErros> excecaoSalvar(ExcecaoSalvar e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ModeloErros erro = new ModeloErros(Instant.now(), status.value(), "Entidade Vazia", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
}
