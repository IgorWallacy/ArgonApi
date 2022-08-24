package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.FormacaoPrecoProduto;

public interface FormacaoPrecoProdutoRepository extends JpaRepository<FormacaoPrecoProduto, Integer> {

	

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

			"	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque, \n"+
			
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao  where pp.idproduto=produto.id  and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, " +
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao join promocaofilial pf on pf.idpromocao=pr.id where pp.idproduto=produto.id and pf.idfilial=formacaoprecoproduto.idfilial and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocionalfilial, " +
			" (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia "+
			
			"FROM\n" +
			"	formacaoprecoproduto\n" +
			"	INNER JOIN produto ON formacaoprecoproduto.idproduto = produto.id\n" +
			"	INNER JOIN filial ON filial.ID = formacaoprecoproduto.idfilial\n" +
			"	INNER JOIN hierarquia ON hierarquia.ID = produto.idhierarquia\n" +
			"  INNER JOIN unidademedida on unidademedida.id = produto.idunidademedida	\n" +
			"	\n" +
			"WHERE\n" +
			"	produto.inativo = '0' \n" +
			" and produto.id=?1 "+
			"ORDER BY\n" +
			"	produto.nome ASC\n" +
			"	", nativeQuery = true)
	List<FormacaoPrecoProduto> porId(Integer id);

}
