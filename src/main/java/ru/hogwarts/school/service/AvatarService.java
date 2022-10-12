package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyNotExistException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;
    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar createAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
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
