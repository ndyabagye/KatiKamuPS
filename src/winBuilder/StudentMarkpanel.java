package winBuilder;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StudentMarkpanel extends JPanel {
	
	private JTextField name;
	private JTextField regNo;
	private JTextField maths;
	private JTextField science;
	private JTextField sst;
	private JTextField english;

	public StudentMarkpanel(int id) {
		
		
		
		try {
    		Connection connection = new DbConnection().getDbConnection();
    		Statement studentStatement = connection.createStatement();
    	    String getStudentQuery = "SELECT * FROM students WHERE id = " + id;
    		
    		ResultSet studentResult = studentStatement.executeQuery(getStudentQuery);
    	     
    		while (studentResult.next()) {
    			int tstudId = studentResult.getInt("id");
    			String regNum = studentResult.getString("regNum");
    	        String studName = studentResult.getString("firstName") +" "+ studentResult.getString("lastName");
    	        String studMath = String.valueOf(studentResult.getInt("Maths"));
    	        String studSci = String.valueOf(studentResult.getInt("Science"));
    	        String studSST = String.valueOf(studentResult.getInt("SST"));
    	        String studEnglish = String.valueOf(studentResult.getInt("English"));
     	       	
    	        setBorder(new EmptyBorder(5, 5, 5, 5));
    	        setLayout(null);
    	        
	      		JLabel Heading = new JLabel(studName + " Marks");
	      		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
	      		Heading.setBounds(152, 11, 184, 22);
	      		add(Heading);
      		
	      		JLabel RegNumber = new JLabel("Reg No.");
	      		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		RegNumber.setBounds(56, 81, 75, 22);
	      		add(RegNumber);
	      		
	      		JLabel Name = new JLabel("Name ");
	      		Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		Name.setBounds(260, 81, 75, 22);
	      		add(Name);
	      		
	      		JLabel Maths = new JLabel("Maths");
	      		Maths.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		Maths.setBounds(56, 122, 75, 22);
	      		add(Maths);
	      		
	      		JLabel Science = new JLabel("Science");
	      		Science.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		Science.setBounds(56, 164, 75, 22);
	      		add(Science);
	      		
	      		
	      		JLabel SST = new JLabel("SST");
	      		SST.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		SST.setBounds(260, 127, 75, 22);
	      		add(SST);
	      		
	      		JLabel English = new JLabel("English");
	      		English.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		English.setBounds(260, 164, 75, 22);
	      		add(English);
	      		
	      		
	      		regNo = new JTextField(regNum);
	      		regNo.setColumns(10);
	      		regNo.setBounds(128, 83, 122, 20);
	      		regNo.setDisabledTextColor(Color.BLACK);
	      		regNo.setEnabled(false);
	      		add(regNo);
	      		
	      		name = new JTextField(studName);
	      		name.setBounds(323, 83, 122, 20);
	      		name.setEnabled(false);
	      		name.setDisabledTextColor(Color.BLACK);
	      		add(name);
	      		name.setColumns(10);
	      		
	      		maths = new JTextField(studMath);
	      		maths.setColumns(10);
	      		maths.setBounds(128, 124, 122, 20);
	      		if(Integer.parseInt(studMath) >= 70 ) {
	      			maths.setDisabledTextColor(Color.BLUE);
	      		} else if(Integer.parseInt(studMath) >= 50) {
	      			maths.setDisabledTextColor(Color.BLACK);
	      		} else {
	      			maths.setDisabledTextColor(Color.RED);	
	      		}
	      		maths.setEnabled(false);
	      		add(maths);
	      		
	      		science = new JTextField(studSci);
	      		science.setColumns(10);
	      		science.setBounds(128, 166, 122, 20);
	      		science.setEnabled(false);
	      		if(Integer.parseInt(studSci) >= 70 ) {
	      			science.setDisabledTextColor(Color.BLUE);
	      		} else if(Integer.parseInt(studSci) >= 50) {
	      			science.setDisabledTextColor(Color.BLACK);
	      		} else {
	      			science.setDisabledTextColor(Color.RED);	
	      		}
	      		add(science);
	      		
	      		sst = new JTextField(studSST);
	      		sst.setColumns(10);
	      		sst.setBounds(323, 123, 122, 22);
	      		sst.setEnabled(false);
	      		if(Integer.parseInt(studSST) >= 70 ) {
	      			sst.setDisabledTextColor(Color.BLUE);
	      		} else if(Integer.parseInt(studSST) >= 50) {
	      			sst.setDisabledTextColor(Color.BLACK);
	      		} else {
	      			sst.setDisabledTextColor(Color.RED);	
	      		}
	      		
	      		add(sst);
	      		
	      		english = new JTextField(studEnglish);
	      		english.setColumns(10);
	      		english.setBounds(323, 165, 122, 22);
	      		english.setEnabled(false);
	      		if(Integer.parseInt(studEnglish) >= 70 ) {
	      			english.setDisabledTextColor(Color.BLUE);
	      		} else if(Integer.parseInt(studEnglish) >= 50) {
	      			english.setDisabledTextColor(Color.BLACK);
	      		} else {
	      			english.setDisabledTextColor(Color.RED);	
	      		}
	      		add(english);
    		}
    		studentStatement.close();
		}catch(Exception exe) {
    		System.out.println("here");
    		exe.printStackTrace();
    	}
	}

}
