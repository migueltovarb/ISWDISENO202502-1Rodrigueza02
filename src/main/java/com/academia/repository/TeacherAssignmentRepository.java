package com.academia.repository;

import com.academia.model.TeacherAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherAssignmentRepository extends MongoRepository<TeacherAssignment, String> {
    List<TeacherAssignment> findByTeacherId(String teacherId);
    List<TeacherAssignment> findByGroupId(String groupId);
}
