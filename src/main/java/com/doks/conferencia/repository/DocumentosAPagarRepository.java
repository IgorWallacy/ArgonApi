package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.DocumentosAPagar;

public interface DocumentosAPagarRepository extends JpaRepository<DocumentosAPagar, Integer> {

	@Query(value = "SELECT\r\n"
			+ "	t1.ID AS id,\r\n"
			+ "	t1.documento AS documento,\r\n"
			+ "	t1.historico AS observacao,\r\n"
			
			+ "	t1.idfilial AS idfilial,\r\n"
			+ "	t4.descricao AS tipodocumento,\r\n"
			
			+ "	t5.codigo AS codigoentidade,\r\n"
			+ "	t5.nome AS nomeentidade,\r\n"
			
			
		
			
		
		
		
		
		
		
		
		
			+ "	t1.valor AS valor,\r\n"
			+ "	t1.saldo AS saldo,\r\n"
			+ "	t1.status AS status\r\n"
		
		
		
	
		
		
			+ "FROM\r\n"
			+ "	financeiro t1\r\n"
			+ "	LEFT JOIN banco t2 ON ( t2.ID = t1.idbanco )\r\n"
			+ "	LEFT JOIN filial t3 ON ( t3.ID = t1.idfilial )\r\n"
			+ "	LEFT JOIN tipodocumentofinanceiro t4 ON ( t4.ID = t1.idtipodocumentofinanceiro )\r\n"
			+ "	LEFT JOIN entidade t5 ON ( t5.ID = t1.identidade )\r\n"
			+ "	LEFT JOIN rota t6 ON ( t6.ID = t5.idrota )\r\n"
			+ "	LEFT JOIN notafiscal t7 ON ( t7.ID = t1.idorigem )\r\n"
			+ "	LEFT JOIN entidade t8 ON ( t8.ID = t7.idrepresentante )\r\n"
			+ "	LEFT JOIN entidade t9 ON ( t9.ID = t1.idrepresentante ) \r\n"
			+ "WHERE\r\n"
			+ "	t1.tipo = 'P' \r\n"
			+ "	AND t1.vencimento >= CURRENT_DATE \r\n"
			+ "	AND t1.vencimento <= CURRENT_DATE \r\n"
		//	+ "	AND t1.idfilial IN ( 1 ) \r\n"
			+ "ORDER BY\r\n"
			+ "	t1.documento" , nativeQuery = true)
			List<DocumentosAPagar> buscarVencimentoHoje();

	@Query(value = "SELECT\r\n"
			+ "	t1.ID AS id,\r\n"
			+ "	t1.documento AS documento,\r\n"
			+ "	t1.historico AS observacao,\r\n"
			
			+ "	t1.idfilial AS idfilial,\r\n"
			+ "	t4.descricao AS tipodocumento,\r\n"
			
			+ "	t5.codigo AS codigoentidade,\r\n"
			+ "	t5.nome AS nomeentidade,\r\n"
			
			
		
			
		
		
		
		
		
		
		
		
			+ "	t1.valor AS valor,\r\n"
			+ "	t1.saldo AS saldo,\r\n"
			+ "	t1.status AS status\r\n"
		
		
		
	
		
		
			+ "FROM\r\n"
			+ "	financeiro t1\r\n"
			+ "	LEFT JOIN banco t2 ON ( t2.ID = t1.idbanco )\r\n"
			+ "	LEFT JOIN filial t3 ON ( t3.ID = t1.idfilial )\r\n"
			+ "	LEFT JOIN tipodocumentofinanceiro t4 ON ( t4.ID = t1.idtipodocumentofinanceiro )\r\n"
			+ "	LEFT JOIN entidade t5 ON ( t5.ID = t1.identidade )\r\n"
			+ "	LEFT JOIN rota t6 ON ( t6.ID = t5.idrota )\r\n"
			+ "	LEFT JOIN notafiscal t7 ON ( t7.ID = t1.idorigem )\r\n"
			+ "	LEFT JOIN entidade t8 ON ( t8.ID = t7.idrepresentante )\r\n"
			+ "	LEFT JOIN entidade t9 ON ( t9.ID = t1.idrepresentante ) \r\n"
			+ "WHERE\r\n"
			+ "	t1.tipo = 'P' \r\n"
			+ "	AND t1.vencimento >= CURRENT_DATE \r\n"
			+ "	AND t1.vencimento <= CURRENT_DATE \r\n"
			+ "	AND t1.idfilial IN ( ?1 ) \r\n"
			+ "ORDER BY\r\n"
			+ "	t1.documento" , nativeQuery = true)
	        List<DocumentosAPagar> buscarVencimentoHoje(Integer filial);
	
	

}
