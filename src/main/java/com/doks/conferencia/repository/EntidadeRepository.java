package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Entidade;

public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {
	
	@Query( value = "select id,codigo,nome,razaosocial,fornecedor,cnpjcpf from entidade where fornecedor = 1 and inativo = 0 order by nome asc", nativeQuery = true)
	public List<Entidade> fornecedores();

}
