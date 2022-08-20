package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.FormacaoPrecoProduto;

public interface FormacaoPrecoProdutoRepository extends JpaRepository<FormacaoPrecoProduto, Integer> {

	@Query(value = "select id, idproduto, idfilial , preco, precocusto from formacaoprecoproduto where formacaoprecoproduto.idproduto=?1", nativeQuery = true)
	List<FormacaoPrecoProduto> buscarPeloIdProduto(String id);

	@Query(value = "select formacaoprecoproduto.id, formacaoprecoproduto.idproduto, formacaoprecoproduto.idfilial , formacaoprecoproduto.preco, formacaoprecoproduto.precocusto, (select hierarquia.id from hierarquia where hierarquia.id = produto.idhierarquia ) as idhierarquia, (select quantidade from total_estoque_view where total_estoque_view.idfilial = formacaoprecoproduto.idfilial and total_estoque_view.idproduto=formacaoprecoproduto.idproduto ) as estoque from formacaoprecoproduto inner join produto on formacaoprecoproduto.idproduto=produto.id where formacaoprecoproduto.idproduto=?1", nativeQuery = true)
	List<FormacaoPrecoProduto> porId(Integer id);

	@Query(value = "SELECT\n" +
			"	formacaoprecoproduto.ID AS ID,\n" +
			"	produto.nome AS produto,\n" +
			"   produto.id as idproduto, " +
			"   produto.ean as ean, " +
			"   produto.codigo as codigo, " +
			"	filial.nome AS filial,\n" +
			"   filial.id as idfilial, " +
			"	formacaoprecoproduto.preco AS preco,\n" +
			"	formacaoprecoproduto.precocusto AS precocusto,\n" +
			"   formacaoprecoproduto.dataultimacompra AS dataultimacompra,\n" +
			"   formacaoprecoproduto.custoalteradopor AS custoalteradopor,\n" +
			"   formacaoprecoproduto.dataprecocusto AS dataprecocusto,\n" +
			"   formacaoprecoproduto.dataalteracaopreco AS dataalteracaopreco,\n" +
			"	unidademedida.nome as unidade,\n" +
			"	hierarquia.nome AS hierarquia,\n" +

			"	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque \n"
			+
			"FROM\n" +
			"	formacaoprecoproduto\n" +
			"	INNER JOIN produto ON formacaoprecoproduto.idproduto = produto.id\n" +
			"	INNER JOIN filial ON filial.ID = formacaoprecoproduto.idfilial\n" +
			"	INNER JOIN hierarquia ON hierarquia.ID = produto.idhierarquia\n" +
			"  INNER JOIN unidademedida on unidademedida.id = produto.idunidademedida	\n" +
			"	\n" +
			"WHERE\n" +
			"	produto.inativo = '0' \n" +
			"ORDER BY\n" +
			"	produto.nome ASC\n" +
			"	", nativeQuery = true)
	List<FormacaoPrecoProduto> todos();

}
