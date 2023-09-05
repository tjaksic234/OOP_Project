package placeholder;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExamSelectionFrame extends JFrame {

    private JComboBox<String> availableExamsComboBox;
    private JButton registerTestButton, showRegisteredExamsButton, removeExamButton;
    private JLabel studentNameLabel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logoutOption;
    private Controller controller;
    private Student student;

    public ExamSelectionFrame() {
        super("Exam Selection");
        setSize(400, 400); // Smaller frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        init();
        layoutSet();
        activateComps();
    }

    public void init() {
        availableExamsComboBox = new JComboBox<>();
        registerTestButton = new JButton("Register Test");
        showRegisteredExamsButton = new JButton("Show results");
        removeExamButton = new JButton("Remove Exam");
        studentNameLabel = new JLabel();

        studentNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        studentNameLabel.setForeground(Color.BLACK);

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        logoutOption = new JMenuItem("Logout");

        menu.add(logoutOption);
        menuBar.add(menu);
    }

    public void layoutSet() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 20, 10, 20);
        constraints.anchor = GridBagConstraints.PAGE_START;
        panel.add(studentNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 20, 10, 20);
        panel.add(availableExamsComboBox, constraints);

        JPanel buttonPanel1 = new JPanel(new GridLayout(1, 2));

        registerTestButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel1.add(registerTestButton);

        showRegisteredExamsButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel1.add(showRegisteredExamsButton);

        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        removeExamButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel2.add(removeExamButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 20, 10, 20);
        panel.add(buttonPanel1, constraints);

        constraints.gridy = 3;
        panel.add(buttonPanel2, constraints);



        setJMenuBar(menuBar);

        add(panel);
    }




    public void activateComps() {
        registerTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (availableExamsComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select an exam");
                } else if (controller.duplicateExamCheck(student, (String) availableExamsComboBox.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Exam is already registered");
                } else {
                    controller.addResult(student, (String) availableExamsComboBox.getSelectedItem());
                    System.out.println("Added exam:" + availableExamsComboBox.getSelectedItem() + " for student "
                            + student.getName() + " " + student.getSurname());
                    JOptionPane.showMessageDialog(null, "Exam registered successfully");
                }
            }
        });

        showRegisteredExamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code to show registered exams here
                // You can fetch and display the exams from the database
                // Display the exams in a dialog or another frame
            }
        });

        removeExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (availableExamsComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select an exam to remove");
                } else if (controller.getRegisteredExams().get(student) == null) {
                    JOptionPane.showMessageDialog(null, "Exam is not registered for the student");
                } else {
                    controller.removeExamFromResult(student, (String) availableExamsComboBox.getSelectedItem());
                    System.out.println("Removed exam: " + availableExamsComboBox.getSelectedItem() + " for student "
                            + student.getName() + " " + student.getSurname());
                    JOptionPane.showMessageDialog(null, "Exam removed successfully");
                }

            }
        });

        logoutOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLoginFrame studentLoginFrame = new StudentLoginFrame();
                studentLoginFrame.setController(controller);
                JOptionPane.showMessageDialog(null, "Logged out successfully");
                dispose();
            }
        });
    }

    public void setStudentNameLabel(String name, String surname) {
        studentNameLabel.setText("Student: " + name + " " + surname);
    }

    public void fillExamComboBox() {
        List<String> exams = controller.getExamInfo();

        if (exams.isEmpty()) {
            registerTestButton.setEnabled(false);
            showRegisteredExamsButton.setEnabled(false);
            removeExamButton.setEnabled(false);
            availableExamsComboBox.addItem("No exams available");
        } else {
            registerTestButton.setEnabled(true);
            showRegisteredExamsButton.setEnabled(true);
            removeExamButton.setEnabled(true);
        }

        availableExamsComboBox.removeAllItems();
        availableExamsComboBox.addItem("Select an exam");
        for (String exam : exams) {
            exam = exam.toUpperCase();
            availableExamsComboBox.addItem(exam);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
        fillExamComboBox();
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
