package winBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField inputEmail;
	private JTextField inputPassword;

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
		
		inputPassword = new JTextField();
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
		contentPane.add(Submit);
	}
}
