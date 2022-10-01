package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    public Student() {
    }

    public Long getId(){
        return this.id;
    }

    public Long setId(Long id){
        this.id = id;
        return id;
    }
    public String getName(){
        return this.name;
    }
    public String setName(String name){
        this.name = name;
        return name;
    }
    public int getAge(){
        return this.age;
    }
    public int setAge(int age){
        this.age = age;
        return age;
    }
    public String toString(){
        return "Имя: "+this.name+" ID: "+this.id+" Возраст: "+this.age;
    }

    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Student student1 = (Student) other;

        return name.equals(student1.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}

