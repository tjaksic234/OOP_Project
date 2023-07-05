package model;

import javax.swing.table.DefaultTableModel;
import java.util.*;


public class TableSorter {

    /**
     * Sorts a table of student grades in descending order.
     *
     * @param studentGrades The unsorted map of student grades.
     * @return A new map with student grades sorted in descending order.
     */
    public static HashMap<Student, Double> sortTableDescending(HashMap<Student, Double> studentGrades) {
        List<Map.Entry<Student, Double>> sortedGrades = new ArrayList<>(studentGrades.entrySet());
        Collections.sort(sortedGrades, new Comparator<Map.Entry<Student, Double>>() {
            @Override
            /**
             * Compares two entries for sorting in descending order.
             *
             * @param entry1 The first entry.
             * @param entry2 The second entry.
             * @return A negative integer, zero, or a positive integer as the first entry is greater than,
             *         equal to, or less than the second entry, respectively.
             */
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

    /**
     * Sorts a table of student grades in ascending order.
     *
     * @param studentGrades The unsorted map of student grades.
     * @return A new map with student grades sorted in ascending order.
     */
    public static HashMap<Student, Double> sortTableAscending(HashMap<Student, Double> studentGrades) {
        List<Map.Entry<Student, Double>> sortedGrades = new ArrayList<>(studentGrades.entrySet());
        Collections.sort(sortedGrades, new Comparator<Map.Entry<Student, Double>>() {
            @Override
            /**
             * Compares two entries for sorting in ascending order.
             *
             * @param entry1 The first entry.
             * @param entry2 The second entry.
             * @return A negative integer, zero, or a positive integer as the first entry is less than,
             *         equal to, or greater than the second entry, respectively.
             */
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
