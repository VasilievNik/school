package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.StudentAlreadyExistException;
import ru.hogwarts.school.Exceptions.StudentNotExistException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService{

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

    public Student updateStudent(Student studentNew) {
        if (studentRepository.existsById(studentNew.getId())){
            Student studentOld = studentRepository.findById(studentNew.getId()).get();
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