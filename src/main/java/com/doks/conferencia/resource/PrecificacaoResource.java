package com.doks.conferencia.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;


import com.doks.conferencia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.doks.conferencia.repository.FilialRepository;
import com.doks.conferencia.repository.FormacaoPrecoProdutoRepository;
import com.doks.conferencia.repository.NotaFiscalRepository;
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
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private FilialRepository filialRepository;

    private List<PrecificacaoItem> todos = new ArrayList<>();

    private List<FormacaoPrecoProduto> todos2 = new ArrayList<>();

    private List<Filial> filiais = new ArrayList<>();


    @Autowired
    private FormacaoPrecoProdutoRepository formacaoPrecoProdutoRepository;

    @GetMapping("/produtos/pendentes/{dataI}/{dataF}/{modoPesquisa}")
    public ResponseEntity<List<PrecificacaoItem>> produtosPendentesDePrecificacao (@PathVariable String dataI, @PathVariable String dataF , @PathVariable Integer modoPesquisa) {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .optionalStart()
                .appendLiteral(" GMT")
                .appendOffset("+HH:mm", "Z")
                .optionalEnd()
                .toFormatter();

        LocalDateTime data1 = LocalDateTime.parse(dataI, dtf);
        LocalDateTime data2 = LocalDateTime.parse(dataF, dtf);

        filiais = filialRepository.findAll();
        int size = filiais.size();

        if (size == 1) {

            return ResponseEntity.ok(repository.buscartodosPendentesFilial(data1, data2, modoPesquisa));
        }
        else {
            return ResponseEntity.ok(repository.buscartodosPendentes(data1, data2, modoPesquisa));
        }
    }


    /*****************************************************************************
     * ATUALIZACAO DE PREÇOS
     *********************************************/
    ///// BUSCA AS NOTAS PELA DATA DE INCLUSAO //////////////
    /* atualiza tabela produto coluna preço */
    @GetMapping("/produtos/precificar/agendar/{dataI}/{dataF}/{filial}")
    public ResponseEntity<List<PrecificacaoItem>> todos(@PathVariable String dataI, @PathVariable String dataF,
                                                        @PathVariable Integer filial) {

        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .optionalStart()
                .appendLiteral(" GMT")
                .appendOffset("+HH:mm", "Z")
                .optionalEnd()
                .toFormatter();

        LocalDateTime data1 = LocalDateTime.parse(dataI, dtf);
        LocalDateTime data2 = LocalDateTime.parse(dataF, dtf);


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
    @PutMapping("/produtos/precificar/{idproduto}/{idfamilia}/{preco}/{idfilial}/{replicarPreco}/{usuario}")
    @Transactional
    public void atualizarPrecoProduto(@PathVariable Integer idproduto, @PathVariable Integer idfamilia,
                                      @PathVariable String preco, @PathVariable Integer idfilial, @PathVariable Integer replicarPreco, @PathVariable Integer usuario , @RequestBody PrecificacaoItem produtos) {

        LocalDateTime agora = LocalDateTime.now();




        filiais = filialRepository.findAll();

        int size = filiais.size();

        if (size == 1) {

            if (idproduto > 0) {

                BigDecimal novoPreco = new BigDecimal(preco);

                if (idfamilia > 0) {

                    produtosRpository.updatePrecoProdutoFamilia(idfamilia, novoPreco, agora);
                    produtosRpository.insertHistoricoPreco(idproduto,novoPreco,agora,idfilial , usuario, produtos.getPrecoAtual());

                } else {

                    produtosRpository.updatePrecoProduto(idproduto, novoPreco, agora);
                    produtosRpository.insertHistoricoPreco(idproduto,novoPreco,agora,idfilial , usuario , produtos.getPrecoAtual());

                }

            }

        } else {

            // System.out.println("atualizando formacao preco produto");

            if (idproduto > 0) {

                BigDecimal novoPreco = new BigDecimal(preco);

                if (idfamilia > 0) {

                    if (replicarPreco == 1) {

                        produtosRpository.updatePrecoFormacaoPrecoProdutoFamilia(idfamilia, novoPreco, agora);
                        filiais.forEach(filial -> {
                            produtosRpository.insertHistoricoPreco(idproduto,novoPreco,agora,filial.getId() , usuario, produtos.getPrecoAtual());
                        });

                    } else if (replicarPreco == 0) {
                        produtosRpository.updatePrecoeENaoReplicaFormacaoPrecoProdutoFamilia(idfamilia, novoPreco,
                                agora, idfilial);
                        produtosRpository.insertHistoricoPreco(idproduto,novoPreco,agora,idfilial , usuario, produtos.getPrecoAtual());

                    }

                }


                else {
                    if (replicarPreco == 1) {

                        produtosRpository.updatePrecoFormacaoPrecoProduto(idproduto, novoPreco, agora);
                        filiais.forEach(filial -> {
                            produtosRpository.insertHistoricoPreco(idproduto,novoPreco,agora,filial.getId() , usuario, produtos.getPrecoAtual());
                        });
                    } else if (replicarPreco == 0) {
                        // System.out.println("atualiza sem familia e nao replica");
                        produtosRpository.updatePrecoENaoReplicaFormacaoPrecoProduto(idproduto, novoPreco, agora,
                                idfilial);
                        produtosRpository.insertHistoricoPreco(idproduto, novoPreco, agora , idfilial , usuario, produtos.getPrecoAtual());
                    }
                }

            }

        }
    }


    /* EXECUTA A ATUIALIZCAO DE PREÇO NO CAMPO DOKS_PRECO_AGENDADO na tabela de produtos --- TEMPORARIO */
    @PutMapping("/produtos/precificar/agenda/produto/{idfilial}/{idproduto}/{idfamilia}/{preco}/{dataagendada_string}/{nomeUsuario}")
    @Transactional
    public void atualizarPrecoAgendadoProduto2(@PathVariable Integer idfilial, @PathVariable Integer idproduto, @PathVariable Integer idfamilia,
                                               @PathVariable String preco, @PathVariable String dataagendada_string, @PathVariable String nomeUsuario) {

        LocalDate dataagendada = LocalDate.parse(dataagendada_string);


        BigDecimal novoPreco = new BigDecimal(preco);

        if (idfamilia == 0) {

            produtosRpository.updateDataAgendadaItemProduto(idproduto, novoPreco, dataagendada, nomeUsuario, idfilial);

        } else {

            produtosRpository.updateDataAgendadaItemProdutoFamilia(novoPreco, dataagendada, idfamilia, nomeUsuario, idfilial);
        }

    }

    /* EXECUTA A ATUIALIZCAO DE PREÇO NO CAMPO DOKS_PRECO_AGENDADO --- TEMPORARIO */
    @PutMapping("/produtos/precificar/agenda/{idproduto}/{idfamilia}/{idNota}/{preco}/{dataagendada_string}/{nomeUsuario}")
    @Transactional
    public void atualizarPrecoAgendadoProduto(@PathVariable Integer idproduto, @PathVariable Integer idfamilia,
                                              @PathVariable String preco, @PathVariable String dataagendada_string, @PathVariable Integer idNota, @PathVariable String nomeUsuario) {

        LocalDate dataagendada = LocalDate.parse(dataagendada_string);

        LocalDateTime dataInclusao = LocalDateTime.now();


        BigDecimal novoPreco = new BigDecimal(preco);

        if (idfamilia == 0) {

            produtosRpository.updateDataAgendadaItemNota(idproduto, novoPreco, dataagendada, idNota, nomeUsuario, dataInclusao);

        } else {

            produtosRpository.updateDataAgendadaItemNotaFamilia(novoPreco, dataagendada, idfamilia, idNota, nomeUsuario, dataInclusao);
        }

    }

    ///// PRECIFICAR BUSCA PELA DATA AGENDADA DO ITEM //////////////
    /* atualiza tabela produto coluna preço */
    @GetMapping("/produtos/precificar/{dataI}/{dataF}/{filial}/{modoPesquisa}")
    public ResponseEntity<List<PrecificacaoItem>> todosDoks(@PathVariable String dataI, @PathVariable String dataF,
                                                            @PathVariable Integer filial, @PathVariable String modoPesquisa) {

        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .optionalStart()
                .appendLiteral(" GMT")
                .appendOffset("+HH:mm", "Z")
                .optionalEnd()
                .toFormatter();

        LocalDateTime data1 = LocalDateTime.parse(dataI, dtf);
        LocalDateTime data2 = LocalDateTime.parse(dataF, dtf);


        Integer modoPesquisaInteger = Integer.parseInt(modoPesquisa);

        filiais = filialRepository.findAll();
        int size = filiais.size();

        if (size == 1) {

            // System.out.println(filial);
            todos = repository.buscarTodosPrecificar(data1, data2, modoPesquisaInteger);

        } else {

            if (filial == 0) {

                todos = repository.buscarTodosPrecificarComFilial(data1, data2, modoPesquisaInteger);
                // System.out.println(filial + "igual a zero");

            } else {

                // System.out.println("busca o metodo de mulfilial" + filial);
                todos = repository.buscarTodosPorFilialPrecificar(data1, data2, filial, modoPesquisaInteger);
            }
        }

        return ResponseEntity.ok(todos);
    }

    ///// PRECIFICAR BUSCA PELA DATA AGENDADA DO ITEM //////////////
    /* atualiza tabela produto coluna preço */
    @GetMapping("/produtos/precificar/produto/{dataI}/{dataF}/{filial}/{modoPesquisa}")
    public ResponseEntity<List<FormacaoPrecoProduto>> todosProdutoDoks(@PathVariable String dataI, @PathVariable String dataF,
                                                                       @PathVariable Integer filial, @PathVariable String modoPesquisa) {

        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .optionalStart()
                .appendLiteral(" GMT")
                .appendOffset("+HH:mm", "Z")
                .optionalEnd()
                .toFormatter();

        LocalDateTime data1 = LocalDateTime.parse(dataI, dtf);
        LocalDateTime data2 = LocalDateTime.parse(dataF, dtf);


        Integer modoPesquisaInteger = Integer.parseInt(modoPesquisa);


        if (filial == 0) {

            todos2 = formacaoPrecoProdutoRepository.buscarTodosPrecificarProdutoComFilial(data1, data2, modoPesquisaInteger);
            // System.out.println(filial + "igual a zero");

        } else {

            // System.out.println("busca o metodo de mulfilial" + filial);
            todos2 = formacaoPrecoProdutoRepository.buscarTodosPorFilialPrecificar(data1, data2, filial, modoPesquisaInteger);
        }


        return ResponseEntity.ok(todos2);
    }


    @PutMapping("/notaId/{notaId}/status/{status}")
    @Transactional
    public void atualizaStatusNotaPrecificado(@PathVariable Integer notaId, @PathVariable Boolean status) {


        notaFiscalRepository.atualizarStatusPrecificacao(notaId, status);


    }


    @PutMapping("/revisado/{id}/{status}")
    @Transactional
    public void atualizaStatusRevisado(@PathVariable Integer id, @PathVariable Boolean status) {


        notaFiscalRepository.atualizarNotaFiscalItemStatusRevisado(id, status);


    }

}
