package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ProdutoBI;

public interface ProdutoBIRepository extends JpaRepository<com.doks.conferencia.model.ProdutoBI, Integer> {

	

	@Query(value = "select produto.id, produto.nome ,"
			+ "filial.nome as filial,"
			+ "( select avg(mve.precocusto) from movimentoestoque mve where mve.data >=?3  and mve.data <= ?5 and mve.idproduto = produto.id and mve.idfilial = formacaoprecoproduto.idfilial and mve.tipodocumento = '1' and mve.cancelado = '0' "
			+ ") as preco_custo, "
			+ "(select avg(v1.precounitario) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as preco_medio_venda, "
			+ "(select sum(v1.quantidade) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as quantidade_vendida,"
			+ "(select sum(v1.desconto) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as total_desconto, "
			+ "(select sum(coalesce(quantidadecomprat1.quantidadeentrada,0)) from movimentoestoque quantidadecomprat1 left join notafiscalitem quantidadecomprat2 on (quantidadecomprat2.id=quantidadecomprat1.iditemoriginal) "
			+ " left join notafiscal quantidadecomprat3 on (quantidadecomprat3.id=quantidadecomprat2.idnotafiscal) left join cfop quantidadecomprat4 on (quantidadecomprat4.id=quantidadecomprat3.idcfop)"
			+ " where quantidadecomprat1.cancelado=0 and quantidadecomprat1.idproduto=produto.id and quantidadecomprat1.variacao=0 and quantidadecomprat1.idfilial =formacaoprecoproduto.idfilial and quantidadecomprat1.tipodocumento=2 and quantidadecomprat1.quantidadeentrada>0 and quantidadecomprat4.consideravenda=1 and quantidadecomprat1.data>=?4 and quantidadecomprat1.data<=?5)"
			+ "as quantidade_comprada, "
			+ "(select sum(notafiscalitem.total) from notafiscalitem join notafiscal on notafiscal.id = notafiscalitem.idnotafiscal join cfop on cfop.id = notafiscal.idcfop "
			+ " where notafiscalitem.idproduto = produto.id and notafiscal.idfilial = formacaoprecoproduto.idfilial and notafiscal.tipodocumento='E' and notafiscal.emissao >= ?4 and notafiscal.emissao <= ?5 and cfop.consideravenda ='1')"
			+ "as total_comprado, "
			+ "(select sum(v1.total) from vendas_itens_view v1 where v1.emissao >= ?3 and v1.emissao <= ?5 and v1.produto = produto.codigo and cast(v1.filial as INTEGER) = ?2) as total_vendido, "
			+ " (select sum(devolucaoitem.quantidade) from devolucaoitem join devolucao on devolucao.id = devolucaoitem.iddevolucao where devolucaoitem.idproduto=produto.id and devolucao.idfilial=formacaoprecoproduto.idfilial and devolucao.data >= ?3 and devolucao.data <=?5 ) as quantidade_devolvida, "
			+ " (select sum(devolucaoitem.total) from devolucaoitem join devolucao on devolucao.id = devolucaoitem.iddevolucao where devolucaoitem.idproduto=produto.id and devolucao.idfilial=formacaoprecoproduto.idfilial and devolucao.data >= ?3 and devolucao.data <=?5 ) as total_devolucao "
			+ " from formacaoprecoproduto join produto on produto.id = formacaoprecoproduto.idproduto join filial on filial.id = formacaoprecoproduto.idfilial where produto.id=?1  and formacaoprecoproduto.idfilial=?2 ", nativeQuery = true)
			List<ProdutoBI> totalVendaPorIdProduto(Integer idproduto, Integer idfilial, LocalDate qtdeDiasVenda,
			LocalDate qtdeDiasCompra, LocalDate now);

}
