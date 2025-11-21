package com.automotriz.repository;

import com.automotriz.model.Instalacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstalacionRepository extends MongoRepository<Instalacion, String> {
    List<Instalacion> findByManufacturaId(String manufacturaId);
}
