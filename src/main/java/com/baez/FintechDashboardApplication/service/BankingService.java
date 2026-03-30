package com.baez.FintechDashboardApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baez.FintechDashboardApplication.model.Conto;
import com.baez.FintechDashboardApplication.model.ContoMetadata;
import com.baez.FintechDashboardApplication.model.Transazione;
import com.baez.FintechDashboardApplication.repository.ContoRepository;
import com.baez.FintechDashboardApplication.repository.MetadataRepository;
import com.baez.FintechDashboardApplication.repository.TransazioneRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Autowired
    private TransazioneRepository transazioneRepository;

    @Transactional
    public void eseguiBonifico(String ibanMittente, String ibanDestinatario, BigDecimal importo, String descrizione) {
        
        // 1. Recupero i conti
        Conto mittente = contoRepository.findByIban(ibanMittente)
                .orElseThrow(() -> new RuntimeException("Mittente non trovato"));
        Conto destinatario = contoRepository.findByIban(ibanDestinatario)
                .orElseThrow(() -> new RuntimeException("Destinatario non trovato"));

        // 2. Controllo Saldo (Business Rule)
        if (mittente.getSaldo().compareTo(importo) < 0) {
            throw new RuntimeException("Saldo insufficiente per completare il bonifico!");
        }

        // 3. Aggiornamento Saldi (SQL)
        mittente.setSaldo(mittente.getSaldo().subtract(importo));
        destinatario.setSaldo(destinatario.getSaldo().add(importo));

        contoRepository.save(mittente);
        contoRepository.save(destinatario);

        // 4. Creazione Record Storici (Le Transazioni per i grafici Angular)
        
        // Transazione in USCITA per il mittente
        Transazione tUscita = new Transazione();
        tUscita.setDescrizione("Bonifico vs " + destinatario.getTitolare() + ": " + descrizione);
        tUscita.setImporto(importo.negate()); // Numero negativo per i grafici
        tUscita.setDataTransazione(LocalDateTime.now());
        tUscita.setTipo(Transazione.TipoTransazione.USCITA);
        tUscita.setConto(mittente);
        transazioneRepository.save(tUscita);

        // Transazione in ENTRATA per il destinatario
        Transazione tEntrata = new Transazione();
        tEntrata.setDescrizione("Bonifico da " + mittente.getTitolare() + ": " + descrizione);
        tEntrata.setImporto(importo);
        tEntrata.setDataTransazione(LocalDateTime.now());
        tEntrata.setTipo(Transazione.TipoTransazione.ENTRATA);
        tEntrata.setConto(destinatario);
        transazioneRepository.save(tEntrata);

    }
}