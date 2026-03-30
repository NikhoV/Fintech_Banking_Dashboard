package com.baez.smart_inventory.repository;

import com.baez.smart_inventory.model.Conto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Long> {
    // Qui Spring crea automaticamente i metodi save(), findAll(), deleteById()...
}