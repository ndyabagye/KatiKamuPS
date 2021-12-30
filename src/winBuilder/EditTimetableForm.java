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
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditTimetableForm extends JFrame {

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
	private int index;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTimetableForm frame = new EditTimetableForm(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditTimetableForm(int id) {

		try {
    		Connection connection = new DbConnection().getDbConnection();
    		Statement timetableStatement = connection.createStatement();
    	    String getTimetableQuery = "SELECT * FROM timetable WHERE id = " + id;

    		ResultSet timetableResult = timetableStatement.executeQuery(getTimetableQuery);

    		while (timetableResult.next()) {
    			int timeTid = timetableResult.getInt("id");
      	        String classC = timetableResult.getString("class");
      	        String monC = timetableResult.getString("Monday");
      	        String tueC = timetableResult.getString("Tuesday");
      	        String wedC = timetableResult.getString("Wednesday");
      	        String thurC = timetableResult.getString("Thursday");
      	        String friC = timetableResult.getString("Friday");

				setTitle("Edit Timetable");
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 500, 325);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);

				JLabel Heading = new JLabel("Edit " + classC +" Timetable");
				Heading.setFont(new Font("Tahoma", Font.PLAIN, 14));
				Heading.setBounds(152, 11, 184, 22);
				contentPane.add(Heading);

				JLabel ClassL = new JLabel("Class ");
				ClassL.setFont(new Font("Tahoma", Font.PLAIN, 13));
				ClassL.setBounds(56, 81, 75, 22);
				contentPane.add(ClassL);

				JLabel MonL  = new JLabel("Mon");
				MonL.setFont(new Font("Tahoma", Font.PLAIN, 13));
				MonL.setBounds(260, 81, 75, 22);
				contentPane.add(MonL);

				JLabel TueL = new JLabel("Tue");
				TueL.setFont(new Font("Tahoma", Font.PLAIN, 13));
				TueL.setBounds(56, 122, 75, 22);
				contentPane.add(TueL);

				JLabel WedL = new JLabel("Wed");
				WedL.setFont(new Font("Tahoma", Font.PLAIN, 13));
				WedL.setBounds(56, 164, 75, 22);
				contentPane.add(WedL);


				JLabel ThurL = new JLabel("Thurs");
				ThurL.setFont(new Font("Tahoma", Font.PLAIN, 13));
				ThurL.setBounds(260, 127, 75, 22);
				contentPane.add(ThurL);

				JLabel FriL = new JLabel("Fri");
				FriL.setFont(new Font("Tahoma", Font.PLAIN, 13));
				FriL.setBounds(260, 164, 75, 22);
				contentPane.add(FriL);

				String[] classArr = {"Select a class", "P1","P2", "P3", "P4", "P5", "P6", "P7"};
				ArrayList<String> classList = new ArrayList<String>(Arrays.asList(classArr));
	      		index = classList.indexOf(classC);
				classT = new JComboBox(classArr);
				classT.setBounds(128, 83, 122, 20);
				if(index == -1) {
	         		 index = 0;
	         	}
	      		classT.setSelectedIndex(Integer.valueOf(index));
				contentPane.add(classT);

				String[] monArr = {"Select Subject","Math", "Science", "SST","English"};
				ArrayList<String> monList = new ArrayList<String>(Arrays.asList(monArr));
	      		index = monList.indexOf(monC);
				mon = new JComboBox(monArr);
				mon.setBounds(323, 83, 122, 20);
				if(index == -1) {
	         		 index = 0;
	         	}
	      		mon.setSelectedIndex(Integer.valueOf(index));
				contentPane.add(mon);

				String[] tueArr = {"Select Subject","Math", "Science", "SST","English"};
				ArrayList<String> tueList = new ArrayList<String>(Arrays.asList(tueArr));
	      		index = tueList.indexOf(tueC);
				tue = new JComboBox(tueArr);
				tue.setBounds(128, 124, 122, 20);
				if(index == -1) {
	         		 index = 0;
	         	}
	      		tue.setSelectedIndex(Integer.valueOf(index));
				contentPane.add(tue);

				String[] wedArr = {"Select Subject","Math", "Science", "SST","English"};
				ArrayList<String> wedList = new ArrayList<String>(Arrays.asList(wedArr));
	      		index = wedList.indexOf(wedC);
				wed = new JComboBox(wedArr);
				wed.setBounds(128, 166, 122, 20);
				if(index == -1) {
	         		 index = 0;
	         	}
	      		wed.setSelectedIndex(Integer.valueOf(index));
				contentPane.add(wed);


				String[] thurArr = {"Select Subject","Math", "Science", "SST","English"};
				ArrayList<String> thurList = new ArrayList<String>(Arrays.asList(thurArr));
	      		index = thurList.indexOf(thurC);
				thur = new JComboBox(thurArr);
				thur.setBounds(323, 123, 122, 22);
				if(index == -1) {
	         		 index = 0;
	         	}
	      		thur.setSelectedIndex(Integer.valueOf(index));
				contentPane.add(thur);

				String[] friArr = {"Select Subject","Math", "Science", "SST","English"};
				ArrayList<String> friList = new ArrayList<String>(Arrays.asList(friArr));
	      		index = friList.indexOf(friC);
				fri = new JComboBox(friArr);
				fri.setBounds(323, 165, 122, 22);
				if(index == -1) {
	         		 index = 0;
	         	}
	      		fri.setSelectedIndex(Integer.valueOf(index));
				contentPane.add(fri);

				JButton updateBtn = new JButton("Update");
				updateBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if(classT.getSelectedIndex()== 0) {
							JOptionPane.showMessageDialog(updateBtn, "Select a proper class");
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
					    		Statement timetableStatement = connection.createStatement();
					    	    String getTimetableQuery = "SELECT * FROM timetable WHERE id = " + id;

								String updateTimetable = "UPDATE timetable SET "
										+ "class = '"+ strClass + "', "
										+ "Monday = '"+ strMon  + "', "
										+ "Tuesday = '"+ strTue    + "', "
										+ "Wednesday= '"+ strWed    +"', "
										+ "Thursday = '"+ strThur +"', "
										+ "Friday = '"+ strFri
										+ "' WHERE ID = "+ id ;

								timetableStatement.executeUpdate(updateTimetable);

								ResultSet updateResult = timetableStatement.executeQuery(getTimetableQuery);

			      	    		while (updateResult.next()) {

			          				index = classList.indexOf(strClass);
			          				classT.setSelectedIndex(Integer.valueOf(index));

			          				index = monList.indexOf(strMon);
			          				mon.setSelectedIndex(Integer.valueOf(index));

			          				index = wedList.indexOf(strTue);
			          				tue.setSelectedIndex(Integer.valueOf(index));

			          				index = wedList.indexOf(strWed);
			          				wed.setSelectedIndex(Integer.valueOf(index));

			          				index = thurList.indexOf(strThur);
			          				thur.setSelectedIndex(Integer.valueOf(index));

			          				index = friList.indexOf(strFri);
			          				fri.setSelectedIndex(Integer.valueOf(index));

			          				// success message
									JOptionPane.showMessageDialog(updateBtn, strClass + " Timetable updated succesfully");
			      	    		}
		      			        connection.close();



							} catch (SQLIntegrityConstraintViolationException x) {
								// success message
								JOptionPane.showMessageDialog(updateBtn, strClass + " timetable already exists conider editing");
							}
							catch(Exception exe) {
								exe.printStackTrace();
							}
						}
					}
				});
				updateBtn.setForeground(Color.BLUE);
				updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
				updateBtn.setBounds(129, 239, 89, 23);
				contentPane.add(updateBtn);

				JButton cancelBtn = new JButton("Reset");
				cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					}
				});
				cancelBtn.setForeground(Color.RED);
				cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelBtn.setBounds(261, 240, 89, 23);
		  		contentPane.add(cancelBtn);
    		}
		  	timetableStatement.close();
    	}catch(Exception exe) {
    		System.out.println("here");
    		exe.printStackTrace();
    	}
	}
}


