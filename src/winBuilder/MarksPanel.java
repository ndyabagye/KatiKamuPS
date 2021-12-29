package winBuilder;


import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.*;


public class MarksPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MarksPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		String[] columns = new String[] {"id","Reg No.", "Name", "Math", "Sci", "SST", "English"};
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
    	        String studName = studentResult.getString("firstName") +" "+ studentResult.getString("lastName");
    	        String studMath = String.valueOf(studentResult.getInt("Maths"));
    	        String studSci = String.valueOf(studentResult.getInt("Science"));
    	        String studSST = String.valueOf(studentResult.getInt("SST"));
    	        String studEnglish = String.valueOf(studentResult.getInt("English"));
     	       
    	        String [] student = {String.valueOf(id), regNum, studName, studMath, studSci, studSST, studEnglish};
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
		
		table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
            	JTable target = (JTable)me.getSource();
                int row = target.getSelectedRow(); // select a row
                int column = target.getSelectedColumn();
                int studId = Integer.valueOf(target.getValueAt(row, 0).toString());
                
                EditMarksForm editMarks = new EditMarksForm(studId);
				editMarks.setVisible(true);
				editMarks.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e){
						try {
							Connection connection = new DbConnection().getDbConnection();
				    		Statement studentStatement = connection.createStatement();
				    	    String getStudentQuery = "SELECT * FROM students WHERE id = " + studId;
				    		
				    		ResultSet studentResult = studentStatement.executeQuery(getStudentQuery);
				    	     
				    		while (studentResult.next()) {
				    			int uptstudId = studentResult.getInt("id");
				      	        
				    			String studMath = String.valueOf(studentResult.getInt("Maths"));
				    	        String studSci = String.valueOf(studentResult.getInt("Science"));
				    	        String studSST = String.valueOf(studentResult.getInt("SST"));
				    	        String studEnglish = String.valueOf(studentResult.getInt("English"));
				     	       
				      	        System.out.println(Integer.valueOf(target.getValueAt(row, 0).toString()));
				      	        target.setValueAt((Object)studMath, row, 3);
				      	        target.setValueAt((Object)studSci, row, 4);
				      	        target.setValueAt((Object)studSST, row, 5);
				      	        target.setValueAt((Object)studEnglish, row, 6);
				    		}
				    		connection.close();
      					}catch(Exception exe) {
      						exe.printStackTrace();
      					}

					}
				});
				    
				//JOptionPane.showMessageDialog(null, table.getValueAt(row, column));
            }
         });
		//Sort table section
        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        JTextField textField = new JTextField();
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setPreferredWidth(200);
        table.setRowSorter(sort);
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search students:"), BorderLayout.WEST);
        p.add(textField, BorderLayout.CENTER);
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
