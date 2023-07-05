package controller;

import Database.StudentDataRepository;
import Database.SubjectDataRepository;
import Database.TableDataRepository;
import model.Student;

import java.util.HashMap;

/**
 * The Controller class manages the interaction between the data repositories and the application logic.
 * It provides methods to add and remove students, manage subject details, and perform calculations on table data.
 */
public class Controller {
    private StudentDataRepository studentDataRepository;
    private SubjectDataRepository subjectDataRepository;
    private TableDataRepository tableDataRepository;

    /**
     * Constructs a Controller object with the specified data repositories.
     *
     * @param studentDataRepository The repository for student data.
     * @param subjectDataRepository The repository for subject data.
     * @param tableDataRepository   The repository for table data.
     */
    public Controller(StudentDataRepository studentDataRepository, SubjectDataRepository subjectDataRepository,
                      TableDataRepository tableDataRepository) {

        this.studentDataRepository = studentDataRepository;
        this.subjectDataRepository = subjectDataRepository;
        this.tableDataRepository = tableDataRepository;
    }

    // Student details manager

    /**
     * Adds a new student to the data repository.
     *
     * @param student The student to be added.
     */
    public void addStudent(Student student) {
        studentDataRepository.addStudent(student);
    }

    /**
     * Removes the last added student from the data repository.
     */
    public void removeLastStudent() {
        studentDataRepository.removeLastStudent();
    }

    /**
     * Retrieves the last added student from the data repository.
     *
     * @return The last added student.
     */
    public Student getLastStudent() {
        return studentDataRepository.getLastStudent();
    }

    // Subject details manager

    /**
     * Adds a subject with the given name and grade to a specific student.
     *
     * @param student      The student to whom the subject is added.
     * @param subject_name The name of the subject.
     * @param grade        The grade obtained in the subject.
     */
    public void addSubject(Student student, String subject_name, int grade) {
        subjectDataRepository.addSubject(student, subject_name, grade);
    }

    /**
     * Retrieves the count of subjects associated with a specific student.
     *
     * @param student The student for whom the subject count is retrieved.
     * @return The count of subjects associated with the student.
     */
    public int getSubjectCount(Student student) {
        return subjectDataRepository.getSubjectCount(student);
    }

    // Table details manager

    /**
     * Calculates the average grades for each student based on the subject data.
     */
    public void averageGradeCalculator() {
        HashMap<Student, HashMap<String, Integer>> subjectMap = subjectDataRepository.getSubjectData();
        tableDataRepository.averageGradeCalculator(subjectMap);
    }

    /**
     * Calculates the average grades for each student based on the subject data and prints them.
     */
    public void printAverageGrades() {
        HashMap<Student, HashMap<String, Integer>> subjectMap = subjectDataRepository.getSubjectData();
        tableDataRepository.averageGradeCalculator(subjectMap);
        tableDataRepository.printAverageGrades();
    }

    /**
     * Retrieves a map of students and their corresponding average grades.
     *
     * @return The map of students and their average grades.
     */
    public HashMap<Student, Double> getAverageGrades() {
        return tableDataRepository.getAverageGradesMap();
    }

    /**
     * Sets the map of students and their corresponding average grades.
     *
     * @param averageGradesMap The map of students and their average grades.
     */
    public void setAverageGrades(HashMap<Student, Double> averageGradesMap) {
        tableDataRepository.setAverageGrades(averageGradesMap);
    }
}
