--liquibase formatted sql

--changeset nikita:1
CREATE INDEX find_student_by_name ON student (name);

--changeset nikita:2
CREATE INDEX find_faculty_by_name_and_color ON faculty (name, color);

