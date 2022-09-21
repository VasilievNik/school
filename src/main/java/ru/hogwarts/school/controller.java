package ru.hogwarts.school;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
class controller {

    @RequestMapping("students")
    class StudentController {

        private final service.StudentService studentService;

        public StudentController(service.StudentService studentService) {
            this.studentService = studentService;
        }

        @PostMapping
        public model.Student addStudent(@RequestBody model.Student student){
            return studentService.createStudent(student);
        }

        @GetMapping
        public model.Student findStudent(@RequestBody Long id){
            return studentService.findStudent(id);
        }

        @DeleteMapping
        public void deleteStudent(@RequestBody Long id){
            studentService.deleteStudent(id);
        }

        @GetMapping("{age}")
        public List<model.Student> ageSearch(@PathVariable int age){
            return studentService.findAge(age);
        }
    }

    @RequestMapping("faculties")
    class FacultyController {

        private final service.FacultyService facultyService;

        public FacultyController(service.FacultyService facultyService) {
            this.facultyService = facultyService;
        }

        @PostMapping
        public model.Faculty addFaculty(@RequestBody model.Faculty faculty){
            return facultyService.createFaculty(faculty);
        }

        @GetMapping
        public model.Faculty findFaculty(@RequestBody Long id){
            return facultyService.findFaculty(id);
        }

        @DeleteMapping
        public void deleteFaculty(@RequestBody Long id){
            facultyService.deleteFaculty(id);
        }

        @GetMapping("{color}")
        public List<model.Faculty> colorSearch(@PathVariable String color){
            return facultyService.findColor(color);
        }

    }
}

