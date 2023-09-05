package Database;

import java.util.ArrayList;
import java.util.List;


public class ExamInformationRepository {

    private List<String> examInfo;


    public ExamInformationRepository() {
        examInfo = new ArrayList<>();
    }


    public void addExamInfo(String exam_subject) {
        examInfo.add(exam_subject);
    }


    public List<String> getExamInfo() {
        return examInfo;
    }




}
