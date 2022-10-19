package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public abstract class Student{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Student(int i, String s, int i1) {
    }

    public Student() {
    }

    public Faculty getFaculty() {
        return faculty;
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

    public boolean equals(Object anObject) {
        return this == anObject;
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}

