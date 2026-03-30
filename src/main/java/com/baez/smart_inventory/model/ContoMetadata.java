package com.baez.smart_inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.util.Map;

@Document(collection = "analisi_conti") // <-- Nome collezione più "Banking"
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder // Aggiungiamo Builder: i Senior lo adorano per creare oggetti al volo
public class ContoMetadata { // <-- Rinominata da ProdottoMetadata

    @Id 
    private String id;

    // Qui salviamo i dati comportamentali (es: "Media Spese Mensili", "Categoria Prevalente")
    private Map<String, Object> metricheComportamentali;

    // Qui l'IA scriverà i suoi consigli (es: "Ti consiglio di investire 200€ in ETF")
    private String aiAdvice;

    private String ultimaAnalisiIA;
}