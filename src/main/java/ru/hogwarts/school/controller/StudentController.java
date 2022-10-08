package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("students")
class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping
    //@PostUpdate
    public Student updateStudent(@RequestBody Student studentNew){
        return studentService.updateStudent(studentNew);
    }

    @GetMapping("{id}")
   // @PutMapping("{id}")
    public Student findStudent(@PathVariable Long id){
        return studentService.findStudent(id);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping
    public List<Student> ageSearch(@RequestParam int age){
        return studentService.findAge(age);
    }
}