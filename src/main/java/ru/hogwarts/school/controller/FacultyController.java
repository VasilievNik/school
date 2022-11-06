package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.OptionalInt;

@RestController
@RequestMapping("faculties")
public
class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty){
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty facultyNew){
        return facultyService.updateFaculty(facultyNew);
    }

    @GetMapping("{id}")
    public Faculty findFaculty(@PathVariable Long id){
        return facultyService.findFaculty(id);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
    }

    @GetMapping
    public List<Faculty> colorSearch(@RequestParam String color){
        return facultyService.findColor(color);
    }

    @GetMapping("/getStudents")
    public List<Student> getFaculty(@RequestParam Long id){
        return (List<Student>) facultyService.getStudents(id);
    }

    @GetMapping("/findByNameOrColor")
    public Faculty nameOrColorSearch(@RequestParam String nameOrColor){
        return facultyService.findByNameIgnoreCase(nameOrColor);
    }

    @GetMapping("/getLongestFaculty")
    public OptionalInt getLongestFaculty(){
        return FacultyService.getLongestFaculty();
    }

    @GetMapping("/parallelTest")
    public int parallelTest(){
        return FacultyService.parallelTest();
    }

}