package com.doks.conferencia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Hierarquia;

public interface GruposRepository extends JpaRepository<Hierarquia, Integer> {

	
	@Query(value = " select h.id,h.codigo,h.nome,h.doks_meta, (( SELECT hq.nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( h.codigo FROM 0 FOR 7 ) )) as doks_grupo_pai, (( SELECT hq.nome FROM hierarquia hq WHERE hq.codigo = SUBSTRING ( h.codigo FROM 1 FOR 12 ) )) as doks_grupo_filho from hierarquia h order by nome asc", nativeQuery = true)
	List<Hierarquia> todos();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE hierarquia SET doks_meta = ?2 WHERE codigo LIKE CONCAT(?1, '%')", nativeQuery = true)
	void updateMeta(String codigo, BigDecimal meta);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE produto \r\n"
			+ "SET percentualmarkupminimo = ?2 \r\n"
			+ "WHERE idhierarquia IN (\r\n"
			+ "  SELECT id \r\n"
			+ "  FROM hierarquia \r\n"
			+ "  WHERE codigo LIKE CONCAT(?1, '%')\r\n"
			+ ")"
			, nativeQuery = true)
	void updateMarkup(String codigo, BigDecimal meta);
	
	

}
