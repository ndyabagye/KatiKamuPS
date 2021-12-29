package winBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class EditStudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField fName;
	private JTextField lName;
	private JTextField regNo;
	private JTextField age;
	private JComboBox gender;
	private JComboBox selectClass;
	
	// for the control statements
	private String stuClass;
	private String stuGender;
	private int index;
		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudentForm frame = new EditStudentForm(2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditStudentForm(int id) {
		
		try {
    		Connection connection = new DbConnection().getDbConnection();
    		Statement studentStatement = connection.createStatement();
    	    
    		ResultSet studentResult = studentStatement.executeQuery("SELECT * FROM students WHERE id = " + id);
    	     
    		while (studentResult.next()) {
    			int tstudId = studentResult.getInt("id");
      	        String regNum = studentResult.getString("regNum");
      	        String firstName = studentResult.getString("firstName");
      	        String lastName = studentResult.getString("lastName");
      	        String studAge = studentResult.getString("stuAge");
      	        String studClass = studentResult.getString("stuClass");
      	        String studGender = studentResult.getString("stuGender");
      	       
      	      setTitle("Edit Student Form");
      		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		setBounds(100, 100, 500, 325);
      		contentPane = new JPanel();
      		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      		setContentPane(contentPane);
      		contentPane.setLayout(null);
      		
      		JLabel Heading = new JLabel("Student Edit Form");
      		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
      		Heading.setBounds(152, 11, 184, 22);
      		contentPane.add(Heading);
      		
      		JLabel FirstName = new JLabel("First Name ");
      		FirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
      		FirstName.setBounds(56, 81, 75, 22);
      		contentPane.add(FirstName);
      		
      		JLabel LastName = new JLabel("Last Name ");
      		LastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
      		LastName.setBounds(260, 81, 75, 22);
      		contentPane.add(LastName);
      		
      		JLabel RegNumber = new JLabel("Reg No.");
      		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
      		RegNumber.setBounds(56, 122, 75, 22);
      		contentPane.add(RegNumber);
      		
      		JLabel Age = new JLabel("Age");
      		Age.setFont(new Font("Tahoma", Font.PLAIN, 13));
      		Age.setBounds(56, 164, 75, 22);
      		contentPane.add(Age);
      		
      		
      		JLabel Gender = new JLabel("Gender");
      		Gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
      		Gender.setBounds(260, 127, 75, 22);
      		contentPane.add(Gender);
      		
      		JLabel Class = new JLabel("Class");
      		Class.setFont(new Font("Tahoma", Font.PLAIN, 13));
      		Class.setBounds(260, 164, 75, 22);
      		contentPane.add(Class);
      		
      		fName = new JTextField(firstName);
      		fName.setBounds(128, 83, 122, 20);
      		contentPane.add(fName);
      		fName.setColumns(10);
      		
      		lName = new JTextField(lastName);
      		lName.setColumns(10);
      		lName.setBounds(323, 83, 122, 20);
      		contentPane.add(lName);
      		
      		regNo = new JTextField(regNum);
      		regNo.setColumns(10);
      		regNo.setBounds(128, 124, 122, 20);
      		contentPane.add(regNo);
      		
      		age = new JTextField(studAge);
      		age.setColumns(10);
      		age.setBounds(128, 166, 122, 20);
      		contentPane.add(age);
      		
      		
      		
      		// select for the gender
      		String[] genderArr = {"Select Gender","Male", "Female"};
      		ArrayList<String> genderList = new ArrayList<String>(Arrays.asList(genderArr));
      		index = genderList.indexOf(studGender);
      		gender = new JComboBox(genderArr);
      		gender.setBounds(323, 123, 122, 22);
      		gender.setSelectedIndex(Integer.valueOf(index));
      		contentPane.add(gender);
      		
      		
      		// select for the class
      		String[] classArr = {"Select a class", "P1","P2", "P3", "P4", "P5", "P6", "P7"};
      		ArrayList<String> classList = new ArrayList<String>(Arrays.asList(genderArr));
      		index = genderList.indexOf(studClass);
      		selectClass = new JComboBox(classArr);
      		selectClass.setBounds(323, 165, 122, 22);
      		selectClass.setSelectedIndex(Integer.valueOf(index));
      		contentPane.add(selectClass);
      		
      		JButton updateBtn = new JButton("Update");
      		updateBtn.addActionListener(new ActionListener() {
      			@Override
      			public void actionPerformed(ActionEvent e) {
      				String firstName = fName.getText().toString();
      				String lastName = lName.getText().toString();
      				String regNum = regNo.getText().toString();
      				String stuAge = age.getText().toString();
      				
      				if(gender.getSelectedIndex()== 0) {
      					JOptionPane.showMessageDialog(updateBtn, "Select a proper gender");
      					System.out.println("Select a proper gender");
      					return;
      				}else {					
      					stuGender = gender.getSelectedItem().toString();
      				}
      				
      				if(selectClass.getSelectedIndex()== 0) {
      					JOptionPane.showMessageDialog(updateBtn, "Select a proper class");
      					System.out.println("Select a proper class");
      					return;
      				}else {					
      					stuClass = selectClass.getSelectedItem().toString();
      				}
      				
      				if(gender.getSelectedIndex()!=0 || selectClass.getSelectedIndex()!=0) {
      					try {
      						Connection connection = new DbConnection().getDbConnection();
      						String query = "INSERT INTO students(firstName, lastName, stuAge, regNum, stuGender, stuClass)"
      								+ " VALUES(?,?,?,?,?,?)";
      						PreparedStatement sta = connection.prepareStatement(query);
      						sta.setString(1, firstName);
      						sta.setString(2, lastName);
      						sta.setString(3, stuAge);
      						sta.setString(4, regNum);
      						sta.setString(5, stuGender);
      						sta.setString(6, stuClass);
      						
      						
      						int i = sta.executeUpdate();
      						System.out.println(i + "records inserted");
      						connection.close();
      					}catch(Exception exe) {
      						System.out.println("here");
      						exe.printStackTrace();
      					}
      				}
      				System.out.format("%s,%s,%s,%s,%s,%s", firstName, lastName, regNum, stuAge, stuGender, stuClass);
      				
      			}
      		});
      		updateBtn.setForeground(Color.BLUE);
      		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      		updateBtn.setBounds(129, 239, 89, 23);
      		contentPane.add(updateBtn);
      		
      		JButton cancelBtn = new JButton("Cancel");
      		cancelBtn.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				// clear input fields
      				fName.setText(firstName);
      				lName.setText(lastName);
      				regNo.setText(regNum);
      				age.setText(studAge);
      				
      				index = genderList.indexOf(studGender);
      				gender.setSelectedIndex(Integer.valueOf(index));
      				
      				index = classList.indexOf(studClass);
      				selectClass.setSelectedIndex(Integer.valueOf(index));
      				
      			}
      		});
      		cancelBtn.setForeground(Color.RED);
      		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
      		cancelBtn.setBounds(261, 240, 89, 23);
      		contentPane.add(cancelBtn);
      			

    			  
    		}
    	    
    	    studentStatement.close();
    	}catch(Exception exe) {
    		System.out.println("here");
    		exe.printStackTrace();
    	}
		
		
		
		
		
			}
}
