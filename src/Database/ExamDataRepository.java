package Database;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The SubjectDataRepository class handles the storage and retrieval of subject data associated with students.
 */
public class ExamDataRepository {

    private List<String> examData;

    /**
     * Constructs a new SubjectDataRepository object.
     * Initializes an empty map to store subject data.
     */
    public ExamDataRepository() {
        examData = new ArrayList<>();
    }


    public void addExam(String exam_subject) {
        examData.add(exam_subject);
    }


    public List<String> getExamData() {
        return examData;
    }




}
