package Database;

import model.Student;

import java.util.HashMap;

/**
 * The TableDataMethodHandler interface defines the contract for handling table data operations.
 */
public interface TableDataMethodHandler {

    /**
     * Calculates the average grades based on the provided subject map.
     *
     * @param subjectMap The map of students and their subject data.
     */
    void averageGradeCalculator(HashMap<Student, HashMap<String, Integer>> subjectMap);

    /**
     * Prints the average grades.
     */
    void printAverageGrades();

    /**
     * Retrieves the map of students and their average grades.
     *
     * @return The map of students and their average grades.
     */
    HashMap<Student, Double> getAverageGrades();

    /**
     * Sets the map of students and their average grades.
     *
     * @param averageGradesMap The map of students and their average grades to be set.
     */
    void setAverageGrades(HashMap<Student, Double> averageGradesMap);
}
