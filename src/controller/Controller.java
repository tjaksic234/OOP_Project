package controller;

import DB_Handlers.StudentDataRepository;
import DB_Handlers.SubjectDataRepository;
import DB_Handlers.TableDataRepository;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private StudentDataRepository studentDataRepository;
    private SubjectDataRepository subjectDataRepository;
    private TableDataRepository tableDataRepository;

    public Controller(StudentDataRepository studentDataRepository, SubjectDataRepository subjectDataRepository,
                      TableDataRepository tableDataRepository) {
        this.studentDataRepository = studentDataRepository;
        this.subjectDataRepository = subjectDataRepository;
        this.tableDataRepository = tableDataRepository;
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
//    public void getSubjectList() {
//        subjectDataRepository.getSubjectList();
//    }
    public void printStudentsWithSubjects() {
        subjectDataRepository.printStudentsWithSubjects();
    }

    // Table details manager
    public void averageGradeCalculator() {
        HashMap<Student, HashMap<String, Integer>> subjectMap = subjectDataRepository.checkMapInfo();
        tableDataRepository.averageGradeCalculator(subjectMap);
    }
    public void printAverageGrades(){
        HashMap<Student, HashMap<String, Integer>> subjectMap = subjectDataRepository.checkMapInfo();
        tableDataRepository.averageGradeCalculator(subjectMap);
        tableDataRepository.printAverageGrades();
    }



}




