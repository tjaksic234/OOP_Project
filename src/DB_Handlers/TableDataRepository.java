package DB_Handlers;

import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TableDataRepository implements TableDataMethodHandler{

    private JTable table;
    private JScrollPane scrollPane;

    public TableDataRepository() {
        table = new JTable();
        scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(450, 63));
        table.setFillsViewportHeight(true);
    }

    @Override
    public void populateTableData(List<Student> studentList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("College");
//        model.addColumn("Average Grade");

        for (Student student : studentList){
            Object[] rowData = {student.getIme(), student.getSurname(), student.getCollege()};
        }

        table.setModel(model);
    }
}
