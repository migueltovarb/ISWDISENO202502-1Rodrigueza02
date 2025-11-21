package com.automotriz.controller;

import com.automotriz.dto.AutomovilDTO;
import com.automotriz.service.AutomovilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automoviles")
@RequiredArgsConstructor
public class AutomovilController {

    private final AutomovilService automovilService;

    @PostMapping
    public ResponseEntity<AutomovilDTO> crear(@Valid @RequestBody AutomovilDTO dto) {
        AutomovilDTO creado = automovilService.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AutomovilDTO>> obtenerTodos() {
        List<AutomovilDTO> automoviles = automovilService.obtenerTodos();
        return ResponseEntity.ok(automoviles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomovilDTO> obtenerPorId(@PathVariable String id) {
        AutomovilDTO automovil = automovilService.obtenerPorId(id);
        return ResponseEntity.ok(automovil);
    }

    @GetMapping("/instalacion/{instalacionId}")
    public ResponseEntity<List<AutomovilDTO>> obtenerPorInstalacionId(
            @PathVariable String instalacionId) {
        List<AutomovilDTO> automoviles = automovilService.obtenerPorInstalacionId(instalacionId);
        return ResponseEntity.ok(automoviles);
    }

    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<List<AutomovilDTO>> obtenerPorFabricante(
            @PathVariable String fabricante) {
        List<AutomovilDTO> automoviles = automovilService.obtenerPorFabricante(fabricante);
        return ResponseEntity.ok(automoviles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutomovilDTO> actualizar(
            @PathVariable String id,
            @Valid @RequestBody AutomovilDTO dto) {
        AutomovilDTO actualizado = automovilService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        automovilService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
