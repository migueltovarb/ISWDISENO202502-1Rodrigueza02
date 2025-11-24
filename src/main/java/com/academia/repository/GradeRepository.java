package com.academia.repository;

import com.academia.model.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends MongoRepository<Grade, String> {
    List<Grade> findByStudentId(String studentId);
    List<Grade> findByStudentIdAndSubjectId(String studentId, String subjectId);
    List<Grade> findByGroupId(String groupId);
    List<Grade> findByTeacherId(String teacherId);
}
