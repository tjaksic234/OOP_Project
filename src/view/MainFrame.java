//package view;
//
//import Database.*;
//import controller.Controller;
//import model.Student;
//import placeholder.TeacherDataRepository;
//import placeholder.TeacherLoginFrame;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// *
// * The MainFrame class functions as the view for presenting student details.
// */
//
//public class MainFrame extends JFrame implements MainFrameListener, StudentDataListener {
//
//    private JTextField name, surname;
//    private JButton next_button, logout_button;
//
//    private Controller controller;
//
//
//    /**
//     * Constructs the main frame for student details.
//     */
//    public MainFrame() {
//        super("Student details:");
//        setSize(500, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        setLocationRelativeTo(null);
//        setResizable(false);
//
//        init();
//        layoutSet();
//        activateComps();
//    }
//
//
//    /**
//     * Initializes the components of the main frame.
//     */
//    public void init() {
//        name = new JTextField(15);
//        surname = new JTextField(15);
//        logout_button = new JButton("Logout");
//        next_button = new JButton("Continue");
//        controller = new Controller(new StudentDataRepository(), new TeacherDataRepository(),
//                new ExamDataRepository(), new TableDataRepository());
//    }
//
//
//    /**
//     * Sets the layout of the main frame using GridBagLayout.
//     */
//    public void layoutSet() {
//        // Create the main panel with GridBagLayout
//        JPanel mainPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2; // Span across two columns
//        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
//
//        // Add Header label
//        JLabel headerLabel = new JLabel("Enter the student details:");
//        Font headerFont = new Font("Arial", Font.BOLD, 18);
//        headerLabel.setFont(headerFont);
//        mainPanel.add(headerLabel, gbc);
//
//        // Move to the next row and reset the column
//        gbc.gridy++;
//
//        // Reset gridwidth
//        gbc.gridwidth = 1;
//
//        // Add Name label and text field
//        JLabel nameLabel = new JLabel("Name:");
//        mainPanel.add(nameLabel, gbc);
//
//        gbc.gridx++; // Move to the next column
//        mainPanel.add(name, gbc);
//
//        // Move to the next row and reset the column
//        gbc.gridx = 0;
//        gbc.gridy++;
//
//        // Add Surname label and text field
//        JLabel surnameLabel = new JLabel("Surname:");
//        mainPanel.add(surnameLabel, gbc);
//
//        gbc.gridx++; // Move to the next column
//        mainPanel.add(surname, gbc);
//
//        // Move to the next row and reset the column
//        gbc.gridx = 0;
//        gbc.gridy++;
//
//        // Add the logout button with padding
//        gbc.insets = new Insets(5, 10, 10, 5); // Adjust padding
//        mainPanel.add(logout_button, gbc);
//
//        // Adjust position for the next button
//        gbc.gridx++; // Move to the next column
//        gbc.anchor = GridBagConstraints.LINE_END; // Align to the right
//        gbc.insets = new Insets(5, 5, 10, 10); // Adjust padding
//        mainPanel.add(next_button, gbc);
//
//        // Add the mainPanel to the JFrame's content pane
//        getContentPane().add(mainPanel);
//    }
//
//
//    /**
//     * Activates the components and their associated actions.
//     */
//    public void activateComps() {
//        // Adding student to database
//        next_button.addActionListener(e -> {
//            String studentName = name.getText();
//            String studentSurname = surname.getText();
//
//            // Check if fields are empty
//            if (studentName.isEmpty() || studentSurname.isEmpty()) {
//                JOptionPane.showMessageDialog(MainFrame.this, "Please fill in all the fields.");
//            } else if (!isAlpha(studentName) || !isAlpha(studentSurname)) {
//                JOptionPane.showMessageDialog(MainFrame.this, "Please enter only letters in the Name and Surname fields.");
//            } else {
//                // Adding student to database
//                controller.addStudent(new Student(studentName, studentSurname));
//
//                // Open the next frame
//                SubjectFrame subjectFrame = new SubjectFrame();
//                subjectFrame.setController(controller);
//                subjectFrame.setMainFrameListener(this);
//                subjectFrame.setStudentDataListener(this);
//                dispose();
//                resetForm();
//            }
//        });
//
//        // Logout button
//        logout_button.addActionListener(e -> {
//            new TeacherLoginFrame();
//            dispose();
//        });
//    }
//
//
//    /**
//     * Resets the form by clearing the text fields.
//     */
//    public void resetForm() {
//        name.setText("");     // Clear the text in the name field
//        surname.setText("");  // Clear the text in the surname field
//    }
//
//
//    @Override
//    /**
//     * Invoked when the subject frame is closed, making the main frame visible.
//     */
//    public void onSubjectFrameClosed() {
//        setVisible(true);
//    }
//
//    @Override
//    /**
//     * Invoked when the student data has changed, updating the text fields accordingly.
//     *
//     * @param student The updated student data.
//     */
//    public void onStudentDataChanged(Student student) {
//        if (student != null) {
//            name.setText(student.getName());
//            surname.setText(student.getSurname());
//        } else {
//            name.setText("");
//            surname.setText("");
//        }
//    }
//
//    // Helper method to check if a string contains only letters
//    private boolean isAlpha(String s) {
//        return s.chars().allMatch(Character::isLetter);
//    }
//}
