package com.automotriz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "instalaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instalacion {
    
    @Id
    private String id;
    
    private String denominacion;
    
    private String localizacion;
    
    private String manufacturaId;
    
    public Instalacion(String denominacion, String localizacion, String manufacturaId) {
        this.denominacion = denominacion;
        this.localizacion = localizacion;
        this.manufacturaId = manufacturaId;
    }
}
