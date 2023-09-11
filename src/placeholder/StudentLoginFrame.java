package placeholder;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentLoginFrame extends JFrame {

    private JButton searchButton, backButton;
    private JTextField studentName, studentSurname;
    private JLabel studentNameLabel, studentSurnameLabel;
    private Controller controller;

    public StudentLoginFrame() {
        super("Student Login");
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
        searchButton = new JButton("Login");
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
        JLabel label = new JLabel("Welcome student");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 15, 20, 0); // Add some vertical spacing
        panel.add(label, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(studentNameLabel, constraints);
        constraints.gridx = 1;
        panel.add(studentName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(studentSurnameLabel, constraints);
        constraints.gridx = 1;
        panel.add(studentSurname, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 0, 0, 0); // Add some spacing before buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton);
        panel.add(buttonPanel, constraints);

        add(panel);
    }

    public void activateComps() {
        backButton.addActionListener(e -> {
            ClientFrame clientFrame = new ClientFrame();
            dispose();
            System.out.println("Back button pressed");
            clientFrame.setController(controller);

        });

       searchButton.addActionListener(e -> {
            String name = studentName.getText();
            String surname = studentSurname.getText();

            if (name.isEmpty() || surname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!name.matches("[a-zA-Z]+") || !surname.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Name and surname must contain only letters", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                List<Student> studentList = controller.getStudentList();

                boolean found = false;

                for (Student student : studentList) {
                    if (student.getName().equalsIgnoreCase(name) && student.getSurname().equalsIgnoreCase(surname)) {
                        JOptionPane.showMessageDialog(null, "Student login successful:\nName: "
                                + student.getName().toUpperCase() + "\nSurname: " +
                                student.getSurname().toUpperCase(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        found = true;

                        StudentReportFrame studentReportFrame = new StudentReportFrame();
                        studentReportFrame.setController(controller);
                        studentReportFrame.setStudent(student);
                        studentReportFrame.setTableData();
                        studentReportFrame.setAverageGrade();
                        dispose();
                        break;
                    }
                }



                if (!found) {
                    JOptionPane.showMessageDialog(null, "No matching student found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
       });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
