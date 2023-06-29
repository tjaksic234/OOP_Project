package model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void removeLastStudent() {
        studentList.remove(studentList.size()-1);
    }

    public Student lastStudent() {
        return studentList.get(studentList.size()-1);
    }



    @Override
    public String toString() {
        return "StudentManager{" +
                "studentList=" + studentList +
                '}';
    }
}
