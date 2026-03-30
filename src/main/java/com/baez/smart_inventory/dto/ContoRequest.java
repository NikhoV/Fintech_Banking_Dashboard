package com.baez.smart_inventory.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class ContoRequest {
    // Campi MySQL
    private String iban;
    private String titolare;
    private BigDecimal saldo;

    // Campi MongoDB (Devono matchare quelli che usi nel Controller)
    private Map<String, Object> metricheComportamentali;
    private String aiAdvice; 
    private String ultimaAnalisiIA;
}