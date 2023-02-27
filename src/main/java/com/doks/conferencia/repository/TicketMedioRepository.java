package com.doks.conferencia.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.TicketMedio;

public interface TicketMedioRepository extends JpaRepository<TicketMedio, BigDecimal> {

	
	@Query(value = "SELECT\r\n"
			+ "    filial.nome as filial,\r\n"
			+ "    SUM(COALESCE(t1.total, 0)) AS valor_liquido,\r\n"
			+ "    COUNT(DISTINCT t1.documento_unique) AS quantidade_vendas,\r\n"
			+ "    COUNT(*) AS quantidade_itens,\r\n"
			+ "    t1.emissao AS data\r\n"
			+ "FROM\r\n"
			+ "    vendas_itens_view t1\r\n"
			+ "    LEFT JOIN produto t2 ON t2.codigo = t1.produto\r\n"
			+ "		LEFT JOIN filial on t1.filial = filial.codigo\r\n"
			+ "WHERE\r\n"
			+ "    t2.tipo = 'P' AND\r\n"
			+ "    t1.emissao >= ?1 AND\r\n"
			+ "    t1.emissao <= ?2 \r\n"
			+ "GROUP BY\r\n"
			+ "    filial.nome,\r\n"
			+ "    t1.emissao\r\n"
			+ "ORDER BY\r\n"
			+ "    filial.nome,\r\n"
			+ "    t1.emissao" , nativeQuery = true)
	List<TicketMedio> buscar(LocalDate data1, LocalDate data2);

}
