package com.doks.conferencia.resource;

import com.doks.conferencia.model.ResumoVendas;
import com.doks.conferencia.repository.ResumoVendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vendas/resumo")
public class ResumoVendasResource {

    @Autowired
    private ResumoVendasRepository repository;

    private List<ResumoVendas> vendas = new ArrayList<ResumoVendas>();


    @GetMapping("/{dataI}/{dataF}/{loja}")
    public ResponseEntity<List<ResumoVendas>> buscarResumo(@PathVariable String dataI, @PathVariable  String dataF , @PathVariable  String loja) {

        LocalDate data1 = LocalDate.parse(dataI);
        LocalDate data2 = LocalDate.parse(dataF);

         Integer i = Integer.parseInt(loja);

         if(i == 0) {

             return ResponseEntity.ok(repository.buscarResumo(data1,data2));

         }

        return ResponseEntity.ok(repository.buscarResumoFilial(data1,data2,loja));
    }
}
