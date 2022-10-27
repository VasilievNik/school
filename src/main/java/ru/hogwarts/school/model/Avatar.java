package ru.hogwarts.school.model;

import javax.persistence.*;

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
    public Student setStudent(Student student) {
        this.student = student;
        return student;
    }

    public Long getId(){
        return this.id;
    }
    public Long setId(Long id){
        this.id = id;
        return id;
    }

    public String getPath() {
        return this.filePath;
    }
    public String setFilePath(String filePath) {
        this.filePath = filePath;
        return filePath;
    }

    public Long getSize() {
        return this.fileSize;
    }
    public Long setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        return fileSize;
    }

    public String getType() {
        return this.mediaType;
    }
    public String setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return mediaType;
    }

    public byte[] getData() {
        return data;
    }
    public byte[] setData(byte[] data) {
        this.data = data;
        return data;
    }

}
