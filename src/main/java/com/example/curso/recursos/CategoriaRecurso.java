package com.example.curso.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entidades.Categoria;
import com.example.curso.servicos.CategoriaServico;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaRecurso {

	@Autowired
	private CategoriaServico servico;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> buscaTodos() {
		List<Categoria> categorias = servico.buscaTodos();
		return ResponseEntity.ok().body(categorias);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscaPorId(@PathVariable Integer id) {
		Categoria categoria = servico.buscaPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
}
