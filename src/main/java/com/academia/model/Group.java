package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private String id;

    private String name; // Ej: 10-A
    private String grade; // Ej: 10
    private String section; // Ej: A
    private int maxStudents;
    private String academicYearId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
