package Database;

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
            studentGrades.put(subject, 0);
        }
    }

    public boolean gradeExists(Student student, String subject, int grade) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        return studentGrades != null && studentGrades.containsKey(subject) && studentGrades.get(subject) == grade;
    }

    public void editGrade(Student student, String subject, int grade) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        if (studentGrades != null) {
            studentGrades.replace(subject, grade);
        }
    }

    public Integer getGrade(Student student, String subject) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        if (studentGrades != null && studentGrades.containsKey(subject)) {
            Integer grade = studentGrades.get(subject);
            if (grade != null && grade != 0) {
                return grade;
            }
        }
        return null;
    }

    public void setGradeData(HashMap<Student, HashMap<String, Integer>> gradeData) {
        this.gradeData = gradeData;
    }

    public int getGradeForSubject(Student student, String subject) {
        HashMap<String, Integer> studentGrades = gradeData.get(student);

        if (studentGrades != null && studentGrades.containsKey(subject)) {
            Integer grade = studentGrades.get(subject);

            if (grade != null && grade != 0) {
                return grade;
            }
        }

        return -1;
    }

}
