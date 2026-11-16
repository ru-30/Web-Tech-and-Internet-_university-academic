package com.auca.university_academic.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int credits;

    // Many-to-One: each course belongs to one department.
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany
    @JoinTable(name = "lecturer_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "lecturer_id"))
    private Set<Lecturer> lecturers = new HashSet<>();

    // One-to-Many: a course has many registrations (join entity).
    @OneToMany(mappedBy = "course")
    private List<Registration> registrations = new ArrayList<>();

    // Constructors
    public Course() {
    }

    public Course(String code, String title, int credits, Department department) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.department = department;
    }

    /**
     * @return UUID return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return String return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return int return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * @return Department return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @return Set<Lecturer> return the lecturers
     */
    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    /**
     * @param lecturers the lecturers to set
     */
    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    /**
     * @return List<Registration> return the registrations
     */
    public List<Registration> getRegistrations() {
        return registrations;
    }

    /**
     * @param registrations the registrations to set
     */
    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

}
