package com.example.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.curso.entidades.ItemPedido;
import com.example.curso.entidades.pk.ItemPedidoPK;

@Repository
public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
