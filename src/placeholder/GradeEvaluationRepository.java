package placeholder;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeEvaluationRepository {

    private HashMap<Student, HashMap<String, Integer>> gradeData;

    public GradeEvaluationRepository() {
        gradeData = new HashMap<>();
    }

    public void addGrade(Student student, String subject, int grade) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        if (studentGrades == null) {
            studentGrades = new HashMap<>();
            gradeData.put(student, studentGrades);
        }

        studentGrades.put(subject, grade);
    }

    public HashMap<Student, HashMap<String, Integer>> getGradeData() {
        return gradeData;
    }

    public void removeGrade(Student student, String subject) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        if (studentGrades != null) {
            studentGrades.remove(subject);
        }
    }

    public boolean gradeExists(Student student, String subject, int grade) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        return studentGrades != null && studentGrades.containsKey(subject) && studentGrades.get(subject) == grade;
    }
}
