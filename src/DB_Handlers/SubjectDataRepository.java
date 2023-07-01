package DB_Handlers;


import model.Student;

import java.util.HashMap;

public class SubjectDataRepository implements SubjectDataMethodHandler{

    private HashMap<Student,HashMap<String,Integer>> subjectMap;

    public SubjectDataRepository() {
        subjectMap = new HashMap<>();
    }


    // TODO implement a new way to fetch and store students into the subjectMap
    @Override
    public void addSubject(Student student,String subject_name, int grade) {
        if (student != null) {
            if (subjectMap.containsKey(student)) {
                HashMap<String, Integer> studentSubjects = subjectMap.get(student);
                studentSubjects.put(subject_name, grade);
            } else {
                HashMap<String, Integer> studentSubjects = new HashMap<>();
                studentSubjects.put(subject_name, grade);
                subjectMap.put(student, studentSubjects);
            }
        }
    }


    @Override
    public void getSubjectList() {
        System.out.println(toString());
    }


    public HashMap<Student, HashMap<String, Integer>> checkMapInfo(){
        return subjectMap;
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                "subjectMap=" + subjectMap +
                '}';
    }

}
