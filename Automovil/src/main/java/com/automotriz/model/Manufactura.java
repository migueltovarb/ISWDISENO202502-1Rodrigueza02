package com.automotriz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "manufacturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufactura {
    
    @Id
    private String id;
    
    private String denominacion;
    
    private String region;
    
    private List<String> instalacionesIds = new ArrayList<>();
    
    public Manufactura(String denominacion, String region) {
        this.denominacion = denominacion;
        this.region = region;
        this.instalacionesIds = new ArrayList<>();
    }
}
