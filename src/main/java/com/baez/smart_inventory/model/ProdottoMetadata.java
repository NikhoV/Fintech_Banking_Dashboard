package com.baez.smart_inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.util.Map;

@Document(collection = "dettagli_prodotti") // Indica a MongoDB il nome della "tabella" (collezione)
@Data // Genera automaticante Getter e Setter con Lombok
@NoArgsConstructor // Costruttore vuoto necessario per Spring
@AllArgsConstructor // Costruttore con tutti i campi
public class ProdottoMetadata {

    @Id // Questo è l'ID univoco di MongoDB (sarà una stringa alfanumerica)
    private String id;

    // Questa Mappa è il "segreto" della flessibilità: 
    // puoi salvarci "Colore" -> "Rosso", "RAM" -> "16GB", "Peso" -> 1.2, ecc.
    private Map<String, Object> caratteristiche;

    // Qui l'IA scriverà i suoi consigli personalizzati
    private String suggerimentoIA;

    // Possiamo aggiungere un campo per la data di ultima analisi
    private String ultimaRevisioneIA;
}