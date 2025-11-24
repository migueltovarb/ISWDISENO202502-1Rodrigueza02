package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "academic_periods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicPeriod {
    @Id
    private String id;

    private String name; // Ej: "Periodo 1", "Periodo 2"
    private int periodNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String academicYear;

    private boolean active;
}
