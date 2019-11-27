package com.example.curso.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entidades.Pedido;
import com.example.curso.servicos.PedidoServico;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoRecurso {

	@Autowired
	private PedidoServico servico;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> buscaTodos() {
		List<Pedido> pedidos = servico.buscaTodos();
		return ResponseEntity.ok().body(pedidos);
	}
}
