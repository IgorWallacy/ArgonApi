package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.VendasItensView;

public interface VendasItensViewRepository extends JpaRepository<VendasItensView, Integer> {

	@Query(value = "SELECT\n" +
			"	MAX(t1.iditem) AS ID,\n" +
			"	MAX(t1.emissao) AS data_emissao,\n" +
			"	t1.produto AS codigo,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS grupo_pai,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS grupo_filho,\n" +
			"	t7.nome AS grupo_neto,\n" +
			"	MAX(CONCAT(t1.produto , ' - ', t1.descricao)) AS descricao,\n" +
			"   t1.condicaopagamento as condicaopagamento, "+
			"	AVG(( select SUM(devolucaoitem.total) from devolucao left join devolucaoitem on (devolucaoitem.iddevolucao = devolucao.id) where devolucao.data between ?1 and ?2  and devolucaoitem.idproduto =  t2.id and devolucao.idfilial = filial.id and devolucao.status in('1','2')  )) AS devolucao,\n" +
            "	AVG(( select SUM(devolucaoitem.quantidade) from devolucao left join devolucaoitem on (devolucaoitem.iddevolucao = devolucao.id) where devolucao.data between ?1 and ?2  and devolucaoitem.idproduto =  t2.id and devolucao.idfilial = filial.id and devolucao.status in('1','2')  )) AS quantidade_devolvida,\n" +
            "	SUM( t1.quantidade ) AS quantidade,\n" +
			"   SUM(t1.total) as valor_total,"+
			"  ( case when t10.nome is null then 'Sem promoção' else t10.nome end ) as nome_promocao, "+
			"  t10.codigo as promocao, "+
			"  AVG((case when t7.doks_meta > 0 then t7.doks_meta else t2.doks_meta end )) as meta, "+

			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.custoaquisicao end  )  from movimentoestoque mve     where mve.data = t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento IN ('1', '2') and mve.cancelado = '0' and mve.variacao='0' and mve.quantidadesaida > 0     )  )) AS preco_ultima_compra," +
			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.custoaquisicao end )  from movimentoestoque mve     where mve.data = t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento IN ('1', '2') and mve.cancelado = '0'  and mve.variacao='0' and mve.quantidadesaida > 0   )   )) * SUM(t1.quantidade)   AS preco_ultima_compra_total," +

			"   SUM (  t1.total  ) / SUM(t1.quantidade)  AS preco_unitario,"+
			"   SUM(t1.desconto) as desconto, "+
			"	t1.filial AS codigo_filial,\n" +
			"	filial.nome AS nome_filial \n" +
			" FROM\n" +
			"	vendas_itens_view t1\n" +
			"	LEFT JOIN produto t2 ON ( t2.codigo = t1.produto )\n" +
			"	LEFT JOIN hierarquia t7 ON ( t7.ID = t2.idhierarquia )\n" +
			"	LEFT JOIN filial ON ( t1.filial = filial.codigo ) \n" +
			"   left join promocao t10 on (t1.idpromocao = t10.id) \n "+


			" where " +
			"  t1.status IN ('0','3','6') and " +

			"  t1.tipoitem='P' AND "+

			"	t1.emissao BETWEEN ?1 \n" +
			"	AND  ?2" +
			"	AND t1.modelo in ('01','2D','55','59','65','99','1','1-A','1B') "+
			" GROUP BY t1.condicaopagamento,t1.produto,grupo_pai,grupo_filho,grupo_neto,descricao,codigo_filial,nome_filial, nome_promocao, promocao "
			 , nativeQuery = true)
	List<VendasItensView> getVendasBI(LocalDate dataInicial , LocalDate dataFinal, Integer modocusto);

	@Query(value = "SELECT\n" +
			"	MAX(t1.iditem) AS ID,\n" +
			"	MAX(t1.emissao) AS data_emissao,\n" +
			"	t1.produto AS codigo,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS grupo_pai,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS grupo_filho,\n" +
			"	t7.nome AS grupo_neto,\n" +
			"	MAX( CONCAT(t1.produto , ' - ', t1.descricao) ) AS descricao,\n" +
			"   t1.condicaopagamento as condicaopagamento, "+
			"	SUM(t1.quantidade) AS quantidade,\n" +
			"   SUM(t1.total) as valor_total,"+
			"  ( case when t10.nome is null then 'Sem promoção' else t10.nome end ) as nome_promocao, "+
			"  t10.codigo as promocao, "+
			"  AVG((case when t7.doks_meta > 0 then t7.doks_meta else t2.doks_meta end )) as meta, "+

			"	AVG(( select SUM(devolucaoitem.total) from devolucao left join devolucaoitem on (devolucaoitem.iddevolucao = devolucao.id) where devolucao.data between ?1 and ?2 and devolucaoitem.idproduto =  t2.id and devolucao.idfilial = filial.id  and devolucao.status in('1','2') )) AS devolucao,\n" +
			"	AVG(( select SUM(devolucaoitem.quantidade) from devolucao left join devolucaoitem on (devolucaoitem.iddevolucao = devolucao.id) where devolucao.data between ?1 and ?2 and devolucaoitem.idproduto =  t2.id and devolucao.idfilial = filial.id and devolucao.status in('1','2')  )) AS quantidade_devolvida,\n" +


			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.custoaquisicao end  )  from movimentoestoque mve     where mve.data  = t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento IN ('1') and mve.cancelado = '0' and mve.variacao='0' and mve.quantidadesaida > 0    )  )) AS preco_ultima_compra," +
			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.custoaquisicao end )  from movimentoestoque mve       where mve.data = t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento IN ('1') and mve.cancelado = '0'  and mve.variacao='0' and mve.quantidadesaida > 0    )   )) * SUM(t1.quantidade)   AS preco_ultima_compra_total," +

			"   SUM (  t1.total  ) / SUM(t1.quantidade)  AS preco_unitario,"+
			"   SUM(t1.desconto) as desconto, "+
			"	t1.filial AS codigo_filial,\n" +
			"	filial.nome AS nome_filial \n" +
			" FROM\n" +
			"	vendas_itens_view t1\n" +
			"	LEFT JOIN produto t2 ON ( t2.codigo = t1.produto )\n" +
			"	LEFT JOIN hierarquia t7 ON ( t7.ID = t2.idhierarquia )\n" +
			"	LEFT JOIN filial ON ( t1.filial = filial.codigo ) \n" +
			"   left join promocao t10 on (t1.idpromocao = t10.id) \n "+


			" where " +
			"  t1.status IN ('0','3','6') and " +

			"  t1.tipoitem='P' AND "+

			"	t1.emissao BETWEEN ?1 \n" +
			"	AND  ?2" +
			"	AND t1.modelo in ('2D','65') "+
			" GROUP BY  t1.condicaopagamento,t1.produto,grupo_pai,grupo_filho,grupo_neto,descricao,codigo_filial,nome_filial, nome_promocao, promocao"
			, nativeQuery = true)
	List<VendasItensView> getVendasBISomentePDV(LocalDate dataInicial , LocalDate dataFinal, Integer modocusto);







}
