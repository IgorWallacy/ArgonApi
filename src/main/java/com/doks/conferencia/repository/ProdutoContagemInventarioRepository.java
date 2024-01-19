package com.doks.conferencia.repository;

import com.doks.conferencia.model.ProdutoContagemInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProdutoContagemInventarioRepository extends JpaRepository<ProdutoContagemInventario, Long> {

    @Query(value="select doks_produto_contagem_inventario.id as id ,doks_produto_contagem_inventario.idfilial,doks_produto_contagem_inventario.nome , doks_produto_contagem_inventario.status, doks_produto_contagem_inventario.inicio, doks_produto_contagem_inventario.fim , doks_produto_contagem_inventario.loja  from doks_produto_contagem_inventario where doks_produto_contagem_inventario.inicio >= ?1   order by id desc", nativeQuery = true)
    List<ProdutoContagemInventario> buscarInventariosPorData(LocalDate inico );

    @Query(value="select doks_produto_contagem_inventario.id as id ,doks_produto_contagem_inventario.idfilial,doks_produto_contagem_inventario.nome , doks_produto_contagem_inventario.status, doks_produto_contagem_inventario.inicio, doks_produto_contagem_inventario.fim , doks_produto_contagem_inventario.loja  from doks_produto_contagem_inventario  order by id desc", nativeQuery = true)
    List<ProdutoContagemInventario> buscarInventarios();

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE doks_produto_contagem_inventario t1 " +
            "SET " +
            "  status = '0', " +
            "  fim=?2 " +
            "WHERE"+
            " id = ?1",nativeQuery = true)
    void finalizar(Long id, LocalDateTime agora);

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE doks_produto_contagem_inventario t1 " +
            "SET " +
            "  status = '1' " +
            "WHERE"+
            " id = ?1",nativeQuery = true)
    void reabrir(long l);
}
