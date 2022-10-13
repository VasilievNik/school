package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private String avatarsDir;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }



    public Avatar updateAvatar(Avatar avatarNew) {
            Avatar avataryOld = avatarRepository.findById(avatarNew.getId()).get();
            avataryOld = avatarNew;
            avatarRepository.save(avataryOld);
            return avatarNew;
    }

    public Avatar findAvatar(Long id) {
        return avatarRepository.findById(id).get();
    }

    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }
}
