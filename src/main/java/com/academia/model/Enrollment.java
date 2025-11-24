package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    private String id;

    private String studentId;
    private String groupId;
    private String academicYearId;

    private LocalDateTime enrollmentDate;
    private String status; // ACTIVE, DROPPED, GRADUATED

    private LocalDateTime createdAt;
}
