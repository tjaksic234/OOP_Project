package placeholder;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {

    private JButton teacherButton, studentButton;

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

        // Add panel to the frame
        add(panel);
    }

    public void activateComps(){
        teacherButton.addActionListener(e -> {
            TeacherLoginFrame teacherLoginFrame = new TeacherLoginFrame();
            dispose();
            System.out.println("Teacher button pressed");
        });

        studentButton.addActionListener(e -> {
            StudentSearchFrame studentSearchFrame = new StudentSearchFrame();
            dispose();
            System.out.println("Student button pressed");
        });
    }

    //TODO add functionality to the StudentLoginFrame so it checks if the student is present in the database or not

}
