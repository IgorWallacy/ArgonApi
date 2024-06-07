package com.doks.conferencia.repository;

import com.doks.conferencia.model.dto.ProdutoCompraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoCompraRepository  extends JpaRepository<ProdutoCompraDTO , Integer> {

    @Query(value="SELECT \n" +
            "    produto.id AS id,\n" +
            "    produto.codigo AS codigo,\n" +
            "    produto.ean AS ean,\n" +
            "    produto.nome AS nome,\n" +
            "    COALESCE(formacaoprecoproduto.custoaquisicao, produto.custoaquisicao) AS precocusto,\n" +
            "    COALESCE(formacaoprecoproduto.preco, produto.preco) AS preco\n" +
           " FROM \n" +
            "    produto\n" +
            "LEFT JOIN \n" +
            "    formacaoprecoproduto ON produto.id = formacaoprecoproduto.idproduto "+
            " WHERE \n" +
            "    produto.inativo = '0'\n" +
            " and formacaoprecoproduto.idfilial = ?1 "+
            "ORDER BY \n" +
            "    produto.nome ASC;", nativeQuery = true)
    List<ProdutoCompraDTO> todosProdutos(Integer filial);


}
