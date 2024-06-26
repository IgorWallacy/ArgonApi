package com.doks.conferencia.repository;

import com.doks.conferencia.model.ProdutoContagemInventarioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutosContagemRepository extends JpaRepository<ProdutoContagemInventarioItem, Long> {

    @Query(value = "SELECT MAX\n" +
            "	( doks_produto_contagem.ID ) AS ID,\n" +
            "	MAX ( doks_produto_contagem.idinventario ) AS idinventario,\n" +
            "	MAX ( produto.codigo ) AS codigo,\n" +
            "	MAX ( case when produto.ean = '' then produto.codigo else produto.ean end ) AS ean,\n" +
            "	MAX ( doks_produto_contagem.idfilial ) AS idfilial,\n" +
            "	MAX ( doks_produto_contagem.idproduto ) AS idproduto,\n" +
            "	MAX ( filial.nome ) AS loja,\n" +
            "	MAX ( doks_produto_contagem.produto ) AS produto,\n" +
            "   MAX (unidademedida.codigo ) as unidade_medida ,"+
            "   MAX (doks_produto_contagem.entrada) as entrada , " +
            "   GREATEST (doks_produto_contagem.recontar) as recontar, " +
            "	SUM ( doks_produto_contagem.quantidade_lida ) AS quantidade_lida,\n" +
            "   MAX ( ( select SUM(quantidade) from vendas_itens_view where vendas_itens_view.produto = produto.codigo and vendas_itens_view.filial = filial.codigo and vendas_itens_view.datahoraemissao between doks_produto_contagem_inventario.inicio and doks_produto_contagem_inventario.fim )  ) as quantidade_vendida_durante,    "+
            "	MAX(doks_produto_contagem.nome_usuario) as nome_usuario ,\n" +
            "	AVG(doks_produto_contagem.quantidade_estoque) AS quantidade_estoque,\n" +

            "   MAX ( CASE WHEN  doks_produto_contagem.quantidade_estoque >= 0 THEN  doks_produto_contagem.quantidade_estoque - doks_produto_contagem.quantidade_lida ELSE doks_produto_contagem.quantidade_lida - doks_produto_contagem.quantidade_estoque end   ) AS divergencia \n" +
            "	FROM\n" +
            "		doks_produto_contagem\n" +
            "		LEFT JOIN filial ON ( filial.ID = idfilial )\n" +
            "		LEFT JOIN produto ON ( produto.ID = doks_produto_contagem.idproduto ) \n" +
            "       LEFT JOIN unidademedida ON (unidademedida.id = produto.idunidademedida) \n "+
            "       LEFT JOIN doks_produto_contagem_inventario on (doks_produto_contagem_inventario.id = doks_produto_contagem.idinventario)"+
            "	WHERE\n" +
            "		doks_produto_contagem.idinventario = ?1 \n" +
            "	GROUP BY\n" +
            "		doks_produto_contagem.idproduto,\n" +
            "		doks_produto_contagem.idfilial,\n" +
            "       doks_produto_contagem.recontar\n "+

            "  ORDER BY\n" +
            "	produto asc ", nativeQuery = true)
    List<ProdutoContagemInventarioItem> buscarProdutoContagem(Long id);

    @Query(value="select doks_produto_contagem.id, produto.codigo as codigo, doks_produto_contagem.entrada, doks_produto_contagem.recontar as recontar,  (case when produto.ean ='' then produto.codigo else produto.ean end) as ean ,doks_produto_contagem.idproduto, doks_produto_contagem.idfilial , unidademedida.codigo as unidade_medida,   doks_produto_contagem.idinventario, (0) as loja, doks_produto_contagem.nome_usuario, doks_produto_contagem.produto, (0) as quantidade_estoque, doks_produto_contagem.divergencia, doks_produto_contagem.quantidade_lida , doks_produto_contagem.quantidade_vendida_durante from doks_produto_contagem left join produto on(produto.id =doks_produto_contagem.idproduto) left join unidademedida on (unidademedida.id=produto.idunidademedida) where doks_produto_contagem.idinventario=?1 order by doks_produto_contagem.id desc ", nativeQuery = true)
    List<ProdutoContagemInventarioItem> buscarItensMobile (Long id);

    @Query(value="select doks_produto_contagem.id, produto.codigo as codigo, doks_produto_contagem.entrada,doks_produto_contagem.recontar as recontar,  (case when produto.ean ='' then produto.codigo else produto.ean end) as ean ,doks_produto_contagem.idproduto, doks_produto_contagem.idfilial , unidademedida.codigo as unidade_medida,   doks_produto_contagem.idinventario, (0) as loja, doks_produto_contagem.nome_usuario, doks_produto_contagem.produto, (0) as quantidade_estoque, doks_produto_contagem.divergencia, doks_produto_contagem.quantidade_lida , doks_produto_contagem.quantidade_vendida_durante from doks_produto_contagem left join produto on(produto.id =doks_produto_contagem.idproduto) left join unidademedida on (unidademedida.id=produto.idunidademedida) where doks_produto_contagem.idinventario=?1 order by doks_produto_contagem.id desc  limit ?2", nativeQuery = true)
    List<ProdutoContagemInventarioItem> buscarItensMobileRows (Long id, Integer rows);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from doks_produto_contagem where doks_produto_contagem.id=?1", nativeQuery = true)
    void deleteByIdProduto(Long id);



    @Modifying(clearAutomatically = true)
    @Query(value="update doks_produto_contagem set quantidade_estoque = ( select saldoestoque_por_produto_filial(idproduto,idfilial)) where idinventario=?1",nativeQuery = true)
    void congelarEstoque(long l);

    @Modifying(clearAutomatically = true)
    @Query(value="update doks_produto_contagem set quantidade_estoque = null where idinventario=?1",nativeQuery = true)
    void descongelarEstoque(long l);

    @Modifying(clearAutomatically = true)
    @Query(value="update doks_produto_contagem set recontar = true where idproduto=?1 and idinventario = ?2 ",nativeQuery = true)
    void marcarRecontagem(Integer idproduto , Integer idinventario);


    @Query(value =  "select doks_produto_contagem.id, produto.codigo as codigo, doks_produto_contagem.entrada, doks_produto_contagem.recontar as recontar,  (case when produto.ean ='' then produto.codigo else produto.ean end) as ean ,doks_produto_contagem.idproduto, doks_produto_contagem.idfilial , unidademedida.codigo as unidade_medida,   doks_produto_contagem.idinventario, (0) as loja, doks_produto_contagem.nome_usuario, doks_produto_contagem.produto, (0) as quantidade_estoque, doks_produto_contagem.divergencia, doks_produto_contagem.quantidade_lida , doks_produto_contagem.quantidade_vendida_durante from doks_produto_contagem left join produto on(produto.id =doks_produto_contagem.idproduto) left join unidademedida on (unidademedida.id=produto.idunidademedida) where doks_produto_contagem.idinventario=?1 and doks_produto_contagem.recontar='1'  order by doks_produto_contagem.idproduto desc"  , nativeQuery = true)
    List<ProdutoContagemInventarioItem>buscarItensMobileRecontar(Integer idinventario);
}
