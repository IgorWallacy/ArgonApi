package com.doks.conferencia.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.VendasPorHora;


public interface VendasPorHoraRepository extends JpaRepository<VendasPorHora, Integer> {

	@Query(value=" select t1.id as iddocumento,t1.horainicial as hora_inicial,t1.valorliquido as valorliquido, filial.nome as filial "
			+ "from operacao t1 left join filial on( filial.codigo = t1.filial) where t1.cancelado=0  "
			+ "and coalesce(t1.statusnfce, '0')  "
			+ "in (0,1,13,5,6) and t1.data>=?1 "
			+ "and t1.data<=?2 "
			+ "and (t1.tipo=1 or (t1.tipo=31 and t1.coo=0))", nativeQuery= true)
	List<VendasPorHora> porHora(LocalDateTime data1, LocalDateTime data2, String filial);

}
