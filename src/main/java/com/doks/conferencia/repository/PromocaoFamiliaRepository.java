package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PromocaoFamilia;

public interface PromocaoFamiliaRepository extends JpaRepository<PromocaoFamilia, Integer> {
	
	
	
	
	
	@Query(value = 	 "SELECT\r\n"
			
			
			+ " promocaofamilia.id as id,"
			+ " promocaofamilia.idfamilia as idfamilia, "
		    + " promocaofamilia.codigopromocao as codigopromocao,"
		    + " promocaofamilia.idpromocao as idpromocao,"
			+ "	hierarquia.nome AS grupo, \r\n"
	
			+ "	promocaofamilia.valor, \r\n"
			
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
	 
			
		
			,
			nativeQuery = true) 

	List<PromocaoFamilia> findByVencimentoBetween( LocalDate data, Integer idFilial);

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


}


