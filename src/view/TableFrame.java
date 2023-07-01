package view;

import controller.Controller;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TableFrame extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private Controller controller;

    public TableFrame(){
        super("Studenti:");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init();
        layoutSet();

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

        // Create a TableRowSorter and associate it with the table model
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Set the custom Comparator for the "Name" column
        Comparator<String> nameLengthComparator = Comparator.comparingInt(String::length);
        sorter.setComparator(0, nameLengthComparator);

        // Sort the table based on the "Age" column in descending order
        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));

        // Create a scroll pane and add the table to it
        scrollPane = new JScrollPane(table);

    }


    public void layoutSet(){
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);

        // TODO implement the buttons and save data logic

        pack(); // Adjust the frame size to fit the components

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
