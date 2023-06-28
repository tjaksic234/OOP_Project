package frame_panel;

import action_listeners.Subject_Listener;
import aux_classes.Student;
import aux_classes.StudentManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SubjectPanel extends JFrame {

    private JComboBox<String> subjects;
    private JComboBox<Integer> grades;
    private JButton previous_button;
    private JButton add_subject;
    private JButton new_student;
    private JButton show_students;
    private StudentPanel studentPanel;
    private Student student;
    private StudentManager studentManager;

    public SubjectPanel(StudentPanel studentPanel,StudentManager studentManager){
        super("Predmeti:");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        this.studentPanel = studentPanel;
        this.studentManager = studentManager;


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
        previous_button.addActionListener(new Subject_Listener(this, studentPanel));
        new_student.addActionListener(new Subject_Listener(this, studentPanel));
        add_subject.addActionListener(new Subject_Listener(this, studentPanel));
        show_students.addActionListener(new Subject_Listener(this,studentPanel));
    }


}
