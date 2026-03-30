package com.baez.smart_inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "conti")
@Data
public class Conto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolare;

    @Column(precision = 19, scale = 2)
    private BigDecimal saldo;

    // Colleghiamo i metadati dell'IA (MongoDB)
    private String aiAdviceId; 
}