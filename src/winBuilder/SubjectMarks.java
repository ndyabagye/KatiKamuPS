package winBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
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


public class SubjectMarks extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectMarks frame = new SubjectMarks("Maths");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SubjectMarks(String subject) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		setLayout(new GridLayout(0, 1, 0, 0));
		setTitle(subject +" Marks");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		String[] columns = new String[] {"Reg No.", "Name", "Class", subject + " Mark", };
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
    	        String studClass = studentResult.getString("stuClass");
    	        String studSubject = studentResult.getString(subject);
    	        String [] student = {regNum,studName,studClass, studSubject};
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

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(200);

		scrollPane.setViewportView(table);



        //Sort table section
        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        JTextField textField = new JTextField();
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
