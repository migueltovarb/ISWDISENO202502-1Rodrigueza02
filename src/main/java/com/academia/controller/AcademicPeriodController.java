package com.academia.controller;

import com.academia.model.AcademicPeriod;
import com.academia.repository.AcademicPeriodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic-periods")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class AcademicPeriodController {

    private final AcademicPeriodRepository academicPeriodRepository;

    public AcademicPeriodController(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createPeriod(@RequestBody AcademicPeriod period) {
        AcademicPeriod created = academicPeriodRepository.save(period);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<?> getAllPeriods() {
        List<AcademicPeriod> periods = academicPeriodRepository.findAll();
        return ResponseEntity.ok(periods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeriod(@PathVariable String id) {
        return academicPeriodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePeriod(@PathVariable String id, @RequestBody AcademicPeriod period) {
        return academicPeriodRepository.findById(id)
                .map(existing -> {
                    existing.setName(period.getName());
                    existing.setActive(period.isActive());
                    existing.setStartDate(period.getStartDate());
                    existing.setEndDate(period.getEndDate());
                    return ResponseEntity.ok(academicPeriodRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePeriod(@PathVariable String id) {
        academicPeriodRepository.deleteById(id);
        return ResponseEntity.ok("Período académico eliminado");
    }
}
