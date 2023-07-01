package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableFrame extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

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

        // Table initialization
        String[] columns = {"Name", "Surname", "Sollege", "Average grade"};
        Object[][] data = {
                {"John", "Doe", "ABC College", 85.5},
                {"Jane", "Smith", "XYZ College", 92.0},
                {"Michael", "Johnson", "DEF College", 78.3},
                {"Michael","Jordan","ABC College",100.0}
                // Add more rows as needed
        };

//        DefaultTableModel model = new DefaultTableModel(data, columns){
//            public boolean isCellEditable(int row, int column){
//                return false;
//            }
//        };



        table = new JTable(data,columns){

            public boolean isCellEditable(int row, int column){
                return false;
            }
            public Component prepareRenderer(TableCellRenderer renderer, int data, int column){
                Component c = super.prepareRenderer(renderer, data, column);

                if(data % 2 == 0){
                    c.setBackground(Color.WHITE);
                }else{
                    c.setBackground(Color.WHITE);
                }
                if (isCellSelected(data, column))
                    c.setBackground(Color.GREEN);
                return c;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(450, 63));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
    }

    public void layoutSet(){
        JPanel northPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //layout za JTable

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(30,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        northPanel.add(scrollPane,gbc);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.NORTH);

    }
}
