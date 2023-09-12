package controller;

import Database.StudentDataRepository;
import model.Student;
import Database.GradeEvaluationRepository;
import model.Teacher;
import Database.TeacherDataRepository;

import java.util.HashMap;
import java.util.List;


public class Controller {
    private StudentDataRepository studentDataRepository;
    private TeacherDataRepository teacherDataRepository;
    private GradeEvaluationRepository gradeEvaluationRepository;


    public Controller(StudentDataRepository studentDataRepository, TeacherDataRepository teacherDataRepository,
                      GradeEvaluationRepository gradeEvaluationRepository) {

        this.studentDataRepository = studentDataRepository;
        this.teacherDataRepository = teacherDataRepository;
        this.gradeEvaluationRepository = gradeEvaluationRepository;
    }

    // Student details manager

    public void addStudent(Student student) {
        studentDataRepository.addStudent(student);
    }

    public List<Student> getStudentList() {
        return studentDataRepository.getStudentData();
    }

    // Teacher details manager

    public void addTeacher(Teacher teacher) {
        teacherDataRepository.addTeacher(teacher);
    }

    public List<String> getSubjectsForTeacher(Teacher teacher) {
        return teacherDataRepository.getSubjectsForTeacher(teacher);
    }

    public void addSubjectToTeacher(Teacher teacher, String subject) {
        teacherDataRepository.addSubjectToTeacher(teacher, subject);
    }

    public HashMap<Teacher, List<String>> getTeacherMap() {
        return teacherDataRepository.getTeacherMap();
    }

    public void removeTeacher(Teacher teacher) {
        teacherDataRepository.removeTeacher(teacher);
    }

    public void removeSubjectFromTeacher(Teacher teacher, String subject) {
        teacherDataRepository.removeSubjectFromTeacher(teacher, subject);
    }

    public Teacher getTeacher(String name, String surname) {
        return teacherDataRepository.getTeacher(name, surname);
    }

    // Student Evaluation manager

    public void addGrade(Student student, String subject, int grade) {
        gradeEvaluationRepository.addGrade(student, subject, grade);
    }

    public HashMap<Student, HashMap<String, Integer>> getGradeData() {
        return gradeEvaluationRepository.getGradeData();
    }

    public void removeGrade(Student student, String subject) {
        gradeEvaluationRepository.removeGrade(student, subject);
    }

    public boolean gradeExists(Student student, String subject, int grade) {
        return gradeEvaluationRepository.gradeExists(student, subject, grade);
    }

    public void editGrade(Student student, String subject, int grade) {
        gradeEvaluationRepository.editGrade(student, subject, grade);
    }

    public Integer getGrade(Student student, String subject) {
        return gradeEvaluationRepository.getGrade(student, subject);
    }

}