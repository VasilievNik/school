package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("avatars")
class AvatarController {

    private final AvatarService avatarService;
    private final StudentService studentService;

    public AvatarController(AvatarService avatarService, StudentService studentService){
        this.avatarService = avatarService;
        this.studentService = studentService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        studentService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public Avatar updateAvatar(@RequestBody Avatar avatarNew){
        return avatarService.updateAvatar(avatarNew);
    }

    @GetMapping("{id}")
    public Avatar findAvatar(@PathVariable Long id){
        return avatarService.findAvatar(id);
    }

    @DeleteMapping("{id}")
    public void deleteAvatar(@PathVariable Long id){
        avatarService.deleteAvatar(id);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Avatar>> getAll(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize){
        return avatarService.getAll(pageNumber, pageSize);
    }

}
