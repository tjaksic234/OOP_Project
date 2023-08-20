package placeholder;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentDetailsFrame extends JFrame {

    private JTable studentTable;
    private JButton logoutButton;

    public StudentDetailsFrame(Student student, Double averageGrade) {
        super("Student Details");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init(student, averageGrade);
        layoutSet();
        activateComps();
    }

    public void init(Student student, Double averageGrade) {
        String formattedAverageGrade = String.format("%.2f", averageGrade);
        String[] columnNames = {"Name", "Surname", "College", "Average Grade"};
        Object[] rowData = {student.getName(), student.getSurname(), student.getCollege(), formattedAverageGrade};

        DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {rowData}, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the entire table non-editable
            }
        };
        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        getContentPane().add(scrollPane);

        logoutButton = new JButton("Logout");
    }

    public void layoutSet() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adding a header label
        JLabel headerLabel = new JLabel("Student Details");
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
