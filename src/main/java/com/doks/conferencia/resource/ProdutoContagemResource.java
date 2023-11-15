package com.doks.conferencia.resource;

import com.doks.conferencia.model.ProdutoContagemInventario;
import com.doks.conferencia.model.ProdutoContagemInventarioItem;
import com.doks.conferencia.repository.ProdutoContagemInventarioRepository;
import com.doks.conferencia.repository.ProdutosContagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produto/contagem")
public class ProdutoContagemResource {

    @Autowired
    private ProdutosContagemRepository repository;
    @Autowired
    private ProdutoContagemInventarioRepository produtoContagemInventarioRepository;



    private List<ProdutoContagemInventario> inventarios = new ArrayList<ProdutoContagemInventario>();

    private List<ProdutoContagemInventarioItem> produtos = new ArrayList<ProdutoContagemInventarioItem>();

    @GetMapping("/inventarios")
    public  ResponseEntity<List<ProdutoContagemInventario>> buscarInventarios () {

        return ResponseEntity.ok(produtoContagemInventarioRepository.buscarInventarios());
    }

    @GetMapping("/inventarios/{id}")
    public ResponseEntity<Optional<ProdutoContagemInventario>> buscarInventariosPorId (@PathVariable String id) {

        Integer idInt = Integer.parseInt(id);

        return ResponseEntity.ok(produtoContagemInventarioRepository.findById(Long.valueOf(idInt)));
    }



    @GetMapping("/porInventario/{id}")
    public ResponseEntity<List<ProdutoContagemInventarioItem>> buscar (@PathVariable String id) {
        Integer i = Integer.parseInt(id);
        return ResponseEntity.ok(repository.buscarProdutoContagem(i.longValue()));
    }
    @GetMapping("/porInventario/mobile/{id}")
    public ResponseEntity<List<ProdutoContagemInventarioItem>> buscarMobile (@PathVariable String id) {
        Integer i = Integer.parseInt(id);
        return ResponseEntity.ok(repository.buscarItensMobile(i.longValue()));
    }
    @Transactional
    @PutMapping("/inventario/finalizar/{id}")
    public void finalizarInventario (@PathVariable String id) {

        LocalDateTime agora = LocalDateTime.now();

       produtoContagemInventarioRepository.finalizar(Long.parseLong(id) , agora);

       repository.congelarEstoque(Long.parseLong(id));

    }
    @Transactional
    @PutMapping("/inventario/reabrir/{id}")
    public void reabrirInventario (@PathVariable String id) {



        produtoContagemInventarioRepository.reabrir(Long.parseLong(id) );

        repository.descongelarEstoque(Long.parseLong(id));

    }
    @PostMapping("/salvar")
    public ResponseEntity<ProdutoContagemInventarioItem> salvarItem(@RequestBody ProdutoContagemInventarioItem produto) {

            LocalDateTime agora = LocalDateTime.now();
            produto.setEntrada(agora);

        return ResponseEntity.ok(repository.save(produto));
    }

    @PostMapping("/inventario/salvar")
    public ResponseEntity<ProdutoContagemInventario> salvarInventario(@RequestBody ProdutoContagemInventario inventario) {

            LocalDateTime now = LocalDateTime.now();

            inventario.setInicio(now);

        return ResponseEntity.ok(produtoContagemInventarioRepository.save(inventario));
    }

    @Transactional
    @DeleteMapping("/inventario/item/{id}")
    public void deletarItem(@PathVariable String id) {

        repository.deleteByIdProduto(Long.valueOf(id));
    }

}
