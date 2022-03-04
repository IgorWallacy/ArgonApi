package com.doks.conferencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.ConferenciaCegaItem;

public interface ConferenciaCegaItemRepository extends JpaRepository<ConferenciaCegaItem, Integer> {

	@Query(value = "select * from conferenciacegaitem where idconferencia = ?1" , nativeQuery = true)
	List<ConferenciaCegaItem> findByidConferencia(Integer id);

	

}
