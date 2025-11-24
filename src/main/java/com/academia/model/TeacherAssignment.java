package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "teacher_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAssignment {
    @Id
    private String id;

    private String teacherId;
    private String subjectId;
    private String groupId;
    private String academicYearId;

    private LocalDateTime assignedAt;
    private LocalDateTime updatedAt;
}
