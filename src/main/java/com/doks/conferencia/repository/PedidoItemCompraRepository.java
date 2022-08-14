package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PedidoItemCompra;

public interface PedidoItemCompraRepository extends JpaRepository<PedidoItemCompra, Long> {

	

	@Query(value="select id,embalagem,idproduto,idpedido,preco,quantidade1,quantidade2,quantidade_venda,unidade_compra from doks_pedido_item_compra where idpedido=?1 " ,nativeQuery = true)
	List<PedidoItemCompra> findByIdpedido(Long idPedido);

	
	


	

}
