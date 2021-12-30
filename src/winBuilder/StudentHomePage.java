package winBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentHomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentHomePage frame = new StudentHomePage(10);
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
	public StudentHomePage(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 513);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("Timetable");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentTimetable mpanel = new StudentTimetable(id);
				setContentPane(mpanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Results");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMarkpanel mpanel = new StudentMarkpanel(id);
				setContentPane(mpanel);
				getContentPane().revalidate();
			}
		});
		menuBar.add(btnNewButton_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome, Student");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLUE);
		panel.add(lblNewLabel);
	}
}