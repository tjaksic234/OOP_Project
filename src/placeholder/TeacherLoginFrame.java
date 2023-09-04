package placeholder;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class TeacherLoginFrame extends JFrame {

    private JPasswordField passwordField;
    private JButton loginButton;
    private JTextField usernameField;
    private JButton backButton;
    private JCheckBox showPassword;

    private Controller controller;

    public TeacherLoginFrame() {
        super("Teacher Login");
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
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        usernameField = new JTextField(15);
        backButton = new JButton("Back");
        showPassword = new JCheckBox("Show password");
    }

    public void layoutSet() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Welcome label
        JLabel label = new JLabel("Welcome teacher");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(label, gbc);

        gbc.gridwidth = 1;

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 0, 10, 10);
        panel.add(usernameLabel, gbc);

        // Username field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(20, 0, 10, 0);
        panel.add(usernameField, gbc);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 10);
        panel.add(passwordLabel, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(passwordField, gbc);


        // Show password checkbox
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0);
        panel.add(showPassword, gbc);

        // Back button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(30, 0, 0, 10);
        panel.add(backButton, gbc);

        // Login button
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(30, 10, 0, 0);
        panel.add(loginButton, gbc);


        add(panel);
    }

    public void activateComps() {
        backButton.addActionListener(e -> {
            ClientFrame clientFrame = new ClientFrame();
            clientFrame.setController(controller);
            dispose();
            System.out.println("Back button pressed");
        });

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword()); // Get the password as a String

            // Loop through the teacherList and check credentials
            boolean loginSuccessful = false;
            Teacher loggedInTeacher = null;
            for (Teacher teacher : controller.getTeacherList()) {
                if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)) {
                    loginSuccessful = true;
                    loggedInTeacher = teacher;
                    break;
                }
            }

            if (loginSuccessful) {
                JOptionPane.showMessageDialog(null, "Login successful");

                GraderFrame graderFrame = new GraderFrame();
                graderFrame.setController(controller);
                graderFrame.setTeacher(loggedInTeacher.getName(), loggedInTeacher.getSurname(), loggedInTeacher.getSubject());
                graderFrame.fillStudentsComboBox();
                graderFrame.fillGradesComboBox();

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password!!!");
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
