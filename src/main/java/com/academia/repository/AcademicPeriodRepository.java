package com.academia.repository;

import com.academia.model.AcademicPeriod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicPeriodRepository extends MongoRepository<AcademicPeriod, String> {
    List<AcademicPeriod> findByAcademicYear(String academicYear);
    List<AcademicPeriod> findByActive(boolean active);
}
