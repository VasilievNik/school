package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;

    public Faculty() {
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

    public String setAge(String color) {
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
