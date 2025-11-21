package com.automotriz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturaDTO {
    
    private String id;
    
    @NotBlank(message = "La denominación es obligatoria")
    private String denominacion;
    
    @NotBlank(message = "La región es obligatoria")
    private String region;
    
    private List<String> instalacionesIds;
}
