package Database;

import model.Student;

import java.util.HashMap;

/**
 * The SubjectDataMethodHandler interface defines the contract for handling subject data operations.
 */
public interface SubjectDataMethodHandler {

    /**
     * Adds a subject with the given name and grade to a specific student.
     *
     * @param student      The student to whom the subject is added.
     * @param subject_name The name of the subject.
     * @param grade        The grade obtained in the subject.
     */
    void addSubject(Student student, String subject_name, int grade);

    /**
     * Retrieves the subject data, represented as a map of students and their subject information.
     *
     * @return The map of students and their subject data.
     */
    HashMap<Student, HashMap<String, Integer>> getSubjectData();

    /**
     * Retrieves the count of subjects associated with a specific student.
     *
     * @param student The student for whom the subject count is retrieved.
     * @return The count of subjects associated with the student.
     */
    int getSubjectCount(Student student);
}
