package placeholder;

import controller.Controller;
import model.Student;

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
    private JMenuItem logoutOption, registerGrade, removeGrade, showResults, clearDisplay;
    private Controller controller;

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

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        logoutOption = new JMenuItem("Logout");
        registerGrade = new JMenuItem("Register Grade");
        removeGrade = new JMenuItem("Remove Grade");
        showResults = new JMenuItem("Show Results");
        clearDisplay = new JMenuItem("Clear Display");

        menu.add(clearDisplay);
        menu.add(registerGrade);
        menu.add(removeGrade);
        menu.add(showResults);
        menu.add(logoutOption);
        menuBar.add(menu);
    }

    public void layoutSet() {
        JPanel panel = new JPanel(new BorderLayout());

        // Professor and Subject labels
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        professorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(professorLabel);
        headerPanel.add(subjectLabel);

        panel.add(headerPanel, BorderLayout.NORTH);

        // Combo boxes and buttons
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        studentsComboBox.setPreferredSize(new Dimension(200, 30));
        gradesComboBox.setPreferredSize(new Dimension(200, 30));
        centerPanel.add(new JLabel("Student:"));
        centerPanel.add(studentsComboBox);
        centerPanel.add(new JLabel("Grade:"));
        centerPanel.add(gradesComboBox);

        panel.add(centerPanel, BorderLayout.CENTER);

        textDisplay.setEditable(false);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        textDisplay.setBorder(BorderFactory.createEtchedBorder());
        JScrollPane scrollPane = new JScrollPane(textDisplay);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        panel.add(scrollPane, BorderLayout.SOUTH);

        setJMenuBar(menuBar);

        add(panel);
    }

    public void activateComps() {
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


                if (controller.gradeExists(student, subject, gradeInt)) {
                    JOptionPane.showMessageDialog(null, "Grade " + grade + " is already registered for student "
                            + studentName + "!\n");
                } else {
                    controller.addGrade(student, subject, gradeInt);
                    textDisplay.append("Registered grade " + grade + " for subject " + subject.toUpperCase() +
                            " for student " + studentName + "!\n");
                }
            }
        });

        removeGrade.addActionListener(e -> {
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

                if (!controller.gradeExists(student, subject, gradeInt)) {
                    JOptionPane.showMessageDialog(null, "Grade " + grade + " is not registered for student "
                            + studentName + "!");
                } else {
                    controller.removeGrade(student, subject);
                    textDisplay.append("Removed grade for subject " + subject.toUpperCase() + " from student " + studentName + "!\n");
                }
            }
        });




        showResults.addActionListener(e -> {
            HashMap<Student, HashMap<String, Integer>> gradeData = controller.getGradeData();

            if (gradeData.isEmpty()) {
                textDisplay.append("No grades registered!\n");
            } else {
                textDisplay.append("Info:\n");
                for (Student student : gradeData.keySet()) {
                    HashMap<String, Integer> studentGrades = gradeData.get(student);

                    if (studentGrades != null) {
                        for (String subject : studentGrades.keySet()) {
                            int grade = studentGrades.get(subject);
                            textDisplay.append(student.getName() + " " + student.getSurname() + " " + subject + " " + grade + "\n");
                        }
                    }
                }
            }
        });

        clearDisplay.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Display cleared!");
            textDisplay.setText("");
        });
    }

    public void fillStudentsComboBox() {
        HashMap<Student, List<String>> studentExams = controller.getRegisteredExams();

        if (studentExams.isEmpty()) {
            registerGrade.setEnabled(false);
            removeGrade.setEnabled(false);
            studentsComboBox.addItem("No students registered");
        }else {
            registerGrade.setEnabled(true);
            removeGrade.setEnabled(true);
        }

        String teacherSubjectLowerCase = teacherSubject.toLowerCase();

        studentsComboBox.removeAllItems();
        studentsComboBox.addItem("Select student");
        for (Student student : studentExams.keySet()) {
            List<String> exams = studentExams.get(student);

            if (exams != null) {
                for (String exam : exams) {
                    if (exam.toLowerCase().equals(teacherSubjectLowerCase)) {
                        studentsComboBox.addItem(student.getName() + " " + student.getSurname());
                        break;
                    }
                }
            }
        }
    }

    public Student findStudent(String studentName) {
        HashMap<Student, List<String>> studentExams = controller.getRegisteredExams();

        for (Student student : studentExams.keySet()) {
            if ((student.getName() + " " + student.getSurname()).equals(studentName)) {
                System.out.println("Found student: " + student.getName() + " " + student.getSurname());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraderFrame());
    }
}
