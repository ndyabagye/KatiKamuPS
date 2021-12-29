package winBuilder;

<<<<<<< HEAD

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

=======
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;
>>>>>>> 3bb2250... not much
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
	 
	 
	// data that will dynamically change		
	String[][] data = {};
	ArrayList<String[]> studentList = new ArrayList<String[]>(Arrays.asList(data));
	
	/**
	 * Create the panel.
	 */
	public MarksPanel() {
<<<<<<< HEAD
		setLayout(new GridLayout(0, 1, 0, 0));
=======
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{22, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
	
		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.insets = new Insets(0, 0, 5, 0);
		gbc_menuBar.fill = GridBagConstraints.BOTH;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		add(menuBar, gbc_menuBar);
		
		JButton btnNewButton = new JButton("English");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data  = fetchData("English");
			}
		});
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mathematics");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data = fetchData("Maths");
			}
		});
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Science");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data = fetchData("Science");
			}
		});
		menuBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("SST");
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data = fetchData("SST");
				System.out.println(Arrays.deepToString(data));
			}
		});
		menuBar.add(btnNewButton_3);
		
>>>>>>> 3bb2250... not much
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
		
		System.out.println(Arrays.deepToString(data));
		table.setModel(new DefaultTableModel(
<<<<<<< HEAD
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
        
        JButton mathsButton = new JButton("Math Marks"); 
        mathsButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		SubjectMarks subjectMarks = new SubjectMarks("Maths");
        		subjectMarks.setVisible(true);
        	}
        });
        
        JButton sciButton = new JButton("Science Marks"); 
        sciButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		SubjectMarks subjectMarks = new SubjectMarks("Science");
        		subjectMarks.setVisible(true);
        	}
        });
        
        JButton sstButton = new JButton("Sst Marks");
        sstButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		SubjectMarks subjectMarks = new SubjectMarks("SST");
        		subjectMarks.setVisible(true);
        	}
        });
        
        JButton engButton = new JButton("English Marks"); 
        engButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		SubjectMarks subjectMarks = new SubjectMarks("English");
        		subjectMarks.setVisible(true);
        	}
        });
         
        JPanel y = new JPanel();
        y.add(mathsButton);
        y.add(sciButton);
        y.add(sstButton);
        y.add(engButton);
        
        
        
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search marks table:"), BorderLayout.WEST);
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
=======
				data,
			new String[] {
				"Reg No.", "First Name", "Last Name", "Marks"
			}
		));
		scrollPane.setViewportView(table);
	}
	


	private String[][] fetchData(String subj) {
		try {
			Connection connection  = new DbConnection().getDbConnection();
			String query = "SELECT * FROM students WHERE "+ subj +" IS NOT NULL";
			
			// create the java statement
			Statement statement = connection.createStatement();
			
			// execute query and get a result set
			ResultSet rs = statement.executeQuery(query);
			
			// clear the array list
			studentList.clear();
			
			// iterate through result set
			while (rs.next()) {
				String regNum = rs.getString("regNum");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String mark = rs.getString(subj);
				String [] student = {regNum, firstName, lastName, mark};
				// add data to the array list
				studentList.add(student);
			}
			data = studentList.toArray(data);
			
//			JScrollPane scrollPane = new JScrollPane();
//			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
//			gbc_scrollPane.fill = GridBagConstraints.BOTH;
//			gbc_scrollPane.gridx = 0;
//			gbc_scrollPane.gridy = 1;
//			add(scrollPane, gbc_scrollPane);
//			
//			table = new JTable();
//			
//			table.setModel(new DefaultTableModel(
//					data,
//				new String[] {
//					"Reg No.", "First Name", "Last Name", "Marks"
//				}
//			));
//			scrollPane.setViewportView(table);
			connection.close();
		}catch(Exception exception) {
			exception.printStackTrace();
		};
				
		return data;
>>>>>>> 3bb2250... not much
	}
	
}
