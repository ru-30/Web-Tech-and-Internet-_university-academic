package com.auca.university_academic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.university_academic.model.*;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, UUID> {
    Optional<Lecturer> findByEmail(String email);

    List<Lecturer> findByDepartmentId(UUID departmentId);

    List<Lecturer> findByCoursesId(UUID courseId);
}
