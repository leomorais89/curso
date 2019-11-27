package com.example.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Categoria;
import com.example.curso.repositorios.CategoriaRepositorio;

@Service
public class CategoriaServico {

	@Autowired
	private CategoriaRepositorio repositorio;
	
	public List<Categoria> buscaTodos() {
		return repositorio.findAll();
	}
	
	public Categoria buscaPorId(Integer id) {
		Optional<Categoria> categoria = repositorio.findById(id);
		return categoria.get();
	}
	
	public Categoria salvar(Categoria categoria) {
		categoria = repositorio.save(categoria);
		return categoria;
	}
	
	public void excluir(Integer id) {
		repositorio.deleteById(id);
	}
	
	public Categoria alterar(Integer id, Categoria categoria) {
		Categoria entidade = repositorio.getOne(id);
		entidade.setNome(categoria.getNome());
		return repositorio.save(entidade);
	}
}
