package com.baez.FintechDashboardApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transazioni")
@Data
public class Transazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal importo;

    @Column(nullable = false)
    private LocalDateTime dataTransazione;

    @Enumerated(EnumType.STRING)
    private TipoTransazione tipo; // ENTRATA o USCITA

    // RELAZIONE: Molte transazioni appartengono a un solo conto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conto_id", nullable = false)
    private Conto conto;

    public enum TipoTransazione {
        ENTRATA, USCITA
    }
}