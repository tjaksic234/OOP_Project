package controller;

import Database.ExamInfoRepository;
import Database.StudentDataRepository;
import model.Student;
import placeholder.GradeEvaluationRepository;
import placeholder.Teacher;
import placeholder.TeacherDataRepository;

import java.util.HashMap;
import java.util.List;


public class Controller {
    private StudentDataRepository studentDataRepository;
    private TeacherDataRepository teacherDataRepository;
    private ExamInfoRepository examInfoRepository;
    private GradeEvaluationRepository gradeEvaluationRepository;


    public Controller(StudentDataRepository studentDataRepository, TeacherDataRepository teacherDataRepository,
                      ExamInfoRepository examInfoRepository,
                      GradeEvaluationRepository gradeEvaluationRepository) {

        this.studentDataRepository = studentDataRepository;
        this.teacherDataRepository = teacherDataRepository;
        this.examInfoRepository = examInfoRepository;
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

    public void addExamInfo(String exam_subject) {
        examInfoRepository.addExamInfo(exam_subject);
    }

    public List<String> getExamInfo() {
        return examInfoRepository.getExamInfo();
    }


    // Teacher details manager

    public void addTeacher(Teacher teacher) {
        teacherDataRepository.addTeacher(teacher);
    }

    public List<Teacher> getTeacherList() {
        return teacherDataRepository.getTeacherList();
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

}
