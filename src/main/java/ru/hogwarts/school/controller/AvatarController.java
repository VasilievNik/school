package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

@RestController
@RequestMapping("avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public Avatar addAvatar(@RequestBody Avatar avatar){
        return avatarService.createAvatar(avatar);
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

}
