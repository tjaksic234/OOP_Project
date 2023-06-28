package aux_classes;

import java.util.LinkedHashMap;

public class SubjectManager {

    private LinkedHashMap<String, Integer> subjectMap;

    public SubjectManager() {
        subjectMap = new LinkedHashMap<>();
    }

    public void addSubject(String subject_name, int grade) {
        subjectMap.put(subject_name, grade);
    }

    public LinkedHashMap<String, Integer> getSubjectMap() {
        return subjectMap;
    }

    @Override
    public String toString() {
        return "SubjectManager{" +
                "subjectMap=" + subjectMap +
                '}';
    }
}
