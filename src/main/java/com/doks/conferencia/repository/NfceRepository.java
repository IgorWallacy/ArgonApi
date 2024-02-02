package com.doks.conferencia.repository;

import com.doks.conferencia.model.NFCe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NfceRepository extends JpaRepository<NFCe , Integer> {
    @Modifying(clearAutomatically = true)
    @Query(value = "update filial set formaemissaonfce = '9' ", nativeQuery = true)
    void updateNfceTo9();
}
