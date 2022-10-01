package ru.hogwarts.school;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import javax.persistence.PostUpdate;
import java.util.List;

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

        @PostUpdate
        public Student updateStudent(@RequestBody Student student, @RequestBody Student studentNew){
            return studentService.updateStudent(student, studentNew);
        }

        @GetMapping("{id}")
        public Student findStudent(@PathVariable Long id){
            return studentService.findStudent(id);
        }

        @GetMapping("{id}")
        public void deleteStudent(@PathVariable Long id){
            studentService.deleteStudent(id);
        }

        @GetMapping
        public List<Student> ageSearch(@RequestBody int age){
            return studentService.findAge(age);
        }
    }

    @RequestMapping("faculties")
    class FacultyController {

        private final FacultyService facultyService;

        public FacultyController(FacultyService facultyService) {
            this.facultyService = facultyService;
        }

        @PostMapping
        public Faculty addFaculty(@RequestBody Faculty faculty){
            return facultyService.createFaculty(faculty);
        }

        @PostUpdate
        public Faculty updateFaculty(@RequestBody Faculty faculty, @RequestBody Faculty facultyNew){
            return facultyService.updateFaculty(faculty, facultyNew);
        }

        @GetMapping("{id}")
        public Faculty findFaculty(@PathVariable Long id){
            return facultyService.findFaculty(id);
        }

        @GetMapping("{id}")
        public void deleteFaculty(@PathVariable Long id){
            facultyService.deleteFaculty(id);
        }

        @GetMapping
        public List<Faculty> colorSearch(@RequestBody String color){
            return facultyService.findColor(color);
        }

    }

