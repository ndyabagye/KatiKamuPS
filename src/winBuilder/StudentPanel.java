package winBuilder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.*;

public class StudentPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public StudentPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		String[] columns = new String[] {"id","Reg No.", "Name", "Age", "Class"};
        String[][] data = {};
        ArrayList<String[]> studentList = new ArrayList<String[]>(Arrays.asList(data));  
        
        try {
    		Connection connection = new DbConnection().getDbConnection();
    		String query = "SELECT * FROM students";

    	    // create the java statement
    	    Statement studentStatement = connection.createStatement();
    	      
    	    // execute the query, and get a java
    	    ResultSet studentResult = studentStatement.executeQuery(query);
    	 
    		
    	    // iterate through the java
    	    while (studentResult.next()){
    	        int id = studentResult.getInt("id");
    	        String regNum = studentResult.getString("regNum");
    	        String stuName = studentResult.getString("firstName") +" "+ studentResult.getString("lastName");
    	        String stuAge = studentResult.getString("stuAge");
    	        String stuClass = studentResult.getString("stuClass");
    	        String [] student = {String.valueOf(id), regNum,stuName, stuAge, stuClass };
    	        studentList.add(student);
    	    }
    	    
    	    data = studentList.toArray(data);
    	
    	    studentStatement.close();
    	}catch(Exception exe) {
    		System.out.println("here");
    		exe.printStackTrace();
    	}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			columns
		));
		scrollPane.setViewportView(table);
		
		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        JTextField textField = new JTextField();

        //set the width of the 3rd column to 200 pixels
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setPreferredWidth(200);
    
        table.setRowSorter(sort);

        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search students:"), BorderLayout.WEST);
        p.add(textField, BorderLayout.CENTER);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
            	JTable target = (JTable)me.getSource();
                int row = target.getSelectedRow(); // select a row
                //JOptionPane.showMessageDialog(null, table.getValueAt(row, 0));
            }
         });
        
        setLayout(new BorderLayout());
        add(p, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        textField.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    //(?i) means case insensitive search
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = textField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

	}
}
