package com.doks.conferencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PedidoCompra;

import java.util.Optional;

public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Integer> {

	@Query(value = "select id,codigo,idcomprador ,idfornecedor,idfilial,dataprevisaoentrega,idcondicaopagamento,dataemissao,valortotal,observacao from pedidocompra where id=?1 " , nativeQuery = true)
	PedidoCompra porId(Integer id);

    //@Query(value = "select doks_pedido_compra.id, doks_pedido_compra.condicao_pagamento, doks_pedido_compra.prazo_entrega, doks_pedido_compra.identidade, doks_pedido_compra.data_emissao, "
		//	+ "total from doks_pedido_compra", nativeQuery = true)
//List<PedidoCompra> todos();
	
	

}
