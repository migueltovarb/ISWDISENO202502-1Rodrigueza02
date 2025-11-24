package com.academia.controller;

import com.academia.model.Enrollment;
import com.academia.repository.EnrollmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentController(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEnrollment(@RequestBody Enrollment enrollment) {
        enrollment.setCreatedAt(LocalDateTime.now());
        enrollment.setEnrollmentDate(LocalDateTime.now());
        Enrollment created = enrollmentRepository.save(enrollment);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollment(@PathVariable String id) {
        return enrollmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentEnrollments(@PathVariable String studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/group/{groupId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getGroupEnrollments(@PathVariable String groupId) {
        List<Enrollment> enrollments = enrollmentRepository.findByGroupId(groupId);
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEnrollment(@PathVariable String id, @RequestBody Enrollment enrollment) {
        return enrollmentRepository.findById(id)
                .map(existing -> {
                    existing.setStatus(enrollment.getStatus());
                    return ResponseEntity.ok(enrollmentRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEnrollment(@PathVariable String id) {
        enrollmentRepository.deleteById(id);
        return ResponseEntity.ok("Inscripción eliminada");
    }
}
