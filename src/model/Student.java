package model;

import java.util.Objects;

public class Student {

    private String name;
    private String surname;
    private String college;

    public Student(String name, String surname, String college){
        this.name = name;
        this.surname = surname;
        this.college = college;
    }

    public String getIme() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCollege() {
        return college;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", college='" + college + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(college, student.college);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, college);
    }


}
