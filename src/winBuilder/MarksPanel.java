package winBuilder;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MarksPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MarksPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 446, 25);
		add(panel);

	}

}
