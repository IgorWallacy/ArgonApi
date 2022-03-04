package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Lote;

public interface LoteRepository extends JpaRepository<Lote, Integer> {

	@Query(value = "select id,codigo,fabricacao,entrada,vencimento,idfilial,idproduto from doks_lote where idfilial =?1 and vencimento between  ?2 and ?3 ", nativeQuery = true)

	List<Lote> findByVencimentoBetween(Integer idfilial, LocalDate start, LocalDate end);
	
	@Query(value = "select id,codigo,fabricacao,entrada,vencimento,idfilial,idproduto from doks_lote where idfilial =?1 and vencimento between  ?2 and ?3 ", nativeQuery = true)
	List<Lote> porVencimentoDeAte (LocalDate dataInicial, LocalDate dataFinal);

	@Query(value = "select id,codigo,fabricacao,entrada,vencimento,idfilial,idproduto from doks_lote where idfilial = ?1", nativeQuery = true)
	List<Lote> findByIdfilial(Integer idfilial);

	//CONTAGEM DE PRODUTOS VENCENDO ESSE MES, ESSA SEMANA,HOJE 
	@Query(value = "SELECT DISTINCT \r\n"
			+ "	count(doks_conferencia_manual.\"id\") \r\n"
			
			+ "FROM\r\n"
			+ "	doks_conferencia_manual\r\n"
			+ "	INNER JOIN\r\n"
			+ "	doks_conferencia_manual_item\r\n"
			+ "	ON \r\n"
			+ "		doks_conferencia_manual.\"id\" = doks_conferencia_manual_item.idconferencia\r\n"
			+ "WHERE\r\n"
			+ "	doks_conferencia_manual.idfilial  =?1 and doks_conferencia_manual_item.data_vencimento between  ?2 and ?3 ", nativeQuery = true)
	Integer findByVencimentoBetweenContador(Integer idfilial, LocalDate dataInicial, LocalDate dataFinal);
	
}
