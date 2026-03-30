package com.baez.smart_inventory.controller;

import com.baez.smart_inventory.dto.ContoRequest;
import com.baez.smart_inventory.model.Conto;
import com.baez.smart_inventory.model.ContoMetadata;
import com.baez.smart_inventory.service.BankingService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/conti") 
public class BankingController {

    @Autowired
    private BankingService bankingService; 

    @PostMapping("/crea")
    public Conto crea(@RequestBody ContoRequest request ) {
        
        Conto c = new Conto();
        c.setIban(request.getIban());
        c.setTitolare(request.getTitolare()); 
        c.setSaldo(request.getSaldo());

        ContoMetadata m = new ContoMetadata();
        m.setMetricheComportamentali(request.getMetricheComportamentali());
        m.setAiAdvice(request.getAiAdvice());
        m.setUltimaAnalisiIA(request.getUltimaAnalisiIA());

        return bankingService.creaContoCompleto(c, m);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return bankingService.getContoCompleto(id); 
    }
}