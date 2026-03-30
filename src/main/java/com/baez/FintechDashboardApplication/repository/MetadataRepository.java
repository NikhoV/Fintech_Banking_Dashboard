package com.baez.FintechDashboardApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.baez.FintechDashboardApplication.model.ContoMetadata;

@Repository
public interface MetadataRepository extends MongoRepository<ContoMetadata, String> {
    // Questo gestirà i JSON fluidi su MongoDB
}