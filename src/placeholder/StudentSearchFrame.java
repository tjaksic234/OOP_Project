package placeholder;

import model.DBHandler;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StudentSearchFrame extends JFrame {

    private JButton searchButton, backButton;
    private JTextField studentName, studentSurname;
    private JLabel studentNameLabel, studentSurnameLabel;

    public StudentSearchFrame() {
        super("Student Search");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init();
        layoutSet();
        activateComps();
    }

    public void init() {
        searchButton = new JButton("Search");
        backButton = new JButton("Back");
        studentName = new JTextField(15);
        studentSurname = new JTextField(15);
        studentNameLabel = new JLabel("Student name:");
        studentSurnameLabel = new JLabel("Student surname:");
    }

    public void layoutSet() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Welcome label
        JLabel label = new JLabel("Search for a student");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 20, 0); // Add some vertical spacing
        panel.add(label, constraints);

        // Student name label and text field
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(studentNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(studentName, constraints);

        // Student surname label and text field
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(studentSurnameLabel, constraints);
        constraints.gridx = 1;
        panel.add(studentSurname, constraints);

        // Search and Back buttons
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 0, 0, 0); // Add some spacing before buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);
        panel.add(buttonPanel, constraints);

        // Add panel to the frame
        add(panel);
    }

    public void activateComps() {
        backButton.addActionListener(e -> {
            new ClientFrame();
            dispose();
            System.out.println("Back button pressed");
        });

       searchButton.addActionListener(e -> {
            String name = studentName.getText();
            String surname = studentSurname.getText();

            if (name.isEmpty() || surname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!name.matches("[a-zA-Z]+") || !surname.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Name and surname must contain only letters", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Search for student data here
                DBHandler dbHandler = new DBHandler();
                HashMap<Student, HashMap<String,Object>> data = dbHandler.readDataFromFile();

                for (Map.Entry<Student, HashMap<String, Object>> entry : data.entrySet()) {
                    Student student = entry.getKey();
                    HashMap<String, Object> studentData = entry.getValue();

                    if (student.getName().equalsIgnoreCase(name) && student.getSurname().equalsIgnoreCase(surname)) {
                        Double averageGrade = (Double) studentData.get("averageGrade");
                        @SuppressWarnings("unchecked")
                        HashMap<String, Integer> subjects = (HashMap<String, Integer>) studentData.get("subjects");

                        StudentDetailsFrame detailsFrame = new StudentDetailsFrame(student, averageGrade, subjects);
                        detailsFrame.setVisible(true);
                        dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Student does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
       });
    }

}
