package com.automotriz.repository;

import com.automotriz.model.Manufactura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturaRepository extends MongoRepository<Manufactura, String> {
}
