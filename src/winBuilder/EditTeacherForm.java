package winBuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class EditTeacherForm extends JFrame {

	private JPanel contentPane;
	private JTextField fName;
	private JTextField lName;
	private JTextField emailField;

	private char[] password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTeacherForm frame = new EditTeacherForm(1);
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
	public EditTeacherForm(int id) {
		try {
    		Connection connection = new DbConnection().getDbConnection();
    		Statement teacherStatement = connection.createStatement();
    	    String getTeacherQuery = "SELECT * FROM teachers WHERE id = " + id;

    		ResultSet teacherResult = teacherStatement.executeQuery(getTeacherQuery);

    		while (teacherResult.next()) {
    			int teachId = teacherResult.getInt("id");
      	        String firstName = teacherResult.getString("firstName");
      	        String lastName = teacherResult.getString("lastName");
      	        String email = teacherResult.getString("email");

				setTitle("Edit Teacher Form");
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

				fName = new JTextField(firstName);
				fName.setBounds(175, 48, 181, 30);
				contentPane.add(fName);
				fName.setColumns(10);

				lName = new JTextField(lastName);
				lName.setColumns(10);
				lName.setBounds(175, 86, 181, 30);
				contentPane.add(lName);

				emailField = new JTextField(email);
				emailField.setColumns(10);
				emailField.setBounds(175, 128, 181, 30);
				contentPane.add(emailField);

				JButton updateBtn = new JButton("Update");
	      		updateBtn.addActionListener(new ActionListener() {
	      			@Override
	      			public void actionPerformed(ActionEvent e) {
	      				String upfirstName = fName.getText().toString();
	      				String uplastName = lName.getText().toString();
	      				String upemail = emailField.getText().toString();


						if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
							JOptionPane.showMessageDialog(updateBtn, "All fields are required!");
						}else {
	      					try {
	      						Connection connection = new DbConnection().getDbConnection();
	      			    		Statement teacherStatement = connection.createStatement();
	      			    	    String getTeacherQuery = "SELECT * FROM teachers WHERE id = " + id;

	      						String updateTeachers = "UPDATE teachers SET "
	      								+ "firstName = '"+ upfirstName 	+ "', "
	      								+ "lastName = '"+ uplastName  + "', "
	      								+ "email = '"+ upemail
	      								+ "' WHERE ID = "+ id ;

	      						teacherStatement.executeUpdate(updateTeachers);

			      	    		ResultSet updateResult = teacherStatement.executeQuery(getTeacherQuery);

			      	    		while (updateResult.next()) {

			      	    			fName.setText(upfirstName);
			          				lName.setText(uplastName);
			          				emailField.setText(upemail);

			          				// success message
									JOptionPane.showMessageDialog(updateBtn, "Teacher updated succesfully");
			      	    		}
		      			        connection.close();
	      					}catch (SQLIntegrityConstraintViolationException x) {
								// success message
								JOptionPane.showMessageDialog(updateBtn, "Teacher with email already exists");
							}

							catch(Exception exe) {
	      						exe.printStackTrace();
	      					}
	      				}

	      				//System.out.format("%s,%s,%s,%s,%s,%s", firstName, lastName, regNum, stuAge, stuGender, stuClass);

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
	      				emailField.setText(email);

	      			}
	      		});
	      		cancelBtn.setForeground(Color.RED);
	      		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      		cancelBtn.setBounds(261, 240, 89, 23);
	      		contentPane.add(cancelBtn);


		}

    		teacherStatement.close();
    	}catch(Exception exe) {
    		System.out.println("here");
    		exe.printStackTrace();
    	}
	}

}
