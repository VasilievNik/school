package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("students")
public
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
    public Student updateStudent(@RequestBody Student studentNew){
        return studentService.updateStudent(studentNew);
    }

    @GetMapping//("{id}")
    public Student findStudent(@PathVariable Long id){
        return studentService.findStudent(id);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping("findByAge")
    public List<Student> ageSearch(@RequestParam int age){
        return studentService.findAge(age);
    }

    @GetMapping("/getFaculty")
    public Faculty getFaculty(@RequestParam Long id){
        return studentService.getFaculty(id);
    }

    @GetMapping("/ageBetween")
    public List<Student> ageBetweenSearch(@RequestParam int min, @RequestParam int max){
        return studentService.findByAgeBetween(min, max);
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024*300) {
            return ResponseEntity.badRequest().body("File is too big");
        }

        studentService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = studentService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);

        Path path = Path.of(avatar.getPath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getType());
            response.setContentLength(Math.toIntExact(avatar.getSize()));
            is.transferTo(os);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("getAllStudents")
    public ResponseEntity<List<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("getStartWithA")
    public List<String> getStartWithA() {
        return StudentService.getStartWithA();
    }

    @GetMapping("getStartWithA")
    public OptionalDouble getAverageAge() {
        return StudentService.getAverageAge();
    }

    @GetMapping("threadTest")
    public void threadTest() {
        StudentService.threadTest();
    }
    
    @GetMapping("threadTest")
    public void threadTestSync() {
        StudentService.threadTest();
    }
}