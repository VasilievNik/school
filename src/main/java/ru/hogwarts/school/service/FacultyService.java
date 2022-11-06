package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyNotExistException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

@Service
public class FacultyService{


    private static FacultyRepository facultyRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    public FacultyService(FacultyRepository facultyrepository) {
        this.facultyRepository = facultyrepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("createFaculty method used in StudentService");
            return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Faculty facultyNew) {
        logger.info("updateFaculty method used in StudentService");
        if (facultyRepository.existsById(facultyNew.getId())){
            Faculty facultyOld = facultyRepository.findById(facultyNew.getId()).get();
            facultyOld = facultyNew;
            facultyRepository.save(facultyOld);
            return facultyNew;
        }
        throw new FacultyNotExistException();
    }

    public Faculty findFaculty(Long id) {
        logger.info("findFaculty method used in StudentService");
        if (facultyRepository.existsById(id)){
            return facultyRepository.findById(id).get();
        }
        throw new FacultyNotExistException();
    }

    public void deleteFaculty(Long id) {
        logger.info("deleteFaculty method used in StudentService");
        if (facultyRepository.existsById(id)){
            facultyRepository.deleteById(id);
        }
        throw new FacultyNotExistException();
    }

    public List<Faculty> findColor(String color){
        logger.info("findColor method used in StudentService");
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Student> getStudents(Long id){
        logger.info("getStudents method used in StudentService");
        return facultyRepository.findById(id).get().getStudents();
    }

    public Faculty findByNameIgnoreCase(String nameOrColor){
        logger.info("findByNameIgnoreCase method used in StudentService");
        return facultyRepository.findByNameIgnoreCase(nameOrColor);
    }

    public static OptionalInt getLongestFaculty(){
        logger.info("getLongestFaculty method used in StudentService");
        return facultyRepository.findAll().stream()
                .map(faculty -> faculty.getName().length())
                .mapToInt(v -> v)
                .max();
    }

    public static int parallelTest(){
        logger.info("parallelTest method used in StudentService");
        int sum = Stream.iterate(1, a -> a +1).parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        return sum;
    }


}