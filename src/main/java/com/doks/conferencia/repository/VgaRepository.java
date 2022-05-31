package com.doks.conferencia.repository;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.VgaModel;

public interface VgaRepository extends JpaRepository<VgaModel, Integer> {

	//TOTAL POR PDV
	@Query(value="select  pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador ,     sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?2 and ?3 and operacao.tipo = 1 and cancelado = 0 and filial = ?1 and pdv=?4 GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc", nativeQuery = true)
	List<VgaModel> findVendasTotalPDV(String idFilial, LocalDate dataInicial, LocalDate dataFinal, Integer pdv);

	//NFCEPDV
	@Query(value = "select pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador ,     sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?2 and ?3 and operacao.tipo = 1 and cancelado = 0 and filial = ?1 and pdv=?4 and operacao.ecfserie = '' GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc \r\n", nativeQuery = true)
	List<VgaModel> findVendasNfcePDV(String idFilial, LocalDate dataInicial, LocalDate dataFinal, Integer pdv);

	//ECFPDV
	@Query(value = "select pagamento.finalizador as id_finalizador, pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador , sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?2 and ?3 and operacao.tipo = 1 and cancelado = 0 and filial = ?1 and pdv=?4 and operacao.ecfserie <> '' GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc \r\n", nativeQuery = true)
	List<VgaModel> findVendasEcfPDV(String idFilial, LocalDate dataInicial, LocalDate dataFinal, Integer pdv);

	// TOTAL				
	@Query(value = " select  pagamento.finalizador as finalizador, pagamento.nomefinalizador as nomefinalizador,     sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?1 and ?2 and operacao.tipo = 1 and cancelado = 0  GROUP BY pagamento.finalizador,pagamento.nomefinalizador order by finalizador  ",nativeQuery = true)
	List<VgaModel> findVendasTotal(LocalDate dateI, LocalDate dateF);
	
	//TOTAL POR FILIAL
	@Query(value = " select  pagamento.finalizador as finalizador, pagamento.nomefinalizador as nomefinalizador,     sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?2 and ?3 and operacao.tipo = 1 and cancelado = 0 and filial = ?1 GROUP BY pagamento.finalizador,pagamento.nomefinalizador order by finalizador  ",nativeQuery = true)
	List<VgaModel> findVendasTotalFilial(String idfilial, LocalDate dateI, LocalDate dateF);

	// ECF TOTAL
	@Query(value = "select pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador ,   sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?1 and ?2 and operacao.tipo = 1 and cancelado = 0   and operacao.ecfserie <> '' GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc \r\n", nativeQuery = true)
	List<VgaModel> findVendasEcf( LocalDate dateI, LocalDate dateF);
	
	// ECF POR FILIAL
	@Query(value = "select pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador ,   sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?2 and ?3 and operacao.tipo = 1 and cancelado = 0 and filial = ?1  and operacao.ecfserie <> '' GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc \r\n", nativeQuery = true)
	List<VgaModel> findVendasEcfFilial(String idfilial, LocalDate dateI, LocalDate dateF);

	// NFCE
	@Query(value = "select  pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador ,  sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?1 and ?2 and operacao.tipo = 1 and cancelado = 0   and operacao.ecfserie = '' GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc \r\n", nativeQuery = true)
	List<VgaModel> findVendasNfce( LocalDate dateI, LocalDate dateF);
	
	// NFCE FILIAL
	@Query(value = "select  pagamento.finalizador as finalizador,  pagamento.nomefinalizador as nomefinalizador ,  sum(pagamento.valorparcela) as total from operacao inner join pagamento on pagamento.idoperacao = operacao.id where data between ?2 and ?3 and operacao.tipo = 1 and cancelado = 0 and filial = ?1  and operacao.ecfserie = '' GROUP BY   pagamento.finalizador, pagamento.finalizador,pagamento.nomefinalizador  order by pagamento.finalizador asc \r\n", nativeQuery = true)
	List<VgaModel> findVendasNfceFilial(String idfilial, LocalDate dateI, LocalDate dateF);

}
