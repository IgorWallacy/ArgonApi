package com.doks.conferencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Filial;

public interface FilialRepository extends JpaRepository<Filial, Integer> {

	@Query( value = "select id,codigo,nome,razaosocial, logofilial from filial where id=?1" ,nativeQuery = true)
	Filial porId (Integer id);

}
