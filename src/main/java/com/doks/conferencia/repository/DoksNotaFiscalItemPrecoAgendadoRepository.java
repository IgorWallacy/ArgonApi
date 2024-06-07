package com.doks.conferencia.repository;

import com.doks.conferencia.model.DoksNotafiscalItemPrecoAgendado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DoksNotaFiscalItemPrecoAgendadoRepository  extends JpaRepository <DoksNotafiscalItemPrecoAgendado , Integer> {



}
