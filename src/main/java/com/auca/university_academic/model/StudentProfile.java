package com.auca.university_academic.model;

import java.util.UUID;
import java.time.LocalDate;

import jakarta.persistence.*;

/** Personal profile of a student (1:1 with Student). */

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EGender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "residential_address")
    private String residentialAddress;

    // Inverse side of the One-to-One: each profile belongs to only one student.
    @OneToOne(mappedBy = "profile")
    private Student student;

    // Constructors
    public StudentProfile() {
    }

    public StudentProfile(LocalDate dateOfBirth, EGender gender, String phoneNumber, String residentialAddress) {
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.residentialAddress = residentialAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
