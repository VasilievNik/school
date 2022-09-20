package ru.hogwarts.school;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exceptions.FacultyAlreadyExistException;
import ru.hogwarts.school.Exceptions.FacultyNotExistException;
import ru.hogwarts.school.Exceptions.StudentAlreadyExistException;
import ru.hogwarts.school.Exceptions.StudentNotExistException;

import java.util.*;
import java.util.stream.Collectors;

public class service {

    private Long studentCounter;
    private Long facultyCounter;

    @Service
    public class StudentService{

        private final Map<Long, model.Student> studentMap = new HashMap<>();

        public model.Student add(model.Student student){
            if (studentMap.containsValue(student)){
                throw new StudentAlreadyExistException();
            }
            studentMap.put(studentCounter, student);
            studentCounter++;
            return student;
        }

        public model.Student find(model.Student student){
            if (studentMap.containsValue(student)){
                return student;
            }
            return null;
        }

        public model.Student update(model.Student student, model.Student studentNew){
            if (studentMap.containsValue(student)){
                Long keyNeeded = null;
                for (Map.Entry<Long, model.Student> entry : studentMap.entrySet()) {
                    if (entry.getValue().equals(student)) {
                        keyNeeded = entry.getKey();
                    }
                }
                studentMap.replace(keyNeeded, student, studentNew);
            }
            return studentNew;
        }

        public model.Student delete(model.Student student){
            if (studentMap.containsValue(student)){
                studentMap.remove(student);
                return student;
            }
            throw new StudentNotExistException();
        }

        public List<model.Student> findAge(int age){
            List<model.Student> studentList = (List<model.Student>) studentMap.values();
            return studentList.stream().filter(student -> student.getAge() == age).collect(Collectors.toList());
        }
    }

    @Service
    public class FacultyService{

       private final Map<Long, model.Faculty> facultyMap = new HashMap<>();

        public model.Faculty add(model.Faculty faculty){
            if (facultyMap.containsValue(faculty)){
                throw new FacultyAlreadyExistException();
            }
            facultyMap.put(facultyCounter, faculty);
            facultyCounter++;
            return faculty;
        }

        public model.Faculty find(model.Faculty faculty){
            if (facultyMap.containsValue(faculty)){
                return faculty;
            }
            return null;
        }

        public model.Faculty update(model.Faculty faculty, model.Faculty facultyNew){
            if (facultyMap.containsValue(faculty)){
                Long keyNeeded = null;
                for (Map.Entry<Long, model.Faculty> entry : facultyMap.entrySet()) {
                    if (entry.getValue().equals(faculty)) {
                        keyNeeded = entry.getKey();
                    }
                }
                facultyMap.replace(keyNeeded, faculty, facultyNew);
            }
            return facultyNew;
        }

        public model.Faculty delete(model.Faculty faculty){
            if (facultyMap.containsValue(faculty)){
                facultyMap.remove(faculty);
                return faculty;
            }
            throw new FacultyNotExistException();
        }

        public List<model.Faculty> findColor(String color){
            List<model.Faculty> facultyList = (List<model.Faculty>) facultyMap.values();
            return facultyList.stream().filter(faculty -> faculty.getColor() == color).collect(Collectors.toList());
        }

    }

}
