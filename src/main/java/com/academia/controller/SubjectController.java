package com.academia.controller;

import com.academia.model.Subject;
import com.academia.repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        subject.setCreatedAt(LocalDateTime.now());
        Subject created = subjectRepository.save(subject);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubject(@PathVariable String id) {
        return subjectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable String id, @RequestBody Subject subject) {
        return subjectRepository.findById(id)
                .map(existing -> {
                    existing.setName(subject.getName());
                    existing.setDescription(subject.getDescription());
                    existing.setCredits(subject.getCredits());
                    existing.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(subjectRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        subjectRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Materia eliminada correctamente");
        return ResponseEntity.ok(response);
    }
}
