package DB_Handlers;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableDataRepository implements TableDataMethodHandler{

    private HashMap<Student, Double> averageGradesMap;

    public TableDataRepository() {
        averageGradesMap = new HashMap<>();
    }


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

    public HashMap<Student, Double> getAverageGradesMap() {
        return averageGradesMap;
    }
}
