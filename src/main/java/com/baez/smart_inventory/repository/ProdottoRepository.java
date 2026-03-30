package com.baez.smart_inventory.repository;

import com.baez.smart_inventory.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    // Qui Spring crea automaticamente i metodi save(), findAll(), deleteById()...
}