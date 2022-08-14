package com.doks.conferencia.repository.bi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doks.conferencia.model.bi.ProdutoBI;

public interface ProdutosBIRepository extends JpaRepository<ProdutoBI, Integer> 
{ 
	@Query(value = " select  t1.id as id, t2.precoultimacompra as preco_ultimacompra ,t1.filial as id_filial, \r\n"
			+ "    t1.quantidade as quantidade,\r\n"
			+ "    t1.total as valor_total,\r\n"
			+ "    t2.lucrobruto as lucro_bruto,\r\n"
			+ "    t1.emissao as data_emissao,"
			
			+ "    t1.descricao as produto ,\r\n"
			+ "    t7.nome as grupo, "
			
			
			+ "    t7.id as idgrupoproduto,\r\n"
		
			
		//	+ "     (select nome from hierarquia hq where hq.codigo = substring(t7.codigo FROM -0 FOR 7) ) as nomeGrupoPai,\r\n"
		
		
			+ "     t1.emissao as emissao from  vendas_itens_view t1 \r\n"
			+ "     left join produto t2 on (t2.codigo=t1.produto) \r\n"
			
		
			+ "     left join hierarquia t7 on (t7.id=t2.idhierarquia) \r\n"
		
			+ "     where  t1.tipoitem='P' and t1.emissao between '2022-05-01' and '2022-05-01'GROUP BY t7.nome , t1.id, t2.precoultimacompra, t1.filial, t1.quantidade,t1.total, t2.lucrobruto, t1.emissao, t1.descricao,t7.id order by t1.descricao asc " , nativeQuery = true)
	List<ProdutoBI> buscarProdutos();

}
