package winBuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RegisterTimetableForm extends JFrame {

	private JPanel contentPane;
	private JComboBox classT;
	private JComboBox mon;
	private JComboBox tue;
	private JComboBox wed;
	private JComboBox thur;
	private JComboBox fri;


	// for the control statements
	private String strClass;
	private String strMon;
	private String strTue;
	private String strWed;
	private String strThur;
	private String strFri;


	public RegisterTimetableForm() {
		setTitle("Add Timetable");
  		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		setBounds(100, 100, 500, 325);
  		contentPane = new JPanel();
  		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  		setContentPane(contentPane);
  		contentPane.setLayout(null);

  		JLabel Heading = new JLabel("Student Registration Form");
  		Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
  		Heading.setBounds(152, 11, 184, 22);
  		contentPane.add(Heading);

  		JLabel FirstName = new JLabel("Class ");
  		FirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
  		FirstName.setBounds(56, 81, 75, 22);
  		contentPane.add(FirstName);

  		JLabel LastName = new JLabel("Mon");
  		LastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
  		LastName.setBounds(260, 81, 75, 22);
  		contentPane.add(LastName);

  		JLabel RegNumber = new JLabel("Tue");
  		RegNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
  		RegNumber.setBounds(56, 122, 75, 22);
  		contentPane.add(RegNumber);

  		JLabel Age = new JLabel("Wed");
  		Age.setFont(new Font("Tahoma", Font.PLAIN, 13));
  		Age.setBounds(56, 164, 75, 22);
  		contentPane.add(Age);


  		JLabel Gender = new JLabel("Thurs");
  		Gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
  		Gender.setBounds(260, 127, 75, 22);
  		contentPane.add(Gender);

  		JLabel Class = new JLabel("Fri");
  		Class.setFont(new Font("Tahoma", Font.PLAIN, 13));
  		Class.setBounds(260, 164, 75, 22);
  		contentPane.add(Class);

  		String[] classArr = {"Select a class", "P1","P2", "P3", "P4", "P5", "P6", "P7"};
  		classT = new JComboBox(classArr);
  		classT.setBounds(128, 83, 122, 20);
  		contentPane.add(classT);

  		String[] monArr = {"Select Subject","Math", "Science", "SST","English"};
  		mon = new JComboBox(monArr);
  		mon.setBounds(323, 83, 122, 20);
  		contentPane.add(mon);

  		String[] tueArr = {"Select Subject","Math", "Science", "SST","English"};
  		tue = new JComboBox(tueArr);
  		tue.setBounds(128, 124, 122, 20);
  		contentPane.add(tue);

  		String[] wedArr = {"Select Subject","Math", "Science", "SST","English"};
  		wed = new JComboBox(wedArr);
  		wed.setBounds(128, 166, 122, 20);
  		contentPane.add(wed);


  		String[] thurArr = {"Select Subject","Math", "Science", "SST","English"};
  		thur = new JComboBox(thurArr);
  		thur.setBounds(323, 123, 122, 22);
  		contentPane.add(thur);

  		String[] friArr = {"Select Subject","Math", "Science", "SST","English"};
  		fri = new JComboBox(friArr);
  		fri.setBounds(323, 165, 122, 22);
  		contentPane.add(fri);

  		JButton createBtn = new JButton("Create");
  		createBtn.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {

				if(classT.getSelectedIndex()== 0) {
					JOptionPane.showMessageDialog(createBtn, "Select a proper class");
					return;
				}else {
					strClass = classT.getSelectedItem().toString();
				}

				if(mon.getSelectedIndex()== 0) {
					strMon = "";
				}else {
					strMon = mon.getSelectedItem().toString();
				}

				if(tue.getSelectedIndex()== 0) {
					strTue = "";
				}else {
					strTue = tue.getSelectedItem().toString();
				}

				if(wed.getSelectedIndex()== 0) {
					strWed = "";
				}else {
					strWed = wed.getSelectedItem().toString();
				}

				if(thur.getSelectedIndex()== 0) {
					strThur = "";
				}else {
					strThur = thur.getSelectedItem().toString();
				}

				if(fri.getSelectedIndex()== 0) {
					strFri = "";
				}else {
					strFri = fri.getSelectedItem().toString();
				}

				if(classT.getSelectedIndex()!=0) {
					try {
						Connection connection = new DbConnection().getDbConnection();
						String query = "INSERT INTO timetable(class, Monday, Tuesday, Wednesday, Thursday, Friday)"
								+ " VALUES(?,?,?,?,?,?)";
						PreparedStatement sta = connection.prepareStatement(query);
						sta.setString(1, strClass);
						sta.setString(2, strMon);
						sta.setString(3, strTue);
						sta.setString(4, strTue);
						sta.setString(5, strWed);
						sta.setString(6, strThur);
						sta.setString(6, strFri);

						int i = sta.executeUpdate();
						// success message
						JOptionPane.showMessageDialog(createBtn, strClass + " Timetable created succesfully");

						classT.setSelectedIndex(0);
						mon.setSelectedIndex(0);
						tue.setSelectedIndex(0);
						wed.setSelectedIndex(0);
						thur.setSelectedIndex(0);
						fri.setSelectedIndex(0);

						connection.close();
					} catch (SQLIntegrityConstraintViolationException x) {
						// success message
						JOptionPane.showMessageDialog(createBtn, strClass + " timetable already exists conider editing");
					}
					catch(Exception exe) {
						exe.printStackTrace();
					}
				}
  			}
  		});
  		createBtn.setForeground(Color.BLUE);
  		createBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
  		createBtn.setBounds(129, 239, 89, 23);
  		contentPane.add(createBtn);

  		JButton cancelBtn = new JButton("Reset");
  		cancelBtn.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  			// clear input fields
  				classT.setSelectedIndex(0);
  				mon.setSelectedIndex(0);
				tue.setSelectedIndex(0);
				wed.setSelectedIndex(0);
				thur.setSelectedIndex(0);
				fri.setSelectedIndex(0);
  			}
  		});
  		cancelBtn.setForeground(Color.RED);
  		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
  		cancelBtn.setBounds(261, 240, 89, 23);
  		contentPane.add(cancelBtn);
	}
}

