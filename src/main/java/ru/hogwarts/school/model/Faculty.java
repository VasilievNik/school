package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private Collection<Student> students;

    public Faculty(long id, String name, String color) {
    }

    public Faculty() {

    }

    public Collection<Student> getStudents(){
        return this.students;
    }

    public Long getId() {
        return this.id;
    }

    public Long setId(Long id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getColor() {
        return this.color;
    }

    public String setColor(String color) {
        this.color = color;
        return color;
    }

    public String toString() {
        return "Имя: " + this.name + " ID: " + this.id + " Цвет: " + this.color;
    }

    public boolean equals(Object anObject) {
        return this == anObject;
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}
