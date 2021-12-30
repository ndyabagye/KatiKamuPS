package winBuilder;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TimeTablePanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TimeTablePanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"7:00 - 8:00", null, null, null, null, null},
				{"8:00 - 8:30", "AS", "SE", "M", "BB", "LY"},
				{"8:30 - 9:30", null, null, null, null, null},
				{"9:30 - 10:30", null, null, null, null, null},
				{"10:30 - 11:00", "B", "R", "E", "A", "K"},
				{"11:00 - 12:00", null, null, null, null, null},
				{"12:00 - 1:00", null, null, null, null, null},
				{"1:00 - 2:00", "L", "U", "N", "C", "H"},
				{"2:00 - 3:00", null, null, null, null, null},
				{"3:00 - 4:00", null, null, null, null, null},
				{"4:00 - 5:00", "H", "O", "M", "E", null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
			}
		));
		scrollPane.setViewportView(table);
		
	}

}
