package winBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		JLabel StudentName = new JLabel("First Name ");
		StudentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		StudentName.setBounds(57, 64, 75, 22);
		contentPane.add(StudentName);
		
		JLabel RegNumber = new JLabel("Reg No.");
		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		RegNumber.setBounds(57, 105, 75, 22);
		contentPane.add(RegNumber);
		
		JLabel Age = new JLabel("Age");
		Age.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Age.setBounds(57, 147, 75, 22);
		contentPane.add(Age);
		
		JLabel LastName = new JLabel("Last Name ");
		LastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LastName.setBounds(261, 64, 75, 22);
		contentPane.add(LastName);
		
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
		
		JComboBox gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Male", "Female"}));
		gender.setBounds(324, 106, 122, 22);
		contentPane.add(gender);
		
		JComboBox subject = new JComboBox();
		subject.setModel(new DefaultComboBoxModel(new String[] {"Select Subject", "Maths", "English", "SST", "Science"}));
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
				String stuGender = gender.getSelectedItem().toString();
				String stuSubject = subject.getSelectedItem().toString();
				String stuClass = studentClass.getText().toString();	
				
				System.out.format("%s,%s,%s,%s,%s,%s,%s", firstName, lastName, regNum, stuAge, stuGender, stuSubject, stuClass);
				
			}
		});
		submitBtn.setForeground(Color.BLUE);
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		submitBtn.setBounds(129, 239, 89, 23);
		contentPane.add(submitBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setForeground(Color.RED);
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancelBtn.setBounds(261, 240, 89, 23);
		contentPane.add(cancelBtn);
	}
}
