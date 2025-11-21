package com.automotriz.service;

import com.automotriz.dto.InstalacionDTO;
import com.automotriz.exception.OperacionInvalidaException;
import com.automotriz.exception.RecursoNoEncontradoException;
import com.automotriz.model.Automovil;
import com.automotriz.model.Instalacion;
import com.automotriz.model.Manufactura;
import com.automotriz.repository.AutomovilRepository;
import com.automotriz.repository.InstalacionRepository;
import com.automotriz.repository.ManufacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstalacionService {

    private final InstalacionRepository instalacionRepository;
    private final ManufacturaRepository manufacturaRepository;
    private final AutomovilRepository automovilRepository;

    public InstalacionDTO crear(InstalacionDTO dto) {
        Manufactura manufactura = manufacturaRepository.findById(dto.getManufacturaId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Manufactura no encontrada con ID: " + dto.getManufacturaId()));
        
        Instalacion instalacion = new Instalacion(
                dto.getDenominacion(),
                dto.getLocalizacion(),
                dto.getManufacturaId()
        );
        
        Instalacion guardada = instalacionRepository.save(instalacion);
        
        manufactura.getInstalacionesIds().add(guardada.getId());
        manufacturaRepository.save(manufactura);
        
        return convertirADTO(guardada);
    }

    public List<InstalacionDTO> obtenerTodas() {
        return instalacionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public InstalacionDTO obtenerPorId(String id) {
        Instalacion instalacion = instalacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Instalación no encontrada con ID: " + id));
        return convertirADTO(instalacion);
    }

    public List<InstalacionDTO> obtenerPorManufacturaId(String manufacturaId) {
        return instalacionRepository.findByManufacturaId(manufacturaId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public InstalacionDTO actualizar(String id, InstalacionDTO dto) {
        Instalacion instalacion = instalacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Instalación no encontrada con ID: " + id));
        
        instalacion.setDenominacion(dto.getDenominacion());
        instalacion.setLocalizacion(dto.getLocalizacion());
        
        Instalacion actualizada = instalacionRepository.save(instalacion);
        return convertirADTO(actualizada);
    }

    public void eliminar(String id) {
        Instalacion instalacion = instalacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Instalación no encontrada con ID: " + id));
        
        List<Automovil> automoviles = automovilRepository.findByInstalacionId(id);
        if (!automoviles.isEmpty()) {
            throw new OperacionInvalidaException(
                    "No se puede eliminar la instalación porque tiene automóviles asociados");
        }
        
        Manufactura manufactura = manufacturaRepository.findById(instalacion.getManufacturaId())
                .orElse(null);
        if (manufactura != null) {
            manufactura.getInstalacionesIds().remove(id);
            manufacturaRepository.save(manufactura);
        }
        
        instalacionRepository.deleteById(id);
    }

    private InstalacionDTO convertirADTO(Instalacion instalacion) {
        return new InstalacionDTO(
                instalacion.getId(),
                instalacion.getDenominacion(),
                instalacion.getLocalizacion(),
                instalacion.getManufacturaId()
        );
    }
}
