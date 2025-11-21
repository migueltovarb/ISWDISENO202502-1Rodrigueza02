package com.automotriz.repository;

import com.automotriz.model.Automovil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomovilRepository extends MongoRepository<Automovil, String> {
    List<Automovil> findByInstalacionId(String instalacionId);
    List<Automovil> findByFabricante(String fabricante);
}
