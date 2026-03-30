package com.baez.FintechDashboardApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baez.FintechDashboardApplication.model.Conto;
import java.util.Optional;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Long> {
    Optional<Conto> findByIban(String iban);
}