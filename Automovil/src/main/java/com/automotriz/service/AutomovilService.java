package com.automotriz.service;

import com.automotriz.dto.AutomovilDTO;
import com.automotriz.exception.RecursoNoEncontradoException;
import com.automotriz.model.Automovil;
import com.automotriz.model.Instalacion;
import com.automotriz.repository.AutomovilRepository;
import com.automotriz.repository.InstalacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutomovilService {

    private final AutomovilRepository automovilRepository;
    private final InstalacionRepository instalacionRepository;

    public AutomovilDTO crear(AutomovilDTO dto) {
        Instalacion instalacion = instalacionRepository.findById(dto.getInstalacionId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Instalación no encontrada con ID: " + dto.getInstalacionId()));
        
        Automovil automovil = new Automovil(
                dto.getFabricante(),
                dto.getVersion(),
                dto.getCategoriaRuedas(),
                dto.getCantidadPuertas(),
                dto.getInstalacionId()
        );
        
        Automovil guardado = automovilRepository.save(automovil);
        return convertirADTO(guardado);
    }

    public List<AutomovilDTO> obtenerTodos() {
        return automovilRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public AutomovilDTO obtenerPorId(String id) {
        Automovil automovil = automovilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Automóvil no encontrado con ID: " + id));
        return convertirADTO(automovil);
    }

    public List<AutomovilDTO> obtenerPorInstalacionId(String instalacionId) {
        return automovilRepository.findByInstalacionId(instalacionId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<AutomovilDTO> obtenerPorFabricante(String fabricante) {
        return automovilRepository.findByFabricante(fabricante).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public AutomovilDTO actualizar(String id, AutomovilDTO dto) {
        Automovil automovil = automovilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Automóvil no encontrado con ID: " + id));
        
        automovil.setFabricante(dto.getFabricante());
        automovil.setVersion(dto.getVersion());
        automovil.setCategoriaRuedas(dto.getCategoriaRuedas());
        automovil.setCantidadPuertas(dto.getCantidadPuertas());
        
        Automovil actualizado = automovilRepository.save(automovil);
        return convertirADTO(actualizado);
    }

    public void eliminar(String id) {
        Automovil automovil = automovilRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Automóvil no encontrado con ID: " + id));
        
        automovilRepository.deleteById(id);
    }

    private AutomovilDTO convertirADTO(Automovil automovil) {
        return new AutomovilDTO(
                automovil.getId(),
                automovil.getFabricante(),
                automovil.getVersion(),
                automovil.getCategoriaRuedas(),
                automovil.getCantidadPuertas(),
                automovil.getInstalacionId()
        );
    }
}
