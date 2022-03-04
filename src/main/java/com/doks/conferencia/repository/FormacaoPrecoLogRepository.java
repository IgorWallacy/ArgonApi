package com.doks.conferencia.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.FormacaoPrecoLog;

public interface FormacaoPrecoLogRepository extends JpaRepository< FormacaoPrecoLog,Integer> {
	
	@Query(value = "select fp.id,fp.idproduto, t1.nome, fp.datahora, fp.idfilial, fp.precovendaajustado, fp.precovenda from formacaoprecolog fp left join produto t1 on (idproduto = t1.id) where fp.datahora >= ?2 and fp.datahora <= ?3 and fp.idfilial = ?1 and fp.precovenda != fp.precovendaajustado ORDER BY fp.datahora asc ", nativeQuery = true)

	List<FormacaoPrecoLog> produtosAlteradosPorDataHora(Integer idfilial, LocalDateTime dataInicialF, LocalDateTime dataFinalF);
	
	

}
