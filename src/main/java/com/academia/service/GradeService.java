package com.academia.service;

import com.academia.model.Grade;
import com.academia.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade createGrade(Grade grade) {
        grade.setCreatedAt(LocalDateTime.now());
        grade.setUpdatedAt(LocalDateTime.now());
        return gradeRepository.save(grade);
    }

    public List<Grade> getStudentGrades(String studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> getGroupGrades(String groupId) {
        return gradeRepository.findByGroupId(groupId);
    }

    public List<Grade> getTeacherGrades(String teacherId) {
        return gradeRepository.findByTeacherId(teacherId);
    }

    public Optional<Grade> getGradeById(String id) {
        return gradeRepository.findById(id);
    }

    public Grade updateGrade(String id, Grade updatedGrade) {
        Optional<Grade> existing = gradeRepository.findById(id);
        if (existing.isPresent()) {
            Grade grade = existing.get();
            grade.setScore(updatedGrade.getScore());
            grade.setComments(updatedGrade.getComments());
            grade.setUpdatedAt(LocalDateTime.now());
            return gradeRepository.save(grade);
        }
        return null;
    }

    public void deleteGrade(String id) {
        gradeRepository.deleteById(id);
    }

    public double calculateStudentAverage(String studentId) {
        List<Grade> grades = getStudentGrades(studentId);
        if (grades.isEmpty()) return 0;
        return grades.stream().mapToDouble(Grade::getScore).average().orElse(0);
    }

    public double calculateGroupAverage(String groupId) {
        List<Grade> grades = getGroupGrades(groupId);
        if (grades.isEmpty()) return 0;
        return grades.stream().mapToDouble(Grade::getScore).average().orElse(0);
    }
}
