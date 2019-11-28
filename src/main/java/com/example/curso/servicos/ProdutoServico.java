package com.example.curso.servicos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Produto;
import com.example.curso.repositorios.ProdutoRepositorio;
import com.example.curso.servicos.excecao.ExcecaoBancoDados;
import com.example.curso.servicos.excecao.ExcecaoRecursoNaoEncontrado;
import com.example.curso.servicos.excecao.ExcecaoSalvar;

@Service
public class ProdutoServico {
	
	@Autowired
	private ProdutoRepositorio repositorio;
	
	public List<Produto> buscaTodos() {
		return repositorio.findAll();
	}
	
	public Produto buscaPorId(Integer id) {
		try {
			Optional<Produto> produto = repositorio.findById(id);
			return produto.get();
		}
		catch (NoSuchElementException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
	
	public Produto salvar(Produto produto) {
		if (produto.getNome() == null) {
			throw new ExcecaoSalvar("Produto está nulo");
		}
		else {
			return repositorio.save(produto);
		}
	}
	
	public void excluir(Integer id) {
		try {
			repositorio.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new ExcecaoBancoDados(e.getMessage());
		}
	}
	
	public Produto alterar(Integer id, Produto produto) {
		try {
			if (produto.getId() == null) {
				throw new ExcecaoSalvar("Produto está nulo");
			}
			else {
				Produto entidade = repositorio.getOne(id);
				entidade.setNome(produto.getNome());
				entidade.setDescricao(produto.getDescricao());
				entidade.setPreco(produto.getPreco());
				entidade.setImgUrl(produto.getImgUrl());
				return repositorio.save(entidade);
			}
		}
		catch (EntityNotFoundException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
}
