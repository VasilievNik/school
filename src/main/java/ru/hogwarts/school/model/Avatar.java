package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public Long getId() {
        return this.id;
    }



    /*public String toString() {
        return "Имя: " + this.name + " ID: " + this.id + " Цвет: " + this.color;
    }

    public boolean equals(Object anObject) {
        return this == anObject;
    }

    public int hashCode() {
        return Objects.hash(name);
    }*/
}
