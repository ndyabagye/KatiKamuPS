package winBuilder;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class HomePageStudent extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageStudent frame = new HomePageStudent();
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
	public HomePageStudent() {
		setTitle("Student Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 513);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton_1 = new JButton("Timetable");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentPanel spanel = new StudentPanel();	
				setContentPane(spanel);
				getContentPane().revalidate();
			}
		});
		
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Results");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarksPanel mpanel = new MarksPanel();
				setContentPane(mpanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton_2);

		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}
}
