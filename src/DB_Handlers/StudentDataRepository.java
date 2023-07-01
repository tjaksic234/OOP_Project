package DB_Handlers;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDataRepository implements StudentDataMethodHandler {

    private List<Student> studentList;

    public StudentDataRepository() {
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
        if (studentList.isEmpty()) {
            return null;
        }else {
            return studentList.get(studentList.size() - 1);
        }
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
