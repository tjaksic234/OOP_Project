package controller;

import Database.StudentDataRepository;
import Database.ExamDataRepository;
import model.Student;
import placeholder.RegisteredExamsRepository;
import placeholder.GradeEvaluationRepository;
import placeholder.Teacher;
import placeholder.TeacherDataRepository;

import java.util.HashMap;
import java.util.List;


public class Controller {
    private StudentDataRepository studentDataRepository;
    private TeacherDataRepository teacherDataRepository;
    private ExamDataRepository examDataRepository;
    private RegisteredExamsRepository registeredExamsRepository;
    private GradeEvaluationRepository gradeEvaluationRepository;


    public Controller(StudentDataRepository studentDataRepository, TeacherDataRepository teacherDataRepository,
                      ExamDataRepository examDataRepository,
                      RegisteredExamsRepository tableDataRepository,
                      GradeEvaluationRepository gradeEvaluationRepository) {

        this.studentDataRepository = studentDataRepository;
        this.teacherDataRepository = teacherDataRepository;
        this.examDataRepository = examDataRepository;
        this.registeredExamsRepository = tableDataRepository;
        this.gradeEvaluationRepository = gradeEvaluationRepository;
    }

    // Student details manager

    /**
     * Adds a new student to the data repository.
     *
     * @param student The student to be added.
     */
    public void addStudent(Student student) {
        studentDataRepository.addStudent(student);
    }

    /**
     * Removes the last added student from the data repository.
     */
    public void removeLastStudent() {
        studentDataRepository.removeLastStudent();
    }

    /**
     * Retrieves the last added student from the data repository.
     *
     * @return The last added student.
     */
    public Student getLastStudent() {
        return studentDataRepository.getLastStudent();
    }

    /**
     * Retrieves the student at the specified index from the data repository.
     *
     * @param index The index of the student to be retrieved.
     * @return The student at the specified index.
     */
    public Student getStudentAtIndex(int index) {
        return studentDataRepository.getStudentAtIndex(index);
    }

    /**
     * Retrieves the list of students from the data repository.
     *
     * @return The list of students.
     */
    public List<Student> getStudentList() {
        return studentDataRepository.getStudentData();
    }

    // Exam details manager

    public void addExam(String exam_subject) {
        examDataRepository.addExam(exam_subject);
    }

    public List<String> getExamData() {
        return examDataRepository.getExamData();
    }


    // Teacher details manager

    public void addTeacher(Teacher teacher) {
        teacherDataRepository.addTeacher(teacher);
    }

    public List<Teacher> getTeacherList() {
        return teacherDataRepository.getTeacherList();
    }


    // Results manager

    public void addResult(Student student, String result) {
        registeredExamsRepository.addResult(student, result);
    }

    public HashMap<Student, List<String>> getRegisteredExams() {
        return registeredExamsRepository.getRegisteredExams();
    }

    public void removeExamFromResult(Student student,String exam) {
        registeredExamsRepository.removeExamFromResult(student, exam);
    }

    public boolean duplicateExamCheck(Student student, String exam) {
        return registeredExamsRepository.duplicateExamCheck(student, exam);
    }

    // Student Evaluation manager

    public void addGrade(String student, int grade) {
        gradeEvaluationRepository.addGrade(student, grade);
    }

    public HashMap<String, List<Integer>> getGradeData() {
        return gradeEvaluationRepository.getGradeData();
    }

    public void removeGrade(String student, int grade) {
        gradeEvaluationRepository.removeGrade(student, grade);
    }

    public boolean gradeExists(String student, int grade) {
        return gradeEvaluationRepository.gradeExists(student, grade);
    }

}
