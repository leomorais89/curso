package com.example.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Usuario;
import com.example.curso.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	public List<Usuario> buscaTodos() {
		return repositorio.findAll();
	}
	
	public Usuario buscaPorId(Integer id) {
		Optional<Usuario> usuario = repositorio.findById(id);
		return usuario.get();
	}
}
