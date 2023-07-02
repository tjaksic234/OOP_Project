package view;

import controller.Controller;
import model.DBHandler;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TableFrame extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private Controller controller;
    private JButton save_data, read_data, new_data;

    public TableFrame(){
        super("Studenti:");
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

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Name", "Surname", "College","Average grade"});

        // Create the table using the model
        table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Create a scroll pane and add the table to it
        scrollPane = new JScrollPane(table);

        // Button initialization
        save_data = new JButton("Save data");
        read_data = new JButton("Read data");
        new_data = new JButton("New data");

    }


    public void layoutSet(){
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);

        // TODO implement the buttons and save data logic
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(save_data, BorderLayout.EAST);
        southPanel.add(read_data, BorderLayout.WEST);
        southPanel.add(new_data, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        pack(); // Adjust the frame size to fit the components

    }

    public void activateComps(){
        // Save data to txt file
        save_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    HashMap<Student, Double> studentGrades = controller.getAverageGrades();
                    DBHandler dbHandler = new DBHandler();
                    dbHandler.saveDataToFile(studentGrades);
                }
            }
        });

        // Read data from txt file
        read_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler dbHandler = new DBHandler();
                HashMap<Student, Double> studentGrades = dbHandler.readDataFromFile();
                controller.setAverageGrades(studentGrades);
                updateTableData();
            }
        });

        // Create new data
        new_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                System.out.println("New data generated successfully.");
                dispose();
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void updateTableData(){
        if (controller != null){
            // Clear the table
            model.setRowCount(0);

            // Get student data from the controller
            HashMap<Student, Double> studentGrades = controller.getAverageGrades();

            // Add data to the table
            for (Map.Entry<Student, Double> entry : studentGrades.entrySet()) {
                Student student = entry.getKey();
                Double averageGrade = entry.getValue();

                // Format the average grade to two decimal places
                String formattedAverageGrade = String.format("%.2f", averageGrade);

                model.addRow(new Object[]{student.getIme(), student.getSurname(),
                        student.getCollege(), formattedAverageGrade});
            }
        }

    }
}
