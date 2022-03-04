package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {
	
	@Query(value = "select id,codigo,ean,nome,inativo,imagem,idhierarquia, idfamilia, idunidademedida from produto where inativo = 0", nativeQuery = true)
	List<Produto> buscarProdutos();

	@Query(value = "select id,codigo,ean,nome,inativo,imagem,idhierarquia ,idfamilia, idunidademedida from produto where id=?1", nativeQuery = true)
	Produto porId(String id);
	
	@Query(value = "select id,codigo,ean,nome,inativo,imagem,idhierarquia ,idfamilia, idunidademedida from produto where codigo= ?1", nativeQuery = true)
	Produto findByCodigo(String codigo);
 
}
