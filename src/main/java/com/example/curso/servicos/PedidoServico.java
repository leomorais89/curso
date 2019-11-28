package com.example.curso.servicos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.ItemPedido;
import com.example.curso.entidades.Pagamento;
import com.example.curso.entidades.Pedido;
import com.example.curso.repositorios.ItemPedidoRepositorio;
import com.example.curso.repositorios.PedidoRepositorio;
import com.example.curso.servicos.excecao.ExcecaoBancoDados;
import com.example.curso.servicos.excecao.ExcecaoRecursoNaoEncontrado;
import com.example.curso.servicos.excecao.ExcecaoSalvar;

@Service
public class PedidoServico {

	@Autowired
	private PedidoRepositorio repositorio;
	
	@Autowired
	private ItemPedidoRepositorio itemPedidoRepostitorio;
	
	public List<Pedido> buscaTodos() {
		return repositorio.findAll();
	}
	
	public Pedido buscaPorId(Integer id) {
		try {
			Optional<Pedido> pedido = repositorio.findById(id);
			return pedido.get();
		}
		catch (NoSuchElementException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
	
	public Pedido fazerPedido(Pedido pedido) {
		if (pedido.getMomento() == null) {
			throw new ExcecaoSalvar("Pedido está nulo");
		}
		else {
			List<ItemPedido> itensPedido = pedido.getItensPedido();
			pedido = repositorio.save(pedido);
			for (ItemPedido itens : itensPedido) {
				itens.setPedido(pedido);
			}
			itemPedidoRepostitorio.saveAll(itensPedido);
			return pedido;
		}
	}
	
	public void cancelar(Integer id) {
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
	
	public Pedido alterar(Integer id, Pedido pedido) {
		try {
			if (pedido.getId() == null) {
				throw new ExcecaoSalvar("Pedido está nulo");
			}
			else {
				Pedido entidade = repositorio.getOne(id);
				entidade.setStatus(pedido.getStatus());
				if (pedido.getPagamento() != null) {
					Pagamento pagamento = new Pagamento(null, pedido.getPagamento().getMomento(), pedido);
					entidade.setPagamento(pagamento);
				}
				return repositorio.save(entidade);
			}
		}
		catch (EntityNotFoundException e) {
			throw new ExcecaoRecursoNaoEncontrado(id);
		}
	}
}
