package com.baez.FintechDashboardApplication.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String ibanMittente;
    private String ibanDestinatario;
    private BigDecimal importo;
    private String descrizione;
}