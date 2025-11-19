// src/main/java/com/vehiculos/controller/VehiculoController.java
package com.vehiculos.controller;

import com.vehiculos.dto.VehiculoDTO;
import com.vehiculos.model.Vehiculo;
import com.vehiculos.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
@RequiredArgsConstructor
@Tag(name = "Vehículos", description = "API para gestionar vehículos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VehiculoController {
    
    private final VehiculoService vehiculoService;
    
    @PostMapping
    @Operation(summary = "Crear nuevo vehículo")
    public ResponseEntity<Vehiculo> crear(@Valid @RequestBody VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoService.crear(vehiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculo);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehículo por ID")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable String id) {
        return vehiculoService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los vehículos")
    public ResponseEntity<List<Vehiculo>> obtenerTodos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodos();
        return ResponseEntity.ok(vehiculos);
    }
    
    @GetMapping("/placa/{placa}")
    @Operation(summary = "Obtener vehículo por placa")
    public ResponseEntity<Vehiculo> obtenerPorPlaca(@PathVariable String placa) {
        return vehiculoService.obtenerPorPlaca(placa)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/marca/{marca}")
    @Operation(summary = "Obtener vehículos por marca")
    public ResponseEntity<List<Vehiculo>> obtenerPorMarca(@PathVariable String marca) {
        List<Vehiculo> vehiculos = vehiculoService.obtenerPorMarca(marca);
        return ResponseEntity.ok(vehiculos);
    }
    
    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener vehículos por estado")
    public ResponseEntity<List<Vehiculo>> obtenerPorEstado(@PathVariable String estado) {
        List<Vehiculo> vehiculos = vehiculoService.obtenerPorEstado(estado);
        return ResponseEntity.ok(vehiculos);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar vehículo")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable String id, @Valid @RequestBody VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoService.actualizar(id, vehiculoDTO);
        return ResponseEntity.ok(vehiculo);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar vehículo")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}