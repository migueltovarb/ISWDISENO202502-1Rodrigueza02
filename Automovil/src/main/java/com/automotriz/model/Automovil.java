package com.automotriz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "automoviles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Automovil {
    
    @Id
    private String id;
    
    private String fabricante;
    
    private String version;
    
    @JsonProperty("categoriaRuedas")
    private String categoriaRuedas;
    
    @JsonProperty("cantidadPuertas")
    private Integer cantidadPuertas;
    
    @JsonProperty("instalacionId")
    private String instalacionId;
    
    public Automovil(String fabricante, String version, String categoriaRuedas, 
                     Integer cantidadPuertas, String instalacionId) {
        this.fabricante = fabricante;
        this.version = version;
        this.categoriaRuedas = categoriaRuedas;
        this.cantidadPuertas = cantidadPuertas;
        this.instalacionId = instalacionId;
    }
}
