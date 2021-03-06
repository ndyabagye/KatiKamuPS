package winBuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class EditMarksForm extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField regNo;
	private JTextField maths;
	private JTextField science;
	private JTextField sst;
	private JTextField english;

	
	private String regNum;
	private String studName;
	private String studMath; 
    private String studSci; 
    private String studSST; 
    private String studEnglish;
    private boolean okay;
	
	public EditMarksForm(int id) {
		try {
    		Connection connection = new DbConnection().getDbConnection();
    		Statement studentStatement = connection.createStatement();
    	    String getStudentQuery = "SELECT * FROM students WHERE id = " + id;

    		ResultSet studentResult = studentStatement.executeQuery(getStudentQuery);

    		while (studentResult.next()) {
    			int tstudId = studentResult.getInt("id");
    			regNum = studentResult.getString("regNum");
    	        studName = studentResult.getString("firstName") +" "+ studentResult.getString("lastName");
    	        studMath = String.valueOf(studentResult.getInt("Maths"));
    	        studSci = String.valueOf(studentResult.getInt("Science"));
    	        studSST = String.valueOf(studentResult.getInt("SST"));
    	        studEnglish = String.valueOf(studentResult.getInt("English"));

      	      	setTitle("Edit Marks Form");
      	      	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      	      	setBounds(100, 100, 500, 325);
	      		contentPane = new JPanel();
	      		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      		setContentPane(contentPane);
	      		contentPane.setLayout(null);

	      		JLabel Heading = new JLabel("Edit " + studName + " Marks");
	      		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
	      		Heading.setBounds(152, 11, 184, 22);
	      		contentPane.add(Heading);

	      		JLabel RegNumber = new JLabel("Reg No.");
	      		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		RegNumber.setBounds(56, 81, 75, 22);
	      		contentPane.add(RegNumber);

	      		JLabel Name = new JLabel("Name ");
	      		Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		Name.setBounds(260, 81, 75, 22);
	      		contentPane.add(Name);

	      		JLabel Maths = new JLabel("Maths");
	      		Maths.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		Maths.setBounds(56, 122, 75, 22);
	      		contentPane.add(Maths);

	      		JLabel Science = new JLabel("Science");
	      		Science.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		Science.setBounds(56, 164, 75, 22);
	      		contentPane.add(Science);


	      		JLabel SST = new JLabel("SST");
	      		SST.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		SST.setBounds(260, 127, 75, 22);
	      		contentPane.add(SST);

	      		JLabel English = new JLabel("English");
	      		English.setFont(new Font("Tahoma", Font.PLAIN, 13));
	      		English.setBounds(260, 164, 75, 22);
	      		contentPane.add(English);

	      		regNo = new JTextField(regNum);
	      		regNo.setColumns(10);
	      		regNo.setBounds(128, 83, 122, 20);
	      		regNo.setEnabled(false);
	      		regNo.setDisabledTextColor(Color.BLACK);
	      		contentPane.add(regNo);

	      		name = new JTextField(studName);
	      		name.setBounds(323, 83, 122, 20);
	      		name.setEnabled(false);
	      		contentPane.add(name);
	      		name.setDisabledTextColor(Color.BLACK);
	      		name.setColumns(10);

	      		maths = new JTextField(studMath);
	      		maths.setColumns(10);
	      		maths.setBounds(128, 124, 122, 20);
	      		contentPane.add(maths);

	      		science = new JTextField(studSci);
	      		science.setColumns(10);
	      		science.setBounds(128, 166, 122, 20);
	      		contentPane.add(science);

	      		sst = new JTextField(studSST);
	      		sst.setColumns(10);
	      		sst.setBounds(323, 123, 122, 22);
	      		contentPane.add(sst);

	      		english = new JTextField(studEnglish);
	      		english.setColumns(10);
	      		english.setBounds(323, 165, 122, 22);
	      		contentPane.add(english);



		  		JButton updateBtn = new JButton("Update");
		  		updateBtn.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				String upmaths = maths.getText().toString();
		  				String upscience = science.getText().toString();
		  				String upsst = sst.getText().toString();
		  				String upenglish = english.getText().toString();
		  				okay = true;
		  				try {
		  					
			  				if(Integer.valueOf(upmaths) < 0 
			  						|| Integer.valueOf(upscience) < 0 
			  						|| Integer.valueOf(upsst) < 0 
			  						|| Integer.valueOf(upenglish) < 0
			  						|| Integer.valueOf(upmaths) > 100
			  						|| Integer.valueOf(upscience) > 100
			  						|| Integer.valueOf(upsst) > 100 
			  						|| Integer.valueOf(upenglish) > 100
			  						
			  						) {
			  					okay = false;
								JOptionPane.showMessageDialog(updateBtn, "Marks must be between 0 and 100");
								return;
							}
	
		  				
		  				
			  				if(okay == true) {
		  						Connection connection = new DbConnection().getDbConnection();
		  			    		Statement studentStatement = connection.createStatement();
		  			    	    String getStudentQuery = "SELECT * FROM students WHERE id = " + id;
	
		  						String updateStudents = "UPDATE students SET "
		  								+ "Maths = '"+ upmaths 	+ "', "
		  								+ "Science = '"+ upscience  + "', "
		  								+ "SST = '"+ upsst    + "', "
		  								+ "English = '"+ upenglish
		  								+ "' WHERE ID = "+ id ;
	
		  						studentStatement.executeUpdate(updateStudents);
	
			      	    		ResultSet updateResult = studentStatement.executeQuery(getStudentQuery);
	
			      	    		while (updateResult.next()) {
			      	    			
			      	    			studMath = upmaths;
			      	    			studSci = upscience;
			      	    			studSST = upsst;
			      	    			studEnglish = upenglish;
			      	    					
			      	    			maths.setText(upmaths);
			          				science.setText(upscience);
			          				sst.setText(upsst);
			          				english.setText(upenglish);
	
			          				JOptionPane.showMessageDialog(updateBtn, "Marks updated succesfully");
			      	    		}
		      			        connection.close();
			  				}
		  				}catch (NumberFormatException n) {
		  					JOptionPane.showMessageDialog(updateBtn, "Marks must be numbers ");
		  				}
		  				catch(Exception exe) {
	  						exe.printStackTrace();
	  					}
  				}
		  		});
		  		updateBtn.setForeground(Color.BLUE);
		  		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  		updateBtn.setBounds(129, 239, 89, 23);
		  		contentPane.add(updateBtn);

      		JButton cancelBtn = new JButton("Cancel");
      		cancelBtn.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				maths.setText(studMath);
      				science.setText(studSci);
      				sst.setText(studSST);
      				english.setText(studEnglish);
      			}
      		});
      		cancelBtn.setForeground(Color.RED);
      		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      		cancelBtn.setBounds(261, 240, 89, 23);
      		contentPane.add(cancelBtn);
    		}
    	    studentStatement.close();
    	}catch(Exception exe) {
    		exe.printStackTrace();
    	}

	}

}
