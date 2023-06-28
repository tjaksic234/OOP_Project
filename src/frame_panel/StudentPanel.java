package frame_panel;

import action_listeners.Student_Listener;
import aux_classes.Student;
import aux_classes.StudentManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentPanel extends JFrame  {

    private JTextField ime;
    private JTextField prezime;
    private JTextField fakultet;
    private JButton next_button;
    private SubjectPanel subjectPanel;
    private StudentManager studentManager;

    public StudentPanel(StudentManager studentManager){
        super("Student detalji:");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        this.studentManager = studentManager;

        init();
        layoutSet();
        activateComps();
    }

    public void init(){
        ime = new JTextField(15);
        prezime = new JTextField(15);
        fakultet = new JTextField(15);
        next_button = new JButton("Dalje");
    }

    public void layoutSet(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //layout za label i textfield atributa ime

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(15,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        panel.add(new JLabel("Ime:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(ime, gbc);


        //layout za label i textfield atributa prezime

        gbc.insets = new Insets(30,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Prezime:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(prezime, gbc);


        //layout za label i textfield atributa fakultet

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Fakultet:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(fakultet, gbc);


        //layout za button

        gbc.insets = new Insets(70,5,15,5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(next_button, gbc);


        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
    }

    public void activateComps(){
        next_button.addActionListener(new Student_Listener(this));
        System.out.println("Button activated");


    }


    public JTextField getIme() {
        return ime;
    }

    public JTextField getPrezime() {
        return prezime;
    }

    public JTextField getFakultet() {
        return fakultet;
    }

    public JButton getNext_button() {
        return next_button;
    }

    public StudentManager getStudentManager() {
        return studentManager;
    }
}

