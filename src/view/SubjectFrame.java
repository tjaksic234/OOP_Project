package view;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class SubjectFrame extends JFrame {

    private JComboBox<String> subjects;
    private JComboBox<Integer> grades;
    private JButton previous_button, add_subject, new_student, show_students;
    private Controller controller;
    private MainFrame mainFrame;


    public SubjectFrame(){
        super("Predmeti:");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);




        init();
        layoutSet();
        activateComps();
    }

    public void init(){
        Integer[] grades_list = {1,2,3,4,5};
        String[] available_subjects = {"Matematika","Fizika","Povijest","Geografija",
                "Kemija","Biologija","Informatika","Tjelesni"};
        subjects = new JComboBox<>(available_subjects);
        grades = new JComboBox<>(grades_list);
        previous_button = new JButton("Nazad");
        add_subject = new JButton("Dodaj predmet");
        new_student = new JButton("Novi student");
        show_students = new JButton("Prikaži studente");

    }

    public void layoutSet(){

        //North panel

        JPanel north_panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //layout i label za predmete

        JLabel subject_label = new JLabel("Odabir predmeta:");
        gbc.insets = new Insets(20,15,5,5);
        north_panel.add(subject_label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 1;
        gbc.insets = new Insets(30, 5, 5, 5);
        subjects.setPreferredSize(new Dimension(200, subjects.getPreferredSize().height));
        subjects.setFont(new Font("Comic Sans", Font.BOLD, 15));
        north_panel.add(subjects, gbc);

        //layout za ocjene
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(30,5,5,5);
        grades.setPreferredSize(new Dimension(50, grades.getPreferredSize().height));
        grades.setFont(new Font("Comic Sans", Font.BOLD, 15));
        north_panel.add(grades, gbc);

        add(north_panel, BorderLayout.NORTH);

        //South panel

        JPanel south_panel = new JPanel(new GridBagLayout());
        south_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(45,20,20,20);

        //layout za botune
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        gbc2.gridy = 0;
        gbc2.gridx = 0;
        south_panel.add(previous_button, gbc2);

        gbc2.gridy = 0;
        gbc2.gridx = 1;
        south_panel.add(add_subject, gbc2);

        gbc2.gridy = 1;
        gbc2.gridx = 0;
        south_panel.add(new_student, gbc2);

        gbc2.gridy = 1;
        gbc2.gridx = 1;
        south_panel.add(show_students, gbc2);


        add(south_panel, BorderLayout.CENTER);
    }

    public JButton getPrevious_button() {
        return previous_button;
    }

    public JButton getAdd_subject() {
        return add_subject;
    }

    public JButton getNew_student() {
        return new_student;
    }

    public JButton getShow_students() {
        return show_students;
    }

    public JComboBox<String> getSubjects() {
        return subjects;
    }

    public JComboBox<Integer> getGrades() {
        return grades;
    }

    public void activateComps(){
        previous_button.addActionListener(e -> {
            getPreviousStudentData();
            mainFrame.setVisible(true);
            dispose();
            controller.removeLastStudent();

        });
        new_student.addActionListener(e -> {
            controller.getStudentList();
            mainFrame.setVisible(true);
            dispose();
        });
        add_subject.addActionListener(e -> {
            String subject = (String) subjects.getSelectedItem();
            Integer grade = (Integer) grades.getSelectedItem();
            Student student = controller.getLastStudent();
            controller.addSubject(student,subject, grade);
            controller.getSubjectList();


        });
        show_students.addActionListener(e -> {
            controller.getStudentsWithSubjectsString();

        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMainFrameListener(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public void getPreviousStudentData(){
        Student student = controller.getLastStudent();
        if (student != null) {
            mainFrame.getIme().setText(student.getIme());
            mainFrame.getSurname().setText(student.getSurname());
            mainFrame.getCollege().setText(student.getCollege());
        }else {
            mainFrame.getIme().setText("");
            mainFrame.getSurname().setText("");
            mainFrame.getCollege().setText("");
        }
    }
}
