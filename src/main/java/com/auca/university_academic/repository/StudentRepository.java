package com.auca.university_academic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.university_academic.model.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByRegistrationNumber(String registrationNumber);

    List<Student> findByDepartmentId(UUID departmentId);
}
