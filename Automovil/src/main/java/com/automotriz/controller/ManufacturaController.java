package com.automotriz.controller;

import com.automotriz.dto.ManufacturaDTO;
import com.automotriz.service.ManufacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturas")
@RequiredArgsConstructor
public class ManufacturaController {

    private final ManufacturaService manufacturaService;

    @PostMapping
    public ResponseEntity<ManufacturaDTO> crear(@Valid @RequestBody ManufacturaDTO dto) {
        ManufacturaDTO creada = manufacturaService.crear(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ManufacturaDTO>> obtenerTodas() {
        List<ManufacturaDTO> manufacturas = manufacturaService.obtenerTodas();
        return ResponseEntity.ok(manufacturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturaDTO> obtenerPorId(@PathVariable String id) {
        ManufacturaDTO manufactura = manufacturaService.obtenerPorId(id);
        return ResponseEntity.ok(manufactura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturaDTO> actualizar(
            @PathVariable String id,
            @Valid @RequestBody ManufacturaDTO dto) {
        ManufacturaDTO actualizada = manufacturaService.actualizar(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        manufacturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
