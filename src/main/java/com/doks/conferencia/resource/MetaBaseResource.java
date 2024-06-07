package com.doks.conferencia.resource;

import com.doks.conferencia.model.Metabase;
import com.doks.conferencia.repository.MetaBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/metabase")
public class MetaBaseResource {

    @Autowired
    private MetaBaseRepository repository;
   // private static final String METABASE_SECRET_KEY = "0e3e2e71e1a38d8aa802d1ff8fc6824dfc4a39674f7093c121be1bed159fe85a";



    @GetMapping("/dashboard")
    public ResponseEntity<List<Metabase>> dashboard() {

        // Recuperando a MetaBase do banco de dados
        Metabase metaBase = repository.findFirstByIdNotNull();
        String metabaseSecretKey = metaBase.getSecretKey(); // Obtendo a chave secreta do banco de dados

        List<Metabase> metabase = repository.findAll();

        String payload = "{\"resource\": {\"dashboard\": 3}, \"params\": {}}";
        Jwt token = JwtHelper.encode(payload, new MacSigner(metabaseSecretKey));
        String tokenValue = token.getEncoded();

        String url = "/embed/dashboard/" + tokenValue +
                "#theme=light&bordered=false&titled=true&refresh=120";

        for (Metabase item : metabase) {
            item.setUrl(url);
        }

       // System.out.println(url);

        return ResponseEntity.ok(metabase);
    }
}
