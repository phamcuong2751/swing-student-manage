package GUI;

import com.xml.XMLReader;
import models.MStudent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListAllStudent extends JFrame {
    private JPanel mainPanel;
    private static final String FILENAME = "data-student.xml";
    private JTable table_listAllStudent;
    private JTextField tb_studentId;
    private JTextField tb_score;
    private JTextField tb_studentName;
    private JTextField tb_description;
    private JTextField tb_address;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public ListAllStudent() {
        setContentPane(mainPanel);
        createUIComponents();
        setTitle("Student Manage");
        setSize(800, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUIComponents() {
        XMLReader xmlFile = new XMLReader();
        loadTable();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MStudent student = new MStudent();
                student.setStudentID(tb_studentId.getText());
                student.setStudentName(tb_studentName.getText());
                student.setScore( Float.parseFloat(tb_score.getText()));
                student.setAddress(tb_address.getText());
                student.setDescription(tb_description.getText());
                xmlFile.write(student, FILENAME);

                loadTable();
            }
        });
    }

    private void loadTable() {
        XMLReader xmlFile = new XMLReader();
        List<MStudent> listStudent = new ArrayList<MStudent>();
        listStudent = xmlFile.read(FILENAME);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnCount(1);
        tableModel.setRowCount(1);
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Student Name");
        tableModel.addColumn("Score");
        tableModel.addColumn("Address");
        tableModel.addColumn("Description");

        for (MStudent student : listStudent) {
            Vector v = new Vector();
            v.add(student.getStudentID());
            v.add(student.getStudentName());
            v.add(student.getScore());
            v.add(student.getAddress());
            v.add(student.getDescription());
            tableModel.addRow(v);
        }

        table_listAllStudent.setModel(tableModel);
    }
}
