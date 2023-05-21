package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ProdutosSemVendas;

public interface ProdutosSemVendasRepository extends JpaRepository<ProdutosSemVendas, Integer> {

	
	@Query(value=" select t1.id as id,"
			+ "coalesce((select precocustot1.precocusto from formacaoprecoproduto precocustot1 where precocustot1.idproduto=t1.id and precocustot1.idfilial=?3),t1.precocusto) as precocusto,"
			+ "coalesce((select dataUltimaComprat1.dataultimacompra from formacaoprecoproduto dataUltimaComprat1 where dataUltimaComprat1.idproduto=t1.id and dataUltimaComprat1.idfilial=?3),t1.dataUltimaCompra) as ultimaCompra,"
			+ "t1.codigo as codigo,"
			+ "t1.ean,"
			+ "t1.casasdecimaisprecocusto as casasdecimaiscusto,"
			+ "t2.casasdecimais as casasdecimaisunidade,"
			+ " t1.nome as produto,"
			+ " t4.nome as fornecedor,"
			+ "t1.idhierarquia as idhierarquia,"
			+ "t3.nome as grupoIII, "
			+ "( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t3.codigo FROM 0 FOR 7 ) ) AS grupoI,\n"
            + "( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t3.codigo FROM 1 FOR 12 ) ) AS grupoII,\n" 
			+ "( select quantidade from total_estoque_view ev  where ev.idproduto = t1.id and ev.idfilial = ?3) as saldo_estoque, "
            + "( select MAX(viv.emissao) from vendas_itens_view viv  where viv.produto = t1.codigo and viv.tipoitem='P' and viv.filial= CAST( ?3 AS TEXT) group by viv.emissao  order by viv.emissao desc limit 1) as ultima_venda " 
			
			+ " from produto t1 "
			+ "left join unidademedida t2 on (t2.id=t1.idunidademedida) "
			+ "left join hierarquia t3 on (t3.id=t1.idhierarquia) "
			+ "left join entidade t4 on (t4.id = t1.idfornecedor) "
			
			+ "where t1.id NOT IN (\r\n"
			+ "  \r\n"
			+ "	 ( select (select idprodutot1.id from produto idprodutot1 where idprodutot1.codigo=t1.produto) as idproduto from item t1 left join operacao t3 on (t3.id=t1.idoperacao) where t3.tipo in (1,31) and t3.cancelado=0 and t1.cancelado=0 and t3.filial = CAST( ?3 AS TEXT) and coalesce(t3.statusnfce, '0')  in (0,1,13,5,6)  and t3.data>=?1 and t3.data<=?2)\r\n"
			+ " \r\n"
			+ " ) "
			+ " and (select dataUltimaComprat1.dataultimacompra from formacaoprecoproduto dataUltimaComprat1 where dataUltimaComprat1.idproduto=t1.id and dataUltimaComprat1.idfilial=?3) >= ?4"
			+ " and t1.tipo = 'P' and t1.tipoproduto='00' "
			+ " and ( select quantidade from total_estoque_view ev  where ev.idproduto = t1.id and ev.idfilial = ?3) is not null ", nativeQuery = true)
	List<ProdutosSemVendas> todos(LocalDate data1, LocalDate data2, Integer filial, LocalDate ultimaCompra);
	
	
	@Query(value=" select t1.id as id,"
			+ "t1.precocusto as precocusto,"
			+ "t1.dataultimacompra as ultimaCompra,"
			+ "t1.codigo as codigo,"
			+ "t1.ean,"
			+ "t1.casasdecimaisprecocusto as casasdecimaiscusto,"
			+ "t2.casasdecimais as casasdecimaisunidade,"
			+ " t1.nome as produto,"
			+ " t4.nome as fornecedor,"
			+ "t1.idhierarquia as idhierarquia,"
			+ "t3.nome as grupoIII, "
			+ "( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t3.codigo FROM 0 FOR 7 ) ) AS grupoI,\n"
            + "( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t3.codigo FROM 1 FOR 12 ) ) AS grupoII,\n" 
			+ "( select quantidade from total_estoque_view ev  where ev.idproduto = t1.id and ev.idfilial = ?3) as saldo_estoque, "
            + "( select MAX(viv.emissao) from vendas_itens_view viv  where viv.produto = t1.codigo and viv.tipoitem='P' and viv.filial= CAST( ?3 AS TEXT) group by viv.emissao  order by viv.emissao desc limit 1) as ultima_venda " 
			
			+ " from produto t1 "
			+ "left join unidademedida t2 on (t2.id=t1.idunidademedida) "
			+ "left join hierarquia t3 on (t3.id=t1.idhierarquia) "
			+ "left join entidade t4 on (t4.id = t1.idfornecedor) "
			
			+ "where t1.id NOT IN (\r\n"
			+ "  \r\n"
			+ "	 ( select (select idprodutot1.id from produto idprodutot1 where idprodutot1.codigo=t1.produto) as idproduto from item t1 left join operacao t3 on (t3.id=t1.idoperacao) where t3.tipo in (1,31) and t3.cancelado=0 and t1.cancelado=0 and t3.filial = CAST( ?3 AS TEXT) and coalesce(t3.statusnfce, '0')  in (0,1,13,5,6)  and t3.data>=?1 and t3.data<=?2)\r\n"
			+ " \r\n"
			+ " ) "
			+ " and t1.dataultimacompra >= ?4"
			+ " and t1.tipo = 'P' and t1.tipoproduto='00' "
			+ " and ( select quantidade from total_estoque_view ev  where ev.idproduto = t1.id and ev.idfilial = ?3) is not null ", nativeQuery = true)
	List<ProdutosSemVendas> todos1Filial(LocalDate data1, LocalDate data2, Integer filial, LocalDate ultimaCompra);

}
