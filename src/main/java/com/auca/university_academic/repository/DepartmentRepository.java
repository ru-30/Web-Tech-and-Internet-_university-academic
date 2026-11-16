package com.auca.university_academic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.university_academic.model.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Optional<Department> findByName(String name);

    Optional<Department> findByCode(String code);
}
