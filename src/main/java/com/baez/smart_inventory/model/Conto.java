package com.baez.smart_inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal; // IMPORTANTE: Solo BigDecimal per i soldi!

@Entity
@Table(name = "conti")
@Data
public class Conto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String iban;

    @Column(nullable = false)
    private String titolare;

    // Precisione 19 cifre, 2 decimali (Standard bancario)
    @Column(precision = 19, scale = 2)
    private BigDecimal saldo;

    // ID del documento MongoDB per i consigli IA
    private String aiAdviceId;
}