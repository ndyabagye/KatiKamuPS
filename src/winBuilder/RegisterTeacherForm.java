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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class RegisterTeacherForm extends JFrame {

	private JPanel contentPane;
	private JTextField fName;
	private JTextField lName;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField verifyPasswordField;
	
	private char[] password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTeacherForm frame = new RegisterTeacherForm();
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
	public RegisterTeacherForm() {
		setTitle("Register Teacher Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(60, 48, 69, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastName.setBounds(60, 94, 69, 15);
		contentPane.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(60, 144, 69, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(60, 183, 69, 15);
		contentPane.add(lblPassword);
		
		JLabel lblVerifyPassword = new JLabel("Verify Password");
		lblVerifyPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVerifyPassword.setBounds(60, 224, 89, 15);
		contentPane.add(lblVerifyPassword);
		
		fName = new JTextField();
		fName.setBounds(175, 48, 181, 30);
		contentPane.add(fName);
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(175, 86, 181, 30);
		contentPane.add(lName);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(175, 128, 181, 30);
		contentPane.add(emailField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 176, 181, 30);
		contentPane.add(passwordField);
		
		verifyPasswordField = new JPasswordField();
		verifyPasswordField.setBounds(175, 222, 181, 30);
		contentPane.add(verifyPasswordField);
		
		JButton Submit = new JButton("Submit");
		Submit.setForeground(Color.BLUE);
		Submit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Submit.setBounds(83, 267, 89, 23);
		Submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = fName.getText().toString();
				String lastName = lName.getText().toString();
				String email = emailField.getText().toString();
				
				// passwords do not match
				if(Arrays.equals(passwordField.getPassword(),verifyPasswordField.getPassword())) {
					password = passwordField.getPassword();
				}else {					
					JOptionPane.showMessageDialog(Submit, "Passwords do not match!!");
					return;
				};
				
				// any field is empty
				if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.length == 0 ) {
					JOptionPane.showMessageDialog(Submit, "All fields are required!");
				}else {
					try {
						Connection connection = new DbConnection().getDbConnection();
						String query = "INSERT INTO teachers(firstName, lastName, email, password)"
								+ " VALUES(?,?,?,?)";
						PreparedStatement sta = connection.prepareStatement(query);
						sta.setString(1, firstName);
						sta.setString(2, lastName);
						sta.setString(3, email);
						sta.setString(4, new String(password));
						
						
						int i = sta.executeUpdate();
						// if successful
						if( i == 1) {
							JOptionPane.showMessageDialog(Submit, "Teacher created successfully");
							fName.setText("");
							lName.setText("");
							emailField.setText("");
							passwordField.setText("");
							verifyPasswordField.setText("");
						}else {
							JOptionPane.showMessageDialog(Submit, "Teacher not created!");
							return;
						}
						
						connection.close();
					}catch (SQLIntegrityConstraintViolationException x) {
						// success message
						JOptionPane.showMessageDialog(Submit, "Teacher with email already exists");
					}
					catch(Exception exception) {
						
					}
				}
				
			}
		});
		contentPane.add(Submit);
		
		JButton Cancel = new JButton("Reset");
		Cancel.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  			// clear input fields
				fName.setText("");
				lName.setText("");
				emailField.setText("");
				passwordField.setText("");
				verifyPasswordField.setText("");
			}
  		});
  		
		Cancel.setForeground(Color.RED);
		Cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Cancel.setBounds(212, 267, 89, 23);
		contentPane.add(Cancel);
	}
}
