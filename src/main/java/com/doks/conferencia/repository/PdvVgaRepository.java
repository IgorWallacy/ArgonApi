package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.PddVga;


public interface PdvVgaRepository extends JpaRepository<PddVga, Integer> {
	
	@Query(value = " select pdv from operacao where filial = ?1 and data between ?2 and ?3 group by pdv order by pdv", nativeQuery = true)
	List<PddVga> findPdv(String filial ,LocalDate dataI, LocalDate dataF);

}
