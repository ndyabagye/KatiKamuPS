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

public class TimeTablePanel extends JPanel {
	private JTable table;
		
	public TimeTablePanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		String[] columns = new String[] {"#","class", "Mon", "Tue", "Wed","Thur","Fri"};
        String[][] data = {};
        ArrayList<String[]> timetableList = new ArrayList<String[]>(Arrays.asList(data));  
        
        try {
    		Connection connection = new DbConnection().getDbConnection();
    		String query = "SELECT * FROM timetable";

    	    // create the java statement
    	    Statement timetableStatement = connection.createStatement();
    	      
    	    // execute the query, and get a java
    	    ResultSet timetableResult = timetableStatement.executeQuery(query);
    	 
    		
    	    // iterate through the java
    	    while (timetableResult.next()){
    	        int id = timetableResult.getInt("id");
    	        String tclass = timetableResult.getString("class");
				String mon = timetableResult.getString("Monday");
				String tue = timetableResult.getString("Tuesday");
				String wed = timetableResult.getString("Wednesday");
				String thur = timetableResult.getString("Thursday");
				String fri = timetableResult.getString("Friday");
				      	        
    	        String [] timetable = {String.valueOf(id), tclass,mon, tue, wed, thur, fri};
    	        timetableList.add(timetable);
    	    }
    	    
    	    data = timetableList.toArray(data);
    	
    	    timetableStatement.close();
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
                int timeId = Integer.valueOf(target.getValueAt(row, 0).toString());
                
                EditTimetableForm editTimetable = new EditTimetableForm(timeId);
				editTimetable.setVisible(true);
				editTimetable.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e){
						try {
							Connection connection = new DbConnection().getDbConnection();
				    		Statement timetableStatement = connection.createStatement();
				    	    String getTimetableQuery = "SELECT * FROM timetable WHERE id = " + timeId;
				    		
				    		ResultSet timetableResult = timetableStatement.executeQuery(getTimetableQuery);
				    	     
				    		while (timetableResult.next()) {
				    			int uptimeId = timetableResult.getInt("id");
				      	        String upclass = timetableResult.getString("class");
				      	        String upMon = timetableResult.getString("Monday");
								String upTue = timetableResult.getString("Tuesday");
								String upWed = timetableResult.getString("Wednesday");
								String upThur = timetableResult.getString("Thursday");
								String upFri = timetableResult.getString("Friday");
				      	        
				      	        target.setValueAt((Object)upclass, row, 1);
				      	        target.setValueAt((Object)upMon, row, 2);
				      	        target.setValueAt((Object)upTue, row, 3);
				      	        target.setValueAt((Object)upWed, row, 4);
				      	        target.setValueAt((Object)upThur, row, 5);
								target.setValueAt((Object)upFri, row, 6);
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
		
		JButton regButton = new JButton("Add Timetable"); 
        regButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		RegisterTimetableForm timetable = new RegisterTimetableForm();
        		timetable.setVisible(true);
        		
        		timetable.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent w){
						
				        DefaultTableModel model = (DefaultTableModel)table.getModel();
				        model.setRowCount(0);
				        
				        try {
				    		Connection connection = new DbConnection().getDbConnection();
				    		String query = "SELECT * FROM timetable";
							
							// create the java statement
							Statement timetableStatement = connection.createStatement();
							
							// execute the query, and get a java
							ResultSet timetableResult = timetableStatement.executeQuery(query);
						
							
							// iterate through the java
							while (timetableResult.next()){
								int id = timetableResult.getInt("id");
								String tclass = timetableResult.getString("class");
								String mon = timetableResult.getString("Monday");
								String tue = timetableResult.getString("Tuesday");
								String wed = timetableResult.getString("Wednesday");
								String thur = timetableResult.getString("Thursday");
								String fri = timetableResult.getString("Friday");
												
								String [] timetable = {String.valueOf(id), tclass,mon, tue, wed, thur, fri};
    	
				    	        model.addRow(timetable);
					    	    
				    	    }
				    	    
				    	    timetableStatement.close();
				    	}catch(Exception exe) {
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
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(50);
        table.setRowSorter(sort);
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search timetables:"), BorderLayout.WEST);
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
