package Database;

import model.Student;

import java.util.HashMap;

/**
 * The SubjectDataRepository class handles the storage and retrieval of subject data associated with students.
 */
public class SubjectDataRepository implements SubjectDataMethodHandler {

    private HashMap<Student, HashMap<String, Integer>> subjectMap;

    /**
     * Constructs a new SubjectDataRepository object.
     * Initializes an empty map to store subject data.
     */
    public SubjectDataRepository() {
        subjectMap = new HashMap<>();
    }

    /**
     * Adds a subject with the given name and grade to a specific student.
     *
     * @param student       The student to whom the subject is added.
     * @param subject_name  The name of the subject.
     * @param grade         The grade obtained in the subject.
     */
    @Override
    public void addSubject(Student student, String subject_name, int grade) {
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

    /**
     * Retrieves the subject data, represented as a map of students and their subject information.
     *
     * @return The map of students and their subject data.
     */
    @Override
    public HashMap<Student, HashMap<String, Integer>> getSubjectData() {
        return subjectMap;
    }

    public void setSubjectData(HashMap<Student, HashMap<String, Integer>> subjectMap) {
        this.subjectMap = subjectMap;
    }

    /**
     * Retrieves the count of subjects associated with a specific student.
     *
     * @param student The student for whom the subject count is retrieved.
     * @return The count of subjects associated with the student.
     */
    @Override
    public int getSubjectCount(Student student) {
        if (subjectMap.containsKey(student)) {
            HashMap<String, Integer> studentSubjects = subjectMap.get(student);
            return studentSubjects.size();
        } else {
            System.out.println("The student does not exist in the database.");
            return 0;
        }
    }
}
