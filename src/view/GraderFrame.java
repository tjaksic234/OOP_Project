package view;

import controller.Controller;
import model.CompletedExamsDialog;
import model.ExamsInProgressDialog;
import model.Student;
import model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class GraderFrame extends JFrame {
    private JComboBox<String> studentsComboBox;
    private JComboBox<String> gradesComboBox;
    private JLabel professorLabel;
    private JLabel subjectLabel;
    private JTextArea textDisplay;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logoutOption, registerGrade, clearDisplay, changeSubject, examsInProgress, completedExams;
    private Controller controller;
    private JButton minusButton, editButton;

    private String teacherName;
    private String teacherSurname;
    private String teacherSubject;


    public GraderFrame() {
        super("Grader");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        init();
        layoutSet();
        activateComps();
    }

    public void init() {
        studentsComboBox = new JComboBox<>();
        gradesComboBox = new JComboBox<>();
        professorLabel = new JLabel();
        subjectLabel = new JLabel();
        textDisplay = new JTextArea();

        minusButton = new JButton("-");
        minusButton.setPreferredSize(new Dimension(50, 30));

        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(80, 30));

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        clearDisplay = new JMenuItem("Clear Display");
        registerGrade = new JMenuItem("Register Grade");
        changeSubject = new JMenuItem("Change Subject");
        examsInProgress = new JMenuItem("Exams in Progress");
        completedExams = new JMenuItem("Graded Exams");
        logoutOption = new JMenuItem("Logout");

        menu.add(clearDisplay);
        menu.add(registerGrade);
        menu.add(changeSubject);
        menu.add(examsInProgress);
        menu.add(completedExams);
        menu.add(logoutOption);
        menuBar.add(menu);
    }

    public void layoutSet() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        professorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(professorLabel);
        headerPanel.add(subjectLabel);
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        studentsComboBox.setPreferredSize(new Dimension(200, 30));
        gradesComboBox.setPreferredSize(new Dimension(100, 30));
        centerPanel.add(new JLabel("Student:"));
        centerPanel.add(studentsComboBox);
        centerPanel.add(new JLabel("Grade:"));
        centerPanel.add(gradesComboBox);
        centerPanel.add(minusButton);
        centerPanel.add(editButton);
        panel.add(centerPanel, BorderLayout.CENTER);

        textDisplay.setEditable(false);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        textDisplay.setBorder(BorderFactory.createEtchedBorder());
        JScrollPane scrollPane = new JScrollPane(textDisplay);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        panel.add(scrollPane, BorderLayout.SOUTH);

        setJMenuBar(menuBar);
        add(panel);
    }


    public void activateComps() {

        editButton.addActionListener(e -> {
            String studentName = (String) studentsComboBox.getSelectedItem();
            String subject = teacherSubject;
            Integer currentGrade = controller.getGrade(findStudent(studentName), subject);

            if (currentGrade == null) {
                JOptionPane.showMessageDialog(null, "Grade is not registered for student "
                        + studentName + "!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (studentName.equals("Select student") || currentGrade == null) {
                textDisplay.append("Please select a student or register a grade first!\n");
            } else {
                String newGrade = JOptionPane.showInputDialog(this, "Edit grade for " +
                        studentName + ":", currentGrade);

                if (newGrade != null) {
                    try {
                        int gradeInt = Integer.parseInt(newGrade);
                        Student student = findStudent(studentName);

                        if (student == null) {
                            JOptionPane.showMessageDialog(null, "Student not found!");
                        } else if (gradeInt < 1 || gradeInt > 5) {
                            JOptionPane.showMessageDialog(null, "Invalid grade. " +
                                    "Please enter a grade between 1 and 5.");
                        } else {
                            controller.editGrade(student, subject, gradeInt);
                            textDisplay.append("Edited grade for subject " + subject.toUpperCase()  + " into " + gradeInt +
                                    " for student " + studentName + "!\n");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid grade. Please enter a valid numeric grade.");
                    }
                }
            }
        });

        minusButton.addActionListener(e -> {
            String studentName = (String) studentsComboBox.getSelectedItem();
            String subject = teacherSubject;

            if (studentName.equals("Select student")) {
                textDisplay.append("Please select a student!\n");
            } else {
                Student student = findStudent(studentName);

                if (student == null) {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                } else {
                    int gradeFromDatabase = controller.getGradeForSubject(student, subject);

                    if (gradeFromDatabase == -1) {
                        JOptionPane.showMessageDialog(null, "Grade for subject " + subject.toUpperCase() + " not found for student " + studentName + "!");
                    } else {
                        controller.removeGrade(student, subject);
                        textDisplay.append("Removed grade " + gradeFromDatabase + " for subject " + subject.toUpperCase() + " from student " + studentName + "!\n");
                    }
                }
            }
        });

        logoutOption.addActionListener(e -> {
            TeacherLoginFrame teacherLoginFrame = new TeacherLoginFrame();
            teacherLoginFrame.setController(controller);
            JOptionPane.showMessageDialog(null, "Logged out successfully!");
            dispose();
        });

        registerGrade.addActionListener(e -> {
            String studentName = (String) studentsComboBox.getSelectedItem();
            String subject = teacherSubject;
            String grade = (String) gradesComboBox.getSelectedItem();

            if (studentName.equals("Select student") || grade.equals("Select grade")) {
                textDisplay.append("Please select a student and a grade!\n");
            } else if (grade.equals("Select grade")) {
                textDisplay.append("Please select a valid grade!\n");
            } else {

                int gradeInt = Integer.parseInt(grade);
                Student student = findStudent(studentName);

                if (student == null) {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                } else if (controller.gradeExists(student, subject, gradeInt) && gradeInt != 0) {
                    JOptionPane.showMessageDialog(null, "Grade " + grade + " is already registered for student "
                            + studentName + "!\n");
                } else {
                    controller.addGrade(student, subject, gradeInt);
                    textDisplay.append("Registered grade " + grade + " for subject " + subject.toUpperCase() +
                            " for student " + studentName + "!\n");
                }
            }
        });

        clearDisplay.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Display cleared!");
            textDisplay.setText("");
        });

        changeSubject.addActionListener(e -> {

            Teacher teacher = controller.getTeacher(teacherName, teacherSurname);

            if (teacher != null) {
                List<String> subjects = controller.getSubjectsForTeacher(teacher);

                if (subjects.size() > 1) {
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel("Select a subject:");
                    JComboBox<String> subjectComboBox = new JComboBox<>(subjects.toArray(new String[0]));
                    panel.add(label);
                    panel.add(subjectComboBox);

                    int result = JOptionPane.showConfirmDialog(this, panel, "Select Subject", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {

                        String selectedSubject = (String) subjectComboBox.getSelectedItem();

                        if (selectedSubject != null) {
                            this.teacherSubject = selectedSubject;
                            subjectLabel.setText(selectedSubject.toUpperCase());
                            fillStudentsComboBox();
                        }
                    }
                } else {
                    changeSubject.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Teacher not found!");
            }
        });

        examsInProgress.addActionListener(e -> {

            ExamsInProgressDialog examsDialog = new ExamsInProgressDialog(this);
            examsDialog.setController(controller);
            examsDialog.setTeacher(teacherName, teacherSurname);
            examsDialog.fillTable();
            examsDialog.setVisible(true);
        });

        completedExams.addActionListener(e -> {

            CompletedExamsDialog completedExamsDialog = new CompletedExamsDialog(this);
            completedExamsDialog.setController(controller);
            completedExamsDialog.setTeacher(teacherName, teacherSurname);
            completedExamsDialog.fillTable();
            completedExamsDialog.setVisible(true);
        });
    }

    public void fillStudentsComboBox() {
        HashMap<Student, HashMap<String, Integer>> gradeData = controller.getGradeData();

        if (gradeData.isEmpty()) {
            registerGrade.setEnabled(false);
            minusButton.setEnabled(false);
            studentsComboBox.addItem("No students registered");
        } else {
            registerGrade.setEnabled(true);
            minusButton.setEnabled(true);
        }

        String teacherSubjectLowerCase = teacherSubject.toLowerCase();

        studentsComboBox.removeAllItems();
        studentsComboBox.addItem("Select student");

        for (Student student : gradeData.keySet()) {
            HashMap<String, Integer> grades = gradeData.get(student);

            if (grades != null) {
                boolean hasSubject = false;
                for (String subject : grades.keySet()) {
                    if (subject.equalsIgnoreCase(teacherSubjectLowerCase)) {
                        hasSubject = true;
                        break;
                    }
                }

                if (hasSubject) {
                    System.out.println("Adding student: " + student.getName() + " " + student.getSurname());
                    studentsComboBox.addItem(student.getName() + " " + student.getSurname());
                } else {
                    System.out.println("Student not added: " + student.getName() + " " + student.getSurname());
                }
            }
        }
    }


    public Student findStudent(String studentName) {
        HashMap<Student, HashMap<String, Integer>> gradeData = controller.getGradeData();

        for (Student student : gradeData.keySet()) {
            if ((student.getName() + " " + student.getSurname()).equals(studentName)) {
                return student;
            }
        }

        return null;
    }


    public void fillGradesComboBox() {
        gradesComboBox.removeAllItems();
        gradesComboBox.addItem("Select grade");
        gradesComboBox.addItem("1");
        gradesComboBox.addItem("2");
        gradesComboBox.addItem("3");
        gradesComboBox.addItem("4");
        gradesComboBox.addItem("5");
    }

    public void setTeacher(String name, String surname, String subject) {

        this.teacherName = name;
        this.teacherSurname = surname;
        this.teacherSubject = subject;

        professorLabel.setText(name.toUpperCase() + " " + surname.toUpperCase());
        subjectLabel.setText(subject.toUpperCase());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}