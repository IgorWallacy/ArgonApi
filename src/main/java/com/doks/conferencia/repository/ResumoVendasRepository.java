package com.doks.conferencia.repository;

import com.doks.conferencia.model.ResumoVendas;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ResumoVendasRepository extends JpaRepository<ResumoVendas,Long> {
    @Query(value ="SELECT \n" +
            "   filial.id AS id,\n" +
            "    (\n" +
            "        SELECT COUNT(cancelado) \n" +
            "                FROM operacao \n" +
            "                  WHERE operacao.data BETWEEN ?1 AND ?2 \n" +
            "                      AND operacao.cancelado = '1' \n" +
            "                      AND operacao.tipo IN (-1, 1, 31)\n" +
            "    AND  operacao.filial =?3 ) AS quantidade_cupom_cancelado,\n" +
            " (SELECT count(item.cancelado) \n" +
            "                FROM item left join operacao on (operacao.id = item.idoperacao)\n" +
            "                  WHERE item.data BETWEEN ?1 AND ?2 \n" +
            "                      AND item.cancelado = '1' \n" +
            " AND operacao.cancelado = '0'\n" +
            " AND operacao.filial = ?3) AS quantidade_item_cancelado,\n" +
            "    (\n" +
            "        SELECT SUM(valorliquido) \n" +
            "        FROM operacao \n" +
            "        WHERE operacao.data BETWEEN ?1 AND ?2 \n" +
            "            AND operacao.cancelado = '1' \n" +
            "            AND operacao.tipo IN (-1, 1, 31)\n" +
            "            AND operacao.filial = ?3 "+
            "    ) AS venda_cancelada_cupom,\n" +
            "    (\n" +
            "      SELECT SUM(item.precoliquido) \n" +
            "                FROM item left join operacao on (operacao.id = item.idoperacao)\n" +

            "                  WHERE item.data BETWEEN ?1 AND ?2 \n" +
            " AND item.cancelado = '1' \n" +
            " AND operacao.cancelado = '0'\n" +
            " AND operacao.filial = ?3) AS venda_cancelada_item,\n" +
            "    (\n" +
            "        SELECT COUNT(id) \n" +
            "        FROM operacao \n" +
            "        WHERE operacao.data BETWEEN ?1 AND ?2 \n" +
            "            AND operacao.tipo IN (-1, 1, 31)\n" +
            "            AND operacao.filial = ?3 "+
            "    ) AS quantidade_cupom,\n" +
            "    (\n" +
            "      0) AS venda_liquida,\n" +
            "    (SELECT \n" +
            "\t SUM(COALESCE(t3.descontoitem,t2.descontosubtotal)) as desconto \n" +
            "FROM\n" +
            "\tcontrolesupervisorpdv t1\n" +
            "\tLEFT JOIN operacao t2 ON ( t2.ID = t1.idoperacao )\n" +
            "\tLEFT JOIN item t3 ON ( t3.ID = t1.iditem ) \n" +
            "WHERE\n" +
            "\tt2.data >= ?1 \n" +
            "\tAND t2.data <= ?2 \n" +
            "  and t2.filial = ?3 \n" +
            "\tAND t1.tipoliberacao IN ( '0', '1' )) AS descontos,\n" +
            "    (\n" +
            "        SELECT SUM(total) \n" +
            "        FROM vendas_itens_view \n" +
            "        WHERE vendas_itens_view.emissao BETWEEN ?1 AND ?2 \n" +
            "            AND vendas_itens_view.status = '3' \n" +
            "            AND vendas_itens_view.tipoitem = 'P'\n" +
            "            AND vendas_itens_view.filial =?3 "+
            "    ) AS venda_bruta\n" +
            "FROM filial  \n" +
            "WHERE filial.id='1' limit 1;\n"
            , nativeQuery = true)

    List<ResumoVendas> buscarResumoFilial(LocalDate data1, LocalDate data2, String loja);

    @Query(value ="SELECT \n" +
            "    filial.id AS id,\n" +
            "    (\n" +
            "        SELECT COUNT(cancelado) \n" +
            "                FROM operacao \n" +
            "                  WHERE operacao.data BETWEEN ?1 AND ?2 \n" +
            "                      AND operacao.cancelado = '1' \n" +
            "                      AND operacao.tipo IN (-1, 1, 31)\n" +
            "     ) AS quantidade_cupom_cancelado,\n" +
            " (SELECT count(item.cancelado) \n" +
            "                FROM item left join operacao on (operacao.id = item.idoperacao)\n" +
            "                  WHERE item.data BETWEEN ?1 AND ?2 \n" +
            "                      AND item.cancelado = '1' \n" +
            " AND operacao.cancelado = '0'\n" +
            " ) AS quantidade_item_cancelado,\n" +
            "    (\n" +
            "        SELECT SUM(valorliquido) \n" +
            "        FROM operacao \n" +
            "        WHERE operacao.data BETWEEN ?1 AND ?2 \n" +
            "            AND operacao.cancelado = '1' \n" +
            "            AND operacao.tipo IN (-1, 1, 31)\n" +
            "             "+
            "    ) AS venda_cancelada_cupom,\n" +
            "    (\n" +
            "      SELECT SUM(item.precoliquido) \n" +
            "                FROM item left join operacao on (operacao.id = item.idoperacao)\n" +

            "                  WHERE item.data BETWEEN ?1 AND ?2 \n" +
            " AND item.cancelado = '1' \n" +
            " AND operacao.cancelado = '0'\n" +
            " ) AS venda_cancelada_item,\n" +
            "    (\n" +
            "        SELECT COUNT(id) \n" +
            "        FROM operacao \n" +
            "        WHERE operacao.data BETWEEN ?1 AND ?2 \n" +
            "            AND operacao.tipo IN (-1, 1, 31)\n" +

            "    ) AS quantidade_cupom,\n" +
            "    ( 0  ) AS venda_liquida,\n" +
            "    (SELECT \n" +
            "\t SUM(COALESCE(t3.descontoitem,t2.descontosubtotal)) as desconto \n" +
            "FROM\n" +
            "\tcontrolesupervisorpdv t1\n" +
            "\tLEFT JOIN operacao t2 ON ( t2.ID = t1.idoperacao )\n" +
            "\tLEFT JOIN item t3 ON ( t3.ID = t1.iditem ) \n" +
            "WHERE\n" +
            "\tt2.data >= ?1 \n" +
            "\tAND t2.data <= ?2 \n" +

            "\tAND t1.tipoliberacao IN ( '0', '1' )) AS descontos,\n" +
            "    (\n" +
            "        SELECT SUM(total) \n" +
            "        FROM vendas_itens_view \n" +
            "        WHERE vendas_itens_view.emissao BETWEEN ?1 AND ?2 \n" +
            "            AND vendas_itens_view.status = '3' \n" +
            "            AND vendas_itens_view.tipoitem = 'P'\n" +

            "    ) AS venda_bruta\n" +
            "FROM filial  \n" +
            "WHERE filial.id='1' limit 1;\n"
            , nativeQuery = true)

    List<ResumoVendas> buscarResumo(LocalDate data1, LocalDate data2);
}
