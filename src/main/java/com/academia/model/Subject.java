package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    private String id;

    private String name;
    private String code;
    private String description;
    private int credits;
    private String area; // Área del conocimiento

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
