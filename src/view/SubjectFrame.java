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
        super("Subjects:");
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
        String[] available_subjects = {"Select a subject","Matematika","Fizika","Povijest","Geografija",
                "Kemija","Biologija","Informatika","Tjelesni"};
        subjects = new JComboBox<>(available_subjects);
        grades = new JComboBox<>(grades_list);
        previous_button = new JButton("Go Back");
        add_subject = new JButton("Add subject");
        new_student = new JButton("New student");
        show_students = new JButton("Show all students");

    }

    public void layoutSet(){

        //North panel

        JPanel north_panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //layout i label za predmete

        JLabel subject_label = new JLabel("Choose at least 3 subjects:");
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

    public void activateComps(){
        show_students.setEnabled(false);
        new_student.setEnabled(false);

        previous_button.addActionListener(e -> {
            getPreviousStudentData();
            mainFrame.setVisible(true);
            dispose();
            controller.removeLastStudent();

        });
        new_student.addActionListener(e -> {
            mainFrame.setVisible(true);
            dispose();
        });
        add_subject.addActionListener(e -> {
            if (subjects.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(
                        SubjectFrame.this,
                        "Please select a subject.",
                        "Missing Subject",
                        JOptionPane.WARNING_MESSAGE
                );
            }else {
                String subject = (String) subjects.getSelectedItem();
                Integer grade = (Integer) grades.getSelectedItem();
                Student student = controller.getLastStudent();
                controller.addSubject(student,subject, grade);

                // Enable the buttons if the student has 3 or more subjects
                if (controller.getSubjectCount(student) >= 3) {
                    show_students.setEnabled(true);
                    new_student.setEnabled(true);
                }
            }
        });
        show_students.addActionListener(e -> {
            System.out.println("==============AVERAGE GRADE DATA CHECK================");
            controller.printAverageGrades();

            // Open the TableFrame
            TableFrame tableFrame = new TableFrame();
            tableFrame.setController(controller);
            tableFrame.updateTableData();
            dispose();

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
            mainFrame.getIme().setText(student.getName());
            mainFrame.getSurname().setText(student.getSurname());
            mainFrame.getCollege().setText(student.getCollege());
        }else {
            mainFrame.getIme().setText("");
            mainFrame.getSurname().setText("");
            mainFrame.getCollege().setText("");
        }
    }
}
