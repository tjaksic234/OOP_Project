package placeholder;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {

    private JTextField teacherNameField, teacherSurnameField, teacherPasswordField, teacherSubjectField;
    private JTextField studentNameField, studentSurnameField;
    private JTextArea dataDisplayArea;
    private JButton saveTeacherButton, saveStudentButton;
    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenuItem adminOption;

    private Controller controller;

    public AdminFrame() {
        super("Admin Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        init();
        layoutSet();
        activateComps();
    }

    public void init() {

        menuBar = new JMenuBar();
        adminMenu = new JMenu("Options");
        adminOption = new JMenuItem("Logout");

        teacherNameField = new JTextField(20);
        teacherSurnameField = new JTextField(20);
        teacherPasswordField = new JTextField(20);
        teacherSubjectField = new JTextField(20);

        studentNameField = new JTextField(20);
        studentSurnameField = new JTextField(20);

        saveTeacherButton = new JButton("Save Teacher");
        saveStudentButton = new JButton("Save Student");

        dataDisplayArea = new JTextArea(10, 40);
        dataDisplayArea.setEditable(false);

        adminMenu.add(adminOption);
        menuBar.add(adminMenu);
    }

    public void layoutSet() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Student Input Panel
        JPanel studentPanel = new JPanel(new GridLayout(2, 10, 30, 10));
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        studentPanel.add(new JLabel("Name:"));
        studentPanel.add(studentNameField);
        studentPanel.add(new JLabel("Surname:"));
        studentPanel.add(studentSurnameField);

        contentPanel.add(studentPanel, gbc);

        // Teacher Input Panel
        JPanel teacherPanel = new JPanel(new GridLayout(4, 10, 30, 10));
        teacherPanel.setBorder(BorderFactory.createTitledBorder("Teacher Information"));

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(teacherPanel, gbc);

        teacherPanel.add(new JLabel("Name:"));
        teacherPanel.add(teacherNameField);
        teacherPanel.add(new JLabel("Surname:"));
        teacherPanel.add(teacherSurnameField);
        teacherPanel.add(new JLabel("Password:"));
        teacherPanel.add(teacherPasswordField);
        teacherPanel.add(new JLabel("Profession:"));
        teacherPanel.add(teacherSubjectField);



        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(buttonPanel, gbc);

        buttonPanel.add(saveTeacherButton);
        buttonPanel.add(saveStudentButton);

        // Set the weightx for the student and teacher panels
        gbc.weightx = 0.5;

        // Data Display Area (on the right)
        JScrollPane scrollPane = new JScrollPane(dataDisplayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data Display"));
        gbc.gridx = 1; // Move to column 1
        gbc.gridy = 0; // Reset to row 0
        gbc.gridheight = 3; // Span 3 rows
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(scrollPane, gbc);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }



    public void activateComps() {


        saveTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = teacherNameField.getText();
                String surname = teacherSurnameField.getText();
                String password = teacherPasswordField.getText();
                String subject = teacherSubjectField.getText();

                if (!name.isEmpty() && !surname.isEmpty() && !password.isEmpty() && !subject.isEmpty()) {

                    Teacher teacher = new Teacher(name, surname, password, subject);
                    controller.addTeacher(teacher);

                    dataDisplayArea.append("##### TEACHER ADDED #####\n");
                    dataDisplayArea.append("Teacher: " + teacher.getName() + " " + teacher.getSurname()  +
                            "\n" + "E-mail: " + teacher.getUsername() + "\n"
                            + "Password: " + teacher.getPassword() + "\n" +
                            "Profession: " + teacher.getSubject() + "\n");

                    System.out.println("Teacher: " + teacher.getName() + " " + teacher.getSurname()  +
                            "\n" + "E-mail: " + teacher.getUsername() + "\n"
                            + "Password: " + teacher.getPassword() + "\n" +
                            "Profession: " + teacher.getSubject() + "\n");

                    controller.addExam(subject);
                    clearTeacherFields();
                } else {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Please fill in all fields.");
                }
            }
        });

        saveStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentNameField.getText();
                String surname = studentSurnameField.getText();

                if (!name.isEmpty() && !surname.isEmpty()) {
                    Student student = new Student(name, surname);
                    controller.addStudent(student);
                    dataDisplayArea.append("##### STUDENT ADDED #####\n");
                    dataDisplayArea.append("Student: " + student.getName() + " " + student.getSurname() + "\n");
                    System.out.println("Student: " + student.getName() + " " + student.getSurname() + "\n");
                    clearStudentFields();
                } else {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Please fill in all fields.");
                }
            }
        });

        adminOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFrame clientFrame = new ClientFrame();
                clientFrame.setController(controller);
                dispose();
            }
        });
    }

    private void clearTeacherFields() {
        teacherNameField.setText("");
        teacherSurnameField.setText("");
        teacherPasswordField.setText("");
        teacherSubjectField.setText("");
    }

    private void clearStudentFields() {
        studentNameField.setText("");
        studentSurnameField.setText("");
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}


