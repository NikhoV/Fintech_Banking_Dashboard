package com.baez.FintechDashboardApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baez.FintechDashboardApplication.model.Conto;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Long> {
    // Qui Spring crea automaticamente i metodi save(), findAll(), deleteById()...
}