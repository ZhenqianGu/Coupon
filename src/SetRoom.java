package CS401Project;

//this class is a window that askes user to set up the room of the array. 
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SetRoom extends JFrame {
	public static Coupon[] couponArray;

	ButtonGroup option = new ButtonGroup();
	CouponNode couponNode = new CouponNode();

	JPanel row1 = new JPanel();
	JLabel setLabel = new JLabel("Set List Length", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JTextField listLengthText = new JTextField("", 10);

	JPanel row3 = new JPanel();
	JButton okButton = new JButton("Apply");
	JButton skipButton = new JButton("Skip");

	public SetRoom() {
		super("Set Up");
		setSize(400, 230);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout layout = new GridLayout(3, 1, 10, 5);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row1.setLayout(layout1);
		row1.add(setLabel);
		add(row1);

		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row2.setLayout(layout2);
		row2.add(listLengthText);
		add(row2);

		FlowLayout layout3 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row3.setLayout(layout3);
		row3.add(okButton);
		row3.add(skipButton);
		add(row3);

		// records the input from user, then initiate a new array with a user entered
		// length.
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomStr = listLengthText.getText();
				// if user doesn't enter a number, click apply will instantiate an array with a
				// length of 30.
				if (listLengthText.getText().trim().equals("") || listLengthText.getText().length() == 0) {
					couponArray = new Coupon[30];
				} else {
					couponArray = new Coupon[Integer.parseInt(listLengthText.getText())];
				}
				try {
					ImportFile imptFile = new ImportFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});

		// if user click skip button, an array with a length of 30 will be instantiated.
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couponArray = new Coupon[30];
				try {
					ImportFile imptFile = new ImportFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}

		});

		setVisible(true);

	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception exc) {
			// ignore error
		}
	}

	public static void main(String[] args) {
		SetRoom.setLookAndFeel();
		SetRoom se = new SetRoom();

	}

}
