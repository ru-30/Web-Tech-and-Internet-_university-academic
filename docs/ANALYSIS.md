# Problem Analysis and Design

## 1. Entities (and why)

| Entity | Why it is an entity |
|---|---|
| **Department** | Core unit; offers courses, employs lecturers, admits students, has one head. |
| **Lecturer** | Employed by a department; teaches courses; one lecturer per department is head. |
| **Student** | Admitted into a department; registers for courses. |
| **StudentProfile** | Personal details (DOB, gender, phone, address). Exactly one per student, so a separate 1:1 entity. |
| **Course** | Belongs to one department; taught by lecturers; registered for by students. |
| **Registration** | Join **entity**: the Student–Course many-to-many carries its own data (date, academic year, semester, status, grade). |

The Lecturer–Course assignment carries no extra data, so it is a plain **join table** (`lecturer_courses`) with no entity, exactly as the assignment requires.

## 2. Attributes

| Entity | Attributes |
|---|---|
| Department | id, name, code, head (lecturer) |
| Lecturer | id, fullName, email, department |
| Student | id, fullName, registrationNumber, email, department, profile |
| StudentProfile | id, dateOfBirth, gender (MALE/FEMALE/OTHER), phoneNumber, residentialAddress |
| Course | id, code, title, credits, department |
| Registration | id, student, course, registrationDate, academicYear, semester, status (PENDING/APPROVED/REJECTED), grade |

## 3. Relationships and cardinality

| # | Relationship | Cardinality | Implementation |
|---|---|---|---|
| 1 | Department – Student | 1 : N | FK `students.department_id` |
| 2 | Department – Course | 1 : N | FK `courses.department_id` |
| 3 | Department – Lecturer (employs) | 1 : N | FK `lecturers.department_id` |
| 4 | Department – Lecturer (head) | 1 : 1 | FK `departments.head_id` (UNIQUE) |
| 5 | Student – StudentProfile | 1 : 1 | FK `students.profile_id` (UNIQUE) |
| 6 | Lecturer – Course | M : N | **Join table** `lecturer_courses(lecturer_id, course_id)` |
| 7 | Student – Course | M : N | **Join entity** `Registration` (two M:1 relations) |

## 4. Which relationships need what

- **Foreign key:** 1–5 (the "many"/owning side holds the FK), and the two FKs inside `registrations`.
- **Join table:** relationship 6 (Lecturer–Course) — no attributes of its own.
- **Join entity:** relationship 7 (Student–Course) — it has attributes (date, year, semester, status, grade), so it must be a real entity.

## 5. Hibernate annotations used

| Relationship | Annotations |
|---|---|
| Department → Students/Courses/Lecturers | `@OneToMany(mappedBy="department")` |
| Student/Course/Lecturer → Department | `@ManyToOne` + `@JoinColumn(name="department_id")` |
| Department ↔ head Lecturer | `@OneToOne` + `@JoinColumn(name="head_id", unique=true)`; inverse `@OneToOne(mappedBy="head")` |
| Student ↔ StudentProfile | `@OneToOne(cascade=ALL, orphanRemoval=true)` + `@JoinColumn(name="profile_id", unique=true)`; inverse `@OneToOne(mappedBy="profile")` |
| Course ↔ Lecturer | `@ManyToMany` + `@JoinTable(name="lecturer_courses", ...)` on Course; `@ManyToMany(mappedBy="lecturers")` on Lecturer |
| Student/Course ↔ Registration | `@OneToMany(mappedBy=...)` on both; `@ManyToOne` + `@JoinColumn` on Registration |

Design notes: UUID primary keys (`@GeneratedValue(strategy = GenerationType.UUID)`), enums stored as strings (`@Enumerated(EnumType.STRING)`), `grade` is nullable (unset until assessed), and `registrations` has a unique constraint on (student, course, academic year, semester) to prevent duplicate registrations.

## 6. ERD
See [`erd.png`](erd.png) (source: [`erd.mmd`](erd.mmd)).
