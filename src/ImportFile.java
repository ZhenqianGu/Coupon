package CS401Project;

//this class allows user to read a .txt file.
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

public class ImportFile extends JFrame {

	public static Coupon first = new Coupon("a", "a", 0, 0, 0, false);
	public static CouponNode list = new CouponNode(first);

	private File sourceFile;
	ButtonGroup option = new ButtonGroup();
	JPanel row1 = new JPanel();
	JLabel selectLabel = new JLabel("Import File", JLabel.LEFT);

	JPanel row2 = new JPanel();
	JButton selectButton = new JButton("Browse");
	JButton nextButton = new JButton("Next");
	String path;

	public ImportFile() throws FileNotFoundException {
		super("Import File");
		setSize(400, 230);
		setResizable(false);

		GridLayout layout = new GridLayout(2, 1, 10, 5);
		setLayout(layout);

		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row1.setLayout(layout1);
		row1.add(selectLabel);
		add(row1);

		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row2.setLayout(layout2);
		row2.add(selectButton);
		row2.add(nextButton);
		add(row2);

		// this button pops out a window that askes user to choose a file.
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = chooseFile.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooseFile.getSelectedFile();

					String source_file = selectedFile.getAbsolutePath();

					String path = selectedFile.getAbsolutePath();

					int idx[] = new int[5];

					Scanner in;

					// the scanner will copy from the .txt file to a linked list.
					try {
						in = new Scanner(new File(path));
						while (in.hasNextLine()) {
							String s = in.nextLine();
							int num = 0;
							for (int i = 0; i < s.length(); i++) {
								if (s.charAt(i) == ' ') {
									idx[num] = i;
									num++;
								}
							}
							// one line in the .txt file will be divided into attributes by the locations of
							// space.

							String provider = s.substring(0, idx[0]);
							String productName = s.substring(idx[0] + 1, idx[1]);
							int price = Integer.parseInt(s.substring(idx[1] + 1, idx[2]));
							int rate = Integer.parseInt(s.substring(idx[2] + 1, idx[3]));
							int expiration = Integer.parseInt(s.substring(idx[3] + 1, idx[4]));
							boolean status = Boolean.parseBoolean(s.substring(idx[4] + 1, s.length()));
							Coupon newCoupon = new Coupon(provider, productName, price, rate, expiration, status);

							CouponNode newNode = new CouponNode(newCoupon);

							// new node will be add to the linked list.
							list = list.add(list, newNode);

						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				Added added = new Added();
			}

		});

		// this method calls next window with most of the functions.
		// And calculates all the final prices and then store them to the array of
		// coupons.
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainUI mainMenu = new MainUI();
				int i = 0;
				CouponNode location = ImportFile.list.getLink();
				while (location != null) {
					SetRoom.couponArray[i] = location.getInfo();
					SetRoom.couponArray[i].setFinalPrice(((double) SetRoom.couponArray[i].getPrice())
							* (1 - (double) SetRoom.couponArray[i].getRate() / 100));
					i++;
					location = location.getLink();
				}
				setVisible(false);

			}
		});
		setVisible(true);
	}
}
