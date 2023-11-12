package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.EstoqueCompras;

public interface EstoqueComprasRepository extends JpaRepository<EstoqueCompras, Integer> {

	@Query(value = "SELECT distinct \n" +
	        "   t1.id as id, "+
			"	CONCAT(t2.codigo,'-',t2.nome) AS nomeproduto,\n" +
			"	t2.codigo AS codigoproduto,\n" +
		
			"  (select sum(viv.quantidade) from vendas_itens_view viv where viv.produto= t1.produto and viv.filial = fl.codigo and viv.emissao BETWEEN ?1 and ?2 limit 1)  as quantidadevendida,"+
			"	t1.embalagem AS quantidadeembalagem,\n" +
			"  (select viv.codigounidademedida from vendas_itens_view viv where viv.produto= t1.produto and viv.filial = fl.codigo and viv.emissao BETWEEN ?1 and ?2 limit 1)  as unvenda,"+
			"	t1.embalagem AS quantidadeembalagem,\n" +
			"(\n" +
			"	SELECT\n" +
			"		numeronfultcomprat3.numeronotafiscal \n" +
			"	FROM\n" +
			"		movimentoestoque numeronfultcomprat1\n" +
			"		LEFT JOIN notafiscalitem numeronfultcomprat2 ON ( numeronfultcomprat2.ID = numeronfultcomprat1.iditemoriginal )\n" +
			"		LEFT JOIN notafiscal numeronfultcomprat3 ON ( numeronfultcomprat3.ID = numeronfultcomprat2.idnotafiscal )\n" +
			"		LEFT JOIN cfop numeronfultcomprat4 ON ( numeronfultcomprat4.ID = numeronfultcomprat3.idcfop ) \n" +
			"	WHERE\n" +
			"		numeronfultcomprat1.cancelado = 0 \n" +
			"		AND numeronfultcomprat1.idproduto = t2.ID \n" +
			"		AND numeronfultcomprat1.variacao = 0 \n" +
			"		AND numeronfultcomprat1.idfilial = t3.idfilial \n" +
			"		AND numeronfultcomprat1.tipodocumento = 2 \n" +
			"		AND numeronfultcomprat1.quantidadeentrada > 0 \n" +
			"		AND numeronfultcomprat4.consideravenda = 1 \n" +
			"	ORDER BY\n" +
			"		numeronfultcomprat1.datahora DESC \n" +
			"		LIMIT 1 \n" +
			"	) AS numeronfultcompra,"+
			
			"	CONCAT( t1.unidade, '(', ROUND(t1.embalagem,2 ), ')'  ) AS codigounidademedida,\n" +
			"	t1.quantidade AS quantidadecompra,\n" +
			"	t1.precocusto AS precocusto,\n" +
			"	t1.total AS total,\n" +
		
			"	fl.nome AS nomefilial,\n" +
			"	t3.numeronotafiscal AS numeronf,\n" +
			"	t3.emissao AS dataemissao,\n" +
			"	t3.entradasaida AS data_emissao,\n" +
			"	t4.codigo AS codigofornecedor,\n" +
			"	t4.razaosocial AS nomefornecedor, \n" +
            "  (select condicaopagamento.descricao from notafiscal left join condicaopagamento on (condicaopagamento.id=notafiscal.idcondicaopagto) where numeronotafiscal  = t3.numeronotafiscal limit 1) as condicaopagamento,"+
			"COALESCE (\n" +
			"		(\n" +
			"		SELECT\n" +
			"			precoultimacomprat1.precoultimacompra \n" +
			"		FROM\n" +
			"			movimentoestoque precoultimacomprat1\n" +
			"			LEFT JOIN notafiscalitem precoultimacomprat2 ON ( precoultimacomprat2.ID = precoultimacomprat1.iditemoriginal )\n" +
			"			LEFT JOIN notafiscal precoultimacomprat3 ON ( precoultimacomprat3.ID = precoultimacomprat2.idnotafiscal )\n" +
			"			LEFT JOIN cfop precoultimacomprat4 ON ( precoultimacomprat4.ID = precoultimacomprat3.idcfop ) \n" +
			"		WHERE\n" +
			"			precoultimacomprat1.cancelado = 0 \n" +
			"			AND precoultimacomprat1.idproduto = t2.ID \n" +
			"			AND precoultimacomprat1.variacao = 0 \n" +
			"			AND precoultimacomprat1.idfilial = t3.idfilial \n" +
			"			AND precoultimacomprat1.tipodocumento = 2 \n" +
			"			AND precoultimacomprat1.quantidadeentrada > 0 \n" +
			"			AND precoultimacomprat4.consideracustomedio = 1 \n" +
			"		ORDER BY\n" +
			"			precoultimacomprat1.datahora DESC \n" +
			"			LIMIT 1 \n" +
			"		),\n" +
			"		t2.precoultimacompra \n" +
			"	) AS precoultimacompra,"+
			" (0) as quantidadesaldoestoque "+
			"FROM\n" +
			"	notafiscalitem t1\n" +
			"	LEFT JOIN produto t2 ON ( t2.ID = t1.idproduto )\n" +
			"	LEFT JOIN notafiscal t3 ON ( t3.ID = t1.idnotafiscal )\n" +
			"	LEFT JOIN entidade t4 ON ( t4.ID = t3.identidade )\n" +
			"	LEFT JOIN cfop t5 ON ( t5.ID = t1.idcfop ) \n" +
			"   LEFT JOIN filial fl on (fl.id = t3.idfilial) \n "+
			"WHERE\n" +
			"	t3.tipodocumento = 'E' \n" +
			"	AND t3.transferencia <> 1 \n" +
			"	AND t5.consideravenda = 1 \n" +
			"   AND t4.id = ?3"+
			"	AND t2.tipoproduto IN ( '00' ) \n" +
			"	AND t3.emissao >= ?1 \n" +
			"	AND t3.emissao <= ?2 \n" +
			"ORDER BY\n" +
			"	t3.emissao,\n" +
			"	t4.razaosocial" , nativeQuery = true)
	List<EstoqueCompras> porData(LocalDate dataI, LocalDate dataF, Integer fornecedor);
	
	
	
	
	
	
	@Query( value = " select distinct formacaoprecoproduto.id as id, filial.nome as nomefilial, produto.codigo as codigoproduto, "
			+ "produto.nome as nomeproduto,"
			+ "MAX(produto.unidademedida) as unvenda,"
			+ "MAX((select notafiscalitem.unidade  from notafiscalitem left join notafiscal on (notafiscal.id = notafiscalitem.idnotafiscal) where notafiscalitem.idproduto = produto.id and notafiscal.tipodocumento = 'E' and notafiscal.idfilial = filial.id and notafiscal.emissao >= ?1 and notafiscal.emissao <= ?2 order by notafiscal.id desc limit 1  )) as codigounidademedida, "
			+ " entidade.nome as nomefornecedor,"
			+ "SUM((select sum(notafiscalitem.total)  from notafiscalitem left join notafiscal on (notafiscal.id = notafiscalitem.idnotafiscal) where notafiscalitem.idproduto = produto.id and notafiscal.tipodocumento = 'E' and notafiscal.idfilial = filial.id and notafiscal.emissao >= ?1 and notafiscal.emissao <= ?2  )) as total,"
			+ "AVG((select saldoestoque_por_produto_filial(produto.id,filial.id) )) as quantidadesaldoestoque,"
			+ "AVG((select avg(notafiscalitem.embalagem)  from notafiscalitem left join notafiscal on (notafiscal.id = notafiscalitem.idnotafiscal) where notafiscalitem.idproduto = produto.id and notafiscal.tipodocumento = 'E' and notafiscal.idfilial = filial.id and notafiscal.emissao >= ?1 and notafiscal.emissao <= ?2  )) as quantidadeembalagem,"
			+ "SUM((0)) as numeronf,"
			+ "( SELECT "
			+ "					numeronfultcomprat3.numeronotafiscal "
			+ "				FROM"
			+ "					movimentoestoque numeronfultcomprat1"
			+ "					LEFT JOIN notafiscalitem numeronfultcomprat2 ON ( numeronfultcomprat2.ID = numeronfultcomprat1.iditemoriginal )"
			+ "					LEFT JOIN notafiscal numeronfultcomprat3 ON ( numeronfultcomprat3.ID = numeronfultcomprat2.idnotafiscal )"
			+ "					LEFT JOIN cfop numeronfultcomprat4 ON ( numeronfultcomprat4.ID = numeronfultcomprat3.idcfop ) "
			+ "				WHERE"
			+ "					numeronfultcomprat1.cancelado = 0 "
			+ "					AND numeronfultcomprat1.idproduto = produto.id "
			+ "					AND numeronfultcomprat1.variacao = 0 "
			+ "					AND numeronfultcomprat1.idfilial = filial.id "
			+ "					AND numeronfultcomprat1.tipodocumento = 2 "
			+ "					AND numeronfultcomprat1.quantidadeentrada > 0 "
			+ "					AND numeronfultcomprat4.consideravenda = 1 "
			+ "				ORDER BY"
			+ "					numeronfultcomprat1.datahora DESC "
			+ "					LIMIT 1 "
			+ ")   as numeronfultcompra,"
			+ "MAX(( select condicaopagamento.descricao from notafiscal left join condicaopagamento on (condicaopagamento.id=notafiscal.idcondicaopagto) where numeronotafiscal  = (SELECT "
			+ "											numeronfultcomprat3.numeronotafiscal "
			+ "										FROM"
			+ "											movimentoestoque numeronfultcomprat1"
			+ "											LEFT JOIN notafiscalitem numeronfultcomprat2 ON ( numeronfultcomprat2.ID = numeronfultcomprat1.iditemoriginal )"
			+ "											LEFT JOIN notafiscal numeronfultcomprat3 ON ( numeronfultcomprat3.ID = numeronfultcomprat2.idnotafiscal )"
			+ "											LEFT JOIN cfop numeronfultcomprat4 ON ( numeronfultcomprat4.ID = numeronfultcomprat3.idcfop ) "
			+ "										WHERE"
			+ "											numeronfultcomprat1.cancelado = 0 "
			+ "											AND numeronfultcomprat1.idproduto = produto.id "
			+ "											AND numeronfultcomprat1.variacao = 0 "
			+ "											AND numeronfultcomprat1.idfilial = filial.id "
			+ "											AND numeronfultcomprat1.tipodocumento = 2 "
			+ "											AND numeronfultcomprat1.quantidadeentrada > 0 "
			+ "											AND numeronfultcomprat4.consideravenda = 1 "
			+ "										ORDER BY"
			+ "											numeronfultcomprat1.datahora DESC "
			+ "											LIMIT 1) limit 1)) as condicaopagamento,"
			+ "formacaoprecoproduto.precoultimacompra as precoultimacompra,"
			+ "SUM((select sum(notafiscalitem.quantidade * notafiscalitem.embalagem )  from notafiscalitem left join notafiscal on (notafiscal.id = notafiscalitem.idnotafiscal) where notafiscalitem.idproduto = produto.id and notafiscal.tipodocumento = 'E' and notafiscal.idfilial = filial.id and notafiscal.emissao >= ?1 and notafiscal.emissao <= ?2  )) as quantidadecompra,"
			+ "SUM((select sum(vendas_itens_view.quantidade) from vendas_itens_view where vendas_itens_view.filial = filial.codigo and vendas_itens_view.produto = produto.codigo and vendas_itens_view.emissao between ?3 and ?4)) as quantidadevendida,"
			+ "AVG((select avg(notafiscalitem.precocusto)  from notafiscalitem left join notafiscal on (notafiscal.id = notafiscalitem.idnotafiscal) where notafiscalitem.idproduto = produto.id and notafiscal.tipodocumento = 'E' and notafiscal.idfilial = filial.id and notafiscal.emissao >= ?1 and notafiscal.emissao <= ?2  )) as precocusto,"
			+ "(null) as data_emissao"
			+ " from formacaoprecoproduto left join produto on (produto.id = formacaoprecoproduto.idproduto)"
			+ " left join filial on ( formacaoprecoproduto.idfilial = filial.id)"
			+ " left join entidade on (produto.idfornecedor = entidade.id)"
			+ " left join hierarquia on (hierarquia.id = produto.idhierarquia) "
			+ " where produto.inativo = '0' and hierarquia.codigo LIKE ?5% "
			+ " group by formacaoprecoproduto.id,nomefilial,codigoproduto,nomeproduto,nomefornecedor,data_emissao,numeronfultcompra,formacaoprecoproduto.precoultimacompra"
			
			,nativeQuery = true)
	List<EstoqueCompras> porDataProdutoEmpresa(LocalDate dataIC, LocalDate dataFC, LocalDate dataIV, LocalDate dataFV, String grupo);

	
	

	

}
