// src/main/java/com/vehiculos/dto/VehiculoDTO.java
package com.vehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
    
    @NotBlank(message = "La placa es requerida")
    private String placa;
    
    @NotBlank(message = "La marca es requerida")
    private String marca;
    
    @NotBlank(message = "El modelo es requerido")
    private String modelo;
    
    @NotNull(message = "El año es requerido")
    @Min(1900)
    private Integer anno;
    
    @NotBlank(message = "El color es requerido")
    private String color;
    
    @NotNull(message = "El precio es requerido")
    @DecimalMin("0.0")
    private Double precio;
    
    @NotBlank(message = "El tipo de combustible es requerido")
    private String tipoCombustible;
    
    @NotNull(message = "El número de puertas es requerido")
    @Min(2)
    @Max(5)
    private Integer numeroPuertas;
    
    private String descripcion;
    
    @NotBlank(message = "El estado es requerido")
    private String estado;
}