package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class StudentTableFrame extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public StudentTableFrame(){
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

        //table initialization
        String[] columns = {"Ime", "Prezime", "Fakultet", "Prosjek"};
        String[][] data = {{"John","Doe","FPMOZ","8.5"},{"Daisy","Duck","FPMOZ","9.5"},
                {"Donald","Duck","FPMOZ","9.0"},{"Mickey","Mouse","FPMOZ","10.0"}};
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

        //scrollPane initialization
        scrollPane = new JScrollPane(table);

    }

    public void layoutSet(){
        JPanel northPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //layout za jtable

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(30,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        northPanel.add(scrollPane, gbc);

        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);

    }
}
