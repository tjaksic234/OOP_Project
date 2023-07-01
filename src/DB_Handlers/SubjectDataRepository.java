package DB_Handlers;


import model.Student;

import java.util.HashMap;
import java.util.Map;

public class SubjectDataRepository implements SubjectDataMethodHandler{

    private HashMap<Student,HashMap<String,Integer>> subjectMap;

    public SubjectDataRepository() {
        subjectMap = new HashMap<>();
    }


    // TODO implement a new way to fetch and store students into the subjectMap
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


//    @Override
//    public void getSubjectList() {
//        System.out.println(toString());
//    }

    @Override
    public void printStudentsWithSubjects() {
        HashMap<Student, HashMap<String, Integer>> subjectMap = checkMapInfo();
        for (Map.Entry<Student, HashMap<String, Integer>> entry : subjectMap.entrySet()) {
            Student student = entry.getKey();
            HashMap<String, Integer> subjects = entry.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append("(Student: ").append(student.getIme()).append(" Surname: ").append(student.getSurname()).
                    append(", College: ").append(student.getCollege()).append(")\n");
            sb.append("Subjects:\n");

            for (Map.Entry<String, Integer> subjectEntry : subjects.entrySet()) {
                String subjectName = subjectEntry.getKey();
                Integer grade = subjectEntry.getValue();
                sb.append("  - ").append(subjectName).append(": ").append(grade).append("\n");
            }

            System.out.println(sb.toString());
        }
    }


    public HashMap<Student, HashMap<String, Integer>> checkMapInfo(){
        return subjectMap;
    }

//    @Override
//    public String toString() {
//        return "SubjectData{" +
//                "subjectMap=" + subjectMap +
//                '}';
//    }

}
