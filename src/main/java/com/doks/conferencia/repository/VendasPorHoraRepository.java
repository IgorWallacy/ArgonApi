package com.doks.conferencia.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.VendasPorHora;


public interface VendasPorHoraRepository extends JpaRepository<VendasPorHora, Integer> {

	@Query(value=" SELECT \n" +
			"    t1.id AS iddocumento,\n" +
			"    t1.horainicial  AS hora_inicial,\n" +
			"    t1.valorliquido AS valorliquido,\n" +
			"    filial.nome AS filial\n" +
			"FROM \n" +
			"    operacao t1\n" +
			"LEFT JOIN \n" +
			"    filial ON filial.codigo = t1.filial\n" +
			"WHERE \n" +
			"    t1.cancelado = 0\n" +
			"    AND COALESCE(t1.statusnfce, '0') IN (0, 1, 13, 5, 6)\n" +
			"    AND t1.data >= ?1\n" +
			"    AND t1.data <= ?2\n" +
			"    AND (t1.tipo = 1 OR (t1.tipo = 31 AND t1.coo = 0))\n " +
			"order by t1.horainicial asc"


			, nativeQuery= true)
	List<VendasPorHora> porHora(LocalDateTime data1, LocalDateTime data2, String filial);

}
