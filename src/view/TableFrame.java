package view;

import controller.Controller;
import model.DBHandler;
import model.Student;
import model.TableSorter;
import placeholder.ClientFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The TableFrame class represents a frame that displays a table of student data.
 */
public class TableFrame extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private Controller controller;
    private JButton save_data, read_data, new_data, sort_asc, sort_desc, logoutButton;
    private DBHandler dbHandler;

    /**
     * Constructs a TableFrame object.
     */
    public TableFrame() {
        super("Students:");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        init();
        layoutSet();
        activateComps();
    }

    /**
     * Initializes the components of the TableFrame.
     */
    public void init() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Name", "Surname", "College", "Average grade"});

        // Create the table using the model
        table = new JTable(model) {
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
        sort_asc = new JButton("Sort ascending");
        sort_desc = new JButton("Sort descending");
        logoutButton = new JButton("Logout");

        // Database handler initialization
        dbHandler = new DBHandler();
    }

    /**
     * Sets the layout of the TableFrame.
     */
    public void layoutSet() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // TODO: Implement the sort buttons
        JPanel sortButtonsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        sortButtonsPanel.add(sort_asc, gbc);

        gbc.gridx = 1;
        sortButtonsPanel.add(sort_desc, gbc);

        // TODO: Implement the read buttons and save data logic
        JPanel dataButtonsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        dataButtonsPanel.add(read_data, gbc);

        gbc.gridx = 1;
        dataButtonsPanel.add(new_data, gbc);

        gbc.gridx = 2;
        dataButtonsPanel.add(save_data, gbc);

        // Add components to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(sortButtonsPanel, gbc);

        gbc.gridy = 2;
        add(dataButtonsPanel, gbc);

        // Create a panel for the Logout button
        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutButton);

        // Set up GridBagConstraints for the Logout button panel
        GridBagConstraints logoutGBC = new GridBagConstraints();
        logoutGBC.gridx = 0;
        logoutGBC.gridy = 3; // Assuming you have three rows before the logout button
        logoutGBC.gridwidth = 2;
        logoutGBC.insets = new Insets(10, 10, 10, 10);
        logoutGBC.anchor = GridBagConstraints.CENTER;

        // Add the Logout button panel to the main panel
        add(logoutPanel, logoutGBC);

        pack();
    }

    /**
     * Activates the components and defines their event listeners.
     */
    public void activateComps() {
        // Save data to txt file
        save_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    HashMap<Student, Double> studentGrades = controller.getAverageGrades();
                    dbHandler.saveDataToFile(studentGrades);

                    JOptionPane.showMessageDialog(null, "Data saved successfully.");
                }
            }
        });

        // Read data from txt file
        read_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Student, Double> studentGrades = dbHandler.readDataFromFile();
                controller.setAverageGrades(studentGrades);
                updateTableData();

                JOptionPane.showMessageDialog(null, "Data read successfully.");
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

        // Sort data ascending
        sort_asc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Student, Double> studentGrades = controller.getAverageGrades();
                controller.setAverageGrades(TableSorter.sortTableAscending(studentGrades));
                updateTableData();
            }
        });

        // Sort data descending
        sort_desc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Student, Double> studentGrades = controller.getAverageGrades();
                controller.setAverageGrades(TableSorter.sortTableDescending(studentGrades));
                updateTableData();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            // Check if a single cell is selected
            if (table.getSelectedRowCount() == 1 && table.getSelectedColumnCount() == 1) {
                int selectedRow = table.getSelectedRow();

                // Get the selected student index based on the selected row
                int modelRow = table.convertRowIndexToModel(selectedRow);

                // Get the selected student object from the controller
                Student selectedStudent = controller.getStudentAtIndex(modelRow);

                // Check if the selected student is not null
                if (selectedStudent != null) {
                    // Check if the selected column is one of the desired columns (name, surname, college, or average grade)
                    int selectedColumn = table.getSelectedColumn();
                    if (selectedColumn >= 0 && selectedColumn <= 3) {
                        // Get the new value from the user
                        String newValue = "";

                        if (selectedColumn == 3) {
                            // If editing the average grade cell, validate the input as a number
                            String input = JOptionPane.showInputDialog(null, "Enter the new average grade:");

                            if (input == null) {
                                System.out.println("User cancelled cell editing.");
                                return; // User cancelled cell editing, exit the listener
                            }

                            try {
                                double averageGrade = Double.parseDouble(input);
                                // Round the average grade to two decimal places
                                newValue = String.format("%.2f", averageGrade);

                                // Update the average grades in the TableDataRepository
                                HashMap<Student, Double> averageGradesMap = controller.getAverageGrades();
                                averageGradesMap.put(selectedStudent, averageGrade);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                                return; // Exit the listener if an invalid number is entered
                            }
                        } else {
                            // If editing other cells, prompt for the new value as a string
                            newValue = JOptionPane.showInputDialog(null, "Enter the new value:");

                            if (newValue == null) {
                                System.out.println("User cancelled cell editing.");
                                return; // User cancelled cell editing, exit the listener
                            }

                            if (!isValidStringValue(newValue)) {
                                JOptionPane.showMessageDialog(null, "Invalid input. Please enter only letters (a-z or A-Z).");
                                return; // Exit the listener if an invalid string is entered
                            }

                            if (selectedColumn == 0) {
                                selectedStudent.setName(newValue);
                            } else if (selectedColumn == 1) {
                                selectedStudent.setSurname(newValue);
                            } else if (selectedColumn == 2) {
                                selectedStudent.setCollege(newValue);
                            }
                        }

                        // Update the table data at the selected row and column index
                        model.setValueAt(newValue, modelRow, selectedColumn);
                    }
                }
            }
        });

        // Logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientFrame();
                System.out.println("Logged out successfully.");
                dispose();
            }
        });
    }

    /**
     * Sets the controller for the TableFrame.
     *
     * @param controller the controller to be set.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Updates the table data based on the current student data in the controller.
     */
    public void updateTableData() {
        if (controller != null) {
            // Clear the table
            model.setRowCount(0);

            // Get student data from the controller
            HashMap<Student, Double> studentGrades = controller.getAverageGrades();

            // Add data to the table
            for (Map.Entry<Student, Double> entry : studentGrades.entrySet()) {
                Student student = entry.getKey();
                Double averageGrade = entry.getValue();
                String formattedAverageGrade = String.format("%.2f", averageGrade);

                model.addRow(new Object[]{student.getName(), student.getSurname(),
                        student.getCollege(), formattedAverageGrade});
            }
        }
    }

    private boolean isValidStringValue(String value) {
        return value.matches("^[a-zA-Z]+$");
    }
}
