package com.baez.smart_inventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private Double prezzo;

    private Integer quantitaStock;

    private Integer scortaMinima;

    // Questo è il "Ponte" verso MongoDB
    // Salviamo solo l'ID del documento Mongo come una stringa
    private String mongoMetadataId;

    // Riferimento al fornitore (lo implementeremo tra poco come Relazione)
    // Per ora lo lasciamo come un semplice ID per non complicare
    private Long fornitoreId;
}
