package com.doks.conferencia.repository;

import com.doks.conferencia.model.Metabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaBaseRepository  extends JpaRepository< Metabase , Long> {


    Metabase findFirstByIdNotNull(); // Método para encontrar a primeira instância de Metabase
}
