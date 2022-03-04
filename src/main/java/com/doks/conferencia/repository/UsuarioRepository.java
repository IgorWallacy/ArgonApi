package com.doks.conferencia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "select id,codigo,nome,senha from usuario where codigo =? and senha =? " , nativeQuery = true)
	List<Usuario> findByCodigoAndsenha(String codigo, String senha);

	@Query(value = "select id, nome, senha, codigo from usuario where codigo =?" , nativeQuery = true)
	List<Usuario> findByCodigoPersonalizado(String codigo);
	
	@Query(value = "select id, nome, senha, codigo from usuario where codigo =?" , nativeQuery = true)
	Usuario peloCodigo(String codigo);
	
	Optional<Usuario> findByCodigo(String codigo);
	

}
