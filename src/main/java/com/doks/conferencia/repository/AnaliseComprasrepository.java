package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.AnaliseCompras;

public interface AnaliseComprasrepository extends JpaRepository<AnaliseCompras, Integer> {

	@Query(value = "select distinct t1.id as id, t2.id as idproduto, t2.nome as produto, t2.codigo as codigo, t2.ean as ean, t5.datainclusao as data_inclusao, t1.total as total_comprado , t8.codigo as cfop, t8.descricao  as cfop_descricao,"
			+ "t1.quantidade as quantidade_comprada,((t1.precounitario + t1.icmssubstituicao + t1.icmsfundopobrezast + t1.freteoutrasdespesas) / t1.embalagem )  as custo_unitario, t7.nome as fornecedor, t5.datainclusao, t1.unidade as unidade_compra,t1.embalagem as embalagem, "
			+ "(select sum(v1.quantidade) from vendas_itens_view v1 where v1.emissao >= ?5 and v1.emissao <= ?6 and v1.produto = t2.codigo and cast(v1.filial as INTEGER) = ?4 ) as quantidade_vendida,"
			+ "(select avg(v1.total) from vendas_itens_view v1 where v1.emissao >= ?5 and v1.emissao <= ?6 and v1.produto = t2.codigo and cast(v1.filial as INTEGER) = ?4 ) as preco_medio_venda,"
			+ "(select quantidade from total_estoque_view ev  where ev.idproduto = t2.id and ev.idfilial = ?4) as saldo_estoque, "
			+ "(select (v1.codigounidademedida) from vendas_itens_view v1 where v1.emissao >= ?5 and v1.emissao <= ?6 and v1.produto = t2.codigo and cast(v1.filial as INTEGER) = ?4 limit 1 ) as unidade_venda"
			+ " from notafiscalitem t1 left join produto t2 on (t2.id=t1.idproduto) left join hierarquia t3 on (t3.id=t2.idhierarquia)  left join notafiscal t5 on (t5.id=t1.idnotafiscal) left join operacaofiscal t6 on (t6.id=t5.idoperacaofiscal) left join entidade t7 on (t7.id=t5.identidade)  left join cfop t8 on (t8.id=t5.idcfop) where t7.id=?3 and  t2.tipo='P' and (t8.consideravenda=1 or t6.consideravenda=1) and t5.tipodocumento='E' and t5.datainclusao >= ?1 and t5.datainclusao <=?2 order by t2.nome asc", nativeQuery = true)
	      List<AnaliseCompras> comprasProdutos(LocalDate dataI, LocalDate dataF, Integer fornecedor, Integer filial, LocalDate dataIV, LocalDate dataFV);
	
	
	


}
