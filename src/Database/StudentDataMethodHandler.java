package Database;

import model.Student;

/**
 * The StudentDataMethodHandler interface defines the contract for handling student data operations.
 */
public interface StudentDataMethodHandler {

    /**
     * Adds a student to the data repository.
     *
     * @param student The student to be added.
     */
    void addStudent(Student student);

    /**
     * Retrieves the list of students from the data repository.
     */
    void getStudentList();

    /**
     * Removes the last added student from the data repository.
     */
    void removeLastStudent();

    /**
     * Retrieves the last added student from the data repository.
     *
     * @return The last added student.
     */
    Student getLastStudent();
}
