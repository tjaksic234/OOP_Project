//package action_listeners;
//
//import model.Student;
//import view.MainFrame;
//import view.SubjectFrame;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Subject_Listener implements ActionListener {
//
//    private MainFrame mainFrame;
//    private SubjectFrame subjectFrame;
//
//    public Subject_Listener(SubjectFrame subjectFrame, MainFrame mainFrame) {
//        this.subjectFrame = subjectFrame;
//        this.mainFrame = mainFrame;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == subjectFrame.getPrevious_button()){
//            // Create a new instance of the StudentPanel and pass the existing data
//            MainFrame previousMainFrame = new MainFrame(mainFrame.getStudentManager());
//            mainFrame.getStudentManager().removeLastStudent();
//
//            // Set the text fields with the existing data
//            previousMainFrame.getIme().setText(mainFrame.getIme().getText());
//            previousMainFrame.getSurname().setText(mainFrame.getSurname().getText());
//            previousMainFrame.getCollege().setText(mainFrame.getCollege().getText());
//
//
//            // Dispose the current SubjectPanel
//            subjectFrame.dispose();
//        } else if (e.getSource() == subjectFrame.getNew_student()) {
//
//            // Create a new studentPanel object
//            MainFrame newMainFrame = new MainFrame(mainFrame.getStudentManager());
//
//            // Dispose the current SubjectPanel
//            subjectFrame.dispose();
//
//            System.out.println("New student button activated.");
//
//        } else if (e.getSource() == subjectFrame.getAdd_subject()) {
//            // Check if all fields are filled out
//            if (subjectFrame.getSubjects().getSelectedItem() == null || subjectFrame.getGrades().getSelectedItem() == null) {
//                JOptionPane.showMessageDialog(subjectFrame, "Please select a subject and a grade.");
//            } else {
//                // Get the selected subject and grade
//                String subject = (String) subjectFrame.getSubjects().getSelectedItem();
//                int grade = (int) subjectFrame.getGrades().getSelectedItem();
//
//                // Get the current student
//                Student currentStudent = mainFrame.getStudentManager().lastStudent();
//
//                // Add the subject and grade to the student's subject manager
//                currentStudent.getSubjectManager().addSubject(subject, grade);
//
//                // Print the subject manager (for testing)
//                System.out.println(currentStudent.getSubjectManager().toString());
//            }
//
//        } else if (e.getSource() == subjectFrame.getShow_students()) {
//
//            System.out.println("Show students button activated.");
//
//            // Print all the students and their associated subjects (for testing)
//            for (Student student : mainFrame.getStudentManager().getStudentList()) {
//                System.out.println(student.toString());
//                System.out.println(student.getSubjectManager().toString());
//            }
//
//        }
//
//    }
//
//}
