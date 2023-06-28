package action_listeners;

import aux_classes.Student;
import frame_panel.SubjectPanel;
import frame_panel.StudentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Listener implements ActionListener {

    private StudentPanel studentPanel;
    private SubjectPanel subjectPanel;

    public Student_Listener(StudentPanel studentPanel) {
        this.studentPanel = studentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentPanel.getNext_button()) {
            // Check if all text fields are filled out
            if (studentPanel.getIme().getText().isEmpty() || studentPanel.getPrezime().getText().isEmpty() ||
                    studentPanel.getFakultet().getText().isEmpty()) {
                JOptionPane.showMessageDialog(studentPanel, "Please fill out all fields.");
            } else {
                // All fields are filled out, store the values in a Student object
                String studentIme = studentPanel.getIme().getText();
                String studentPrezime = studentPanel.getPrezime().getText();
                String studentFakultet = studentPanel.getFakultet().getText();

                Student student = new Student(studentIme, studentPrezime, studentFakultet);

                //Saving the student object to the list
                studentPanel.getStudentManager().addStudent(student);

                // Print the student object (for testing)
                System.out.println(studentPanel.getStudentManager().getStudentList().toString());

                // Proceed to the next frame
                subjectPanel = new SubjectPanel(studentPanel,studentPanel.getStudentManager());

                // Dispose the current StudentPanel
                studentPanel.dispose();

            }
        }
    }



}
