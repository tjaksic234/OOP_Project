package placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GradeEvaluationRepository {

    private HashMap<String, List<Integer>> gradeData;

    public GradeEvaluationRepository() {
        gradeData = new HashMap<>();
    }

    public void addGrade(String student, int grade) {
        List<Integer> results = gradeData.get(student);
        if (results == null) {
            results = new ArrayList<>();
            gradeData.put(student, results);
        }
        results.add(grade);
    }

    public HashMap<String, List<Integer>> getGradeData() {
        return gradeData;
    }

    public void removeGrade(String student, int gradeToRemove) {
        List<Integer> results = gradeData.get(student);
        if (results != null) {
            if (results.contains(gradeToRemove)) {
                results.remove(Integer.valueOf(gradeToRemove));
            }
        }
    }


    public boolean gradeExists(String student, int grade) {
        List<Integer> grades = gradeData.get(student);
        if (grades == null) {
            return false;
        } else {
            return grades.contains(grade);

        }
    }



}
