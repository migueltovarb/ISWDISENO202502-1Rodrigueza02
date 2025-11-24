package com.academia.controller;

import com.academia.model.Grade;
import com.academia.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createGrade(@RequestBody Grade grade) {
        try {
            Grade createdGrade = gradeService.createGrade(grade);
            return ResponseEntity.ok(createdGrade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear calificación: " + e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentGrades(@PathVariable String studentId) {
        List<Grade> grades = gradeService.getStudentGrades(studentId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/group/{groupId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getGroupGrades(@PathVariable String groupId) {
        List<Grade> grades = gradeService.getGroupGrades(groupId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> getTeacherGrades(@PathVariable String teacherId) {
        List<Grade> grades = gradeService.getTeacherGrades(teacherId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGradeById(@PathVariable String id) {
        return gradeService.getGradeById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> updateGrade(@PathVariable String id, @RequestBody Grade grade) {
        Grade updatedGrade = gradeService.updateGrade(id, grade);
        if (updatedGrade != null) {
            return ResponseEntity.ok(updatedGrade);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> deleteGrade(@PathVariable String id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok("Calificación eliminada");
    }

    @GetMapping("/student/{studentId}/average")
    public ResponseEntity<?> getStudentAverage(@PathVariable String studentId) {
        double average = gradeService.calculateStudentAverage(studentId);
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("studentId", studentId);
            put("average", average);
        }});
    }

    @GetMapping("/group/{groupId}/average")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getGroupAverage(@PathVariable String groupId) {
        double average = gradeService.calculateGroupAverage(groupId);
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("groupId", groupId);
            put("average", average);
        }});
    }
}
