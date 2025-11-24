package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "grades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    @Id
    private String id;

    private String studentId;
    private String subjectId;
    private String groupId;
    private String academicPeriodId;

    private double score; // Calificación
    private String evaluationType; // PARTIAL, FINAL, PRACTICAL
    private String comments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String teacherId;
}
