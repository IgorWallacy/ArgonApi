package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.TabelaPrecoDisplay;

public interface TabelaPrecoDisplayRepository extends JpaRepository<TabelaPrecoDisplay, Integer> {

	@Query(value = " select produto.id as id , produto.codigo as codigo , produto.nome as produto , formacaoprecoproduto.preco as preco ,"
			+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao left join promocaofilial on (promocaofilial.idpromocao=pr.id) where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE and  ( promocaofilial.idfilial IS NULL OR promocaofilial.idfilial=?2 ) limit 1) as precopromocao, "
			+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao left join promocaofilial on (promocaofilial.idpromocao=pr.id) where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE and  ( promocaofilial.idfilial IS NULL OR promocaofilial.idfilial=?2 )  limit 1) as precopromocaofamilia "
			+ " from formacaoprecoproduto left join produto on (produto.id = formacaoprecoproduto.idproduto) where produto.idtabelaprecopainel=?1 and formacaoprecoproduto.idfilial=?2 and produto.inativo='0' order by produto.codigo asc " ,nativeQuery = true)
	List<TabelaPrecoDisplay> porTabela(Integer id , Integer filial);

	
	@Query(value = " select produto.id as id , produto.codigo as codigo , produto.nome as produto , produto.preco as preco ,"
			+ " (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao left join promocaofilial on (promocaofilial.idpromocao=pr.id) where pp.idproduto=produto.id and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE  limit 1) as precopromocao, "
			+ " (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao left join promocaofilial on (promocaofilial.idpromocao=pr.id) where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocaofamilia "
			+ " from  produto  where produto.idtabelaprecopainel=?1 and produto.inativo='0' order by produto.codigo asc" ,nativeQuery = true)
	
	List<TabelaPrecoDisplay> porTabelaSingle(Integer id);

}
