package com.baez.smart_inventory.controller;

import com.baez.smart_inventory.dto.ProdottoRequest;
import com.baez.smart_inventory.model.Prodotto;
import com.baez.smart_inventory.model.ProdottoMetadata;
import com.baez.smart_inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/crea")
    public Prodotto crea(@RequestBody ProdottoRequest request) {
        
        Prodotto p = new Prodotto();
        p.setNome(request.getNome());
        p.setPrezzo(request.getPrezzo());
        p.setQuantitaStock(request.getQuantita());

        ProdottoMetadata m = new ProdottoMetadata();
        m.setCaratteristiche(request.getCaratteristiche());

        return inventoryService.creaProdottoCompleto(p, m);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return inventoryService.getProdottoCompleto(id);
    }
}