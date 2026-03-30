package com.baez.smart_inventory.repository;

import com.baez.smart_inventory.model.ContoMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends MongoRepository<ContoMetadata, String> {
    // Questo gestirà i JSON fluidi su MongoDB
}