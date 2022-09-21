package ru.hogwarts.school;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyAlreadyExistException;
import ru.hogwarts.school.Exceptions.FacultyNotExistException;
import ru.hogwarts.school.Exceptions.StudentAlreadyExistException;
import ru.hogwarts.school.Exceptions.StudentNotExistException;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

public class service {

    @Service
    public class StudentService{

        private final StudentRepository studentRepository;
        public StudentService(StudentRepository studentrepository) {
            this.studentRepository = studentrepository;
        }

        public model.Student createStudent(model.Student student) {
            if (studentRepository.existsById(student.getId())){
                return (model.Student) studentRepository.save(student);
            }
            throw new StudentAlreadyExistException();
        }

        public model.Student findStudent(Long id) {
            if (studentRepository.existsById(id)){
                return (model.Student) studentRepository.findById(id).get();
            }
            throw new StudentNotExistException();
        }

        public void deleteStudent(Long id) {
            if (studentRepository.existsById(id)){
                studentRepository.deleteById(id);
            }
            throw new StudentNotExistException();
        }

        public List<model.Student> findAge(int age){
            List<model.Student> studentList = (List<model.Student>) studentRepository.findAll();
            return studentList.stream().filter(student -> student.getAge() == age).collect(Collectors.toList());
        }
    }

    @Service
    public class FacultyService{

        private final FacultyRepository facultyRepository;
        public FacultyService(FacultyRepository facultyrepository) {
            this.facultyRepository = facultyrepository;
        }

        public model.Faculty createFaculty(model.Faculty faculty) {
            if (facultyRepository.existsById(faculty.getId())){
                return (model.Faculty) facultyRepository.save(faculty);
            }
            throw new FacultyAlreadyExistException();
        }

        public model.Faculty findFaculty(Long id) {
            if (facultyRepository.existsById(id)){
                return (model.Faculty) facultyRepository.findById(id).get();
            }
            throw new FacultyNotExistException();
        }

        public void deleteFaculty(Long id) {
            if (facultyRepository.existsById(id)){
                facultyRepository.deleteById(id);
            }
            throw new FacultyNotExistException();
        }

        public List<model.Faculty> findColor(String color){
            List<model.Faculty> facultyList = (List<model.Faculty>) facultyRepository.findAll();
            return facultyList.stream().filter(faculty -> faculty.getColor() == color).collect(Collectors.toList());
        }

    }

}
