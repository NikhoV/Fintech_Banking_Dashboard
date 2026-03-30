package com.baez.FintechDashboardApplication.repository;

import com.baez.FintechDashboardApplication.model.Transazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransazioneRepository extends JpaRepository<Transazione, Long> {
    // Questo ci servirà per i grafici: "Dammi tutte le transazioni di questo conto"
    List<Transazione> findByContoIdOrderByDataTransazioneDesc(Long contoId);
}