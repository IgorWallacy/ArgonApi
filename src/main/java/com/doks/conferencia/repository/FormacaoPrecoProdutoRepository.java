package com.doks.conferencia.repository;

import java.time.LocalDateTime;
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
			"   produto.doks_meta ,"+
			"   produto.idfamilia ,"+
			"	formacaoprecoproduto.preco AS preco,\n" +
			"	formacaoprecoproduto.precocusto AS precocusto,\n" +
			"   formacaoprecoproduto.dataultimacompra AS dataultimacompra,\n" +
			"   formacaoprecoproduto.custoalteradopor AS custoalteradopor,\n" +
			"   formacaoprecoproduto.dataprecocusto AS dataprecocusto,\n" +
			"   formacaoprecoproduto.dataalteracaopreco AS dataalteracaopreco,\n" +
			"	unidademedida.nome as unidade,\n" +
			"	formacaoprecoproduto.doks_preco_agendado , \r\n"+
		    "   formacaoprecoproduto.doks_data_agendada , "+
			"   formacaoprecoproduto.doks_usuario_nome_agendado , "+
			"	t7.nome AS hierarquia,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS hierarquiaII,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS hierarquiaIII,\n" +

			"	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque, \n"+
			
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao  where pp.idproduto=produto.id  and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, " +
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao join promocaofilial pf on pf.idpromocao=pr.id where pp.idproduto=produto.id and pf.idfilial=formacaoprecoproduto.idfilial and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocionalfilial, " +
			" (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia "+
			
			"FROM\n" +
			"	formacaoprecoproduto\n" +
			"	INNER JOIN produto ON formacaoprecoproduto.idproduto = produto.id\n" +
			"	INNER JOIN filial ON filial.ID = formacaoprecoproduto.idfilial\n" +
			"	INNER JOIN hierarquia t7 ON (t7.ID = produto.idhierarquia) \n" +
			"  INNER JOIN unidademedida on unidademedida.id = produto.idunidademedida	\n" +
			"	\n" +
			"WHERE\n" +
			"	produto.inativo = '0' \n" +
			" and produto.id=?1 "+
			"ORDER BY\n" +
			"	produto.nome ASC\n" +
			"	", nativeQuery = true)
	List<FormacaoPrecoProduto> porId(Integer id);
	
	
	@Query(value = "SELECT \r\n"
			+ "				formacaoprecoproduto.ID AS ID, \r\n"
			+ "			CONCAT(produto.codigo,' - ', produto.nome) AS produto, \r\n"
			+ "			   produto.id as idproduto,  \r\n"
			+ "			   produto.ean as ean,  \r\n"
			+ "			   produto.codigo as codigo,  \r\n"
			+ "            produto.percentualmarkupminimo as percentualmarkup,"
			+ "			CONCAT(filial.codigo, ' - ', filial.nome) AS filial, \r\n"
			+ "            produto.idfamilia,"
			+ "			   filial.id as idfilial,  \r\n"
			+ "            produto.doks_meta ,"
			+ "				formacaoprecoproduto.preco AS preco, \r\n"
			+ "				formacaoprecoproduto.precocusto AS precocusto, \r\n"
			+ "			   formacaoprecoproduto.dataultimacompra AS dataultimacompra, \r\n"
			+ "			   formacaoprecoproduto.custoalteradopor AS custoalteradopor, \r\n"
			+ "			   formacaoprecoproduto.dataprecocusto AS dataprecocusto, \r\n"
			+ "			   formacaoprecoproduto.dataalteracaopreco AS dataalteracaopreco, \r\n"
			+ "	           formacaoprecoproduto.doks_preco_agendado , \r\n"
		    + "            formacaoprecoproduto.doks_data_agendada , "
			+ "            formacaoprecoproduto.doks_usuario_nome_agendado, "
			+ "				unidademedida.nome as unidade, \r\n"
			+ "				t7.nome AS hierarquia, \r\n"
			+ "	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS hierarquiaII,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS hierarquiaIII,\n" 
			+ "\r\n"
			+ "	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque, \n"
			+ "			\r\n"
			+ "			 (0) as precopromocional,  \r\n"
			+ "			 (0) as precopromocionalfilial,  \r\n"
			+ "			 (0) as precopromocionalfamilia \r\n"
			+ "			\r\n"
			+ "			FROM \r\n"
			+ "				formacaoprecoproduto \r\n"
			+ "				INNER JOIN produto ON formacaoprecoproduto.idproduto = produto.id \r\n"
			+ "				INNER JOIN filial ON filial.ID = formacaoprecoproduto.idfilial \r\n"
			+ "				INNER JOIN hierarquia t7 ON (t7.ID = produto.idhierarquia) \r\n"
			+ "			  INNER JOIN unidademedida on unidademedida.id = produto.idunidademedida	 \r\n"
			+ "				 \r\n"
			+ "			WHERE \r\n"
			+ "				produto.inativo = '0'  \r\n"
			+ "			\r\n"
			+ "			ORDER BY \r\n"
			+ "				produto.nome ASC " , nativeQuery = true)
	List<FormacaoPrecoProduto> todos ();


	@Query(value = "SELECT\n" +
			"	formacaoprecoproduto.ID AS ID,\n" +
			"	produto.nome AS produto,\n" +
			"   produto.id as idproduto, " +
			"   produto.ean as ean, " +
			"   produto.codigo as codigo, " +
			"   produto.percentualmarkupminimo as percentualmarkup,"+
			"   produto.idfamilia ,"+
			"	filial.nome AS filial,\n" +
			"   filial.id as idfilial, " +
			"   produto.doks_meta ,"+
			"	formacaoprecoproduto.preco AS preco,\n" +
			"	formacaoprecoproduto.precocusto AS precocusto,\n" +
			"   formacaoprecoproduto.dataultimacompra AS dataultimacompra,\n" +
			"   formacaoprecoproduto.custoalteradopor AS custoalteradopor,\n" +
			"   formacaoprecoproduto.dataprecocusto AS dataprecocusto,\n" +
			"   formacaoprecoproduto.dataalteracaopreco AS dataalteracaopreco,\n" +
			"	formacaoprecoproduto.doks_preco_agendado , \r\n"+
		    "   formacaoprecoproduto.doks_data_agendada , "+
			"   formacaoprecoproduto.doks_usuario_nome_agendado , "+
			"	unidademedida.nome as unidade,\n" +
			"	t7.nome AS hierarquia,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS hierarquiaII,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS hierarquiaIII,\n" +

			"	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque, \n"+
			
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao  where pp.idproduto=produto.id  and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, " +
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao join promocaofilial pf on pf.idpromocao=pr.id where pp.idproduto=produto.id and pf.idfilial=formacaoprecoproduto.idfilial and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocionalfilial, " +
			" (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia "+
			
			"FROM\n" +
			"	formacaoprecoproduto\n" +
			"	LEFT JOIN produto ON formacaoprecoproduto.idproduto = produto.id\n" +
			"	LEFT JOIN filial ON filial.ID = formacaoprecoproduto.idfilial\n" +
			"	LEFT JOIN hierarquia t7 ON (t7.ID = produto.idhierarquia) \n" +
			"  LEFT JOIN unidademedida on unidademedida.id = produto.idunidademedida	\n" +
			"	\n" +
			"WHERE\n" +
			"	produto.inativo = '0' \n" +
			" and produto.codigo=?1 "+
			"ORDER BY\n" +
			"	produto.nome ASC\n" +
			"	", nativeQuery = true)
	List<FormacaoPrecoProduto> porCodigo(String codigo);

	

	@Query(value = "SELECT\n" +
			"	formacaoprecoproduto.ID AS ID,\n" +
			"	produto.nome AS produto,\n" +
			"   produto.id as idproduto, " +
			"   produto.ean as ean, " +
			"   produto.codigo as codigo, " +
			"   produto.percentualmarkupminimo as percentualmarkup,"+
			"   produto.idfamilia ,"+
			"	filial.nome AS filial,\n" +
			"   filial.id as idfilial, " +
			"   produto.doks_meta ,"+
			"	formacaoprecoproduto.preco AS preco,\n" +
			"	formacaoprecoproduto.precocusto AS precocusto,\n" +
			"   formacaoprecoproduto.dataultimacompra AS dataultimacompra,\n" +
			"   formacaoprecoproduto.custoalteradopor AS custoalteradopor,\n" +
			"   formacaoprecoproduto.dataprecocusto AS dataprecocusto,\n" +
			"   formacaoprecoproduto.dataalteracaopreco AS dataalteracaopreco,\n" +
			"	formacaoprecoproduto.doks_preco_agendado , \r\n"+
		    "   formacaoprecoproduto.doks_data_agendada , "+
			"   formacaoprecoproduto.doks_usuario_nome_agendado , "+
			"	unidademedida.nome as unidade,\n" +
			"	t7.nome AS hierarquia,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS hierarquiaII,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS hierarquiaIII,\n" +

			"	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque, \n"+
			
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao  where pp.idproduto=produto.id  and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, " +
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao join promocaofilial pf on pf.idpromocao=pr.id where pp.idproduto=produto.id and pf.idfilial=formacaoprecoproduto.idfilial and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocionalfilial, " +
			" (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia "+
			
			"FROM\n" +
			"	formacaoprecoproduto\n" +
			"	INNER JOIN produto ON formacaoprecoproduto.idproduto = produto.id\n" +
			"	INNER JOIN filial ON filial.ID = formacaoprecoproduto.idfilial\n" +
			"	INNER JOIN hierarquia t7 ON (t7.ID = produto.idhierarquia) \n" +
			"  INNER JOIN unidademedida on unidademedida.id = produto.idunidademedida	\n" +
			"	\n" +
			"WHERE\n" +
			"	produto.inativo = '0' \n" +
			" and formacaoprecoproduto.doks_data_agendada BETWEEN ?1 AND ?2 "+
			" ORDER BY\n" +
			"	produto.nome ASC\n" +
			"	", nativeQuery = true)
	List<FormacaoPrecoProduto> buscarTodosPrecificarProdutoComFilial(LocalDateTime data1, LocalDateTime data2);


	@Query(value = "SELECT\n" +
			"	formacaoprecoproduto.ID AS ID,\n" +
			"	produto.nome AS produto,\n" +
			"   produto.id as idproduto, " +
			"   produto.ean as ean, " +
			"   produto.codigo as codigo, " +
			"   produto.percentualmarkupminimo as percentualmarkup,"+
			"   produto.idfamilia ,"+
			"	filial.nome AS filial,\n" +
			"   filial.id as idfilial, " +
			"   produto.doks_meta ,"+
			"	formacaoprecoproduto.preco AS preco,\n" +
			"	formacaoprecoproduto.precocusto AS precocusto,\n" +
			"   formacaoprecoproduto.dataultimacompra AS dataultimacompra,\n" +
			"   formacaoprecoproduto.custoalteradopor AS custoalteradopor,\n" +
			"   formacaoprecoproduto.dataprecocusto AS dataprecocusto,\n" +
			"   formacaoprecoproduto.dataalteracaopreco AS dataalteracaopreco,\n" +
			"	formacaoprecoproduto.doks_preco_agendado , \r\n"+
		    "   formacaoprecoproduto.doks_data_agendada , "+
			"   formacaoprecoproduto.doks_usuario_nome_agendado , "+
			"	unidademedida.nome as unidade,\n" +
			"	t7.nome AS hierarquia,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 0 FOR 7 ) ) AS hierarquiaII,\n" +
			"	( SELECT nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( t7.codigo FROM 1 FOR 12 ) ) AS hierarquiaIII,\n" +

			"	( SELECT quantidade FROM total_estoque_view WHERE total_estoque_view.idfilial = formacaoprecoproduto.idfilial AND total_estoque_view.idproduto = formacaoprecoproduto.idproduto ) AS estoque, \n"+
			
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao  where pp.idproduto=produto.id  and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocional, " +
			" (select pp.valor from promocao pr join promocaoproduto pp on pr.id=pp.idpromocao join promocaofilial pf on pf.idpromocao=pr.id where pp.idproduto=produto.id and pf.idfilial=formacaoprecoproduto.idfilial and pr.datainicial <= CURRENT_DATE and pr.datafinal>= CURRENT_DATE limit 1) as precopromocionalfilial, " +
			" (select pf.valor from promocao pr join promocaofamilia pf on pr.id=pf.idpromocao where pf.idfamilia=produto.idfamilia and pr.datainicial<=CURRENT_DATE and pr.datafinal>=CURRENT_DATE limit 1) as precopromocionalfamilia "+
			
			"FROM\n" +
			"	formacaoprecoproduto\n" +
			"	INNER JOIN produto ON formacaoprecoproduto.idproduto = produto.id\n" +
			"	INNER JOIN filial ON filial.ID = formacaoprecoproduto.idfilial\n" +
			"	INNER JOIN hierarquia t7 ON (t7.ID = produto.idhierarquia) \n" +
			"  INNER JOIN unidademedida on unidademedida.id = produto.idunidademedida	\n" +
			"	\n" +
			"WHERE\n" +
			"	produto.inativo = '0' \n" +
			" and formacaoprecoproduto.idfilial =?3 "+
			" and formacaoprecoproduto.doks_data_agendada BETWEEN ?1 AND ?2 "+
			" ORDER BY\n" +
			"	produto.nome ASC\n" +
			"	", nativeQuery = true)
	List<FormacaoPrecoProduto> buscarTodosPorFilialPrecificar(LocalDateTime data1, LocalDateTime data2, Integer filial);

}
