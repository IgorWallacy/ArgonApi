package com.doks.conferencia.repository;

import java.util.List;

import com.doks.conferencia.model.PedidoCompra;
import com.doks.conferencia.model.dto.ProdutoCompraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PedidoItemCompra;

public interface PedidoItemCompraRepository extends JpaRepository<PedidoItemCompra, Integer> {

	

	//@Query(value="select id,embalagem,idproduto,idpedido,preco,quantidade1,quantidade2,quantidade_venda,unidade_compra,total from doks_pedido_item_compra where idpedido=?1 " ,nativeQuery = true)
	//List<PedidoItemCompra> findByIdpedido(Long idPedido);
	
	@Query(value= "select id, embalagem, idproduto, idpedidocompra, preco, (0) as sugestao, (select produto.percentualmarkupminimo from produto where produto.id = pedidocompraitem.idproduto limit 1 ) as doks_percentualmarkupminimo, COALESCE( ( select preco from formacaoprecoproduto left join pedidocompra on (pedidocompra.id = pedidocompraitem.idpedidocompra)  where formacaoprecoproduto.idproduto = pedidocompraitem.idproduto and formacaoprecoproduto.idfilial = pedidocompra.idfilial limit 1), (select preco from produto where produto.id=pedidocompraitem.idproduto limit 1 ) ) as doks_preco_venda_na_data, quantidade , doks_quantidade1, doks_quantidade2, fatorconversao,  doks_quantidade_venda, idunidademedida, observacao, total  from pedidocompraitem where idpedidocompra=?1 order by idproduto", nativeQuery = true)
	List<PedidoItemCompra> findByIdPedidoCompra(Integer id);

	@Query(value="select id, embalagem, idproduto, idpedidocompra, preco, quantidade ,doks_preco_venda_na_data, (0) as doks_percentualmarkupminimo, doks_quantidade1, doks_quantidade2, fatorconversao, doks_quantidade_venda, idunidademedida, observacao, total  from pedidocompraitem where id=?1 ", nativeQuery = true)
	PedidoItemCompra porId(Integer id);

	@Query(value= "select id, embalagem, idproduto, idpedidocompra, preco, quantidade ,doks_preco_venda_na_data, (0) as doks_percentualmarkupminimo, doks_quantidade1, doks_quantidade2, fatorconversao,  doks_quantidade_venda, idunidademedida, observacao, total  from pedidocompraitem where idpedidocompra=?1 and idproduto=?2  ", nativeQuery = true)
	List<PedidoItemCompra> findByIdPedidoIdProduto(Integer idPedido, Integer idProduto);



	
	


	

}
