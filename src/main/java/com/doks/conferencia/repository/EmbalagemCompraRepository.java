package com.doks.conferencia.repository;

import com.doks.conferencia.model.Embalagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmbalagemCompraRepository extends JpaRepository <Embalagem , Integer> {


    @Query(value = "select embalagem.id as id, unidademedida.nome as nome , embalagem.fatorconversao as fator_conversao  from embalagem left join unidademedida on (unidademedida.id = embalagem.idunidademedida) where embalagem.inativo='0' and embalagem.tipoembalagem='1' and embalagem.idproduto=?1", nativeQuery = true)
    List<Embalagem> embalagemUltimaCompra (Integer idproduto);
}
