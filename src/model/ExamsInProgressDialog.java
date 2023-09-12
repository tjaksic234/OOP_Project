package model;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamsInProgressDialog extends JDialog {
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton okButton;

    private String teacherName;
    private String teacherSurname;
    private Teacher teacher;

    private Controller controller;

    public ExamsInProgressDialog(JFrame parent) {
        super(parent, "Exams in Progress", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        tableModel = new DefaultTableModel(new Object[]{"Student", "Subject"}, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setAutoCreateRowSorter(true);
        table.setEnabled(false);
        scrollPane = new JScrollPane(table);

        okButton = new JButton("OK");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        fillTable();

        okButton.addActionListener(e -> {
            dispose();
        });

    }

    public void fillTable() {
        Teacher loggedTeacher = teacher;

        if (loggedTeacher != null) {
            List<String> teacherSubjects = controller.getSubjectsForTeacher(loggedTeacher);
            System.out.println(teacherSubjects);

            HashMap<Student, HashMap<String, Integer>> gradeData = controller.getGradeData();

            for (Student student : gradeData.keySet()) {
                HashMap<String, Integer> grades = gradeData.get(student);
                if (grades != null) {
                    for (String subject : grades.keySet()) {
                        if (teacherSubjects.contains(subject)) {
                            if (grades.get(subject) == 0 || grades.get(subject) == null) {
                                tableModel.addRow(new Object[]{student.getName() + " " + student.getSurname(), subject});
                            }
                        }
                    }
                }
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setTeacher(String teacherName, String teacherSurname) {
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;

        HashMap<Teacher, List<String>> teacherMap = controller.getTeacherMap();
        for (Map.Entry<Teacher, List<String>> entry : teacherMap.entrySet()) {
            Teacher teacher = entry.getKey();
            if (teacher.getName().equalsIgnoreCase(teacherName) && teacher.getSurname().equalsIgnoreCase(teacherSurname)) {
                this.teacher = teacher;
                break;
            }
        }
    }
}

