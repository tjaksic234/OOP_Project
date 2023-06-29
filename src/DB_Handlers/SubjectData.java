package DB_Handlers;


import java.util.HashMap;

public class SubjectData implements SubjectDataMethodHandler{

    private HashMap<String, Integer> subjectMap;

    public SubjectData() {
        subjectMap = new HashMap<>();
    }

    @Override
    public void addSubject(String subject_name, int grade) {
        subjectMap.put(subject_name, grade);
    }

    @Override
    public void getSubjectList() {
        System.out.println(toString());
    }

    @Override
    public void createNewSubjectData() {
        subjectMap = new HashMap<>();
    }

    public HashMap<String,Integer> checkMapInfo(){
        return subjectMap;
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                "subjectMap=" + subjectMap +
                '}';
    }


}
