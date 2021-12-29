package winBuilder;



import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class ViewRegisteredStudents extends JPanel 
{
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
               JFrame f = new JFrame("Search in JTable");
               f.add(new ViewRegisteredStudents());
               f.setSize(500, 180);
               f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               f.setLocationRelativeTo(null);
               f.setVisible(true);
            }
        });
    }
 
    public ViewRegisteredStudents() 
    {	
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

        //create a JTable with data
        JTable table = new JTable(data, columns);
        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        JTextField textField = new JTextField();

        //set the width of the 3rd column to 200 pixels
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setPreferredWidth(200);
    
        table.setRowSorter(sort);

        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Search for a word:"), BorderLayout.WEST);
        p.add(textField, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(p, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        textField.getDocument().addDocumentListener(new DocumentListener()
    {
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