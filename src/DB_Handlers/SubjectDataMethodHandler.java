package DB_Handlers;

import model.Student;

public interface SubjectDataMethodHandler {

    void addSubject(Student student,String subject_name, int grade);
//    void getSubjectList();
    void printStudentsWithSubjects();

}
