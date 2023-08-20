package view;

import Database.*;
import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;

/**
 *
 * The MainFrame class functions as the view for presenting student details.
 */

public class MainFrame extends JFrame implements MainFrameListener, StudentDataListener {

    private JTextField name, surname, college;
    private JButton next_button;

    private Controller controller;


    /**
     * Constructs the main frame for student details.
     */
    public MainFrame() {
        super("Student details:");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init();
        layoutSet();
        activateComps();
    }


    /**
     * Initializes the components of the main frame.
     */
    public void init() {
        name = new JTextField(15);
        surname = new JTextField(15);
        college = new JTextField(15);
        next_button = new JButton("Next");
        controller = new Controller(new StudentDataRepository(), new SubjectDataRepository(), new TableDataRepository());
    }


    /**
     * Sets the layout of the main frame using GridBagLayout.
     */
    public void layoutSet() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Layout for the "Name" label and text field
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(15, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(name, gbc);

        // Layout for the "Surname" label and text field
        gbc.insets = new Insets(30, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Surname:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(surname, gbc);

        // Layout for the "College" label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("College:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(college, gbc);

        // Layout for the "Next" button
        gbc.insets = new Insets(70, 5, 15, 5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(next_button, gbc);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
    }


    /**
     * Activates the components and their associated actions.
     */
    public void activateComps() {
        // Adding student to database
        next_button.addActionListener(e -> {
            String studentName = name.getText();
            String studentSurname = surname.getText();
            String studentCollege = college.getText();

            // Check if fields are empty
            if (studentName.isEmpty() || studentSurname.isEmpty() || studentCollege.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.this, "Please fill in all the fields.");
            } else {
                // Adding student to database
                controller.addStudent(new Student(studentName, studentSurname, studentCollege));

                // Open the next frame
                SubjectFrame subjectFrame = new SubjectFrame();
                subjectFrame.setController(controller);
                subjectFrame.setMainFrameListener(this);
                subjectFrame.setStudentDataListener(this);
                dispose();
                resetForm();
            }
        });
    }


    /**
     * Resets the form by clearing the text fields.
     */
    public void resetForm() {
        name.setText("");     // Clear the text in the name field
        surname.setText("");  // Clear the text in the surname field
        college.setText("");  // Clear the text in the college field
    }


    @Override
    /**
     * Invoked when the subject frame is closed, making the main frame visible.
     */
    public void onSubjectFrameClosed() {
        setVisible(true);
    }

    @Override
    /**
     * Invoked when the student data has changed, updating the text fields accordingly.
     *
     * @param student The updated student data.
     */
    public void onStudentDataChanged(Student student) {
        if (student != null) {
            name.setText(student.getName());
            surname.setText(student.getSurname());
            college.setText(student.getCollege());
        } else {
            name.setText("");
            surname.setText("");
            college.setText("");
        }
    }
}
