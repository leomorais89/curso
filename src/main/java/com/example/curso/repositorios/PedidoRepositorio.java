package com.example.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.curso.entidades.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

}
