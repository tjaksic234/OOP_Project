package Database;

import java.util.ArrayList;
import java.util.List;


public class ExamInfoRepository {

    private List<String> examInfo;


    public ExamInfoRepository() {
        examInfo = new ArrayList<>();
    }


    public void addExamInfo(String exam_subject) {
        examInfo.add(exam_subject);
    }


    public List<String> getExamInfo() {
        return examInfo;
    }




}
