package com.doks.conferencia.resource;

import com.doks.conferencia.model.Usuario;
import com.doks.conferencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioLogadoResource {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/logado/{codigo}")
    private ResponseEntity<Usuario> usuarioLogado (@PathVariable String codigo) {

        return ResponseEntity.ok(repository.peloCodigo(codigo));
    }
}
