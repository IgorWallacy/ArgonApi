package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.NotaFiscalConfFisica;

public interface NotaFiscalConfFisicaRepository extends JpaRepository<NotaFiscalConfFisica, Integer> {

	
	@Query(value = "select * from NotaFiscalConfFisica where idnotafiscal = ?1 " , nativeQuery = true )
	List<NotaFiscalConfFisica> findBynotafiscal(Integer id);

}
