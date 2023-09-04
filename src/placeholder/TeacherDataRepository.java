package placeholder;

import java.util.ArrayList;
import java.util.List;

public class TeacherDataRepository {

    private List<Teacher> teacherList;

    public TeacherDataRepository(){
        teacherList = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher){
        teacherList.add(teacher);
    }

    public List<Teacher> getTeacherList(){
        return teacherList;
    }


}
