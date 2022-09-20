package ru.hogwarts.school;

import java.util.Objects;

public class model {

    public class Student {
        private Long id;
        private String name;
        private int age;

        public Student(Long id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
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

    public class Faculty {
        private Long id;
        private String name;
        private String color;

        public Faculty(Long id, String name, String color) {
            this.id = id;
            this.name = name;
            this.color = color;
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
        public String getColor(){
            return this.color;
        }
        public String setAge(String color){
            this.color = color;
            return color;
        }
        public String toString(){
            return "Имя: "+this.name+" ID: "+this.id+" Цвет: "+this.color;
        }

        public boolean equals(Object other) {
            if (this.getClass() != other.getClass()) {
                return false;
            }
            Faculty faculty1 = (Faculty) other;

            return name.equals(faculty1.name);
        }

        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
