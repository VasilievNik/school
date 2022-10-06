package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyAlreadyExistException;
import ru.hogwarts.school.Exceptions.FacultyNotExistException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService{

    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyrepository) {
        this.facultyRepository = facultyrepository;
    }

    public Faculty createFaculty(Faculty faculty) {
            return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Faculty facultyNew) {
        if (facultyRepository.existsById(facultyNew.getId())){
            Faculty facultyOld = facultyRepository.findById(facultyNew.getId()).get();
            facultyOld = facultyNew;
            facultyRepository.save(facultyOld);
            return facultyNew;
        }
        throw new FacultyNotExistException();
    }

    public Faculty findFaculty(Long id) {
        if (facultyRepository.existsById(id)){
            return facultyRepository.findById(id).get();
        }
        throw new FacultyNotExistException();
    }

    public void deleteFaculty(Long id) {
        if (facultyRepository.existsById(id)){
            facultyRepository.deleteById(id);
        }
        throw new FacultyNotExistException();
    }

    public List<Faculty> findColor(String color){
        return facultyRepository.findAllByColor(color);
    }
}