package com.baez.FintechDashboardApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baez.FintechDashboardApplication.model.Conto;
import com.baez.FintechDashboardApplication.model.ContoMetadata;
import com.baez.FintechDashboardApplication.repository.ContoRepository;
import com.baez.FintechDashboardApplication.repository.MetadataRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankingService {

    @Autowired
    private ContoRepository contoRepository;

    @Autowired
    private MetadataRepository metadataRepository;

    // 1. Metodo per CREARE il conto (Salva su MySQL e MongoDB)
    @Transactional
    public Conto creaContoCompleto(Conto conto, ContoMetadata metadata) {
        // Salvo prima i metadati su MongoDB per ottenere l'ID
        ContoMetadata savedMetadata = metadataRepository.save(metadata);
        
        // Collego l'ID di MongoDB al record MySQL
        conto.setAiAdviceId(savedMetadata.getId());
        
        return contoRepository.save(conto);
    }

    // 2. Metodo per LEGGERE il conto (Unisce MySQL + MongoDB)
    public Map<String, Object> getContoCompleto(Long id) {
        Conto c = contoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conto non trovato con ID: " + id));

        // Cerco i dettagli extra su MongoDB
        ContoMetadata m = metadataRepository.findById(c.getAiAdviceId())
                .orElse(new ContoMetadata());

        // Unione dei dati per il Frontend Angular
        Map<String, Object> response = new HashMap<>();
        response.put("id", c.getId());
        response.put("iban", c.getIban());
        response.put("titolare", c.getTitolare());
        response.put("saldo", c.getSaldo());
        response.put("dettagli_ia", m.getAiAdvice()); // I dati dinamici NoSQL
        
        return response;
    }
}