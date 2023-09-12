package view;

import controller.Controller;
import model.Student;
import model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminFrame extends JFrame {

    private JTextField teacherNameField, teacherSurnameField, teacherPasswordField, teacherSubjectField;
    private JTextField studentNameField, studentSurnameField;
    private JTextArea dataDisplayArea;
    private JButton saveTeacherButton, saveStudentButton;
    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenuItem addSubjectsOption;
    private JMenuItem logoutOption;
    private JMenuItem addProfessionOption;
    private Student student;

    private Controller controller;

    public AdminFrame() {
        super("Admin Panel");
        setSize(600, 300);
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
        addSubjectsOption = new JMenuItem("Register exams");
        addProfessionOption = new JMenuItem("Profession manager");
        logoutOption = new JMenuItem("Logout");

        teacherNameField = new JTextField(20);
        teacherSurnameField = new JTextField(20);
        teacherPasswordField = new JTextField(20);
        teacherSubjectField = new JTextField(20);

        studentNameField = new JTextField(20);
        studentSurnameField = new JTextField(20);

        saveTeacherButton = new JButton("Add New Teacher");
        saveStudentButton = new JButton("Add New Student");

        dataDisplayArea = new JTextArea(10, 40);
        dataDisplayArea.setEditable(false);

        adminMenu.add(addSubjectsOption);
        adminMenu.add(addProfessionOption);
        adminMenu.add(logoutOption);
        menuBar.add(adminMenu);
    }

    public void layoutSet() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel studentPanel = new JPanel(new GridLayout(2, 10, 30, 10));
        studentPanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
        studentPanel.add(welcomeLabel);

        contentPanel.add(studentPanel, gbc);

        JLabel infoLabel = new JLabel("If problems occur call +385953450121");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel teacherPanel = new JPanel(new GridLayout(2, 10, 30, 10));
        teacherPanel.setBorder(BorderFactory.createTitledBorder("Additional Information"));
        teacherPanel.add(infoLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(teacherPanel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(buttonPanel, gbc);

        buttonPanel.add(saveTeacherButton);
        buttonPanel.add(saveStudentButton);


        gbc.weightx = 0.5;

        JScrollPane scrollPane = new JScrollPane(dataDisplayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data Display"));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(scrollPane, gbc);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }


    public void activateComps() {

        //====================================== TEACHER MANAGER ======================================

        saveTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField nameField = new JTextField(20);
                JTextField surnameField = new JTextField(20);
                JTextField passwordField = new JTextField(20);
                JTextField subjectField = new JTextField(20);


                panel.setLayout(new GridLayout(5, 2));
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Surname:"));
                panel.add(surnameField);
                panel.add(new JLabel("Password:"));
                panel.add(passwordField);
                panel.add(new JLabel("Profession:"));
                panel.add(subjectField);
                panel.add(new JLabel());

                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Teacher Information",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String password = passwordField.getText();
                    String subject = subjectField.getText();

                    if (name.isEmpty() && surname.isEmpty() && password.isEmpty() && subject.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!isAlpha(name) || !isAlpha(surname) || !isAlpha(subject)) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid name, surname, and subject.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (isDuplicateTeacher(name, surname, subject)) {
                        JOptionPane.showMessageDialog(null, "This teacher already exists.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Teacher teacher = new Teacher(name, surname, password, subject.toUpperCase());
                        controller.addTeacher(teacher);

                        controller.addSubjectToTeacher(teacher, subject.toUpperCase());

                        dataDisplayArea.append("##### TEACHER ADDED #####\n");
                        dataDisplayArea.append("Teacher: " + teacher.getName() + " " + teacher.getSurname() +
                                "\n" + "E-mail: " + teacher.getUsername() + "\n"
                                + "Password: " + teacher.getPassword() + "\n" +
                                "Profession: " + teacher.getSubject() + "\n");

                        System.out.println("Teacher: " + teacher.getName() + " " + teacher.getSurname() +
                                "\n" + "E-mail: " + teacher.getUsername() + "\n"
                                + "Password: " + teacher.getPassword() + "\n" +
                                "Profession: " + teacher.getSubject() + "\n");

                        clearTeacherFields();
                    }
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Cancelled");
                }
            }
        });

        //====================================== STUDENT MANAGER ======================================

        saveStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField nameField = new JTextField(20);
                JTextField surnameField = new JTextField(20);
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Surname:"));
                panel.add(surnameField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Student Information",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    String surname = surnameField.getText();

                    if (name.isEmpty() && surname.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!isAlpha(name) || !isAlpha(surname)) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid name and surname.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (isDuplicateStudent(name, surname)) {
                        JOptionPane.showMessageDialog(null, "This student already exists.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Student student = new Student(name.toUpperCase(), surname.toUpperCase());
                        controller.addStudent(student);
                        dataDisplayArea.append("##### STUDENT ADDED #####\n");
                        dataDisplayArea.append("Student: " + student.getName() + " " + student.getSurname() + "\n");
                        System.out.println("Student: " + student.getName() + " " + student.getSurname() + "\n");
                        clearStudentFields();
                    }
                }
            }
        });

        //====================================== SUBJECT MANAGER ======================================

        addSubjectsOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JComboBox<String> studentComboBox = new JComboBox<>();
                JComboBox<String> examComboBox = new JComboBox<>();
                JTextArea displayArea = new JTextArea(10, 40);
                displayArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(displayArea);


                JButton addButton = new JButton("+");
                JButton removeButton = new JButton("-");

                List<Student> students = controller.getStudentList();
                if (students.isEmpty()) {
                    addButton.setEnabled(false);
                    removeButton.setEnabled(false);
                    studentComboBox.addItem("No students available");
                } else {
                    addButton.setEnabled(true);
                    removeButton.setEnabled(true);
                    studentComboBox.removeAllItems();
                    studentComboBox.addItem("Select a student");
                    for (Student student : students) {
                        studentComboBox.addItem(student.getName().toUpperCase() + " " + student.getSurname().toUpperCase());
                    }
                }

                // Fill exam combo box
                List<String> exams = new ArrayList<>();

                for (Map.Entry<Teacher, List<String>> entry : controller.getTeacherMap().entrySet()) {
                    List<String> subjects = entry.getValue();
                    for (String subject : subjects) {
                        if (!exams.contains(subject)) {
                            exams.add(subject);
                        }
                    }
                }

                examComboBox.removeAllItems();

                if (exams.isEmpty()) {
                    examComboBox.addItem("No exams available");
                    addButton.setEnabled(false);
                    removeButton.setEnabled(false);
                } else {
                    addButton.setEnabled(true);
                    removeButton.setEnabled(true);
                    examComboBox.addItem("Select an exam");

                    for (String exam : exams) {
                        examComboBox.addItem(exam.toUpperCase());
                    }
                }




                panel.setLayout(new BorderLayout());
                JPanel comboPanel = new JPanel(new FlowLayout());
                comboPanel.add(new JLabel("Select Student: "));
                comboPanel.add(studentComboBox);
                comboPanel.add(new JLabel("Select Exam: "));
                comboPanel.add(examComboBox);
                comboPanel.add(addButton);
                comboPanel.add(removeButton);
                panel.add(comboPanel, BorderLayout.NORTH);
                panel.add(scrollPane, BorderLayout.CENTER);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (studentComboBox.getSelectedIndex() == 0 && examComboBox.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(null, "Please select a student and an exam");
                        } else {
                            String selectedStudentName = (String) studentComboBox.getSelectedItem();

                            Student selectedStudent = null;
                            for (Student student : students) {
                                if ((student.getName() + " " + student.getSurname()).equalsIgnoreCase(selectedStudentName)) {
                                    selectedStudent = student;
                                    break;
                                }
                            }
                            if (selectedStudent != null) {
                                if (controller.gradeExists(selectedStudent, (String) examComboBox.getSelectedItem(), 0)) {
                                    JOptionPane.showMessageDialog(null, "Exam is already registered");
                                } else {
                                    controller.addGrade(selectedStudent, (String) examComboBox.getSelectedItem(), 0);

                                    System.out.println("Added exam: " + examComboBox.getSelectedItem() + " for student "
                                            + selectedStudent.getName() + " " + selectedStudent.getSurname());
                                    displayArea.append("Added exam: " + examComboBox.getSelectedItem() + " for student "
                                            + selectedStudent.getName() + " " + selectedStudent.getSurname() + "\n");

                                    JOptionPane.showMessageDialog(null, "Exam registered successfully");

                                }
                            }
                        }
                    }
                });

                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (studentComboBox.getSelectedIndex() == 0 && examComboBox.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(null, "Please select a student and an exam");
                        } else {
                            String selectedStudentName = (String) studentComboBox.getSelectedItem();

                            Student selectedStudent = null;
                            for (Student student : students) {
                                if ((student.getName() + " " + student.getSurname()).equalsIgnoreCase(selectedStudentName)) {
                                    selectedStudent = student;
                                    break;
                                }
                            }
                            if (selectedStudent != null) {
                                if (!controller.gradeExists(selectedStudent, (String) examComboBox.getSelectedItem(), 0)) {
                                    JOptionPane.showMessageDialog(null, "Exam is already deleted");
                                } else {
                                    controller.removeGrade(selectedStudent, (String) examComboBox.getSelectedItem());

                                    System.out.println("Deleted exam: " + examComboBox.getSelectedItem() + " for student "
                                            + selectedStudent.getName() + " " + selectedStudent.getSurname());
                                    displayArea.append("Deleted exam: " + examComboBox.getSelectedItem() + " for student "
                                            + selectedStudent.getName() + " " + selectedStudent.getSurname() + "\n");

                                    JOptionPane.showMessageDialog(null, "Exam deleted successfully");
                                }
                            }
                        }
                    }
                });

                int result = JOptionPane.showConfirmDialog(null, panel, "Manage Subjects",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("OK");
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Cancelled");
                }
            }
        });

        logoutOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFrame clientFrame = new ClientFrame();
                clientFrame.setController(controller);
                dispose();
            }
        });

        //====================================== PROFESSION MANAGER ======================================

        addProfessionOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JComboBox<String> teacherComboBox = new JComboBox<>();
                JLabel professionLabel = new JLabel("Profession:");
                JTextField professionField = new JTextField(20);
                JButton addButton = new JButton("+");
                JButton removeButton = new JButton("-");

                Map<Teacher, List<String>> teachers = controller.getTeacherMap();
                if (teachers.isEmpty()) {
                    teacherComboBox.addItem("No teachers available");
                    addButton.setEnabled(false);
                    removeButton.setEnabled(false);
                } else {
                    teacherComboBox.addItem("Select a teacher");
                    addButton.setEnabled(true);
                    removeButton.setEnabled(true);
                    for (Teacher teacher : teachers.keySet()) {
                        teacherComboBox.addItem(teacher.getName() + " " + teacher.getSurname());
                    }
                }

                JPanel panel = new JPanel(new GridLayout(3, 2));
                panel.add(new JLabel("Select Teacher:"));
                panel.add(teacherComboBox);
                panel.add(professionLabel);
                panel.add(professionField);
                panel.add(addButton);
                panel.add(removeButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedTeacherName = (String) teacherComboBox.getSelectedItem();
                        String profession = professionField.getText();

                        if (selectedTeacherName.isEmpty() || profession.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please select a teacher and enter a profession.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Teacher selectedTeacher = null;
                            for (Teacher teacher : teachers.keySet()) {
                                if ((teacher.getName() + " " + teacher.getSurname()).equals(selectedTeacherName)) {
                                    selectedTeacher = teacher;
                                    break;
                                }
                            }

                            if (selectedTeacher != null) {
                                if (!teachers.get(selectedTeacher).contains(profession)) {
                                    controller.addSubjectToTeacher(selectedTeacher, profession.toUpperCase());
                                    JOptionPane.showMessageDialog(null, "Profession added successfully.",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                    System.out.println("Added profession: " + profession + " to teacher: " +
                                            selectedTeacher.getName() + " " + selectedTeacher.getSurname());
                                    System.out.println(controller.getSubjectsForTeacher(selectedTeacher));
                                } else {
                                    JOptionPane.showMessageDialog(null, "Profession already exists.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Selected teacher not found.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedTeacherName = (String) teacherComboBox.getSelectedItem();
                        String profession = professionField.getText().toUpperCase();

                        if (selectedTeacherName.isEmpty() || profession.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please select a teacher and enter a profession.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Teacher selectedTeacher = null;
                            for (Teacher teacher : teachers.keySet()) {
                                if ((teacher.getName() + " " + teacher.getSurname()).equals(selectedTeacherName)) {
                                    selectedTeacher = teacher;
                                    break;
                                }
                            }

                            if (selectedTeacher != null) {
                                if (teachers.get(selectedTeacher).contains(profession)) {
                                    controller.removeSubjectFromTeacher(selectedTeacher, profession.toUpperCase());
                                    JOptionPane.showMessageDialog(null, "Profession removed successfully.",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                    System.out.println("Removed profession: " + profession + " from teacher: " +
                                            selectedTeacher.getName() + " " + selectedTeacher.getSurname());
                                    System.out.println(controller.getSubjectsForTeacher(selectedTeacher));
                                } else {
                                    JOptionPane.showMessageDialog(null, "Profession has already been removed.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Selected teacher not found.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                int result = JOptionPane.showConfirmDialog(null, panel, "Add/Remove Profession",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("OK");
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Cancelled");
                }
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

    private boolean isAlpha(String str) {
        return str != null && str.matches("^[a-zA-Z\\s]+$");
    }

    private boolean isDuplicateTeacher(String name, String surname, String subject) {

        Teacher newTeacher = new Teacher(name, surname, "", subject.toUpperCase());

        for (Map.Entry<Teacher, List<String>> entry : controller.getTeacherMap().entrySet()) {
            Teacher existingTeacher = entry.getKey();
            if (existingTeacher.getName().equals(newTeacher.getName()) ||
                    existingTeacher.getSurname().equalsIgnoreCase(newTeacher.getSurname())) {
                return true;
            }
        }

        return false;
    }


    private boolean isDuplicateStudent(String name, String surname) {
        List<Student> addedStudents = controller.getStudentList();
        for (Student student : addedStudents) {
            if (student.getName().equalsIgnoreCase(name) && student.getSurname().equalsIgnoreCase(surname)) {
                return true;
            }
        }
        return false;
    }




}