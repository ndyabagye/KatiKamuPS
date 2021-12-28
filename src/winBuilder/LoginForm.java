package winBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class LoginForm extends JFrame {


	private JPanel contentPane;
	private JTextField inputEmail;
	private JPasswordField inputPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setTitle("KTPS Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Email.setBounds(70, 87, 48, 14);
		contentPane.add(Email);

		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Password.setBounds(70, 138, 66, 14);
		contentPane.add(Password);

		JLabel Heading = new JLabel("KTPS Login");
		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Heading.setBounds(162, 11, 133, 22);
		contentPane.add(Heading);

		inputEmail = new JTextField();
		inputEmail.setBounds(162, 82, 160, 27);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);

		inputPassword = new JPasswordField();
		inputPassword.setColumns(10);
		inputPassword.setBounds(162, 133, 160, 27);
		contentPane.add(inputPassword);

		JButton Cancel = new JButton("Cancel");
		Cancel.setForeground(new Color(255, 0, 0));
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Cancel.setBounds(100, 198, 89, 23);
		contentPane.add(Cancel);

		JButton Submit = new JButton("Login");
		Submit.setForeground(new Color(0, 0, 255));
		Submit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Submit.setBounds(233, 198, 89, 23);
		Submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = inputEmail.getText().toString();
				
				
				// until we figure out a more secure way
				// of getting passwords from db
				@SuppressWarnings("deprecation")
				String password = inputPassword.getText();
				
				System.out.println(email+ " "+ password);
				try {
					Connection connection = new DbConnection().getDbConnection();
					Statement stm = connection.createStatement();
					String query = "SELECT * FROM teachers WHERE email='"+ email +"' and password='"+ password+"'";	
					ResultSet rs = stm.executeQuery(query);
					
					if(rs.next()) {
						// email and password are true
						// close login form 
						dispose();
						// see student details
						ViewRegisteredStudents view = new ViewRegisteredStudents();
						view.setVisible(true);
					}else{
						// email or password is false
						JOptionPane.showMessageDialog(Submit, "Incorrect credentials");	
//						inputEmail.setText("");
//						inputPassword.setText("");	
					}
				}catch(Exception exception) {
					System.out.println(exception.getMessage());
				}
				
			}
		});
		contentPane.add(Submit);
	}
}
