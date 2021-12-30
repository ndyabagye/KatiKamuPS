package winBuilder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TeacherPanel extends JPanel {

	private JTable table;
	
	public TeacherPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		String[] columns = new String[] {"id","First Name", "Last Name", "Email"};
        String[][] data = {};
        ArrayList<String[]> teacherList = new ArrayList<String[]>(Arrays.asList(data));  
        
        try {
    		Connection connection = new DbConnection().getDbConnection();
    		String query = "SELECT * FROM teachers";

    	    // create the java statement
    	    Statement teacherStatement = connection.createStatement();
    	      
    	    // execute the query, and get a java
    	    ResultSet teacherResult = teacherStatement.executeQuery(query);
    	 
    		
    	    // iterate through the java
    	    while (teacherResult.next()){
    	        int id = teacherResult.getInt("id");
    	        String firstName = teacherResult.getString("firstName");
    	        String lastName = teacherResult.getString("lastName");
    	        String email = teacherResult.getString("email");
    	        String [] teacher = {String.valueOf(id), firstName, lastName, email};
    	        teacherList.add(teacher);
    	    }
    	    
    	    data = teacherList.toArray(data);
    	
    	    teacherStatement.close();
    	}catch(Exception exe) {
    		System.out.println("here");
    		exe.printStackTrace();
    	}
		
        DefaultTableModel model = new DefaultTableModel(data, columns);
        
		table = new JTable();
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
            	JTable target = (JTable)me.getSource();
                int row = target.getSelectedRow(); // select a row
                int column = target.getSelectedColumn();
                int teachId = Integer.valueOf(target.getValueAt(row, 0).toString());
                
                EditTeacherForm editTeacher = new EditTeacherForm(teachId);
				editTeacher.setVisible(true);
				editTeacher.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e){
						try {
							Connection connection = new DbConnection().getDbConnection();
				    		Statement teacherStatement = connection.createStatement();
				    	    String getTeacherQuery = "SELECT * FROM teachers WHERE id = " + teachId;
				    		
				    		ResultSet teacherResult = teacherStatement.executeQuery(getTeacherQuery);
				    	     
				    		while (teacherResult.next()) {
				    			int uptteachId = teacherResult.getInt("id");
								String firstName = teacherResult.getString("firstName");
								String lastName = teacherResult.getString("lastName");
								String email = teacherResult.getString("email");
    	        
				      	        target.setValueAt((Object)firstName, row, 1);
				      	        target.setValueAt((Object)lastName, row, 2);
				      	        target.setValueAt((Object)email, row, 3);
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
		
		JButton regButton = new JButton("Register Teachers"); 
        regButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		RegisterTeacherForm teacher = new RegisterTeacherForm();
        		teacher.setVisible(true);
        		
        		teacher.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent w){
						
				        DefaultTableModel model = (DefaultTableModel)table.getModel();
				        model.setRowCount(0);
				        
				        try {
				    		Connection connection = new DbConnection().getDbConnection();
				    		String query = "SELECT * FROM teachers";

				    	    // create the java statement
				    	    Statement teacherStatement = connection.createStatement();
				    	      
				    	    // execute the query, and get a java
				    	    ResultSet teacherResult = teacherStatement.executeQuery(query);
				    	 
				    		
				    	    // iterate through the java
				    	    while (teacherResult.next()){
				    	        int id = teacherResult.getInt("id");
								String firstName = teacherResult.getString("firstName");
								String lastName = teacherResult.getString("lastName");
								String email = teacherResult.getString("email");
								String [] teacher = {String.valueOf(id), firstName, lastName, email};
							
				    	        model.addRow(teacher);
				    	    }
				    	    
				    	    
				    	    teacherStatement.close();
				    	}catch(Exception exe) {
				    		System.out.println("here");
				    		exe.printStackTrace();
				    	}
				        
				   
					}
				});

        	}
        });
         
        JPanel y = new JPanel();
        y.add(regButton);
        
		        
        //Sort table section
        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        JTextField textField = new JTextField();
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(200);
        table.setRowSorter(sort);
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search teachers:"), BorderLayout.WEST);
        p.add(textField, BorderLayout.CENTER);
        p.add(y, BorderLayout.SOUTH);
        
        
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
