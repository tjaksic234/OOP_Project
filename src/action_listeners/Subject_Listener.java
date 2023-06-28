package action_listeners;

import aux_classes.Student;
import aux_classes.SubjectManager;
import frame_panel.StudentPanel;
import frame_panel.SubjectPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Subject_Listener implements ActionListener {

    private StudentPanel studentPanel;
    private SubjectPanel subjectPanel;

    public Subject_Listener(SubjectPanel subjectPanel, StudentPanel studentPanel) {
        this.subjectPanel = subjectPanel;
        this.studentPanel = studentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == subjectPanel.getPrevious_button()){
            // Create a new instance of the StudentPanel and pass the existing data
            StudentPanel previousStudentPanel = new StudentPanel(studentPanel.getStudentManager());
            studentPanel.getStudentManager().removeStudent();

            // Set the text fields with the existing data
            previousStudentPanel.getIme().setText(studentPanel.getIme().getText());
            previousStudentPanel.getPrezime().setText(studentPanel.getPrezime().getText());
            previousStudentPanel.getFakultet().setText(studentPanel.getFakultet().getText());


            // Dispose the current SubjectPanel
            subjectPanel.dispose();
        } else if (e.getSource() == subjectPanel.getNew_student()) {

            // Create a new studentPanel object
            StudentPanel newStudentPanel = new StudentPanel(studentPanel.getStudentManager());

            // Dispose the current SubjectPanel
            subjectPanel.dispose();

            System.out.println("New student button activated.");

        } else if (e.getSource() == subjectPanel.getAdd_subject()) {
            // Check if all fields are filled out
            if (subjectPanel.getSubjects().getSelectedItem() == null || subjectPanel.getGrades().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(subjectPanel, "Please select a subject and a grade.");
            } else {
                // Get the selected subject and grade
                String subject = (String) subjectPanel.getSubjects().getSelectedItem();
                int grade = (int) subjectPanel.getGrades().getSelectedItem();

                // Get the current student
                Student currentStudent = studentPanel.getStudentManager().lastStudent();

                // Add the subject and grade to the student's subject manager
                currentStudent.getSubjectManager().addSubject(subject, grade);

                // Print the subject manager (for testing)
                System.out.println(currentStudent.getSubjectManager().toString());
            }

        } else if (e.getSource() == subjectPanel.getShow_students()) {

            System.out.println("Show students button activated.");

            // Print all the students and their associated subjects (for testing)
            for (Student student : studentPanel.getStudentManager().getStudentList()) {
                System.out.println(student.toString());
                System.out.println(student.getSubjectManager().toString());
            }

        }

    }

}
