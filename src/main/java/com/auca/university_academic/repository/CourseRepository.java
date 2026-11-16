package com.auca.university_academic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.university_academic.model.*;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByCode(String code);

    List<Course> findByDepartmentId(UUID departmentId);

    List<Course> findByLecturersId(UUID lecturerId);
}
