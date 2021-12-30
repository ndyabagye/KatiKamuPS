package winBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class HomePage extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("Teacher Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 513);
		
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
				TimetablePanel spanel = new TimetablePanel();	
				setContentPane(spanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton_3);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}
}
