package placeholder;

import model.Student;

import java.util.*;

public class RegisteredExamsRepository {

    private HashMap<Student, List<String>> registeredExams;

    public RegisteredExamsRepository() {
        registeredExams = new HashMap<>();
    }

    public void addResult(Student student, String exam) {
        List<String> exams = registeredExams.get(student);
        if (exams == null) {
            exams = new ArrayList<>();
            registeredExams.put(student, exams);
        }
        exams.add(exam);
    }

    public HashMap<Student, List<String>> getRegisteredExams() {
        return registeredExams;
    }

    public void removeExamFromResult(Student student, String exam) {
        List<String> exams = registeredExams.get(student);
        if (exams != null) {
            exams.remove(exam);
        }
    }

    public boolean duplicateExamCheck(Student student, String exam) {
        List<String> exams = registeredExams.get(student);
        return exams != null && exams.contains(exam);
    }


}
