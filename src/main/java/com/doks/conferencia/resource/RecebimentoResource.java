package com.doks.conferencia.resource;


import com.doks.conferencia.model.Recebimento;
import com.doks.conferencia.repository.RecebimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/recebimento")
public class RecebimentoResource {

    @Autowired
    private RecebimentoRepository repository;

    @GetMapping("/porPagamento/{dataI}/{dataF}/{loja}")
    private ResponseEntity<List<Recebimento>> porPagamento (@PathVariable String dataI, @PathVariable String dataF, @PathVariable Integer loja) {

        if(loja == 0) {
            return ResponseEntity.ok(repository.porPagamento(LocalDate.parse(dataI) , LocalDate.parse(dataF)));
        } else {
            return ResponseEntity.ok(repository.porPagamentoLolja(LocalDate.parse(dataI) , LocalDate.parse(dataF) , loja));
        }


    }
}
