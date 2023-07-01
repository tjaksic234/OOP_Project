package controller;

import DB_Handlers.StudentDataRepository;
import DB_Handlers.SubjectDataRepository;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private StudentDataRepository studentDataRepository;
    private SubjectDataRepository subjectDataRepository;

    public Controller(StudentDataRepository studentDataRepository, SubjectDataRepository subjectDataRepository) {
        this.studentDataRepository = studentDataRepository;
        this.subjectDataRepository = subjectDataRepository;
    }

    // Student details manager
    public void addStudent(Student student) {
        studentDataRepository.addStudent(student);
    }
    public void getStudentList() {
        studentDataRepository.getStudentList();
    }
    public void removeLastStudent() {
        studentDataRepository.removeLastStudent();
    }
    public Student getLastStudent() {
        return studentDataRepository.getLastStudent();
    }

    // Subject details manager
    public void addSubject(Student student,String subject_name, int grade) {
        subjectDataRepository.addSubject(student, subject_name, grade);
    }
    public void getSubjectList() {
        subjectDataRepository.getSubjectList();
    }


    public void getStudentsWithSubjectsString() {
        HashMap<Student, HashMap<String, Integer>> subjectMap = subjectDataRepository.checkMapInfo();
        for (Map.Entry<Student, HashMap<String, Integer>> entry : subjectMap.entrySet()) {
            Student student = entry.getKey();
            HashMap<String, Integer> subjects = entry.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append("Student: ").append(student.toString()).append("\n");
            sb.append("Subjects: ").append(subjects.toString());
            System.out.println(sb.toString());
        }
    }

}




