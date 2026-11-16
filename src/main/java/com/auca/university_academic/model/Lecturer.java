package com.auca.university_academic.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

/** Lecturer employed by a department; may head it and may teach many courses. */

@Entity
@Table(name = "lecturers")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    // Many-to-One: many lecturers are employed by one department.
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Inverse side of the One-to-One head relationship.
    @OneToOne(mappedBy = "head")
    private Department headOfDepartment;

    @ManyToMany(mappedBy = "lecturers")
    private Set<Course> courses = new HashSet<>();

    // Constructors
    public Lecturer() {
    }

    public Lecturer(String fullName, String email, Department department) {
        this.fullName = fullName;
        this.email = email;
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
     * @return String return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return Department return the headOfDepartment
     */
    public Department getHeadOfDepartment() {
        return headOfDepartment;
    }

    /**
     * @param headOfDepartment the headOfDepartment to set
     */
    public void setHeadOfDepartment(Department headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    /**
     * @return Set<Course> return the courses
     */
    public Set<Course> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
