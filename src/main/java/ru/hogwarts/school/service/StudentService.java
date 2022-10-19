package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.Exceptions.StudentNotExistException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService{

    //@Value("${avatars.dir.path}")       //ПОЧЕМУ ТЫ НЕ РАБОТАЕШЬ?!?!?!?!
    @Value("{avatars.dir.path}")
    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    @Autowired
    public StudentService(StudentRepository studentrepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentrepository;
        this.avatarRepository = avatarRepository;
    }

    public Student createStudent(Student student) {
            return studentRepository.save(student);
    }

    public Student updateStudent(Student studentNew) {
        if (studentRepository.existsById(studentNew.getId())){
            Student studentOld = studentRepository.findById(studentNew.getId()).get();
            studentOld = studentNew;
            studentRepository.save(studentOld);
            return studentNew;
        }
        throw new StudentNotExistException();
    }

    public Student findStudent(Long id) {
        if (studentRepository.existsById(id)){
            return studentRepository.findById(id).get();
        }
        throw new StudentNotExistException();
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        throw new StudentNotExistException();
    }

    public List<Student> findAge(int age){
        return studentRepository.findAllByAge(age);
    }

    public Avatar findAvatar(long studentId) {
        return avatarRepository.findByStudentId(studentId);
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Faculty getFaculty(Long id){
        return studentRepository.findById(id).get().getFaculty();
    }

    public List<Student> findByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer getAvgAge(){
        return studentRepository.getAvgAge();
    }

    Integer getAmount(){
        return studentRepository.getAmount();
    }

    List<Student> getLastFive(){
        return studentRepository.getLastFive();
    }
}