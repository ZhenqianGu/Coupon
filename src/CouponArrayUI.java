package CS401Project;

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

//this class is used to show all the coupons in a table, for both before sorted and after sorted. 
public class CouponArrayUI extends JPanel implements ItemListener, ActionListener, Runnable {

	public CouponArrayUI() {

		super(new GridLayout(1, 0));

		JTable table = MainUI.table;

		JScrollPane scrollPane = new JScrollPane(table);

		table.setPreferredScrollableViewportSize(new Dimension(700, 500));
		table.setFillsViewportHeight(true);
		table.setShowGrid(true);

		JFrame frame = new JFrame("Sorted Coupon");
		frame.add(new JScrollPane(table));
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
