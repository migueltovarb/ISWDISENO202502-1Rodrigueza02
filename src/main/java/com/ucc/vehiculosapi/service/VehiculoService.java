// src/main/java/com/vehiculos/service/VehiculoService.java
package com.vehiculos.service;

import com.vehiculos.dto.VehiculoDTO;
import com.vehiculos.model.Vehiculo;
import com.vehiculos.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    
    private final VehiculoRepository vehiculoRepository;
    
    @Transactional
    public Vehiculo crear(VehiculoDTO vehiculoDTO) {
        // Validar que no exista vehículo con la misma placa
        if (vehiculoRepository.findByPlaca(vehiculoDTO.getPlaca()).isPresent()) {
            throw new RuntimeException("Ya existe un vehículo con esa placa");
        }
        
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(vehiculoDTO.getPlaca());
        vehiculo.setMarca(vehiculoDTO.getMarca());
        vehiculo.setModelo(vehiculoDTO.getModelo());
        vehiculo.setAnno(vehiculoDTO.getAnno());
        vehiculo.setColor(vehiculoDTO.getColor());
        vehiculo.setPrecio(vehiculoDTO.getPrecio());
        vehiculo.setTipoCombustible(vehiculoDTO.getTipoCombustible());
        vehiculo.setNumeroPuertas(vehiculoDTO.getNumeroPuertas());
        vehiculo.setDescripcion(vehiculoDTO.getDescripcion());
        vehiculo.setEstado(vehiculoDTO.getEstado());
        vehiculo.setFechaCreacion(LocalDateTime.now());
        vehiculo.setFechaActualizacion(LocalDateTime.now());
        
        return vehiculoRepository.save(vehiculo);
    }
    
    @Transactional(readOnly = true)
    public Optional<Vehiculo> obtenerPorId(String id) {
        return vehiculoRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Vehiculo> obtenerPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }
    
    @Transactional(readOnly = true)
    public List<Vehiculo> obtenerPorMarca(String marca) {
        return vehiculoRepository.findByMarca(marca);
    }
    
    @Transactional(readOnly = true)
    public List<Vehiculo> obtenerPorEstado(String estado) {
        return vehiculoRepository.findByEstado(estado);
    }
    
    @Transactional
    public Vehiculo actualizar(String id, VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        
        vehiculo.setPlaca(vehiculoDTO.getPlaca());
        vehiculo.setMarca(vehiculoDTO.getMarca());
        vehiculo.setModelo(vehiculoDTO.getModelo());
        vehiculo.setAnno(vehiculoDTO.getAnno());
        vehiculo.setColor(vehiculoDTO.getColor());
        vehiculo.setPrecio(vehiculoDTO.getPrecio());
        vehiculo.setTipoCombustible(vehiculoDTO.getTipoCombustible());
        vehiculo.setNumeroPuertas(vehiculoDTO.getNumeroPuertas());
        vehiculo.setDescripcion(vehiculoDTO.getDescripcion());
        vehiculo.setEstado(vehiculoDTO.getEstado());
        vehiculo.setFechaActualizacion(LocalDateTime.now());
        
        return vehiculoRepository.save(vehiculo);
    }
    
    @Transactional
    public void eliminar(String id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("Vehículo no encontrado");
        }
        vehiculoRepository.deleteById(id);
    }
}