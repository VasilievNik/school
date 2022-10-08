package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

@RestController
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

   // @PostUpdate
    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty facultyNew){
        return facultyService.updateFaculty(facultyNew);
    }

    @GetMapping("{id}")
    //@PutMapping("{id}")
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

}