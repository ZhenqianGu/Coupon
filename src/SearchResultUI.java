package CS401Project;

//this class is a table that shows the search results. 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SearchResultUI extends JPanel implements ItemListener, ActionListener, Runnable {

	public SearchResultUI() {

		super(new GridLayout(1, 0));

		JScrollPane scrollPane = new JScrollPane(SearchUI.searchTable);

		JFrame frame = new JFrame("Matched Coupon");
		frame.add(new JScrollPane(SearchUI.searchTable));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(850, 500);

	}

	@Override
	public void run() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}

}
