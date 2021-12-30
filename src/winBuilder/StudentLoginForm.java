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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class StudentLoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField fName;
	private JTextField regNo;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLoginForm frame = new StudentLoginForm();
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
	public StudentLoginForm() {
		setTitle("Student Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(88, 70, 81, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblRegNo = new JLabel("Reg No.");
		lblRegNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRegNo.setBounds(88, 115, 81, 21);
		contentPane.add(lblRegNo);
		
		fName = new JTextField();
		fName.setBounds(196, 70, 120, 21);
		contentPane.add(fName);
		fName.setColumns(10);
		
		regNo = new JTextField();
		regNo.setColumns(10);
		regNo.setBounds(196, 116, 120, 21);
		contentPane.add(regNo);
		
		lblNewLabel_1 = new JLabel("Student Login Form");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(148, 11, 111, 21);
		contentPane.add(lblNewLabel_1);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setForeground(Color.RED);
		Cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Cancel.setBounds(88, 175, 89, 23);
		contentPane.add(Cancel);
		
		JButton Submit = new JButton("Login");
		Submit.setForeground(Color.BLUE);
		Submit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Submit.setBounds(196, 175, 89, 23);
		Submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String firstName = fName.getText().toString();
				String regNum = regNo.getText().toString();
				
				try {
					Connection connection = new DbConnection().getDbConnection();
					Statement stm = connection.createStatement();
					String query = "SELECT * FROM students WHERE firstName='"+ firstName +"' and regNum='"+ regNum+"'";	
					ResultSet rs = stm.executeQuery(query);
					
					if(rs.next()) {
						int id = rs.getInt("id");
						// firstName and regNum are true
						// close login form 
						dispose();
						// see student details
						StudentHomePage view = new StudentHomePage(id);
						view.setVisible(true);
					}else{
						// firstName or regNum is false
						JOptionPane.showMessageDialog(Submit, "Incorrect credentials");	
						fName.setText("");
						regNo.setText("");	
					}
				}catch(Exception exception) {
					System.out.println(exception.getMessage());
				}
			}
		});
		contentPane.add(Submit);
	}
}
