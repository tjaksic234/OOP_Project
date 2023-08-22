package placeholder;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StudentDetailsFrame extends JFrame {

    private JTable studentTable;
    private JButton logoutButton;

    public StudentDetailsFrame(Student student, Double averageGrade, HashMap<String, Integer> subjects) {
        super("Student Details");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init(student, averageGrade, subjects);
        layoutSet(student);
        activateComps();
    }

    public void init(Student student, Double averageGrade, HashMap<String, Integer> subjects) {
        String formattedAverageGrade = String.format("%.2f", averageGrade);
        String[] columnNames = {"Subject", "Grade"};

        DefaultTableModel tableModel = new DefaultTableModel(new Object[0][2], columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the entire table non-editable
            }
        };

        // Populate the table with subjects and grades
        for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
            String subject = entry.getKey();
            Integer grade = entry.getValue();
            tableModel.addRow(new Object[]{subject, grade});
        }

        // Add a row for displaying average grade
        String formattedAverageGradeLabel = "<html><b>Average Grade</b></html>";
        tableModel.addRow(new Object[]{formattedAverageGradeLabel, formattedAverageGrade});

        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        getContentPane().add(scrollPane);

        logoutButton = new JButton("Logout");
    }


    public void layoutSet(Student student) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adding a header label with student's name and surname
        JLabel headerLabel = new JLabel("Student Details: " + student.getName() + " " + student.getSurname());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(headerLabel, BorderLayout.NORTH);

        // Adding the table
        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Adding the logout button
        panel.add(logoutButton, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public void activateComps() {
        logoutButton.addActionListener(e -> {
            new ClientFrame();
            dispose();
            System.out.println("Logout button pressed");
        });
    }
}
