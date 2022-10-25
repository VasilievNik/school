- liquibase formated sql

- changeset nikita:1
CREATE INDEX find_student_by_name ON student (name);
SELECT * FROM student WHERE name ='Gneg'

- changeset nikita:2
CREATE INDEX find_faculty_by_name_and_color ON faculty (name, color);
SELECT * FROM student
WHERE name ='Slizz' AND name ='Green'

