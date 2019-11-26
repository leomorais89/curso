package com.example.curso.recursos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entidades.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso {

	@GetMapping
	public ResponseEntity<Usuario> buscaTodos(){
		Usuario u = new Usuario(1, "Leonardo", "leo@gmail", "99999999", "teste");
		return ResponseEntity.ok().body(u);
	}
}
