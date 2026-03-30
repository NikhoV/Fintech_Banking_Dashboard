package com.baez.FintechDashboardApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal; 
import java.util.List;

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

    @OneToMany(mappedBy = "conto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transazione> transazioni;
}