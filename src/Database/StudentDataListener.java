package Database;

import model.Student;

/**
 * The StudentDataListener interface defines the contract for listening to events related to student data changes.
 */
public interface StudentDataListener {

    /**
     * Called when the student data has changed.
     *
     * @param student The student object representing the changed data.
     *                Implementing classes should provide the necessary logic to handle this event.
     */
    void onStudentDataChanged(Student student);
}
