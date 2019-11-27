package com.example.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.curso.entidades.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

	
}
