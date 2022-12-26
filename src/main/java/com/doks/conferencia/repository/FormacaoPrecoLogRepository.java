package com.doks.conferencia.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.FormacaoPrecoLog;

public interface FormacaoPrecoLogRepository extends JpaRepository< FormacaoPrecoLog,Integer> {
	
	@Query(value = "select fp.id,fp.idproduto, t1.nome, fp.dataalteracaopreco as dataHora, fp.idfilial, fp.preco as precovendaajustado, (0) as precovenda from formacaoprecoproduto fp left join produto t1 on (idproduto = t1.id) where fp.dataalteracaopreco >= ?2 and fp.dataalteracaopreco <= ?3 and fp.idfilial = ?1  ORDER BY fp.dataalteracaopreco asc ", nativeQuery = true)

	List<FormacaoPrecoLog> produtosAlteradosPorDataHora(Integer idfilial, LocalDateTime dataInicialF, LocalDateTime dataFinalF);
	
	

}
