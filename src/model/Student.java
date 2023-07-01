package model;

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


}
