package CS401Project;

//this class is a window of the search function
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchUI extends JFrame {
	public static CouponSortSearch couponSS = new CouponSortSearch();
	ButtonGroup option = new ButtonGroup();
	public static Object[][] matchedTable = new Object[SetRoom.couponArray.length + 1][1];
	public static JTable searchTable;
	// row 1
	JPanel row1 = new JPanel();
	JLabel searchByLabel = new JLabel("Search By", JLabel.LEFT);
	JTextField area = new JTextField("", 10);

	// set up row 2
	JPanel row2 = new JPanel();
	JCheckBox providerPick = new JCheckBox("", true);
	JLabel providerLabel = new JLabel("Provider", JLabel.LEFT);
	JTextField providerText = new JTextField("", 10);

	// set up row 3
	JPanel row3 = new JPanel();
	JCheckBox productNamePick = new JCheckBox("", false);
	JLabel productNameLabel = new JLabel("Product Name", JLabel.LEFT);
	JTextField productNameText = new JTextField("", 10);

	// set up row 4
	JPanel row4 = new JPanel();
	JCheckBox pricePick = new JCheckBox("", false);
	JLabel priceLabel = new JLabel("Price", JLabel.LEFT);
	JTextField priceText = new JTextField("", 10);

	// set up row 5
	JPanel row5 = new JPanel();
	JCheckBox ratePick = new JCheckBox("", false);
	JLabel rateLabel = new JLabel("Rate", JLabel.LEFT);
	JTextField rateText = new JTextField("", 10);

	// set up row 6
	JPanel row6 = new JPanel();
	JCheckBox expirationPick = new JCheckBox("", false);
	JLabel expirationLabel = new JLabel("Expiration", JLabel.LEFT);
	JTextField expirationText = new JTextField("", 10);

	// set up row 7
	JPanel row7 = new JPanel();
	JCheckBox statusPick = new JCheckBox("", false);
	JLabel statusLabel = new JLabel("Status", JLabel.LEFT);
	JTextField statusText = new JTextField("", 10);

	// set up row 8
	JPanel row8 = new JPanel();
	JButton searchButton = new JButton("Search");

	public SearchUI() {
		super("Search Coupon");
		setSize(480, 500);
		setResizable(false);
		GridLayout layout = new GridLayout(8, 3, 100, 10);
		setLayout(layout);

		// Add listerners to all the check boxes
		searchButton.addActionListener(couponSS);

		providerPick.addItemListener(couponSS);
		productNamePick.addItemListener(couponSS);
		pricePick.addItemListener(couponSS);
		ratePick.addItemListener(couponSS);
		expirationPick.addItemListener(couponSS);
		statusPick.addItemListener(couponSS);

		FlowLayout layout1 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row1.setLayout(layout1);
		row1.add(searchByLabel);
		add(row1);

		FlowLayout layout2 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row2.setLayout(layout2);
		row2.add(providerPick);
		option.add(providerPick);
		row2.add(providerLabel);
		row2.add(providerText);
		add(row2);

		FlowLayout layout3 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row3.setLayout(layout3);
		row3.add(productNamePick);
		option.add(productNamePick);
		row3.add(productNameLabel);
		row3.add(productNameText);
		add(row3);

		FlowLayout layout4 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row4.setLayout(layout4);
		row4.add(pricePick);
		option.add(pricePick);
		row4.add(priceLabel);
		row4.add(priceText);
		add(row4);

		FlowLayout layout5 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row5.setLayout(layout5);
		row5.add(ratePick);
		option.add(ratePick);
		row5.add(rateLabel);
		row5.add(rateText);
		add(row5);

		FlowLayout layout6 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row6.setLayout(layout6);
		row6.add(expirationPick);
		option.add(expirationPick);
		row6.add(expirationLabel);
		row6.add(expirationText);
		add(row6);

		FlowLayout layout7 = new FlowLayout(FlowLayout.LEFT, 10, 10);
		row7.setLayout(layout7);
		row7.add(statusPick);
		option.add(statusPick);
		row7.add(statusLabel);
		row7.add(statusText);
		add(row7);

		FlowLayout layout8 = new FlowLayout(FlowLayout.CENTER, 10, 10);
		row8.setLayout(layout8);
		row8.add(searchButton);
		add(row8);

		setVisible(true);

		// search function
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;
				CouponNode location = ImportFile.list.getLink();
				while (location != null) {
					num++;
					location = location.getLink();
				}

				// the search depends on different attributes.

				if (providerPick.isSelected()) {
					// first clean up the data table, in case there is something left
					// from the last search.
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						matchedTable[i][0] = null;

					}
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.linearMatchedTable[i][0] = null;
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.binaryMatchedTable[i][0] = null;

					// get the input from the user
					String providerKey = MainUI.searchUI.providerText.getText();
					// first, sort the array by the chosen attribute.
					SetRoom.couponArray = SearchUI.couponSS.sortByProvider(SetRoom.couponArray, num);
					// then apply both of the linear search and binary search
					int[] resultLinear = couponSS.searchByProviderLinear(SetRoom.couponArray, providerKey, num);
					int[] resultBinary = couponSS.searchByProviderBinary(SetRoom.couponArray, providerKey, num);
					// copy the result from linear search to the output table
					int count = 0;
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.linearMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.linearMatchedTable[i][0];
							count++;
						}
					}
					// copy the result from binary search to the output table
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.binaryMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.binaryMatchedTable[i][0];
							count++;
						}
					}
					// set up the output table
					Object[] columnNames = { "Matched Coupon" };
					Object[][] data = matchedTable;
					searchTable = new JTable(data, columnNames);
					SearchResultUI searchResultUI = new SearchResultUI();
				}

				// search function by product name.
				else if (productNamePick.isSelected()) {
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						matchedTable[i][0] = null;

					}
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.linearMatchedTable[i][0] = null;
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.binaryMatchedTable[i][0] = null;

					String productNameKey = MainUI.searchUI.productNameText.getText();
					SetRoom.couponArray = SearchUI.couponSS.sortByProductName(SetRoom.couponArray, num);
					int[] resultLinear = couponSS.searchByProductNameLinear(SetRoom.couponArray, productNameKey, num);
					int[] resultBinary = couponSS.searchByProductNameBinary(SetRoom.couponArray, productNameKey, num);
					int count = 0;
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.linearMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.linearMatchedTable[i][0];
							count++;
						}
					}
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.binaryMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.binaryMatchedTable[i][0];
							count++;
						}
					}
					Object[] columnNames = { "Matched Coupon" };
					Object[][] data = matchedTable;
					searchTable = new JTable(data, columnNames);
					SearchResultUI searchResultUI = new SearchResultUI();
				}

				// search function by price.
				else if (pricePick.isSelected()) {
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						matchedTable[i][0] = null;

					}
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.linearMatchedTable[i][0] = null;
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.binaryMatchedTable[i][0] = null;

					int priceKey = Integer.parseInt(MainUI.searchUI.priceText.getText());
					SetRoom.couponArray = SearchUI.couponSS.sortByPrice(SetRoom.couponArray, num);
					int[] resultLinear = couponSS.searchByPriceLinear(SetRoom.couponArray, priceKey, num);
					int[] resultBinary = couponSS.searchByPriceBinary(SetRoom.couponArray, priceKey, num);
					int count = 0;
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.linearMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.linearMatchedTable[i][0];
							count++;
						}
					}
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.binaryMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.binaryMatchedTable[i][0];
							count++;
						}
					}
					Object[] columnNames = { "Matched Coupon" };
					Object[][] data = matchedTable;
					searchTable = new JTable(data, columnNames);
					SearchResultUI searchResultUI = new SearchResultUI();
				}
				// search function by rate.
				else if (ratePick.isSelected()) {
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						matchedTable[i][0] = null;

					}
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.linearMatchedTable[i][0] = null;
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.binaryMatchedTable[i][0] = null;

					int rateKey = Integer.parseInt(MainUI.searchUI.rateText.getText());
					SetRoom.couponArray = SearchUI.couponSS.sortByRate(SetRoom.couponArray, num);
					int[] resultLinear = couponSS.searchByRateLinear(SetRoom.couponArray, rateKey, num);
					int[] resultBinary = couponSS.searchByRateBinary(SetRoom.couponArray, rateKey, num);
					int count = 0;
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.linearMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.linearMatchedTable[i][0];
							count++;
						}
					}
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.binaryMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.binaryMatchedTable[i][0];
							count++;
						}
					}
					Object[] columnNames = { "Matched Coupon" };
					Object[][] data = matchedTable;
					searchTable = new JTable(data, columnNames);
					SearchResultUI searchResultUI = new SearchResultUI();
				}

				// search function by expiration.
				else if (expirationPick.isSelected()) {
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						matchedTable[i][0] = null;

					}
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.linearMatchedTable[i][0] = null;
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.binaryMatchedTable[i][0] = null;

					int expirationKey = Integer.parseInt(MainUI.searchUI.expirationText.getText());
					SetRoom.couponArray = SearchUI.couponSS.sortByExpiration(SetRoom.couponArray, num);
					int[] resultLinear = couponSS.searchByExpirationLinear(SetRoom.couponArray, expirationKey, num);
					int[] resultBinary = couponSS.searchByExpirationBinary(SetRoom.couponArray, expirationKey, num);
					int count = 0;
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.linearMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.linearMatchedTable[i][0];
							count++;
						}
					}
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.binaryMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.binaryMatchedTable[i][0];
							count++;
						}
					}
					Object[] columnNames = { "Matched Coupon" };
					Object[][] data = matchedTable;
					searchTable = new JTable(data, columnNames);
					SearchResultUI searchResultUI = new SearchResultUI();
				}

				// search function by unused/redeemed.
				else if (statusPick.isSelected()) {
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						matchedTable[i][0] = null;

					}
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.linearMatchedTable[i][0] = null;
					for (int i = 0; i < SetRoom.couponArray.length; i++)
						CouponSortSearch.binaryMatchedTable[i][0] = null;

					boolean statusKey = Boolean.parseBoolean(MainUI.searchUI.statusText.getText());
					SetRoom.couponArray = SearchUI.couponSS.sortByStatus(SetRoom.couponArray, num);
					int[] resultLinear = couponSS.searchByStatusLinear(SetRoom.couponArray, statusKey, num);
					int[] resultBinary = couponSS.searchByStatusBinary(SetRoom.couponArray, statusKey, num);
					int count = 0;
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.linearMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.linearMatchedTable[i][0];
							count++;
						}
					}
					for (int i = 0; i < SetRoom.couponArray.length; i++) {
						if (CouponSortSearch.binaryMatchedTable[i][0] != null) {
							matchedTable[count][0] = CouponSortSearch.binaryMatchedTable[i][0];
							count++;
						}
					}
					Object[] columnNames = { "Matched Coupon" };
					Object[][] data = matchedTable;
					searchTable = new JTable(data, columnNames);
					SearchResultUI searchResultUI = new SearchResultUI();
				}
			}
		});
	}
}
