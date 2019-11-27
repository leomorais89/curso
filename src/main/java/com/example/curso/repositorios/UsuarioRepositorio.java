package com.example.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curso.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	
}
