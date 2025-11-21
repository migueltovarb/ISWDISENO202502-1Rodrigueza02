package com.automotriz.service;

import com.automotriz.dto.ManufacturaDTO;
import com.automotriz.exception.OperacionInvalidaException;
import com.automotriz.exception.RecursoNoEncontradoException;
import com.automotriz.model.Manufactura;
import com.automotriz.repository.InstalacionRepository;
import com.automotriz.repository.ManufacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturaService {

    private final ManufacturaRepository manufacturaRepository;
    private final InstalacionRepository instalacionRepository;

    public ManufacturaDTO crear(ManufacturaDTO dto) {
        Manufactura manufactura = new Manufactura(dto.getDenominacion(), dto.getRegion());
        Manufactura guardada = manufacturaRepository.save(manufactura);
        return convertirADTO(guardada);
    }

    public List<ManufacturaDTO> obtenerTodas() {
        return manufacturaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ManufacturaDTO obtenerPorId(String id) {
        Manufactura manufactura = manufacturaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Manufactura no encontrada con ID: " + id));
        return convertirADTO(manufactura);
    }

    public ManufacturaDTO actualizar(String id, ManufacturaDTO dto) {
        Manufactura manufactura = manufacturaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Manufactura no encontrada con ID: " + id));
        
        manufactura.setDenominacion(dto.getDenominacion());
        manufactura.setRegion(dto.getRegion());
        
        Manufactura actualizada = manufacturaRepository.save(manufactura);
        return convertirADTO(actualizada);
    }

    public void eliminar(String id) {
        Manufactura manufactura = manufacturaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Manufactura no encontrada con ID: " + id));
        
        List<String> instalacionesIds = manufactura.getInstalacionesIds();
        if (instalacionesIds != null && !instalacionesIds.isEmpty()) {
            throw new OperacionInvalidaException(
                    "No se puede eliminar la manufactura porque tiene instalaciones asociadas");
        }
        
        manufacturaRepository.deleteById(id);
    }

    private ManufacturaDTO convertirADTO(Manufactura manufactura) {
        return new ManufacturaDTO(
                manufactura.getId(),
                manufactura.getDenominacion(),
                manufactura.getRegion(),
                manufactura.getInstalacionesIds()
        );
    }
}
