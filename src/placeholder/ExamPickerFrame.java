package placeholder;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExamPickerFrame extends JFrame {

    private JComboBox<String> availableExamsComboBox;
    private JButton registerExamButton, showRegisteredExamsButton, removeExamButton;
    private JLabel studentNameLabel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logoutOption;
    private Controller controller;
    private Student student;

    public ExamPickerFrame() {
        super("Exam Selection");
        setSize(400, 400);
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
        registerExamButton = new JButton("Register Test");
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

        registerExamButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel1.add(registerExamButton);

        removeExamButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel1.add(removeExamButton);

        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        showRegisteredExamsButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel2.add(showRegisteredExamsButton);

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
        registerExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (availableExamsComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select an exam");
                } else if (controller.gradeExists(student,(String) availableExamsComboBox.getSelectedItem(), 0)) {
                    JOptionPane.showMessageDialog(null, "Exam is already registered");
                } else {
                    controller.addGrade(student, (String) availableExamsComboBox.getSelectedItem(), 0);
                    System.out.println("Added exam: " + availableExamsComboBox.getSelectedItem() + " for student "
                            + student.getName() + " " + student.getSurname());
                    JOptionPane.showMessageDialog(null, "Exam registered successfully");
                }
            }
        });

        showRegisteredExamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Proceeding to the results page");
                StudentReportFrame studentReportFrame = new StudentReportFrame();
                studentReportFrame.setController(controller);
                studentReportFrame.setStudent(student);
                studentReportFrame.setAverageGrade();
                studentReportFrame.setTableData();
                dispose();
            }
        });

        removeExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (availableExamsComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select an exam to remove");
                } else if (!controller.gradeExists(student,(String) availableExamsComboBox.getSelectedItem(), 0)) {
                    JOptionPane.showMessageDialog(null, "Exam is not registered for the student");
                } else {
                    controller.removeGrade(student, (String) availableExamsComboBox.getSelectedItem());
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
                System.out.println("Registered" + controller.getGradeData());
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
            registerExamButton.setEnabled(false);
            showRegisteredExamsButton.setEnabled(false);
            removeExamButton.setEnabled(false);
            availableExamsComboBox.addItem("No exams available");
        } else {
            registerExamButton.setEnabled(true);
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
