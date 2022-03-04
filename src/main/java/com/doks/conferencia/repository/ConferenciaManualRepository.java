package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ConferenciaManual;

public interface ConferenciaManualRepository extends JpaRepository< ConferenciaManual, Long> {

	@Query(value = "select id,codigo,data_emissao,data_entrada,numero_nota_fiscal,serie_nota_fiscal,valor_nota_fiscal,status,finalizado,identidade,idfilial,id_usuario from doks_conferencia_manual where idfilial= ?1 order by id desc", nativeQuery = true)
	List<ConferenciaManual> porFilial(int id);

}
