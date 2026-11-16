-- Relational schema (what Hibernate generates with ddl-auto=update on PostgreSQL)
-- PK = primary key, FK = foreign key

CREATE TABLE student_profiles (
    id                  UUID PRIMARY KEY,
    date_of_birth       DATE        NOT NULL,
    gender              VARCHAR(255) NOT NULL,   -- MALE | FEMALE | OTHER
    phone_number        VARCHAR(255),
    residential_address VARCHAR(255)
);

CREATE TABLE departments (
    id      UUID PRIMARY KEY,
    name    VARCHAR(255) NOT NULL UNIQUE,
    code    VARCHAR(255) UNIQUE,
    head_id UUID UNIQUE                          -- FK -> lecturers(id), added after lecturers exists
);

CREATE TABLE lecturers (
    id            UUID PRIMARY KEY,
    full_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    department_id UUID REFERENCES departments(id)
);
ALTER TABLE departments ADD CONSTRAINT fk_departments_head FOREIGN KEY (head_id) REFERENCES lecturers(id);

CREATE TABLE courses (
    id            UUID PRIMARY KEY,
    code          VARCHAR(255) NOT NULL UNIQUE,
    title         VARCHAR(255) NOT NULL,
    credits       INTEGER      NOT NULL,
    department_id UUID         NOT NULL REFERENCES departments(id)
);

CREATE TABLE lecturer_courses (                  -- JOIN TABLE (Lecturer M:N Course)
    course_id   UUID NOT NULL REFERENCES courses(id),
    lecturer_id UUID NOT NULL REFERENCES lecturers(id),
    PRIMARY KEY (course_id, lecturer_id)
);

CREATE TABLE students (
    id                  UUID PRIMARY KEY,
    full_name           VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL UNIQUE,
    email               VARCHAR(255) NOT NULL UNIQUE,
    department_id       UUID NOT NULL REFERENCES departments(id),
    profile_id          UUID UNIQUE REFERENCES student_profiles(id)
);

CREATE TABLE registrations (                     -- JOIN ENTITY (Student M:N Course)
    id                UUID PRIMARY KEY,
    student_id        UUID NOT NULL REFERENCES students(id),
    course_id         UUID NOT NULL REFERENCES courses(id),
    registration_date DATE NOT NULL,
    academic_year     VARCHAR(255) NOT NULL,
    semester          INTEGER NOT NULL,
    status            VARCHAR(255) NOT NULL,     -- PENDING | APPROVED | REJECTED
    grade             DOUBLE PRECISION,
    UNIQUE (student_id, course_id, academic_year, semester)
);
