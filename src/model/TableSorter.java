package model;

import javax.swing.table.DefaultTableModel;
import java.util.*;

public class TableSorter {
    public static HashMap<Student, Double> sortTableDescending(HashMap<Student, Double> studentGrades) {
        List<Map.Entry<Student, Double>> sortedGrades = new ArrayList<>(studentGrades.entrySet());
        Collections.sort(sortedGrades, new Comparator<Map.Entry<Student, Double>>() {
            @Override
            public int compare(Map.Entry<Student, Double> entry1, Map.Entry<Student, Double> entry2) {
                // Sort in descending order
                return Double.compare(entry2.getValue(), entry1.getValue());
            }
        });

        // Create a new sorted map
        LinkedHashMap<Student, Double> sortedStudentGrades = new LinkedHashMap<>();
        for (Map.Entry<Student, Double> entry : sortedGrades) {
            sortedStudentGrades.put(entry.getKey(), entry.getValue());
        }

        return sortedStudentGrades;
    }

    public static HashMap<Student, Double> sortTableAscending(HashMap<Student, Double> studentGrades) {
        List<Map.Entry<Student, Double>> sortedGrades = new ArrayList<>(studentGrades.entrySet());
        Collections.sort(sortedGrades, new Comparator<Map.Entry<Student, Double>>() {
            @Override
            public int compare(Map.Entry<Student, Double> entry1, Map.Entry<Student, Double> entry2) {
                // Sort in ascending order
                return Double.compare(entry1.getValue(), entry2.getValue());
            }
        });

        // Create a new sorted map
        LinkedHashMap<Student, Double> sortedStudentGrades = new LinkedHashMap<>();
        for (Map.Entry<Student, Double> entry : sortedGrades) {
            sortedStudentGrades.put(entry.getKey(), entry.getValue());
        }

        return sortedStudentGrades;
    }
}
