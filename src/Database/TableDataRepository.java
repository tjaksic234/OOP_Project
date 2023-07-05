package Database;

import model.Student;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * The TableDataRepository class handles the storage and manipulation of table data.
 */
public class TableDataRepository implements TableDataMethodHandler {

    private HashMap<Student, Double> averageGradesMap;

    /**
     * Constructs a new TableDataRepository object.
     * Initializes an empty map to store average grades.
     */
    public TableDataRepository() {
        averageGradesMap = new HashMap<>();
    }

    /**
     * Calculates the average grades based on the provided subject map.
     *
     * @param subjectMap The map of students and their subject data.
     */
    @Override
    public void averageGradeCalculator(HashMap<Student, HashMap<String, Integer>> subjectMap) {
        for (Map.Entry<Student, HashMap<String, Integer>> entry : subjectMap.entrySet()) {
            Student student = entry.getKey();
            HashMap<String, Integer> subjects = entry.getValue();

            int totalGrades = 0;
            int subjectCount = subjects.size();

            for (Integer grade : subjects.values()) {
                totalGrades += grade;
            }

            double averageGrade = (double) totalGrades / subjectCount;

            averageGradesMap.put(student, averageGrade);
        }
    }

    /**
     * Prints the average grades.
     */
    @Override
    public void printAverageGrades() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (Map.Entry<Student, Double> entry : averageGradesMap.entrySet()) {
            Student student = entry.getKey();
            Double averageGrade = entry.getValue();

            String formattedAverageGrade = decimalFormat.format(averageGrade);

            StringBuilder sb = new StringBuilder();
            sb.append("Student: ").append(student.toString()).append("\n");
            sb.append("Average grade: ").append(formattedAverageGrade);
            System.out.println(sb.toString());
        }
    }

    /**
     * Retrieves the map of students and their average grades.
     *
     * @return The map of students and their average grades.
     */
    @Override
    public HashMap<Student, Double> getAverageGradesMap() {
        return averageGradesMap;
    }

    /**
     * Sets the map of students and their average grades.
     *
     * @param averageGradesMap The map of students and their average grades to be set.
     */
    @Override
    public void setAverageGrades(HashMap<Student, Double> averageGradesMap) {
        this.averageGradesMap = averageGradesMap;
    }
}
