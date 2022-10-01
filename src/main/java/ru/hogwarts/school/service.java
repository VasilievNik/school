package ru.hogwarts.school;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyAlreadyExistException;
import ru.hogwarts.school.Exceptions.FacultyNotExistException;
import ru.hogwarts.school.Exceptions.StudentAlreadyExistException;
import ru.hogwarts.school.Exceptions.StudentNotExistException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;



    @Service
    class StudentService{

        private final StudentRepository studentRepository;
        public StudentService(StudentRepository studentrepository) {
            this.studentRepository = studentrepository;
        }

        public Student createStudent(Student student) {
            if (studentRepository.existsById(student.getId())){
                return studentRepository.save(student);
            }
            throw new StudentAlreadyExistException();
        }

        public Student updateStudent(Student student, Student studentNew) {
            if (studentRepository.existsById(student.getId())){
                Student studentOld = studentRepository.findById(student.getId()).get();
                studentOld = studentNew;
                studentRepository.save(studentOld);
                return studentNew;
            }
            throw new StudentNotExistException();
        }

        public Student findStudent(Long id) {
            if (studentRepository.existsById(id)){
                return studentRepository.findById(id).get();
            }
            throw new StudentNotExistException();
        }

        public void deleteStudent(Long id) {
            if (studentRepository.existsById(id)){
                studentRepository.deleteById(id);
            }
            throw new StudentNotExistException();
        }

        public List<Student> findAge(int age){
            return studentRepository.findAllByAge(age);
        }

    }

    @Service
    class FacultyService{

        private final FacultyRepository facultyRepository;
        public FacultyService(FacultyRepository facultyrepository) {
            this.facultyRepository = facultyrepository;
        }

        public Faculty createFaculty(Faculty faculty) {
            if (facultyRepository.existsById(faculty.getId())){
                return facultyRepository.save(faculty);
            }
            throw new FacultyAlreadyExistException();
        }

        public Faculty updateFaculty(Faculty faculty, Faculty facultyNew) {
            if (facultyRepository.existsById(faculty.getId())){
                Faculty facultyOld = facultyRepository.findById(faculty.getId()).get();
                facultyOld = facultyNew;
                facultyRepository.save(facultyOld);
                return facultyNew;
            }
            throw new FacultyNotExistException();
        }

        public Faculty findFaculty(Long id) {
            if (facultyRepository.existsById(id)){
                return facultyRepository.findById(id).get();
            }
            throw new FacultyNotExistException();
        }

        public void deleteFaculty(Long id) {
            if (facultyRepository.existsById(id)){
                facultyRepository.deleteById(id);
            }
            throw new FacultyNotExistException();
        }

        public List<Faculty> findColor(String color){
            return facultyRepository.findAllByColor(color);
        }
    }
