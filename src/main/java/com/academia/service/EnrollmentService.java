package com.academia.service;

import com.academia.model.Enrollment;
import com.academia.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        enrollment.setCreatedAt(LocalDateTime.now());
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus("ACTIVE");
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(String id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> getStudentEnrollments(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment updateEnrollment(String id, Enrollment updatedEnrollment) {
        Optional<Enrollment> existing = enrollmentRepository.findById(id);
        if (existing.isPresent()) {
            Enrollment enrollment = existing.get();
            enrollment.setStatus(updatedEnrollment.getStatus());
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }

    public void deleteEnrollment(String id) {
        enrollmentRepository.deleteById(id);
    }
}
