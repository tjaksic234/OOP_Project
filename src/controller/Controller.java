package controller;

import DB_Handlers.StudentData;
import DB_Handlers.SubjectData;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private StudentData studentData;
    private SubjectData subjectData;

    public Controller(StudentData studentData, SubjectData subjectData) {
        this.studentData = studentData;
        this.subjectData = subjectData;
    }

    //Student details manager
    public void addStudent(Student student) {
        studentData.addStudent(student);
    }
    public void getStudentList() {
        studentData.getStudentList();
    }
    public void removeLastStudent() {
        studentData.removeLastStudent();
    }

    //Subject details manager
    public void addSubject(String subject_name, int grade) {
        subjectData.addSubject(subject_name, grade);
    }
    public void getSubjectList() {
        subjectData.getSubjectList();
    }
    public void createNewSubjectData() {
        subjectData.createNewSubjectData();
    }

    public void getStudentsWithSubjectsString() {
        StringBuilder sb = new StringBuilder();
        List<Student> students = studentData.checkInfo();
        for (Student student : students) {
            sb.append("Student: ").append(student.getIme()).append(" ").append(student.getSurname()).append(" ")
                    .append(student.getCollege()).append("\n");
            sb.append("Subjects:\n");
            Map<String, Integer> subjects = subjectData.checkMapInfo();
            for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
                sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
