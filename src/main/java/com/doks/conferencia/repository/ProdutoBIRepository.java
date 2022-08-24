package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ProdutoBI;

public interface ProdutoBIRepository extends JpaRepository<com.doks.conferencia.model.ProdutoBI, Integer> {

	

	@Query(value = "select produto.id, produto.nome ,"
			+ "filial.nome as filial,"
			+ "(select avg(v1.precounitario) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as preco_medio_venda, "
			+ "(select sum(v1.quantidade) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as quantidade_vendida,"
			+ "(select sum(v1.desconto) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as total_desconto, "
			+ "(select sum(notafiscalitem.quantidade) from notafiscalitem join notafiscal on notafiscal.id = notafiscalitem.idnotafiscal join cfop on cfop.id = notafiscal.idcfop "
			+ " where notafiscalitem.idproduto = produto.id and notafiscal.idfilial = formacaoprecoproduto.idfilial and notafiscal.tipodocumento='E' and notafiscal.emissao >= ?4 and notafiscal.emissao <= ?5 and cfop.consideravenda ='1')"
			+ "as quantidade_comprada, "
			+ "(select sum(v1.total) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as total_vendido "
			+ " from formacaoprecoproduto join produto on produto.id = formacaoprecoproduto.idproduto join filial on filial.id = formacaoprecoproduto.idfilial where produto.id=?1  and formacaoprecoproduto.idfilial=?2 ", nativeQuery = true)
			List<ProdutoBI> totalVendaPorIdProduto(Integer idproduto, Integer idfilial, LocalDate qtdeDiasVenda,
			LocalDate qtdeDiasCompra, LocalDate now);

}
