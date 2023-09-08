package placeholder;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {

    private JButton teacherButton, studentButton;
    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenuItem adminOption;

    private Controller controller;

    public ClientFrame() {
        super("Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init();
        layoutSet();
        activateComps();
    }

    public void init(){
        teacherButton = new JButton("Teacher");
        studentButton = new JButton("Student");
        menuBar = new JMenuBar();
        adminMenu = new JMenu("Admin");
        adminOption = new JMenuItem("Login");

        adminMenu.add(adminOption);
        menuBar.add(adminMenu);
    }

    public void layoutSet(){
        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add label
        JLabel label = new JLabel("Welcome to the Student Management System!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        // Add teacher button
        panel.add(teacherButton);

        // Add student button
        panel.add(studentButton);

        // Add menu bar
        setJMenuBar(menuBar);


        // Add panel to the frame
        add(panel);
    }

    public void activateComps(){
        teacherButton.addActionListener(e -> {
            TeacherLoginFrame teacherLoginFrame = new TeacherLoginFrame();
            dispose();
            System.out.println("Teacher button pressed");
            teacherLoginFrame.setController(controller);
        });

        studentButton.addActionListener(e -> {
            StudentLoginFrame studentLoginFrame = new StudentLoginFrame();
            studentLoginFrame.setController(controller);
            dispose();
            System.out.println("Student button pressed");
        });

        adminOption.addActionListener(e -> {
            String masterPassword = JOptionPane.showInputDialog(this, "Enter Master Password:");
            if (masterPassword != null && masterPassword.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Admin Login Successful!");
                AdminFrame adminFrame = new AdminFrame();
                adminFrame.setController(controller);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Admin Login Failed. Invalid Master Password.");
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
