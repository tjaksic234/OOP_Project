package DB_Handlers;

import model.Student;

import java.util.List;

public interface StudentDataMethodHandler {

    void addStudent(Student student);
    void getStudentList();
    void removeLastStudent();
    Student getLastStudent();
}
