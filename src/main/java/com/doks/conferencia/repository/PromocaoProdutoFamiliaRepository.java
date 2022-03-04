package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PromocaoProdutoFamiliaFiltro;

public interface PromocaoProdutoFamiliaRepository extends JpaRepository<PromocaoProdutoFamiliaFiltro, Integer> {
	
	@Query(value = "select promocaoproduto.id as id , promocaoproduto.idpromocao as idpromocao , produto.id as idproduto, produto.nome as nome_produto, promocaoproduto.valor as valor, promocao.datainicial as datainicial, promocao.datafinal as datafinal from produto "
			 +"left join promocaoproduto on promocaoproduto.idproduto = produto.id "     
			+" join promocao on promocaoproduto.idpromocao = promocao.id"
			    
			     + "  where promocao.id = '404' "
			     + " UNION ALL "
			     
			     +"  select promocaofamilia.id as id, promocaofamilia.idpromocao as idpromocao, produto.id , produto.nome as nome_produto, promocaofamilia.valor as valor , promocao.datainicial as datainicial, promocao.datafinal as datafinal from produto  "
			     + " join promocaofamilia.id "
		
			   
			     + " join familiaproduto on familiaproduto.id = promocaofamilia.idfamilia "
			   
			   
			     
			     + " where promocao.id = '404' " ,
			
			
			nativeQuery = true) 

	List<PromocaoProdutoFamiliaFiltro> findByVencimentoBetween( LocalDate data, Integer idFilial);

}
