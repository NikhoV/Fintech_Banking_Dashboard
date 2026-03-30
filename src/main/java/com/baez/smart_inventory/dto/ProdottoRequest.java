package com.baez.smart_inventory.dto;

import lombok.Data;
import java.util.Map;

@Data
public class ProdottoRequest {
    // Campi per MySQL
    private String nome;
    private Double prezzo;
    private Integer quantita;

    // Campi per MongoDB
    private Map<String, Object> caratteristiche;
}