package com.doks.conferencia.service;

import com.doks.conferencia.model.NFCe;
import com.doks.conferencia.repository.NfceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateNfceTo9 {

    @Autowired
    private NfceRepository repository;

    private static final Logger log = LoggerFactory.getLogger(NFCe.class);


    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void updateTo9 () {
        log.info("Atualizando para contingencia nfce");
        repository.updateNfceTo9();
    }


}
