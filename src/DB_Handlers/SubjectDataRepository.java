package DB_Handlers;


import model.Student;

import java.util.HashMap;
import java.util.Map;

public class SubjectDataRepository implements SubjectDataMethodHandler{

    private HashMap<Student,HashMap<String,Integer>> subjectMap;

    public SubjectDataRepository() {
        subjectMap = new HashMap<>();
    }


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
    public HashMap<Student, HashMap<String, Integer>> getSubjectData(){
        return subjectMap;
    }

    @Override
    public int getSubjectCount(Student student) {
        if (subjectMap.containsKey(student)) {
            HashMap<String, Integer> studentSubjects = subjectMap.get(student);
            return studentSubjects.size();
        } else {
            return 0;
        }
    }

}
