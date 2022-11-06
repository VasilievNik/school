package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService{

    @Value("{avatars.dir.path}")
    private String avatarsDir;

    private static StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentrepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentrepository;
        this.avatarRepository = avatarRepository;
    }

    public Student createStudent(Student student) {
        logger.info("createStudent method used in StudentService");
        return studentRepository.save(student);
    }

    public Student updateStudent(Student studentNew) {
        logger.info("updateStudent method used in StudentService");
        if (studentRepository.existsById(studentNew.getId())){
            Student studentOld = studentRepository.findById(studentNew.getId()).get();
            studentOld = studentNew;
            studentRepository.save(studentOld);
            return studentNew;
        }
        throw new StudentNotExistException();
    }

    public Student findStudent(Long id) {
        logger.info("findStudent method used in StudentService");
        if (studentRepository.existsById(id)){
            return studentRepository.findById(id).get();
        }
        throw new StudentNotExistException();
    }

    public void deleteStudent(Long id) {
        logger.info("deleteStudent method used in StudentService");
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        throw new StudentNotExistException();
    }

    public List<Student> findAge(int age){
        logger.info("findAge method used in StudentService");
        return studentRepository.findAllByAge(age);
    }

    public Avatar findAvatar(long studentId) {
        logger.info("findAvatar method used in StudentService");
        return avatarRepository.findByStudentId(studentId);
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("uploadAvatar method used in StudentService");
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
        logger.info("getExtensions method used in StudentService");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Faculty getFaculty(Long id){
        logger.info("getFaculty method used in StudentService");
        return studentRepository.findById(id).get().getFaculty();
    }

    public List<Student> findByAgeBetween(int min, int max){
        logger.info("findByAgeBetween method used in StudentService");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer getAvgAge(){
        logger.info("getAvgAge method used in StudentService");
        return studentRepository.getAvgAge();
    }

    Integer getAmount(){
        logger.info("getAmount method used in StudentService");
        return studentRepository.getAmount();
    }

    List<Student> getLastFive(){
        logger.info("getLastFive method used in StudentService");
        return studentRepository.getLastFive();
    }

    public static List<String> getStartWithA(){
        logger.info("getStartWithA method used in StudentService");
        return studentRepository.findAll().stream()
                .map(student -> student.getName())
                .filter(s -> s.startsWith("a"))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static OptionalDouble getAverageAge(){
        logger.info("getAverageAge method used in StudentService");
        Stream<Integer> stream = studentRepository.findAll().stream()
                .map(student -> student.getAge());
        IntStream intStream = (IntStream) stream;
        OptionalDouble fin = intStream.average();
        return fin;
    }


}