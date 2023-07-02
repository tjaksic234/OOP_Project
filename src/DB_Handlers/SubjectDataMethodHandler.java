package DB_Handlers;

import model.Student;

import java.util.HashMap;

public interface SubjectDataMethodHandler {

    void addSubject(Student student,String subject_name, int grade);
    HashMap<Student, HashMap<String, Integer>> getSubjectData();
    int getSubjectCount(Student student);

}
