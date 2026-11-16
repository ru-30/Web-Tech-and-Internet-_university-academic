package com.auca.university_academic.model;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/** Academic department (e.g. Computer Science). */

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String code;

    // One-to-One: each department has exactly one head (a lecturer).
    // Owning side -> FK column "head_id" in departments.
    @OneToOne
    @JoinColumn(name = "head_id", unique = true)
    private Lecturer head;

    // One-to-Many: a department employs several lecturers (FK lives in lecturers.department_id)
    @OneToMany(mappedBy = "department")
    private List<Lecturer> lecturers = new ArrayList<>();

    // One-to-Many: a department offers several courses (FK lives in courses.department_id)
    @OneToMany(mappedBy = "department")
    private List<Course> courses = new ArrayList<>();

    // One-to-Many: a department admits several students (FK lives in students.department_id)
    @OneToMany(mappedBy = "department")
    private List<Student> students = new ArrayList<>();

    // Constructors
    public Department() {
    }

    public Department(String name, String code) {
        this.name = name;
        this.code = code;
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
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return Lecturer return the head
     */
    public Lecturer getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Lecturer head) {
        this.head = head;
    }

    /**
     * @return List<Lecturer> return the lecturers
     */
    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    /**
     * @param lecturers the lecturers to set
     */
    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    /**
     * @return List<Course> return the courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * @return List<Student> return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
