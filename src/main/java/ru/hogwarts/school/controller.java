package ru.hogwarts.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/hogwarts")
class controller {

    @RequestMapping("/Students")
    class StudentController {

        private final service.StudentService studentService;

        public StudentController(service.StudentService studentService) {
            this.studentService = studentService;
        }

        @RequestMapping(method=GET)
        public List<model.Student> ageSearch(@RequestParam int age){
            return studentService.findAge(age);
        }

        @GetMapping("/add")
        public model.Student addStudent(@RequestParam model.Student student){
            return studentService.add(student);
        }

        @GetMapping("/find")
        public model.Student findStudent(@RequestParam model.Student student){
            return studentService.find(student);
        }

        @GetMapping("/update")
        public model.Student updateStudent(@RequestParam model.Student student, @RequestParam model.Student studentNew){
            return studentService.update(student, studentNew);
        }

        @GetMapping("/delete")
        public model.Student deleteStudent(@RequestParam model.Student student){
            return studentService.delete(student);
        }

    }

    @RequestMapping("/Faculties")
    class FacultyController {

        private final service.FacultyService facultyService;

        public FacultyController(service.FacultyService facultyService) {
            this.facultyService = facultyService;
        }

        @GetMapping("/add")
        public model.Faculty addStudent(@RequestParam model.Faculty faculty){
            return facultyService.add(faculty);
        }

        @GetMapping("/find")
        public model.Faculty findStudent(@RequestParam model.Faculty faculty){
            return facultyService.find(faculty);
        }

        @GetMapping("/update")
        public model.Faculty updateStudent(@RequestParam model.Faculty faculty, @RequestParam model.Faculty facultyNew){
            return facultyService.update(faculty, facultyNew);
        }

        @GetMapping("/delete")
        public model.Faculty deleteStudent(@RequestParam model.Faculty faculty){
            return facultyService.delete(faculty);
        }

        @RequestMapping(method=GET)
        public List<model.Faculty> colorSearch(@RequestParam String color){
            return facultyService.findColor(color);
        }

    }
}

