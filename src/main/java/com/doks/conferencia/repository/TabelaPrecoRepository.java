package com.doks.conferencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ConfiguracaoPainelDePreco;



public interface TabelaPrecoRepository extends JpaRepository<ConfiguracaoPainelDePreco, Integer> {

	@Query(value=" select logo from configuracaopaineldepreco where idfilial = ?1" , nativeQuery = true)
	byte[] getImage(Integer idfilial);

	
	
	

}
