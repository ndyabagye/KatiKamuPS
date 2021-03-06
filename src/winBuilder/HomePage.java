package winBuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HomePage extends JFrame {

	public HomePage() {
		setTitle("Teacher Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 513);
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton_1 = new JButton("Students");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentPanel spanel = new StudentPanel();	
				setContentPane(spanel);
				getContentPane().revalidate();
			}
		});
		
		menuBar.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Marks");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarksPanel mpanel = new MarksPanel();
				setContentPane(mpanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Teachers");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherPanel spanel = new TeacherPanel();	
				setContentPane(spanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_3 = new JButton("Timetable");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeTablePanel spanel = new TimeTablePanel();	
				setContentPane(spanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton_3);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		menuBar.add(Box.createHorizontalGlue());
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginForm login = new LoginForm();
				login.setVisible(true);
			}
		});
		menuBar.add(logoutButton);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome to Katikamu Primary School Application");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLUE);
		panel.add(lblNewLabel);
	}
}
