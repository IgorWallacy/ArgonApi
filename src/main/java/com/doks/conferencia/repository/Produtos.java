package com.doks.conferencia.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {
	
	@Query(value = "select id,codigo,ean,nome,inativo,imagem,idhierarquia, idfamilia, idunidademedida from produto where inativo = 0", nativeQuery = true)
	List<Produto> buscarProdutos();

	@Query(value = "select id,codigo,ean,nome,inativo,imagem,idhierarquia ,idfamilia, idunidademedida from produto where id=?1", nativeQuery = true)
	Produto porId(String id);
	
	@Query(value = "select id,codigo,ean,nome,inativo,imagem,idhierarquia ,idfamilia, idunidademedida from produto where codigo= ?1", nativeQuery = true)
	Produto findByCodigo(String codigo);
	
	/////////////////////////////////////////ATUALIZA PRECO DA TABELA PRODUTO /////////////////////////////////////////////////
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update produto set preco = ?2 , dataalteracaopreco = ?3 where idfamilia=?1", nativeQuery = true)
	void updatePrecoProdutoFamilia(Integer idfamilia, BigDecimal preco, LocalDateTime agora);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update produto set preco = ?2 , dataalteracaopreco = ?3 where id = ?1", nativeQuery = true)
	void updatePrecoProduto(Integer id , BigDecimal preco, LocalDateTime agora);
	
	
	
	
   /////////////////////////////////////////ATUALIZA PRECO DA TABELA FORMACAOPRECOPRODUTO ///////////////////////////////////////
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update formacaoprecoproduto set preco = ?2 , dataalteracaopreco = ?3 from produto where formacaoprecoproduto.idproduto = produto.id and produto.idfamilia=?1", nativeQuery = true)
	void updatePrecoFormacaoPrecoProdutoFamilia(Integer idfamilia, BigDecimal preco, LocalDateTime agora);
	
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update formacaoprecoproduto set preco = ?2 , dataalteracaopreco = ?3 where idproduto = ?1", nativeQuery = true)
	void updatePrecoFormacaoPrecoProduto(Integer id , BigDecimal preco, LocalDateTime agora);

	
	@Modifying(clearAutomatically = true)
	@Query(value = "update formacaoprecoproduto set preco = ?2 , dataalteracaopreco = ?3 from produto where formacaoprecoproduto.idproduto = produto.id and produto.idfamilia=?1 and formacaoprecoproduto.idfilial = ?4", nativeQuery = true)
	void updatePrecoeENaoReplicaFormacaoPrecoProdutoFamilia(Integer idfamilia, BigDecimal novoPreco, LocalDateTime agora,
			Integer idfilial);

	
	@Modifying(clearAutomatically = true)
	@Query(value = "update formacaoprecoproduto set preco = ?2 , dataalteracaopreco = ?3 where formacaoprecoproduto.idproduto = ?1 and formacaoprecoproduto.idfilial =?4 ", nativeQuery = true)
	void updatePrecoENaoReplicaFormacaoPrecoProduto(Integer idproduto, BigDecimal novoPreco, LocalDateTime agora,
			Integer idfilial);
	
	
	
	
	
	
	/************** ATUALIZA COLUNA TEMPORARARIA (AGENDAMENTO METODOS) ************/
	
	
	
	
	@Modifying(clearAutomatically = true)
	@Query(value="update notafiscalitem set doks_preco_agendado = ?2, doks_data_agendada = ?3 where notafiscalitem.idproduto = ?1 and idnotafiscal = ?4" ,nativeQuery = true)
	void updateDataAgendadaItemNota (Integer id , BigDecimal preco, LocalDate dataagendada, Integer notafiscalId);
	
 
}
