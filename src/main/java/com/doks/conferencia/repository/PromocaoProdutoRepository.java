package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Produto;

public interface PromocaoProdutoRepository extends JpaRepository<Produto, Integer> {
	
	
	
	
	
	@Query(value = "SELECT distinct promocaoproduto.id, produto.codigo, produto.nome, produto.ean, produto.idunidademedida, produto.idhierarquia, produto.idfamilia,produto.imagem,produto.inativo, promocaoproduto.valor from produto "
			+ "  join promocaoproduto "
			+ " on promocaoproduto.idproduto = produto.id"
			+ " join promocaofamilia on promocaofamilia.idfamilia = produto.idfamilia "
			+ " left join promocao on promocao.id = promocaofamilia.idpromocao "
			
			
			
			+ " where promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0'"
			,
			nativeQuery = true) 

	List<Produto> findByVencimentoBetween( LocalDate data, Integer idFilial);

	/*
	 * + "	UNION ALL \r\n"
			+ "SELECT\r\n"
			
			
			+ " promocaofamilia.idpromocao as id,"
			+ " promocao.codigo as codigo,"
			+ "	produto.id as idproduto, \r\n"
			+ "	hierarquia.nome AS grupo, \r\n"
			+ "	unidademedida.nome AS unidademedida, \r\n"
			+ "	promocaofamilia.valor, \r\n"
			+ "	promocaofamilia.idpromocao, \r\n"
			+ "	promocao.datainicial, \r\n"
			+ "	promocao.datafinal\r\n"
			+ "FROM\r\n"
			+ "	promocaofamilia\r\n"
			+ "	JOIN\r\n"
			+ "	familiaproduto\r\n"
			+ "	ON \r\n"
			+ "		familiaproduto.\"id\" = promocaofamilia.idfamilia\r\n"
			+ "	JOIN\r\n"
			+ "	produto\r\n"
			+ "	ON \r\n"
			+ " produto.idfamilia = familiaproduto.id"
			+ "	JOIN\r\n"
			+ "	unidademedida\r\n"
			+ "	ON \r\n"
			+ "		unidademedida.\"id\" = produto.idunidademedida\r\n"
			+ "	JOIN\r\n"
			+ "	hierarquia\r\n"
			+ "	ON \r\n"
			+ "		hierarquia.\"id\" = produto.idhierarquia\r\n"
			+ "	JOIN\r\n"
			+ "	promocao\r\n"
			+ "	ON \r\n"
			+ "		promocaofamilia.idpromocao = promocao.\"id\"\r\n"
			+ "	left join promocaofilial on promocaofilial.idpromocao = promocao.id\r\n"
			+ "	left join filial on filial.id = promocaofilial.idfilial\r\n"
			+ "WHERE \r\n"
			+ " filial.id = ?2 and promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0' \r\n"
			+ " and filial.id = promocaofilial.idfilial or promocaofilial.idfilial is null and promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0' "
	 */

	
	/*
	 * "SELECT DISTINCT \r\n"
			
			
			
			+ " promocaoproduto.id as id, "
			+ " promocao.codigo as codigo,"
			+ "	promocaoproduto.idproduto as idproduto,\r\n"
			+ " promocaoproduto.idpromocao as idpromocao,"
		
			+ "	promocaoproduto.valor \r\n"
			
			+ "FROM \r\n"
			+ "	promocaoproduto \r\n"
			+ " join promocao on promocao.id = promocaoproduto.idpromocao "
			+ " join produto on produto.id = promocaoproduto.idproduto"
			+ " join promocaofamilia on promocaofamilia.idpromocao = promocaoproduto.idpromocao"
			+ " join familiaproduto on familiaproduto.id = produto.id"
		
			+ " where promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0' \r\n"
			+ "	UNION ALL \r\n"
			+ "SELECT\r\n"
			
			
			+ " promocaofamilia.idpromocao as id,"
			+ " promocao.codigo as codigo,"
			+ " promocaoproduto.idproduto as idproduto,"
			+ "	promocaofamilia.idpromocao, \r\n"
			+ "	promocaofamilia.valor \r\n"
			
			+ "FROM\r\n"
			+ "	promocaofamilia\r\n"
			+ "	JOIN\r\n"
			+ "	familiaproduto\r\n"
			+ "	ON \r\n"
			+ "		familiaproduto.\"id\" = promocaofamilia.idfamilia\r\n"
			+ "	JOIN\r\n"
			+ "	produto\r\n"
			+ "	ON \r\n"
			+ " produto.idfamilia = familiaproduto.id"
			+ "	JOIN\r\n"
			+ "	unidademedida\r\n"
			+ "	ON \r\n"
			+ "		unidademedida.\"id\" = produto.idunidademedida\r\n"
			+ "	JOIN\r\n"
			+ "	hierarquia\r\n"
			+ "	ON \r\n"
			+ "		hierarquia.\"id\" = produto.idhierarquia\r\n"
			+ "	JOIN\r\n"
			+ "	promocao\r\n"
			+ "	ON \r\n"
			+ "		promocaofamilia.idpromocao = promocao.\"id\"\r\n"
			
			+ "	left join promocaofilial on promocaofilial.idpromocao = promocao.id\r\n"
			+ "	left join filial on filial.id = promocaofilial.idfilial\r\n"
			+ "WHERE \r\n"
			+ " filial.id = ?2 and promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0' \r\n"
			+ " and filial.id = promocaofilial.idfilial or promocaofilial.idfilial is null and promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0' "
	
			
	 */
	
	
	/*
	 * "SELECT distinct produto.id, produto.nome, produto.ean, produto.idunidademedida, produto.idhierarquia, produto.idfamilia,produto.imagem,produto.inativo, promocaoproduto.codigo, promocaoproduto.valor  from produto "
			+ " full join promocaoproduto "
			+ " on promocaoproduto.idproduto = produto.id"
			+ " full join promocao on promocao.id = promocaoproduto.idpromocao "
			+ " full join familiaproduto on familiaproduto.id = produto.idfamilia "
			+ " full join promocaofamilia on promocaofamilia.idfamilia = familiaproduto.id "
			
			+ " where promocao.datainicial <= ?1 and promocao.datafinal >= ?1  and promocao.inativo = '0'"
	 * 
	 */

}


