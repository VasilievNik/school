package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private String avatarsDir;
    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Autowired
    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Collection<Avatar>> getAll(Integer pageNumber, Integer pageSize) {
        logger.info("Method to get all of avatars was invoked");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Collection<Avatar> avatarsList = avatarRepository.findAll(pageRequest).getContent();
        if (avatarsList.isEmpty()) {
            logger.error("There are no avatars");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avatarsList);
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
