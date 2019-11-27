package com.example.curso.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entidades.Usuario;
import com.example.curso.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso {

	@Autowired
	private UsuarioServico servico;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscaTodos(){
		List<Usuario> list = servico.buscaTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> buscaPorId(@PathVariable Integer id) {
		Usuario usuario = servico.buscaPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
}
