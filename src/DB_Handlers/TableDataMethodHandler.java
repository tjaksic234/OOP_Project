package DB_Handlers;

import model.Student;

import java.util.HashMap;
import java.util.List;

public interface TableDataMethodHandler {

    void averageGradeCalculator(HashMap<Student, HashMap<String, Integer>> subjectMap);
    void printAverageGrades();
    HashMap<Student, Double> getAverageGradesMap();
    void setAverageGrades(HashMap<Student, Double> averageGradesMap);
}
