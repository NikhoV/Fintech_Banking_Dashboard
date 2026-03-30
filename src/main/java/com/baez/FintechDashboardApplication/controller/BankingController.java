package com.baez.FintechDashboardApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baez.FintechDashboardApplication.dto.ContoRequest;
import com.baez.FintechDashboardApplication.model.Conto;
import com.baez.FintechDashboardApplication.model.ContoMetadata;
import com.baez.FintechDashboardApplication.service.BankingService;

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