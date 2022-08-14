package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {
	
	@Query( value = "select id, idfilial, conferencia, tipodocumento, numeronotafiscal, razaosocial, cnpjcpf,emissao, valortotalnota   from notafiscal where tipodocumento = 'E' and serie = '55' and conferencia is not null  ", nativeQuery = true)
	List<NotaFiscal> filtradoConferencia();
	
	
	@Query( value = "select id, idfilial, conferencia, tipodocumento, numeronotafiscal, razaosocial, cnpjcpf,emissao, valortotalnota   from notafiscal where tipodocumento = 'E' and serie = '55' and conferencia is not null and idfilial =?1  ", nativeQuery = true)
	List<NotaFiscal> filialbyId(Integer id);

	@Modifying(clearAutomatically = true)
	@Query (value = "update notafiscal set doks_precificado=?2 where id = ?1" , nativeQuery = true)
	void atualizarStatusPrecificacao(Integer notaId, Boolean status);

}
