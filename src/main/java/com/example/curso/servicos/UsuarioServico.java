package com.example.curso.servicos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Usuario;
import com.example.curso.repositorios.UsuarioRepositorio;
import com.example.curso.servicos.excecao.ExcecaoBancoDados;
import com.example.curso.servicos.excecao.ExcecaoRecursoNaoEncontrado;
import com.example.curso.servicos.excecao.ExcecaoSalvar;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	public List<Usuario> buscaTodos() {
		return repositorio.findAll();
	}
	
	public Usuario buscaPorId(Integer id) {
		try {
			Optional<Usuario> usuario = repositorio.findById(id);
			return usuario.get();
		}
		catch (NoSuchElementException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
	
	public Usuario salvar(Usuario usuario) {
		if (usuario.getNome() == null) {
			throw new ExcecaoSalvar("Usuário está nulo");
		}
		else {
			return repositorio.save(usuario);
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
	
	public Usuario alterar(Integer id, Usuario usuario) {
		try {
			if (usuario.getId() == null) {
				throw new ExcecaoSalvar("Usuário está nulo");
			}
			else {
				Usuario entidade = repositorio.getOne(id);
				entidade.setNome(usuario.getNome());
				entidade.setEmail(usuario.getEmail());
				entidade.setTelefone(usuario.getTelefone());
				return repositorio.save(entidade);
			}
		}
		catch (EntityNotFoundException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
}
