package com.baez.smart_inventory.service;

import com.baez.smart_inventory.model.Prodotto;
import com.baez.smart_inventory.model.ProdottoMetadata;
import com.baez.smart_inventory.repository.ProdottoRepository;
import com.baez.smart_inventory.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Service
public class InventoryService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private MetadataRepository metadataRepository;

    @Transactional // Se uno dei due salvataggi fallisce, annulla tutto (Solidità!)
    public Prodotto creaProdottoCompleto(Prodotto prodotto, ProdottoMetadata metadata) {
        
        // 1. Salviamo i metadati su MongoDB
        ProdottoMetadata metaSalvato = metadataRepository.save(metadata);
        
        // 2. Prendiamo l'ID di MongoDB e lo mettiamo nel prodotto SQL
        prodotto.setMongoMetadataId(metaSalvato.getId());
        
        // 3. Salviamo il prodotto principale su MySQL
        return prodottoRepository.save(prodotto);
    }
    
    public Map<String, Object> getProdottoCompleto(Long id) {
    // 1. Cerca su MySQL
    Prodotto p = prodottoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

    // 2. Cerca su MongoDB usando l'ID salvato nel prodotto
    ProdottoMetadata m = metadataRepository.findById(p.getMongoMetadataId())
            .orElse(new ProdottoMetadata()); // Se non c'è, restituiamo un oggetto vuoto

    // 3. Uniamo i dati in una mappa per il frontend
    return Map.of(
        "id", p.getId(),
        "nome", p.getNome(),
        "prezzo", p.getPrezzo(),
        "quantita", p.getQuantitaStock(),
        "dettagli_tecnici", m.getCaratteristiche(),
        "analisi_ia", m.getSuggerimentoIA() != null ? m.getSuggerimentoIA() : "In attesa di analisi..."
    );
}
}

