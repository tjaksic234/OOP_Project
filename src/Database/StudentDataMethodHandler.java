package Database;

import model.Student;

public interface StudentDataMethodHandler {

    void addStudent(Student student);
    void getStudentList();
    void removeLastStudent();
    Student getLastStudent();
}
