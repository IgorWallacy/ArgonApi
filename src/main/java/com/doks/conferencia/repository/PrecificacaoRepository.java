package com.doks.conferencia.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PrecificacaoItem;

public interface PrecificacaoRepository extends JpaRepository<PrecificacaoItem, Integer> {
	
	// Buscar todas as notas de empresa com 1 filial no banco
	   @Query(value =  "SELECT DISTINCT\r\n"
			+ " notafiscalitem.id,"
			+ "	notafiscal.id as idnotafiscal, \r\n"   
			+ "	notafiscal.idfilial, \r\n"
			+ "	notafiscal.numeronotafiscal, \r\n"
			+ "	notafiscal.razaosocial, \r\n"
			+ " notafiscal.datainclusao as entradasaida, "
			+ " notafiscal.doks_precificado as doks_precificado, "
			+ "	notafiscalitem.precounitario, \r\n"
			+ "	notafiscalitem.descricao, \r\n"
			+ "	( ( notafiscalitem.custototal / ( notafiscalitem.quantidade * notafiscalitem.embalagem)) )  as precocusto, \r\n"
			+ " produto.preco as precoatual,"
			+ "	notafiscalitem.doks_preco_agendado as precoagendado, \r\n"
			+ " notafiscalitem.doks_data_agendada as dataagendada, "
			+ " notafiscalitem.doks_usuario_nome_agendado as usuario_agendado, "
			+ " produto.ean as ean, "
			+ " produto.idfamilia as idfamilia,"
			+ " produto.percentualmarkupminimo as percentualmarkup,"
			+ " produto.lucrobrutominimo as percentualmarkdown,"
			+ " produto.codigo as codigo, "
			+ " produto.id as idproduto, "
			+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, "
			+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia, "
			+ " filial.nome  "
			+ " FROM\r\n"
			+ "	notafiscal\r\n"
			+ "	INNER JOIN\r\n"
			+ "	notafiscalitem\r\n"
			+ "	ON \r\n"
			+ "		notafiscal.\"id\" = notafiscalitem.idnotafiscal\r\n"
			+ "	INNER JOIN\r\n"
			+ "	produto\r\n"
			+ "	ON \r\n"
			+ "		notafiscalitem.idproduto = produto.\"id\"\r\n"
			+ " INNER JOIN filial on notafiscal.idfilial = filial.id "
			+ " WHERE\r\n"
			+ "	notafiscal.datainclusao BETWEEN ?1 AND ?2 \r\n"
			+ " and notafiscal.tipodocumento= 'E' "
			+ "ORDER BY\r\n"
			+ "	notafiscal.razaosocial ASC" ,nativeQuery =  true)
			List<PrecificacaoItem> buscarTodosAgendar(LocalDateTime dataInicial , LocalDateTime dataFinal);
	   
	   
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   //////////////////////////////////////////////////// DAQUI PRA BAIXO MULT EMPRESA
	   
	//Buscar as notas de empresas separado por filial
	// Buscar todas as notas de empresa com 2 filiais ou mais no banco
		 @Query(value =  "SELECT distinct \r\n"
					+ " notafiscalitem.id,"
					+ "	notafiscal.idfilial, \r\n"
					+ "	notafiscal.numeronotafiscal, \r\n"
					+ "	notafiscal.id as idnotafiscal, \r\n"
					+ "	notafiscal.razaosocial, \r\n"
					+ " notafiscal.datainclusao as entradasaida, "
					+ " notafiscal.doks_precificado as doks_precificado, "
					+ "	notafiscalitem.precounitario, \r\n"
					+ "	notafiscalitem.descricao, \r\n"
					+ "	( ( notafiscalitem.custototal / ( notafiscalitem.quantidade * notafiscalitem.embalagem)) )  as precocusto, \r\n"
					+ "	formacaoprecoproduto.preco as precoatual, \r\n"
					+ " notafiscalitem.doks_data_agendada as dataagendada, "
					+ " notafiscalitem.doks_preco_agendado as precoagendado, "
					+ " notafiscalitem.doks_usuario_nome_agendado as usuario_agendado, "
					+ " produto.ean as ean, "
					+ " produto.idfamilia as idfamilia,"
					+ " produto.percentualmarkupminimo as percentualmarkup,"
					+ " produto.lucrobrutominimo as percentualmarkdown,"
					+ " produto.codigo as codigo, "
					+ " produto.id as idproduto, "
					+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, "
					+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia, "
					+ " filial.nome "
					+ " FROM\r\n"
					+ "	notafiscal\r\n"
					+ "	INNER JOIN\r\n"
					+ "	notafiscalitem\r\n"
					+ "	ON \r\n"
					+ "		notafiscal.\"id\" = notafiscalitem.idnotafiscal\r\n"
					+ "	INNER JOIN\r\n"
					+ "	produto\r\n"
					+ "	ON \r\n"
					+ "		notafiscalitem.idproduto = produto.\"id\"\r\n"
					+ " INNER JOIN filial on notafiscal.idfilial = filial.id "
					+ " INNER JOIN formacaoprecoproduto on formacaoprecoproduto.idproduto = notafiscalitem.idproduto "
					
					
					+ " WHERE\r\n"
					+ "	notafiscal.datainclusao BETWEEN ?1 AND ?2 \r\n"
					+ " and notafiscal.idfilial = ?3 "
					+ " and notafiscal.tipodocumento= 'E' "
					+ " and formacaoprecoproduto.idfilial = filial.id "
					
					+ "ORDER BY\r\n"
					+ "	notafiscal.razaosocial ASC" ,nativeQuery =  true)
			List<PrecificacaoItem> buscarTodosPorFilial(LocalDateTime data1, LocalDateTime data2, Integer filial);
	
	
	// Buscar todas as notas de empresa com 2 filiais ou mais no banco
	 @Query(value =  "SELECT distinct \r\n"
				+ " notafiscalitem.id,"
				+ "	notafiscal.idfilial, \r\n"
				+ "	notafiscal.numeronotafiscal, \r\n"
				+ "	notafiscal.id as idnotafiscal, \r\n"
				+ "	notafiscal.razaosocial, \r\n"
				+ " notafiscal.datainclusao as entradasaida, "
				+ " notafiscal.doks_precificado as doks_precificado, "
				+ "	notafiscalitem.precounitario, \r\n"
				+ "	notafiscalitem.descricao, \r\n"
				+ "	( ( notafiscalitem.custototal / ( notafiscalitem.quantidade * notafiscalitem.embalagem)) )  as precocusto, \r\n"
				+ "	formacaoprecoproduto.preco as precoatual, \r\n"
				+ " notafiscalitem.doks_data_agendada as dataagendada, "
				+ " notafiscalitem.doks_preco_agendado as precoagendado, "
				+ " notafiscalitem.doks_usuario_nome_agendado as usuario_agendado, "
				+ " produto.ean as ean, "
				+ " produto.idfamilia as idfamilia,"
				+ " produto.percentualmarkupminimo as percentualmarkup,"
				+ " produto.lucrobrutominimo as percentualmarkdown,"
				+ " produto.codigo as codigo, "
				+ " produto.id as idproduto, "
				+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, "
				+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia, "
				+ " filial.nome "
				+ " FROM\r\n"
				+ "	notafiscal\r\n"
				+ "	INNER JOIN\r\n"
				+ "	notafiscalitem\r\n"
				+ "	ON \r\n"
				+ "		notafiscal.\"id\" = notafiscalitem.idnotafiscal\r\n"
				+ "	INNER JOIN\r\n"
				+ "	produto\r\n"
				+ "	ON \r\n"
				+ "		notafiscalitem.idproduto = produto.\"id\"\r\n"
				+ " INNER JOIN filial on notafiscal.idfilial = filial.id "
				+ " INNER JOIN formacaoprecoproduto on formacaoprecoproduto.idproduto = notafiscalitem.idproduto "
				
				
				+ " WHERE\r\n"
				+ "	notafiscal.datainclusao BETWEEN ?1 AND ?2 \r\n"
				+ " and notafiscal.tipodocumento= 'E' "
				+ " and formacaoprecoproduto.idfilial = filial.id "
				+ "ORDER BY\r\n"
				+ "	notafiscal.razaosocial ASC" ,nativeQuery =  true)
			List<PrecificacaoItem> buscarTodosComFilialAgendar(LocalDateTime dataInicial , LocalDateTime dataFinal, Integer filial);
	 
	 
	 
	 
	 /************************************* BUSCAR AGENDAMENTO DE PREÇOS */
	
	 
	 
	// Buscar todas as notas de empresa com 2 filiais ou mais no banco
		 @Query(value =  "SELECT distinct \r\n"
					+ " notafiscalitem.id,"
					+ "	notafiscal.idfilial, \r\n"
					+ "	notafiscal.numeronotafiscal, \r\n"
					+ "	notafiscal.id as idnotafiscal, \r\n"
					+ "	notafiscal.razaosocial, \r\n"
					+ " notafiscal.datainclusao as entradasaida, "
					+ " notafiscal.doks_precificado as doks_precificado, "
					+ "	notafiscalitem.precounitario, \r\n"
					+ "	notafiscalitem.descricao, \r\n"
					+ "	( ( notafiscalitem.custototal / ( notafiscalitem.quantidade * notafiscalitem.embalagem)) )  as precocusto, \r\n"
					+ "	formacaoprecoproduto.preco as precoatual, \r\n"
					+ " notafiscalitem.doks_data_agendada as dataagendada, "
					+ " notafiscalitem.doks_preco_agendado as precoagendado, "
					+ " notafiscalitem.doks_usuario_nome_agendado as usuario_agendado,"
					+ " produto.ean as ean, "
					+ " produto.idfamilia as idfamilia,"
					+ " produto.percentualmarkupminimo as percentualmarkup,"
					+ " produto.lucrobrutominimo as percentualmarkdown,"
					+ " produto.codigo as codigo, "
					+ " produto.id as idproduto, "
					+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, "
					+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia, "
					+ " filial.nome "
					+ " FROM\r\n"
					+ "	notafiscal\r\n"
					+ "	INNER JOIN\r\n"
					+ "	notafiscalitem\r\n"
					+ "	ON \r\n"
					+ "		notafiscal.\"id\" = notafiscalitem.idnotafiscal\r\n"
					+ "	INNER JOIN\r\n"
					+ "	produto\r\n"
					+ "	ON \r\n"
					+ "		notafiscalitem.idproduto = produto.\"id\"\r\n"
					+ " INNER JOIN filial on notafiscal.idfilial = filial.id "
					+ " INNER JOIN formacaoprecoproduto on formacaoprecoproduto.idproduto = notafiscalitem.idproduto "
					
					
					+ " WHERE\r\n"
					+ "	notafiscalitem.doks_data_agendada BETWEEN ?1 AND ?2 \r\n"
					+ " and notafiscal.tipodocumento= 'E' "
					+ " and formacaoprecoproduto.idfilial = filial.id "
					+ "ORDER BY\r\n"
					+ "	notafiscal.razaosocial ASC" ,nativeQuery =  true)
		 		List<PrecificacaoItem> buscarTodosPrecificarComFilial(LocalDateTime data1, LocalDateTime data2);
	 
	 
	 		
		 
		// Buscar todas as notas de empresa com 1 filial no banco
		   @Query(value =  "SELECT DISTINCT\r\n"
				+ " notafiscalitem.id,"
				+ "	notafiscal.id as idnotafiscal, \r\n"   
				+ "	notafiscal.idfilial, \r\n"
				+ "	notafiscal.numeronotafiscal, \r\n"
				+ "	notafiscal.razaosocial, \r\n"
				+ " notafiscal.datainclusao as entradasaida, "
				+ " notafiscal.doks_precificado as doks_precificado, "
				+ "	notafiscalitem.precounitario, \r\n"
				+ "	notafiscalitem.descricao, \r\n"
				+ "	( ( notafiscalitem.custototal / ( notafiscalitem.quantidade * notafiscalitem.embalagem)) )  as precocusto, \r\n"
				+ " produto.preco as precoatual,"
				+ "	notafiscalitem.doks_preco_agendado as precoagendado, \r\n"
				+ " notafiscalitem.doks_data_agendada as dataagendada, "
				+ " notafiscalitem.doks_usuario_nome_agendado as usuario_agendado,"
				+ " produto.ean as ean, "
				+ " produto.idfamilia as idfamilia,"
				+ " produto.percentualmarkupminimo as percentualmarkup,"
				+ " produto.lucrobrutominimo as percentualmarkdown,"
				+ " produto.codigo as codigo, "
				+ " produto.id as idproduto, "
				+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, "
				+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia, "
				+ " filial.nome "
				+ " FROM\r\n"
				+ "	notafiscal\r\n"
				+ "	INNER JOIN\r\n"
				+ "	notafiscalitem\r\n"
				+ "	ON \r\n"
				+ "		notafiscal.\"id\" = notafiscalitem.idnotafiscal\r\n"
				+ "	INNER JOIN\r\n"
				+ "	produto\r\n"
				+ "	ON \r\n"
				+ "		notafiscalitem.idproduto = produto.\"id\"\r\n"
				+ " INNER JOIN filial on notafiscal.idfilial = filial.id "
				+ " WHERE\r\n"
				+ "	notafiscalitem.doks_data_agendada BETWEEN ?1 AND ?2  \r\n"
			
				+ " and notafiscal.tipodocumento= 'E' "
				+ "ORDER BY\r\n"
				+ "	notafiscal.razaosocial ASC" ,nativeQuery =  true)
				List<PrecificacaoItem> buscarTodosPrecificar(LocalDateTime dataInicial , LocalDateTime dataFinal);
		   
		       
		       
		    	
		   @Query(value =  "SELECT distinct \r\n"
					+ " notafiscalitem.id,"
					+ "	notafiscal.idfilial, \r\n"
					+ "	notafiscal.numeronotafiscal, \r\n"
					+ "	notafiscal.id as idnotafiscal, \r\n"
					+ "	notafiscal.razaosocial, \r\n"
					+ " notafiscal.datainclusao as entradasaida, "
					+ " notafiscal.doks_precificado as doks_precificado, "
					+ "	notafiscalitem.precounitario, \r\n"
					+ "	notafiscalitem.descricao, \r\n"
					+ "	( ( notafiscalitem.custototal / ( notafiscalitem.quantidade * notafiscalitem.embalagem)) )  as precocusto, \r\n"
					+ "	formacaoprecoproduto.preco as precoatual, \r\n"
					+ " notafiscalitem.doks_data_agendada as dataagendada, "
					+ " notafiscalitem.doks_preco_agendado as precoagendado, "
					+ " notafiscalitem.doks_usuario_nome_agendado as usuario_agendado,"
					+ " produto.ean as ean, "
					+ " produto.idfamilia as idfamilia,"
					+ " produto.percentualmarkupminimo as percentualmarkup,"
					+ " produto.lucrobrutominimo as percentualmarkdown,"
					+ " produto.codigo as codigo, "
					+ " produto.id as idproduto, "
					+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, "
					+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia, "
					+ " filial.nome "
					+ " FROM\r\n"
					+ "	notafiscal\r\n"
					+ "	INNER JOIN\r\n"
					+ "	notafiscalitem\r\n"
					+ "	ON \r\n"
					+ "		notafiscal.\"id\" = notafiscalitem.idnotafiscal\r\n"
					+ "	INNER JOIN\r\n"
					+ "	produto\r\n"
					+ "	ON \r\n"
					+ "		notafiscalitem.idproduto = produto.\"id\"\r\n"
					+ " INNER JOIN filial on notafiscal.idfilial = filial.id "
					+ " INNER JOIN formacaoprecoproduto on formacaoprecoproduto.idproduto = notafiscalitem.idproduto "
					
					
					+ " WHERE\r\n"
					+ "	notafiscalitem.doks_data_agendada BETWEEN ?1 AND ?2 \r\n"
					+ " and notafiscal.idfilial = ?3 "
					+ " and notafiscal.tipodocumento= 'E' "
					+ " and formacaoprecoproduto.idfilial = filial.id "
					
					+ "ORDER BY\r\n"
					+ "	notafiscal.razaosocial ASC" ,nativeQuery =  true)
			List<PrecificacaoItem> buscarTodosPorFilialPrecificar(LocalDateTime data1, LocalDateTime data2, Integer filial);



		   
		  
	

}
