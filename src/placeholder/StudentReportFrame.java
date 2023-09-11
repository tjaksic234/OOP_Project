package placeholder;

import controller.Controller;
import model.DBHandler;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentReportFrame extends JFrame {

    private JTable table;
    private JTextField averageGradeTextField;
    private JLabel nameLabel;
    private Student student;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logoutOption;
    private TableRowSorter<DefaultTableModel> tableRowSorter;

    private Controller controller;

    public StudentReportFrame() {
        setTitle("Student Table View");
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
        nameLabel = new JLabel();
        averageGradeTextField = new JTextField(10);
        averageGradeTextField.setEditable(false);

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        logoutOption = new JMenuItem("Logout");
        menuBar.add(menu);
        menu.add(logoutOption);
    }

    public void layoutSet() {
        String[] columnNames = {"Subject", "Condition", "Grade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableRowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableRowSorter);

        JScrollPane scrollPane = new JScrollPane(table);

        JLabel averageGradeLabel = new JLabel("Average Grade:");
        averageGradeTextField = new JTextField(10);
        averageGradeTextField.setEditable(false);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(nameLabel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(averageGradeLabel);
        bottomPanel.add(averageGradeTextField);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setJMenuBar(menuBar);

        pack();
    }


    public void activateComps() {

        logoutOption.addActionListener(e -> {
            StudentLoginFrame studentLoginFrame = new StudentLoginFrame();
            studentLoginFrame.setController(controller);
            JOptionPane.showMessageDialog(null, "Logout successful",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

    }

    public void setAverageGrade() {
        HashMap<Student, HashMap<String, Integer>> gradeData = controller.getGradeData();

        if (gradeData != null) {
            HashMap<String, Integer> studentGrades = gradeData.get(student);

            if (studentGrades != null && !studentGrades.isEmpty()) {
                ArrayList<Integer> grades = new ArrayList<>();

                for (String subject : studentGrades.keySet()) {
                    Integer grade = studentGrades.get(subject);
                    if (grade != null) {
                        grades.add(grade);
                    }
                }

                if (!grades.isEmpty()) {
                    int sum = 0;
                    for (Integer grade : grades) {
                        sum += grade;
                    }
                    double average = (double) sum / grades.size();
                    averageGradeTextField.setText(String.valueOf(average));
                } else {
                    averageGradeTextField.setText("No grades are documented");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No exams are documented");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No information available");
        }
    }


    public void setTableData() {
        HashMap<Student, HashMap<String, Integer>> gradeData = controller.getGradeData();

        if (gradeData != null) {
            HashMap<String, Integer> studentGrades = gradeData.get(student);

            if (studentGrades != null && !studentGrades.isEmpty()) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);

                for (String subject : studentGrades.keySet()) {
                    String condition = "";
                    Integer grade = studentGrades.get(subject);

                    if (grade != null) {
                        if (grade == 1) {
                            condition = "Failed";
                        } else if (grade == 0) {
                            condition = "Not yet graded";
                            
                        } else {
                            condition = "Passed";
                        }
                    }

                    tableModel.addRow(new Object[]{subject, condition, grade});
                }
            } else {
                JOptionPane.showMessageDialog(null, "No grade data available for the student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No grade data available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void setStudent(Student student){
        this.student = student;
        nameLabel = new JLabel("Name: " + student.getName().toUpperCase() + " " + student.getSurname().toUpperCase());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
