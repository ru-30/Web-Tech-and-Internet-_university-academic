package com.auca.university_academic.model;

import java.util.UUID;
import java.time.LocalDate;

import jakarta.persistence.*;



@Entity
@Table(name = "registrations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id", "academic_year", "semester"}))
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Many-to-One: the student who registers.
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Many-to-One: the course being registered for.
    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(nullable = false)
    private int semester;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERegistrationStatus status;

    // Nullable: no grade until the course has been assessed.
    @Column
    private Double grade;

    // Constructors
    public Registration() {
    }

    public Registration(Student student, Course course, LocalDate registrationDate, String academicYear, int semester, ERegistrationStatus status) {
        this.student = student;
        this.course = course;
        this.registrationDate = registrationDate;
        this.academicYear = academicYear;
        this.semester = semester;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public ERegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(ERegistrationStatus status) {
        this.status = status;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

}
