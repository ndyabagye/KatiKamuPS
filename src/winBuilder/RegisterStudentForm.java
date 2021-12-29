package winBuilder;

import java.awt.BorderLayout;
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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;

public class RegisterStudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField fName;
	private JTextField lName;
	private JTextField regNo;
	private JTextField age;
	private JTextField studentClass;
	private JComboBox gender;
	private JComboBox subject;
	
	// for the control statements
	private String stuSubject;
	private String stuGender;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStudentForm frame = new RegisterStudentForm();
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
	public RegisterStudentForm() {
		setTitle("Register Student Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Heading = new JLabel("Student Registration Form");
		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Heading.setBounds(152, 11, 184, 22);
		contentPane.add(Heading);
		
		JLabel FirstName = new JLabel("First Name ");
		FirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		FirstName.setBounds(57, 64, 75, 22);
		contentPane.add(FirstName);
		
		JLabel LastName = new JLabel("Last Name ");
		LastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LastName.setBounds(261, 64, 75, 22);
		contentPane.add(LastName);
		
		JLabel RegNumber = new JLabel("Reg No.");
		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		RegNumber.setBounds(57, 105, 75, 22);
		contentPane.add(RegNumber);
		
		JLabel Age = new JLabel("Age");
		Age.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Age.setBounds(57, 147, 75, 22);
		contentPane.add(Age);
		
		
		JLabel Gender = new JLabel("Gender");
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Gender.setBounds(261, 110, 75, 22);
		contentPane.add(Gender);
		
		JLabel Subject = new JLabel("Subject");
		Subject.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Subject.setBounds(261, 147, 75, 22);
		contentPane.add(Subject);
		
		JLabel Class = new JLabel("Class");
		Class.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Class.setBounds(57, 194, 75, 22);
		contentPane.add(Class);
		
		fName = new JTextField();
		fName.setBounds(129, 66, 122, 20);
		contentPane.add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(324, 66, 122, 20);
		contentPane.add(lName);
		
		regNo = new JTextField();
		regNo.setColumns(10);
		regNo.setBounds(129, 107, 122, 20);
		contentPane.add(regNo);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(129, 149, 122, 20);
		contentPane.add(age);
		
		studentClass = new JTextField();
		studentClass.setColumns(10);
		studentClass.setBounds(129, 196, 122, 20);
		contentPane.add(studentClass);
		
		String[] genderArr = {"Select Gender","Male", "Female"};
		gender = new JComboBox(genderArr);
		gender.setBounds(324, 106, 122, 22);
		contentPane.add(gender);
		
		String[] subjectArr = {"Select Subject", "Maths", "English", "SST", "Science"};
		subject = new JComboBox(subjectArr);
		subject.setBounds(324, 148, 122, 22);
		contentPane.add(subject);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = fName.getText().toString();
				String lastName = lName.getText().toString();
				String regNum = regNo.getText().toString();
				String stuAge = age.getText().toString();
				String stuClass = studentClass.getText().toString();	
				
				if(gender.getSelectedIndex()== 0) {
					JOptionPane.showMessageDialog(submitBtn, "Select a proper gender");
					System.out.println("Select a proper gender");
					return;
				}else {					
					stuGender = gender.getSelectedItem().toString();
				}
				
				if(subject.getSelectedIndex()== 0) {
					JOptionPane.showMessageDialog(submitBtn, "Select a proper subject");
					System.out.println("Select a proper subject");
					return;
				}else {					
					stuSubject = subject.getSelectedItem().toString();
				}
				
				if(gender.getSelectedIndex()!=0 || subject.getSelectedIndex()!=0) {
					try {
						Connection connection = new DbConnection().getDbConnection();
						String query = "INSERT INTO students(firstName, lastName, stuAge, regNum, stuGender, stuSubject, stuClass)"
								+ " VALUES(?,?,?,?,?,?,?)";
						PreparedStatement sta = connection.prepareStatement(query);
						sta.setString(1, firstName);
						sta.setString(2, lastName);
						sta.setString(3, stuAge);
						sta.setString(4, regNum);
						sta.setString(5, stuGender);
						sta.setString(6, stuSubject);
						sta.setString(7, stuClass);
						
						
						int i = sta.executeUpdate();
						System.out.println(i + "records inserted");
						connection.close();
					}catch(Exception exe) {
						System.out.println("here");
						exe.printStackTrace();
					}
				}
				System.out.format("%s,%s,%s,%s,%s,%s,%s", firstName, lastName, regNum, stuAge, stuGender, stuSubject, stuClass);
				
			}
		});
		submitBtn.setForeground(Color.BLUE);
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		submitBtn.setBounds(129, 239, 89, 23);
		contentPane.add(submitBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear input fields
				fName.setText("");
				lName.setText("");
				regNo.setText("");
				age.setText("");
				studentClass.setText("");
				gender.setSelectedIndex(0);
				subject.setSelectedIndex(0);
			}
		});
		cancelBtn.setForeground(Color.RED);
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancelBtn.setBounds(261, 240, 89, 23);
		contentPane.add(cancelBtn);
	}
}
