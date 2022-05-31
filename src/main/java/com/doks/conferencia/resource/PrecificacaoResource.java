package com.doks.conferencia.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doks.conferencia.model.Filial;
import com.doks.conferencia.model.PrecificacaoItem;
import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.PrecificacaoRepository;
import com.doks.conferencia.repository.Produtos;

@RestController()
@RequestMapping("/api_precificacao")
public class PrecificacaoResource {

	@Autowired
	private PrecificacaoRepository repository;

	@Autowired
	private Produtos produtosRpository;

	@Autowired
	private FilialRepository filialRepository;

	private List<PrecificacaoItem> todos = new ArrayList<>();

	private List<Filial> filiais = new ArrayList<>();

	/*****************************************************************************
	 * ATUALIZACAO DE PREÇOS
	 *********************************************/
	///// BUSCA AS NOTAS PELA DATA DE INCLUSAO //////////////
	/* atualiza tabela produto coluna preço */
	@GetMapping("/produtos/precificar/agendar/{dataI}/{dataF}/{filial}")
	public ResponseEntity<List<PrecificacaoItem>> todos(@PathVariable String dataI, @PathVariable String dataF,
			@PathVariable Integer filial) {

		LocalDateTime data1 = LocalDateTime.parse(dataI);
		LocalDateTime data2 = LocalDateTime.parse(dataF);
		
	

		filiais = filialRepository.findAll();
		int size = filiais.size();

		if (size == 1) {

			// System.out.println(filial);
			todos = repository.buscarTodosAgendar(data1, data2);
			// System.out.println("buscando a tabela produto");

		} else {

			if (filial == 0) {

				todos = repository.buscarTodosComFilialAgendar(data1, data2, filial);
				// System.out.println(filial + "igual a zero");

			} else {

				// System.out.println("busca o metodo de mulfilial" + filial);
				todos = repository.buscarTodosPorFilial(data1, data2, filial);
			}
		}

		return ResponseEntity.ok(todos);
	}

	/*
	 * EXECUTA A ATUALIZACAO DE PREÇOS NA TABELA PRODUTO NO CAMPO PREÇO ..... METODO
	 * FINAL
	 */
	@PutMapping("/produtos/precificar/{idproduto}/{idfamilia}/{preco}/{idfilial}/{replicarPreco}")
	@Transactional
	public void atualizarPrecoProduto(@PathVariable Integer idproduto, @PathVariable Integer idfamilia,
			@PathVariable String preco, @PathVariable Integer idfilial, @PathVariable Integer replicarPreco) {

		LocalDateTime agora = LocalDateTime.now();

		// System.out.println(idfamilia);

		filiais = filialRepository.findAll();
		int size = filiais.size();

		if (size == 1) {

			if (idproduto > 0) {

				BigDecimal novoPreco = new BigDecimal(preco);

				if (idfamilia > 0) {

					produtosRpository.updatePrecoProdutoFamilia(idfamilia, novoPreco, agora);

				} else {

					produtosRpository.updatePrecoProduto(idproduto, novoPreco, agora);

				}

			}

		} else {

			// System.out.println("atualizando formacao preco produto");

			if (idproduto > 0) {

				BigDecimal novoPreco = new BigDecimal(preco);

				if (idfamilia > 0) {

					if (replicarPreco == 1) {

						produtosRpository.updatePrecoFormacaoPrecoProdutoFamilia(idfamilia, novoPreco, agora);

					} else if (replicarPreco == 0) {
						produtosRpository.updatePrecoeENaoReplicaFormacaoPrecoProdutoFamilia(idfamilia, novoPreco,
								agora, idfilial);
					}

				}
				////////////////////////////////////////////// DAQUI PRA CIMA FAMILIA OK
				////////////////////////////////////////////// /////////////////////////////////////////////////

				else {
					if (replicarPreco == 1) {

						produtosRpository.updatePrecoFormacaoPrecoProduto(idproduto, novoPreco, agora);
					}

					else if (replicarPreco == 0) {
						// System.out.println("atualiza sem familia e nao replica");
						produtosRpository.updatePrecoENaoReplicaFormacaoPrecoProduto(idproduto, novoPreco, agora,
								idfilial);
					}
				}

			}

		}
	}

	/* EXECUTA A ATUIALIZCAO DE PREÇO NO CAMPO DOKS_PRECO_AGENDADO --- TEMPORARIO */
	@PutMapping("/produtos/precificar/agenda/{idproduto}/{idfamilia}/{idNota}/{preco}/{dataagendada_string}")
	@Transactional
	public void atualizarPrecoAgendadoProduto(@PathVariable Integer idproduto, @PathVariable Integer idfamilia,
			@PathVariable String preco,
			@PathVariable String dataagendada_string, @PathVariable Integer idNota) {

		LocalDate dataagendada = LocalDate.parse(dataagendada_string);
		
		

		
			BigDecimal novoPreco = new BigDecimal(preco);

			

			produtosRpository.updateDataAgendadaItemNota(idproduto, novoPreco, dataagendada, idNota);

		
	}

	///// PRECIFICAR BUSCA PELA DATA AGENDADA DO ITEM //////////////
	/* atualiza tabela produto coluna preço */
	@GetMapping("/produtos/precificar/{dataI}/{dataF}/{filial}")
	public ResponseEntity<List<PrecificacaoItem>> todosDoks(@PathVariable String dataI, @PathVariable String dataF,
			@PathVariable Integer filial) {

		LocalDateTime data1 = LocalDateTime.parse(dataI);
		LocalDateTime data2 = LocalDateTime.parse(dataF);

		filiais = filialRepository.findAll();
		int size = filiais.size();

		if (size == 1) {

			// System.out.println(filial);
			todos = repository.buscarTodosPrecificar(data1, data2);

		} else {
			

			if (filial == 0) {

				todos = repository.buscarTodosPrecificarComFilial(data1, data2);
			//	 System.out.println(filial + "igual a zero");

			} else {

			//	 System.out.println("busca o metodo de mulfilial" + filial);
				todos = repository.buscarTodosPorFilialPrecificar(data1, data2, filial);
			}
		}

		return ResponseEntity.ok(todos);
	}

}
