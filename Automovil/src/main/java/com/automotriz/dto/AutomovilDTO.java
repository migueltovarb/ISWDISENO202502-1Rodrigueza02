package com.automotriz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomovilDTO {
    
    private String id;
    
    @NotBlank(message = "El fabricante es obligatorio")
    private String fabricante;
    
    @NotBlank(message = "La versión es obligatoria")
    private String version;
    
    @NotBlank(message = "La categoría de ruedas es obligatoria")
    @JsonProperty("categoriaRuedas")
    private String categoriaRuedas;
    
    @NotNull(message = "La cantidad de puertas es obligatoria")
    @Min(value = 1, message = "La cantidad de puertas debe ser al menos 1")
    @JsonProperty("cantidadPuertas")
    private Integer cantidadPuertas;
    
    @NotBlank(message = "El ID de instalación es obligatorio")
    @JsonProperty("instalacionId")
    private String instalacionId;
}
