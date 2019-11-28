package com.example.curso.servicos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Categoria;
import com.example.curso.repositorios.CategoriaRepositorio;
import com.example.curso.servicos.excecao.ExcecaoBancoDados;
import com.example.curso.servicos.excecao.ExcecaoRecursoNaoEncontrado;
import com.example.curso.servicos.excecao.ExcecaoSalvar;

@Service
public class CategoriaServico {

	@Autowired
	private CategoriaRepositorio repositorio;
	
	public List<Categoria> buscaTodos() {
		return repositorio.findAll();
	}
	
	public Categoria buscaPorId(Integer id) {
		try {
			Optional<Categoria> categoria = repositorio.findById(id);
			return categoria.get();
		}
		catch (NoSuchElementException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
	
	public Categoria salvar(Categoria categoria) {
		if (categoria.getNome() == null) {
			throw new ExcecaoSalvar("Categoria está nula");
		}
		else {
			categoria = repositorio.save(categoria);
			return categoria;
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
	
	public Categoria alterar(Integer id, Categoria categoria) {
		try {
			if (categoria.getId() == null) {
				throw new ExcecaoSalvar("Categoria está nula");
			}
			else {
				Categoria entidade = repositorio.getOne(id);
				entidade.setNome(categoria.getNome());
				return repositorio.save(entidade);
			}
		}
		catch (EntityNotFoundException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
}
