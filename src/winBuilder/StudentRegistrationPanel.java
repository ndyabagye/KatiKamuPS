package winBuilder;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StudentRegistrationPanel extends JPanel {

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
	
	/**
	 * Create the panel.
	 */
	public StudentRegistrationPanel() {
		setLayout(null);
		
		JLabel Heading = new JLabel("Student Registration Form");
		Heading.setBounds(152, 37, 161, 17);
		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(Heading);
		
		JLabel FirstName = new JLabel("First Name ");
		FirstName.setBounds(77, 107, 70, 20);
		FirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(FirstName);
		
		fName = new JTextField();
		fName.setBounds(159, 107, 86, 20);
		add(fName);
		fName.setColumns(10);
		
		JLabel LastName = new JLabel("Last Name ");
		LastName.setBounds(275, 107, 64, 20);
		LastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LastName);
		
		lName = new JTextField();
		lName.setBounds(349, 107, 86, 20);
		lName.setColumns(10);
		add(lName);
		
		regNo = new JTextField();
		regNo.setBounds(159, 133, 86, 20);
		regNo.setColumns(10);
		add(regNo);
		
		
		JLabel RegNumber = new JLabel("Reg No.");
		RegNumber.setBounds(77, 132, 70, 22);
		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(RegNumber);
		
		// select for the gender
		String[] genderArr = {"Select Gender","Male", "Female"};
		gender = new JComboBox(genderArr);
		gender.setBounds(342, 133, 93, 20);
		add(gender);
		
		// select for the class
		String[] classArr = {"Select a class", "P1","P2", "P3", "P4", "P5", "P6", "P7"};
		JLabel Gender = new JLabel("Gender");
		Gender.setBounds(275, 132, 41, 22);
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(Gender);
		
		age = new JTextField();
		age.setBounds(159, 159, 86, 20);
		age.setColumns(10);
		add(age);
		
		JLabel Age = new JLabel("Age");
		Age.setBounds(77, 159, 70, 20);
		Age.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(Age);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setBounds(227, 236, 86, 23);
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = fName.getText().toString();
				String lastName = lName.getText().toString();
				String regNum = regNo.getText().toString();
				String stuAge = age.getText().toString();
				
				if(gender.getSelectedIndex()== 0) {
					JOptionPane.showMessageDialog(submitBtn, "Select a proper gender");
					System.out.println("Select a proper gender");
					return;
				}else {					
					stuGender = gender.getSelectedItem().toString();
				}
				
				if(selectClass.getSelectedIndex()== 0) {
					JOptionPane.showMessageDialog(submitBtn, "Select a proper class");
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
						// success message
						JOptionPane.showMessageDialog(submitBtn, "Student created succesfully");
						System.out.println(i + "records inserted");
						connection.close();
					}catch(Exception exe) {
						exe.printStackTrace();
					}
				}
				System.out.format("%s,%s,%s,%s,%s,%s", firstName, lastName, regNum, stuAge, stuGender, stuClass);
				
			}
		});
		selectClass = new JComboBox(classArr);
		selectClass.setBounds(345, 159, 90, 20);
		add(selectClass);
		
		JLabel Class = new JLabel("Class");
		Class.setBounds(275, 159, 30, 20);
		Class.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(Class);
		submitBtn.setForeground(Color.BLUE);
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(submitBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(349, 236, 86, 23);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear input fields
				fName.setText("");
				lName.setText("");
				regNo.setText("");
				age.setText("");
				selectClass.setSelectedIndex(0);
				gender.setSelectedIndex(0);
			}
		});
		cancelBtn.setForeground(Color.RED);
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cancelBtn);
		
	}

}
