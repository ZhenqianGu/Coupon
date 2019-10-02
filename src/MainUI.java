package CS401Project;

//this is the window with most of the functions. 
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

public class MainUI extends JFrame {

	ButtonGroup option = new ButtonGroup();
	CouponNode couponNode = new CouponNode();
	public static SearchUI searchUI;
	public static Object[][] dataTable = new Object[SetRoom.couponArray.length + 1][7];
	public static JTable table;

	// row 1
	JPanel row1 = new JPanel();
	JLabel providerLabel = new JLabel("Provider", JLabel.LEFT);
	JLabel productNameLabel = new JLabel("Product Name", JLabel.LEFT);
	JLabel priceLabel = new JLabel("Price", JLabel.LEFT);
	JLabel rateLabel = new JLabel("Discount Rate", JLabel.LEFT);
	JLabel expirationLabel = new JLabel("Expiration", JLabel.LEFT);
	JLabel unUsedLabel = new JLabel("Unused", JLabel.LEFT);
	// row 2
	JPanel row2 = new JPanel();
	JTextField providerText = new JTextField("", 10);
	JTextField productNameText = new JTextField("", 10);
	JTextField priceText = new JTextField("", 10);
	JTextField rateText = new JTextField("", 10);
	JTextField expirationText = new JTextField("", 10);
	JTextField statusText = new JTextField("", 10);
	// row 3
	JPanel row3 = new JPanel();
	JButton addButton = new JButton("Add Coupon");
	JButton searchButton = new JButton("Search Coupon");
	// row 4
	JPanel row4 = new JPanel();
	JLabel sortLabel = new JLabel("Sort By", JLabel.LEFT);

	// row 5
	JPanel row5 = new JPanel();
	JCheckBox providerPick = new JCheckBox("", false);
	JLabel providerLabel2 = new JLabel("Privoder            ", JLabel.LEFT);

	JCheckBox productNamePick = new JCheckBox("", false);
	JLabel productNameLabel2 = new JLabel("Product Name            ", JLabel.LEFT);

	JCheckBox pricePick = new JCheckBox("", false);
	JLabel priceLabel2 = new JLabel("Price            ", JLabel.LEFT);

	JCheckBox ratePick = new JCheckBox("", false);
	JLabel rateLabel2 = new JLabel("Rate            ", JLabel.LEFT);

	JCheckBox expirationPick = new JCheckBox("", false);
	JLabel expirationLabel2 = new JLabel("Expiration            ", JLabel.LEFT);

	JCheckBox statusPick = new JCheckBox("", false);
	JLabel statusLabel = new JLabel("Status            ", JLabel.LEFT);

	JCheckBox finalPricePick = new JCheckBox("", false);
	JLabel finalPriceLabel = new JLabel("Final Price   ", JLabel.LEFT);

	// row 6
	JPanel row6 = new JPanel();
	JButton showAllButton = new JButton("Show All Coupon");

	public MainUI() {
		super("Coupon");
		setSize(1000, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(6, 6, 10, 5);
		setLayout(layout);

//this button add the coupon manually input from user to the array. 
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// get data from the texts entered by user, and calculates the final price.
				// Then store the new coupon into the array.
				String provider = providerText.getText();
				String productName = productNameText.getText();
				int price = Integer.parseInt(priceText.getText());
				int rate = Integer.parseInt(rateText.getText());
				int expiration = Integer.parseInt(expirationText.getText());
				boolean status = Boolean.parseBoolean(statusText.getText());
				double finalPrice = ((double) price * (1 - (double) rate / 100));

				// count the character of the product name.
				int productNameCount = 0;
				for (int i = 0; i < productName.length(); i++) {
					if (productName.charAt(i) != ' ')
						productNameCount++;
				}

				// when everything is ok, the new coupon node will be added to the linked list.
				if (provider.length() <= 20 && productNameCount <= 20 && (rate > 4 & rate < 81)
						&& (expiration >= 0 && expiration < 366)) {
					Coupon newCoupon = new Coupon(provider, productName, price, rate, expiration, status, finalPrice);
					CouponNode newNode = new CouponNode(newCoupon);

					ImportFile.list.add(ImportFile.list, newNode);
					// count the node number in total, this number will help to find the next
					// available spot in the array.
					int num = 0;
					CouponNode location = ImportFile.list.getLink();
					while (location != null) {
						num++;
						location = location.getLink();
					}
					// put new coupon to the array.
					SetRoom.couponArray[num - 1] = newCoupon;
					Added couponAdded = new Added();

					// if the provider is too long, then it pops out a window.
				} else if (provider.length() > 20) {
					ProviderTooLong providerTooLong = new ProviderTooLong();
				}
				// if the product name is too long, then it pops out a window.
				else if (productNameCount > 20) {
					ProductNameTooLong productNameTooLong = new ProductNameTooLong();
				}
				// if the rate is out of range, then it pops out a window.
				else if (rate < 5 || rate > 80) {
					RateOutofRange rateOutofRange = new RateOutofRange();
				}
				// if the expiration is out of range, then it pops out a window.
				else if (expiration < 0 || expiration > 365) {
					ExpirationOutofRange expirationOutofRange = new ExpirationOutofRange();
				}

			}
		}

		);

		// this button will call the window if search function
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchUI = new SearchUI();

			}
		});

		// this button will call a table shows all the coupons.
		// with the check box, user can view the coupons in sorted sequences by selected
		// attributes.
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;
				CouponNode location = ImportFile.list.getLink();

				// count the total number of coupons.
				while (location != null) {
					num++;
					location = location.getLink();
				}
				// put coupons into the table in its original sequence.
				for (int i = 0; i < num; i++) {

					dataTable[i][0] = SetRoom.couponArray[i].getProvider();
					dataTable[i][1] = SetRoom.couponArray[i].getProductName();
					dataTable[i][2] = SetRoom.couponArray[i].getPrice();
					dataTable[i][3] = SetRoom.couponArray[i].getRate();
					dataTable[i][4] = SetRoom.couponArray[i].getExpiration();
					dataTable[i][5] = SetRoom.couponArray[i].getStatus();
					dataTable[i][6] = SetRoom.couponArray[i].getFinalPrice();

				}

				// sort the coupons by provider, then put everything into the table.
				if (providerPick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByProvider(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();

					}

				}
				// sort the coupons by product name, then put everything into the table.
				if (productNamePick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByProductName(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();
					}
				}

				// sort the coupons by price, then put everything into the table.
				if (pricePick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByPrice(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();

					}

				}
				// sort the coupons by rate, then put everything into the table.
				if (ratePick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByRate(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();

					}

				}
				// sort the coupons by expiration, then put everything into the table.
				if (expirationPick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByExpiration(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();

					}

				}
				// sort the coupons by unused/redeemed, then put everything into the table.
				if (statusPick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByStatus(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();

					}

				}

				// sort the coupons by final price, then put everything into the table.
				if (finalPricePick.isSelected()) {
					Coupon[] sorted = SearchUI.couponSS.sortByFinalPrice(SetRoom.couponArray, num);
					for (int i = 0; i < num; i++) {

						dataTable[i][0] = sorted[i].getProvider();
						dataTable[i][1] = sorted[i].getProductName();
						dataTable[i][2] = sorted[i].getPrice();
						dataTable[i][3] = sorted[i].getRate();
						dataTable[i][4] = sorted[i].getExpiration();
						dataTable[i][5] = sorted[i].getStatus();
						dataTable[i][6] = sorted[i].getFinalPrice();

					}
				}

				// set up the table that shows all the coupons in unsorted/ sorted sequence.
				Object[] columnNames = { "Privoder", "Product Name", "Price", "Rate", "Expiration", "Unused",
						"Final Price" };
				Object[][] data = MainUI.dataTable;
				table = new JTable(data, columnNames);
				CouponArrayUI couponArrayUI = new CouponArrayUI();

			}
		});

		providerPick.addItemListener(SearchUI.couponSS);
		productNamePick.addItemListener(SearchUI.couponSS);
		pricePick.addItemListener(SearchUI.couponSS);
		ratePick.addItemListener(SearchUI.couponSS);
		expirationPick.addItemListener(SearchUI.couponSS);
		statusPick.addItemListener(SearchUI.couponSS);

		// set up row 1
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 90, 20);
		row1.setLayout(layout1);
		row1.add(providerLabel);
		row1.add(productNameLabel);
		row1.add(priceLabel);
		row1.add(rateLabel);
		row1.add(expirationLabel);
		row1.add(unUsedLabel);
		add(row1);

		// set up row 2
		FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 28, 0);
		row2.setLayout(layout2);
		row2.add(providerText);
		row2.add(productNameText);
		row2.add(priceText);
		row2.add(rateText);
		row2.add(expirationText);
		row2.add(statusText);

		providerText.setEditable(true);
		productNameText.setEditable(true);
		priceText.setEditable(true);
		rateText.setEditable(true);
		expirationText.setEditable(true);
		add(row2);
		// set up row 3
		FlowLayout layout3 = new FlowLayout(FlowLayout.LEFT, 70, 20);
		row3.add(addButton);
		row3.add(searchButton);
		add(row3);

		// set up row 4
		FlowLayout layout4 = new FlowLayout(FlowLayout.LEFT, 70, 0);
		row4.setLayout(layout4);
		row4.add(sortLabel);
		add(row4);

		// set up row 5
		FlowLayout layout5 = new FlowLayout(FlowLayout.LEFT, 15, 0);
		row5.setLayout(layout5);
		row5.add(providerPick);
		row5.add(providerLabel2);
		row5.add(productNamePick);
		row5.add(productNameLabel2);
		row5.add(pricePick);
		row5.add(priceLabel2);
		row5.add(ratePick);
		row5.add(rateLabel2);
		row5.add(expirationPick);
		row5.add(expirationLabel2);
		row5.add(statusPick);
		row5.add(statusLabel);
		row5.add(finalPricePick);
		row5.add(finalPriceLabel);

		option.add(providerPick);
		option.add(productNamePick);
		option.add(pricePick);
		option.add(ratePick);
		option.add(expirationPick);
		option.add(statusPick);
		option.add(finalPricePick);

		add(row5);

		// set up row 6
		FlowLayout layout6 = new FlowLayout(FlowLayout.CENTER, 70, 0);
		row6.setLayout(layout6);
		row6.add(showAllButton);
		add(row6);

		setVisible(true);
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception exc) {
			// ignore error
		}
	}

}
