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

public class StudentTimetable extends JPanel {

	private JTextField classF;
	private JTextField mon;
	private JTextField tue;
	private JTextField wed;
	private JTextField thur;
	private JTextField fri;

	public StudentTimetable(int id){
		try {
    		Connection connection = new DbConnection().getDbConnection();
    		Statement studentStatement = connection.createStatement();
    	    String getStudentQuery = "SELECT * FROM students WHERE id = " + id;

    		ResultSet studentResult = studentStatement.executeQuery(getStudentQuery);

    		while (studentResult.next()) {
    			String studClass = studentResult.getString("stuClass");
    			String studName = studentResult.getString("firstName") +" "+ studentResult.getString("lastName");

    			Statement timetableStatement = connection.createStatement();

    			String getClassTimetableQuery = "SELECT * FROM timetable WHERE class = '" + studClass +"'";

        		ResultSet classTimetableResult = timetableStatement.executeQuery(getClassTimetableQuery);

        		if (!classTimetableResult.isBeforeFirst() ) {
        			JLabel Heading = new JLabel(studClass + " Time Table Not Yet Available");
    	      		Heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    	      		Heading.setForeground(Color.RED);
    	      		Heading.setBounds(152, 11, 184, 22);
    	      		add(Heading);
        		} else {
	        		while (classTimetableResult.next()) {
	        			String classC = classTimetableResult.getString("class");
	          	        String monC = classTimetableResult.getString("Monday");
	          	        String tueC = classTimetableResult.getString("Tuesday");
	          	        String wedC = classTimetableResult.getString("Wednesday");
	          	        String thurC = classTimetableResult.getString("Thursday");
	          	        String friC = classTimetableResult.getString("Friday");

	        			setBorder(new EmptyBorder(5, 5, 5, 5));
	        	        setLayout(null);

	    	      		JLabel Heading = new JLabel(studName + " Time Table");
	    	      		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    	      		Heading.setBounds(152, 11, 184, 22);
	    	      		add(Heading);

	    	      		JLabel Class = new JLabel("Class.");
	    	      		Class.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    	      		Class.setBounds(56, 81, 75, 22);
	    	      		add(Class);

	    	      		JLabel Monday = new JLabel("Mon ");
	    	      		Monday.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    	      		Monday.setBounds(260, 81, 75, 22);
	    	      		add(Monday);

	    	      		JLabel Tuesday = new JLabel("Tue");
	    	      		Tuesday.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    	      		Tuesday.setBounds(56, 122, 75, 22);
	    	      		add(Tuesday);

	    	      		JLabel Wednesday = new JLabel("Wed");
	    	      		Wednesday.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    	      		Wednesday.setBounds(56, 164, 75, 22);
	    	      		add(Wednesday);


	    	      		JLabel Thursday = new JLabel("Thur");
	    	      		Thursday.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    	      		Thursday.setBounds(260, 127, 75, 22);
	    	      		add(Thursday);

	    	      		JLabel Friday = new JLabel("Fri");
	    	      		Friday.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    	      		Friday.setBounds(260, 164, 75, 22);
	    	      		add(Friday);


	    	      		classF = new JTextField(classC);
	    	      		classF.setColumns(10);
	    	      		classF.setBounds(128, 83, 122, 20);
	    	      		classF.setDisabledTextColor(Color.BLACK);
	    	      		classF.setEnabled(false);
	    	      		add(classF);

	    	      		mon = new JTextField(monC);
	    	      		mon.setBounds(323, 83, 122, 20);
	    	      		mon.setEnabled(false);
	    	      		mon.setDisabledTextColor(Color.BLACK);
	    	      		add(mon);
	    	      		mon.setColumns(10);

	    	      		tue = new JTextField(tueC);
	    	      		tue.setColumns(10);
	    	      		tue.setBounds(128, 124, 122, 20);
	    	      		tue.setDisabledTextColor(Color.BLUE);
	    	      		tue.setEnabled(false);
	    	      		add(tue);

	    	      		wed = new JTextField(wedC);
	    	      		wed.setColumns(10);
	    	      		wed.setBounds(128, 166, 122, 20);
	    	      		wed.setEnabled(false);
	    	      		wed.setDisabledTextColor(Color.BLUE);
	    	      		add(wed);

	    	      		thur = new JTextField(thurC);
	    	      		thur.setColumns(10);
	    	      		thur.setBounds(323, 123, 122, 22);
	    	      		thur.setEnabled(false);
	    	      		thur.setDisabledTextColor(Color.BLUE);
	    	      		add(thur);

	    	      		fri = new JTextField(friC);
	    	      		fri.setColumns(10);
	    	      		fri.setBounds(323, 165, 122, 22);
	    	      		fri.setEnabled(false);
	    	      		fri.setDisabledTextColor(Color.BLUE);
	    	      		add(fri);
        			}
        		}
    		}
    		connection.close();
		}catch(Exception exe) {
    		exe.printStackTrace();
    	}

	}
}
