package com.example.curso.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Produto;
import com.example.curso.repositorios.ProdutoRepositorio;

@Service
public class ProdutoServico {
	
	@Autowired
	private ProdutoRepositorio repositorio;
	
	public List<Produto> buscaTodos() {
		return repositorio.findAll();
	}
}
