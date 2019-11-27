package com.example.curso.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.entidades.Pedido;
import com.example.curso.repositorios.PedidoRepositorio;

@Service
public class PedidoServico {

	@Autowired
	private PedidoRepositorio repositorio;
	
	public List<Pedido> buscaTodos() {
		return repositorio.findAll();
	}
}
