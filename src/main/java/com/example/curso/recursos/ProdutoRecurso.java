package com.example.curso.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entidades.Produto;
import com.example.curso.servicos.ProdutoServico;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoRecurso {

	@Autowired
	private ProdutoServico servico;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscaTodos() {
		List<Produto> produtos = servico.buscaTodos();
		return ResponseEntity.ok().body(produtos);
	}
}
