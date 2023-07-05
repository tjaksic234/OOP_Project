package view;

import controller.Controller;
import model.DBHandler;
import model.Student;
import model.TableSorter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The TableFrame class extends JFrame and represents a frame that displays a table of student data.
 */
public class TableFrame extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private Controller controller;
    private JButton save_data, read_data, new_data, sort_asc, sort_desc;
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
}
