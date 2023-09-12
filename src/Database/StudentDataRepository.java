package Database;

import model.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentDataRepository {

    private List<Student> studentList;


    public StudentDataRepository() {
        studentList = new ArrayList<>();
    }


    public void addStudent(Student student) {
        studentList.add(student);
    }


    public void getStudentList() {
        for (Student student : studentList) {
            System.out.println("(Student: " + student.getName() + ", Surname: " + student.getSurname() + ")");
        }
    }


    public void removeLastStudent() {
        studentList.remove(studentList.size() - 1);
    }


    public Student getLastStudent() {
        if (studentList.isEmpty()) {
            return null;
        } else {
            return studentList.get(studentList.size() - 1);
        }
    }


    public Student getStudentAtIndex(int index) {
        return studentList.get(index);
    }


    public List<Student> getStudentData() {
        return studentList;
    }
}
