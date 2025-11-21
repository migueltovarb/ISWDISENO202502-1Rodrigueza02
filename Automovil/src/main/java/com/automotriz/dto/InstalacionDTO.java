package com.automotriz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstalacionDTO {
    
    private String id;
    
    @NotBlank(message = "La denominación es obligatoria")
    private String denominacion;
    
    @NotBlank(message = "La localización es obligatoria")
    private String localizacion;
    
    @NotBlank(message = "El ID de manufactura es obligatorio")
    private String manufacturaId;
}
