# University Course Registration and Academic Management System

Web Technology and Internet — Individual Assignment (Model & Repository layers).
Stack: Java 17, Spring Boot, Spring Data JPA / Hibernate, PostgreSQL, Maven.

**Student Name:** _________  **Registration No.:** _________

## Contents
| Deliverable | Location |
|---|---|
| Problem analysis & identified entities | [`docs/ANALYSIS.md`](docs/ANALYSIS.md) |
| ERD | [`docs/erd.png`](docs/erd.png) |
| Relational schema | [`docs/schema.sql`](docs/schema.sql) |
| Models (entities) | `src/main/java/com/auca/university_academic/model` |
| Repositories | `src/main/java/com/auca/university_academic/repository` |
| Mapping tests | `src/test/java/.../EntityMappingTests.java` |
| Screenshots | `docs/screenshots/` |

## Run it
1. Create the database:
   ```sql
   CREATE DATABASE university_academic_db;
   ```
2. Set your password (default in `application.properties` is `postgres`):
   `set DB_PASSWORD=yourpassword` (Windows) / `export DB_PASSWORD=yourpassword` (Linux/macOS).
3. Start the app — Hibernate creates the tables automatically (`ddl-auto=update`):
   ```
   ./mvnw spring-boot:run
   ```
4. Run the mapping tests (in-memory H2, no setup needed): `./mvnw test`

## Verify the generated tables (for screenshots)
```sql
\dt
\d registrations
\d lecturer_courses
\d students
\d departments
```
Expected tables: `departments, lecturers, courses, lecturer_courses, students, student_profiles, registrations`.
