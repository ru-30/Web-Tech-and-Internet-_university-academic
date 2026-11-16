package com.auca.university_academic.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.university_academic.model.*;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    List<Registration> findByStudentId(UUID studentId);

    List<Registration> findByCourseId(UUID courseId);

    List<Registration> findByStatus(ERegistrationStatus status);

    List<Registration> findByAcademicYearAndSemester(String academicYear, int semester);
}
