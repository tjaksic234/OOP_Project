package Database;

import model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentDataRepository class handles the storage and retrieval of student data.
 */
public class StudentDataRepository implements StudentDataMethodHandler {

    private List<Student> studentList;

    /**
     * Constructs a new StudentDataRepository object.
     * Initializes an empty list to store student data.
     */
    public StudentDataRepository() {
        studentList = new ArrayList<>();
    }

    /**
     * Adds a student to the data repository.
     *
     * @param student The student to be added.
     */
    @Override
    public void addStudent(Student student) {
        studentList.add(student);
    }

    /**
     * Retrieves the list of students from the data repository and prints their details.
     */
    @Override
    public void getStudentList() {
        for (Student student : studentList) {
            System.out.println("(Student: " + student.getName() + ", Surname: " + student.getSurname() +
                    ", College: " + student.getCollege() + ")");
        }
    }

    /**
     * Removes the last added student from the data repository.
     */
    @Override
    public void removeLastStudent() {
        studentList.remove(studentList.size() - 1);
    }

    /**
     * Retrieves the last added student from the data repository.
     *
     * @return The last added student, or null if the repository is empty.
     */
    @Override
    public Student getLastStudent() {
        if (studentList.isEmpty()) {
            return null;
        } else {
            return studentList.get(studentList.size() - 1);
        }
    }

    /**
     * Retrieves the list of students stored in the data repository.
     *
     * @return The list of students.
     */
    public List<Student> getStudentData() {
        return studentList;
    }
}
