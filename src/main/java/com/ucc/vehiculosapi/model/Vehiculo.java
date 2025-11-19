// src/main/java/com/vehiculos/model/Vehiculo.java
package com.vehiculos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Document(collection = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    
    @Id
    private String id;
    
    @NotBlank(message = "La placa es requerida")
    @Size(min = 6, max = 10, message = "La placa debe tener entre 6 y 10 caracteres")
    private String placa;
    
    @NotBlank(message = "La marca es requerida")
    private String marca;
    
    @NotBlank(message = "El modelo es requerido")
    private String modelo;
    
    @NotNull(message = "El año es requerido")
    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    @Max(value = 2100, message = "El año debe ser menor a 2100")
    private Integer anno;
    
    @NotBlank(message = "El color es requerido")
    private String color;
    
    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private Double precio;
    
    @NotBlank(message = "El tipo de combustible es requerido")
    private String tipoCombustible; // GASOLINA, DIESEL, HIBRIDO, ELECTRICO
    
    @NotNull(message = "El número de puertas es requerido")
    @Min(2)
    @Max(5)
    private Integer numeroPuertas;
    
    private String descripcion;
    
    @NotBlank(message = "El estado es requerido")
    private String estado; // DISPONIBLE, VENDIDO, MANTENIMIENTO
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}