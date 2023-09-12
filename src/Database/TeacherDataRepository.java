package Database;

import model.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherDataRepository {

    private HashMap<Teacher, List<String>> teacherMap;

    public TeacherDataRepository() {
        teacherMap = new HashMap<>();
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher, new ArrayList<>());
    }

    public void removeTeacher(Teacher teacher) {
        teacherMap.remove(teacher);
    }

    public HashMap<Teacher, List<String>> getTeacherMap() {
        return teacherMap;
    }

    public void addSubjectToTeacher(Teacher teacher, String subject) {

        List<String> subjects = teacherMap.get(teacher);

        if (!subjects.contains(subject)) {
            subjects.add(subject);
        }

        teacherMap.put(teacher, subjects);
    }


    public void removeSubjectFromTeacher(Teacher teacher, String subject) {
        List<String> subjects = teacherMap.getOrDefault(teacher, new ArrayList<>());

        if (subjects != null) {
            subjects.remove(subject);
        }
    }

    public List<String> getSubjectsForTeacher(Teacher teacher) {
        return teacherMap.getOrDefault(teacher, new ArrayList<>());
    }

    public Teacher getTeacher(String name, String surname) {
        for (Map.Entry<Teacher, List<String>> entry : teacherMap.entrySet()) {
            Teacher teacher = entry.getKey();

            if (teacher.getName().equalsIgnoreCase(name) && teacher.getSurname().equalsIgnoreCase(surname)) {
                return teacher;
            }
        }

        return null;
    }


}