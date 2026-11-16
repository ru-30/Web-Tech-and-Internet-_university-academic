package com.auca.university_academic.model;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false, unique = true)
    private String email;

    // Many-to-One: each student is assigned to one department.
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // One-to-One: exactly one profile per student.
    // Owning side -> FK column "profile_id" (unique) in students.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id", unique = true)
    private StudentProfile profile;

    // One-to-Many: a student has many registrations (join entity).
    @OneToMany(mappedBy = "student")
    private List<Registration> registrations = new ArrayList<>();

    // Constructors
    public Student() {
    }

    public Student(String fullName, String registrationNumber, String email, Department department) {
        this.fullName = fullName;
        this.registrationNumber = registrationNumber;
        this.email = email;
        this.department = department;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

}
