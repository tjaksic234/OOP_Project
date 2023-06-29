package DB_Handlers;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentData implements StudentDataMethodHandler {

    private List<Student> studentList;

    public StudentData() {
        studentList = new ArrayList<>();
    }

    @Override
    public void addStudent(Student student) {
        studentList.add(student);
    }

    @Override
    public void getStudentList() {
        System.out.println(toString());
    }

    @Override
    public void removeLastStudent() {
        studentList.remove(studentList.size()-1);
    }

    @Override
    public Student getLastStudent() {
        return studentList.get(studentList.size()-1);
    }

    public List<Student> checkInfo() {
    	return studentList;
    }

    @Override
    public String toString() {
        return "StudentDataRepository{" +
                "studentList=" + studentList +
                '}';
    }
}
