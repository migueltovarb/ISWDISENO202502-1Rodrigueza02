package com.automotriz.controller;

import com.automotriz.dto.InstalacionDTO;
import com.automotriz.service.InstalacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instalaciones")
@RequiredArgsConstructor
public class InstalacionController {

    private final InstalacionService instalacionService;

    @PostMapping
    public ResponseEntity<InstalacionDTO> crear(@Valid @RequestBody InstalacionDTO dto) {
        InstalacionDTO creada = instalacionService.crear(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InstalacionDTO>> obtenerTodas() {
        List<InstalacionDTO> instalaciones = instalacionService.obtenerTodas();
        return ResponseEntity.ok(instalaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstalacionDTO> obtenerPorId(@PathVariable String id) {
        InstalacionDTO instalacion = instalacionService.obtenerPorId(id);
        return ResponseEntity.ok(instalacion);
    }

    @GetMapping("/manufactura/{manufacturaId}")
    public ResponseEntity<List<InstalacionDTO>> obtenerPorManufacturaId(
            @PathVariable String manufacturaId) {
        List<InstalacionDTO> instalaciones = instalacionService.obtenerPorManufacturaId(manufacturaId);
        return ResponseEntity.ok(instalaciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstalacionDTO> actualizar(
            @PathVariable String id,
            @Valid @RequestBody InstalacionDTO dto) {
        InstalacionDTO actualizada = instalacionService.actualizar(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        instalacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
