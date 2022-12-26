package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.StatusPdv;

public interface StatusPdvRepository extends JpaRepository<StatusPdv, Integer> {
	
	@Query(value = "select s.id,s.pdv,s.ultimacarga,s.ultimoping,s.versao,s.ipservidor,s.ip,s.currenttimemillis,CONCAT(s.usuario,' - ',u.nome) as usuario,CONCAT(s.filial,' - ',f.nome) as filial from statuspdv s LEFT JOIN usuario u on ( u.codigo=s.usuario) left join filial f on (f.codigo = s.filial)  where ultimoping >= CURRENT_DATE  order by s.filial,s.pdv asc", nativeQuery = true)
	List<StatusPdv> status();

}
