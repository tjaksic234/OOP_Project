//package action_listeners;
//
//import model.Student;
//import view.SubjectFrame;
//import view.MainFrame;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class NextButtonListener implements ActionListener {
//
//    private MainFrame mainFrame;
//    private SubjectFrame subjectFrame;
//
//    public NextButtonListener(MainFrame mainFrame) {
//        this.mainFrame = mainFrame;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == mainFrame.getNext_button()) {
//            // Check if all text fields are filled out
//            if (mainFrame.getIme().getText().isEmpty() || mainFrame.getSurname().getText().isEmpty() ||
//                    mainFrame.getCollege().getText().isEmpty()) {
//                JOptionPane.showMessageDialog(mainFrame, "Please fill out all fields.");
//            } else {
//                // All fields are filled out, store the values in a Student object
//                String studentIme = mainFrame.getIme().getText();
//                String studentPrezime = mainFrame.getSurname().getText();
//                String studentFakultet = mainFrame.getCollege().getText();
//
//                Student student = new Student(studentIme, studentPrezime, studentFakultet);
//
//                //Saving the student object to the list
//                mainFrame.getStudentManager().addStudent(student);
//
//                // Print the student object (for testing)
//                System.out.println(mainFrame.getStudentManager().getStudentList().toString());
//
//                // Proceed to the next frame
//                subjectFrame = new SubjectFrame(mainFrame, mainFrame.getStudentManager());
//
//                // Dispose the current StudentPanel
//                mainFrame.dispose();
//
//            }
//        }
//    }
//
//
//
//}
