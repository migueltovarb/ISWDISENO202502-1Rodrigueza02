// src/main/java/com/vehiculos/repository/VehiculoRepository.java
package com.vehiculos.repository;

import com.vehiculos.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
    
    Optional<Vehiculo> findByPlaca(String placa);
    
    List<Vehiculo> findByMarca(String marca);
    
    List<Vehiculo> findByEstado(String estado);
    
    List<Vehiculo> findByPrecioGreaterThanAndPriceioLessThan(Double precioMin, Double precioMax);
    
    List<Vehiculo> findByAnno(Integer anno);
}