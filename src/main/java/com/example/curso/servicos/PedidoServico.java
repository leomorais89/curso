package com.example.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.ItemPedido;
import com.example.curso.entidades.Pagamento;
import com.example.curso.entidades.Pedido;
import com.example.curso.repositorios.ItemPedidoRepositorio;
import com.example.curso.repositorios.PedidoRepositorio;

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
		Optional<Pedido> pedido = repositorio.findById(id);
		return pedido.get();
	}
	
	public Pedido fazerPedido(Pedido pedido) {
		List<ItemPedido> itensPedido = pedido.getItensPedido();
		pedido = repositorio.save(pedido);
		for (ItemPedido itens : itensPedido) {
			itens.setPedido(pedido);
		}
		itemPedidoRepostitorio.saveAll(itensPedido);
		return pedido;
	}
	
	public void cancelar(Integer id) {
		repositorio.deleteById(id);
	}
	
	public Pedido alterar(Integer id, Pedido pedido) {
		Pedido entidade = repositorio.getOne(id);
		entidade.setStatus(pedido.getStatus());
		if (pedido.getPagamento() != null) {
			Pagamento pagamento = new Pagamento(null, pedido.getPagamento().getMomento(), pedido);
			entidade.setPagamento(pagamento);
		}
		return repositorio.save(entidade);
	}
}
