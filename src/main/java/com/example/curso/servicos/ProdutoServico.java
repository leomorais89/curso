package com.example.curso.servicos;

import java.util.List;
import java.util.Optional;

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
	
	public Produto buscaPorId(Integer id) {
		Optional<Produto> produto = repositorio.findById(id);
		return produto.get();
	}
	
	public Produto salvar(Produto produto) {
		return repositorio.save(produto);
	}
	
	public void excluir(Integer id) {
		repositorio.deleteById(id);
	}
	
	public Produto alterar(Integer id, Produto produto) {
		Produto entidade = repositorio.getOne(id);
		entidade.setNome(produto.getNome());
		entidade.setDescricao(produto.getDescricao());
		entidade.setPreco(produto.getPreco());
		entidade.setImgUrl(produto.getImgUrl());
		return repositorio.save(entidade);
	}
}
