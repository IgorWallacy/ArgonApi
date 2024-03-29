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
			"	MAX(t1.produto) AS codigo,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS grupo_pai,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS grupo_filho,\n" +
			"	t7.nome AS grupo_neto,\n" +
			"	 CONCAT(MAX(t1.produto) , ' - ', t1.descricao) AS descricao,\n" +
			"	SUM(t1.quantidade) AS quantidade,\n" +
			"   SUM(t1.total) as valor_total,"+
			"  ( case when t10.nome is null then 'Sem promoção' else t10.nome end ) as nome_promocao, "+
			"  t10.codigo as promocao, "+
			"  AVG((case when t7.doks_meta > 0 then t7.doks_meta else t2.doks_meta end )) as meta, "+

			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.precocusto end  )  from movimentoestoque mve where mve.data >= t1.emissao  and mve.data <= t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento = '1' and mve.cancelado = '0')  )) AS preco_ultima_compra," +
			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.precocusto end )  from movimentoestoque mve where mve.data >= t1.emissao  and mve.data <= t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento = '1' and mve.cancelado = '0')   )) * SUM(t1.quantidade)   AS preco_ultima_compra_total," +

			"	AVG(t1.precounitario) AS preco_unitario,\n" +
			"	t1.filial AS codigo_filial,\n" +
			"	filial.nome AS nome_filial, \n" +
			"   t11.nome as fornecedor "+

			"FROM\n" +
			"	vendas_itens_view t1\n" +
			"	LEFT JOIN produto t2 ON ( t2.codigo = t1.produto )\n" +
			"	LEFT JOIN hierarquia t7 ON ( t7.ID = t2.idhierarquia )\n" +
			"	LEFT JOIN filial ON ( t1.filial = filial.codigo ) \n" +
			"   left join promocao t10 on (t1.idpromocao = t10.id) \n "+
			"   left join entidade  t11 on (t11.id = t2.idfornecedor) "+

			" where " +
			" t1.status = '3' and " +
			"  t1.tipoitem='P' AND "+
			"	t1.emissao BETWEEN ?1 \n" +
			"	AND  ?2" +
			" GROUP BY  grupo_pai,grupo_filho,grupo_neto,descricao,codigo_filial,nome_filial,t11.nome, nome_promocao, promocao "+
			" order by descricao  \n" , nativeQuery = true)
	List<VendasItensView> getVendasBI(LocalDate dataInicial , LocalDate dataFinal, Integer modocusto);




	@Query(value = "SELECT\n" +
			"	MAX(t1.iditem) AS ID,\n" +
			"	t1.emissao AS data_emissao,\n" +
			"	MAX(t1.produto) AS codigo,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS grupo_pai,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS grupo_filho,\n" +
			"	t7.nome AS grupo_neto,\n" +
			"	 CONCAT(MAX(t1.produto) , ' - ', t1.descricao) AS descricao,\n" +
			"	SUM(t1.quantidade) AS quantidade,\n" +
			"   SUM(t1.total) as valor_total,"+
			"  ( case when t10.nome is null then 'Sem promoção' else t10.nome end )as nome_promocao, "+
			"  t10.codigo as promocao, "+
			"  AVG((case when t7.doks_meta > 0 then t7.doks_meta else t2.doks_meta end )) as meta, "+

			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.precocusto end  )  from movimentoestoque mve where mve.data >= t1.emissao  and mve.data <= t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento = '1' and mve.cancelado = '0')  )) AS preco_ultima_compra," +
			"AVG(( (select avg( case when ?3 = 0 then mve.precoultimacompra when ?3 = 1 then mve.precocusto end )  from movimentoestoque mve where mve.data >= t1.emissao  and mve.data <= t1.emissao and mve.idproduto = t2.id AND mve.idfilial = filial.id and mve.tipodocumento = '1' and mve.cancelado = '0')   )) * SUM(t1.quantidade)   AS preco_ultima_compra_total," +

			"	AVG(t1.precounitario) AS preco_unitario,\n" +
			"	t1.filial AS codigo_filial,\n" +
			"	filial.nome AS nome_filial, \n" +
			"   t11.nome as fornecedor "+

			"FROM\n" +
			"	vendas_itens_view t1\n" +
			"	LEFT JOIN produto t2 ON ( t2.codigo = t1.produto )\n" +
			"	LEFT JOIN hierarquia t7 ON ( t7.ID = t2.idhierarquia )\n" +
			"	LEFT JOIN filial ON ( t1.filial = filial.codigo ) \n" +
			"   left join promocao t10 on (t1.idpromocao = t10.id) \n "+
			"   left join entidade  t11 on (t11.id = t2.idfornecedor) "+

			" where " +
			" t1.status = '3' and "+
			"  t1.tipoitem='P' AND "+
			"	t1.emissao BETWEEN ?1 \n" +
			"	AND  ?2" +
			" GROUP BY  grupo_pai,grupo_filho,grupo_neto,descricao,codigo_filial,nome_filial,t11.nome,t1.emissao,promocao,nome_promocao "+
			" order by descricao  \n" , nativeQuery = true)
			List<VendasItensView> getVendasBIGroup(LocalDate dataInicial , LocalDate dataFinal, Integer modocusto);


}
