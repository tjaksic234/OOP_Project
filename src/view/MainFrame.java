package view;

import DB_Handlers.StudentData;
import DB_Handlers.SubjectData;
import controller.Controller;
import model.Student;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame  {

    private JTextField name, surname, college;
    private JButton next_button;

    private Controller controller;
    private SubjectFrame subjectFrame;


    public MainFrame(){
        super("Student detalji:");
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
        name = new JTextField(15);
        surname = new JTextField(15);
        college = new JTextField(15);
        next_button = new JButton("Dalje");
        controller = new Controller(new StudentData(),new SubjectData());

    }

    public void layoutSet(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //layout za label i textfield atributa name

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(15,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        panel.add(new JLabel("Ime:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(name, gbc);


        //layout za label i textfield atributa surname

        gbc.insets = new Insets(30,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Prezime:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(surname, gbc);


        //layout za label i textfield atributa college

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Fakultet:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(college, gbc);


        //layout za button

        gbc.insets = new Insets(70,5,15,5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(next_button, gbc);


        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
    }

    public void activateComps(){

        // Adding student to database
        next_button.addActionListener(e -> {
            String studentName = name.getText();
            String studentSurname = surname.getText();
            String studentCollege = college.getText();

            // Check if fields are empty
            if (studentName.isEmpty() || studentSurname.isEmpty() || studentCollege.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.this, "Please fill in all the fields.");
            }else {
                // Adding student to database
                controller.addStudent(new Student(studentName, studentSurname, studentCollege));

                // Perform other actions or open new frames as needed
                System.out.println("------------------DATA CHECKER---------------------");
                controller.getStudentList();

                // Open the next frame
                subjectFrame = new SubjectFrame(this);
                setVisible(false);
            }

        });
    }


    public JTextField getIme() {
        return name;
    }

    public JTextField getSurname() {
        return surname;
    }

    public JTextField getCollege() {
        return college;
    }

    public Controller getController() {
        return controller;
    }

    public void resetForm(){
        name.setText("");     // Clear the text in the name field
        surname.setText("");  // Clear the text in the surname field
        college.setText("");  // Clear the text in the college field
    }
}

