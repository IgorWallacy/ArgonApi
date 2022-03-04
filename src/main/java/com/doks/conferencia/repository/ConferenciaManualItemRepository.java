package com.doks.conferencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ConferenciaManualItem;

public interface ConferenciaManualItemRepository extends JpaRepository<ConferenciaManualItem, Long> {

	@Query(value = "select id,data_vencimento,observacao,idconferencia,idproduto,fatorconversao,quantidade,idunidademedida,status,quantidade_saida from doks_conferencia_manual_item where idconferencia=?1 order by id desc", nativeQuery = true)
	List<ConferenciaManualItem> buscarPeloIdConferencia(Long id);
	
	
	
	
	@Query(value="update doks_conferencia_manual_item set status = 'ACEITO' where id=?1",nativeQuery = true)
	 ConferenciaManualItem updateParaAprovado(Long id);
	
	
	
	
	
	@Query( value = "SELECT DISTINCT\r\n"
			+ "	doks_conferencia_manual_item.idconferencia, \r\n"
			+ "	doks_conferencia_manual.id as id_conferenica, \r\n"
			+ "	doks_conferencia_manual_item.id, \r\n"
			+ "	doks_conferencia_manual_item.data_vencimento, \r\n"
			+ "	doks_conferencia_manual_item.fatorconversao, \r\n"
			+ "	doks_conferencia_manual_item.observacao, \r\n"
			+ "	doks_conferencia_manual_item.quantidade, \r\n"
			+ "	doks_conferencia_manual_item.idunidademedida, \r\n"
			+ "	doks_conferencia_manual_item.idproduto, \r\n"
			+ "	doks_conferencia_manual_item.status, \r\n"
			+ "(select sum(t1.quantidade) from vendas_itens_view t1 where t1.emissao >= doks_conferencia_manual.data_entrada and t1.emissao <= CURRENT_DATE and t1.produto = pr.codigo and cast(t1.filial as INTEGER) = ?1 ) as quantidade_saida,"
			+ "	doks_conferencia_manual.idfilial\r\n"
			+ "FROM\r\n"
			+ "	doks_conferencia_manual_item\r\n"
			+ "	INNER JOIN\r\n"
			+ "	doks_conferencia_manual\r\n"
			+ "	ON \r\n"
			+ "		doks_conferencia_manual_item.idconferencia = doks_conferencia_manual.id\r\n"
			+"left join produto pr on doks_conferencia_manual_item.idproduto = pr.id "
			+ "WHERE\r\n"
			+ "	doks_conferencia_manual.idfilial = ?1" ,nativeQuery = true)
	List<ConferenciaManualItem> buscarPorFilial(Integer idFilial); 
	
	 
	 
	

	@Query( value = "SELECT DISTINCT\r\n"
			+ "	doks_conferencia_manual_item.idconferencia, \r\n"
			+ "	doks_conferencia_manual.id as id_conferenica, \r\n"
			+ "	doks_conferencia_manual_item.id, \r\n"
			+ "	doks_conferencia_manual_item.data_vencimento, \r\n"
			+ "	doks_conferencia_manual_item.fatorconversao, \r\n"
			+ "	doks_conferencia_manual_item.observacao, \r\n"
			+ "	doks_conferencia_manual_item.quantidade, \r\n"
			+ "	doks_conferencia_manual_item.idunidademedida, \r\n"
			+ "	doks_conferencia_manual_item.idproduto, \r\n"
			+ "	doks_conferencia_manual_item.status, \r\n"
		//	+ "(select sum(mve.quantidadesaida) from movimentoestoque mve where mve.data >= doks_conferencia_manual.data_entrada and mve.data <= CURRENT_DATE and mve.idproduto = doks_conferencia_manual_item.idproduto and mve.idfilial = ?1 and mve.tipodocumento = '1' and mve.cancelado = '0') as quantidade_saida,"
		+ "(select sum(t1.quantidade) from vendas_itens_view t1 where t1.emissao >= doks_conferencia_manual.data_entrada and t1.emissao <= CURRENT_DATE and t1.produto = pr.codigo and cast(t1.filial as INTEGER) = ?1 ) as quantidade_saida,"
		+ "	doks_conferencia_manual.idfilial\r\n"
			+ "FROM\r\n"
			+ "	doks_conferencia_manual_item\r\n"
			+ "	INNER JOIN\r\n"
			+ "	doks_conferencia_manual\r\n"
			+ "	ON \r\n"
			+ "		doks_conferencia_manual_item.idconferencia = doks_conferencia_manual.id\r\n"
			+ " left join produto pr on doks_conferencia_manual_item.idproduto = pr.id "
			+ "WHERE\r\n"
			+ "	doks_conferencia_manual.idfilial = ?1 and doks_conferencia_manual_item.data_vencimento between ?2 and ?3 " ,nativeQuery = true)
	List<ConferenciaManualItem> findByVencimentoBetween(Integer idfilial, LocalDate dataInicial, LocalDate dataFinal);



	

	@Query( value = "SELECT DISTINCT\r\n"
			+ "	doks_conferencia_manual_item.idconferencia, \r\n"
			+ "	doks_conferencia_manual.id as id_conferenica, \r\n"
			+ "	doks_conferencia_manual_item.id, \r\n"
			+ "	doks_conferencia_manual_item.data_vencimento, \r\n"
			+ "	doks_conferencia_manual_item.fatorconversao, \r\n"
			+ "	doks_conferencia_manual_item.observacao, \r\n"
			+ "	doks_conferencia_manual_item.quantidade, \r\n"
			+ "	doks_conferencia_manual_item.idunidademedida, \r\n"
			+ "	doks_conferencia_manual_item.idproduto, \r\n"
			+ "	doks_conferencia_manual_item.status, \r\n"
		//	+ "(select sum(mve.quantidadesaida) from movimentoestoque mve where mve.data >= doks_conferencia_manual.data_entrada and mve.data <= CURRENT_DATE and mve.idproduto = doks_conferencia_manual_item.idproduto and mve.idfilial = ?1 and mve.tipodocumento = '1' and mve.cancelado = '0') as quantidade_saida,"
		+ "(select sum(t1.quantidade) from vendas_itens_view t1 where t1.emissao >= doks_conferencia_manual.data_entrada and t1.emissao <= CURRENT_DATE and t1.produto = pr.codigo and cast(t1.filial as INTEGER) = ?1 ) as quantidade_saida,"
		+ "	doks_conferencia_manual.idfilial\r\n"
			+ "FROM\r\n"
			+ "	doks_conferencia_manual_item\r\n"
			+ "	INNER JOIN\r\n"
			+ "	doks_conferencia_manual\r\n"
			+ "	ON \r\n"
			+ "		doks_conferencia_manual_item.idconferencia = doks_conferencia_manual.id\r\n"
			+ " left join produto pr on doks_conferencia_manual_item.idproduto = pr.id "
			+ "WHERE\r\n"
			+ "	doks_conferencia_manual.idfilial = ?1 and doks_conferencia_manual_item.data_vencimento between ?2 and ?3 and "
			+ " (select sum(t1.quantidade) from vendas_itens_view t1 where t1.emissao >= doks_conferencia_manual.data_entrada and t1.emissao <= CURRENT_DATE and t1.produto = pr.codigo and cast(t1.filial as INTEGER) = ?1 ) <"
			+ " (doks_conferencia_manual_item.quantidade * 	doks_conferencia_manual_item.fatorconversao) " ,nativeQuery = true)	
	List<ConferenciaManualItem> findByVencimentoBetweenSaldoPositivo(Integer idfilial, LocalDate dataInicial,
			LocalDate dataFinal);

} 

