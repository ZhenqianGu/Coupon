package CS401Project;

//this class contains all the functions of sorting and searching coupons. 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CouponSortSearch implements SortAndSearch, ItemListener, ActionListener, Runnable {
	public static Object[][] linearMatchedTable = new Object[SetRoom.couponArray.length + 1][1];
	public static Object[][] binaryMatchedTable = new Object[SetRoom.couponArray.length + 1][1];
	public static Object[] columnNames = { "Matched Coupon" };
	public static Object[][] data;

	// this method can sort the coupon array by providers. Bubble sort implemented.
	public Coupon[] sortByProvider(Coupon[] list, int num) {
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j < num - 1 - i; j++) {
				if (list[j].getProvider().compareTo(list[j + 1].getProvider()) > 0) {
					Coupon temp = new Coupon();
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		return list;
	}

//this method search by provider in a linear way. 
	public int[] searchByProviderLinear(Coupon[] list, String key, int num) {
		boolean found = false;
		// fist put all the result into an array list.
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			if (list[i].getProvider().equals(key)) {
				result.add(i);
				found = true;
			}
		}
		// then copy all the result into an array.
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}

		int tableCount = 0;
		// then copy all the result from the array to a table that will show to the user
		// later.
		if (found == false)
			linearMatchedTable[0][0] = "No coupon is found - " + num + "th by linear search.";
		for (int i = 0; i < rst.length; i++) {
			linearMatchedTable[tableCount][0] = list[rst[i]].toString() + "		found in " + (rst[i] + 1)
					+ "th by linear search.";
			tableCount++;
		}

		return rst;
	}

	// binary search by provider.
	public int[] searchByProviderBinary(Coupon[] list, String key, int num) {
		int[] result = new int[num];
		boolean found = false;

		// Hashmap stores the index and the count of the matched coupon.
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			result[i] = -1;
		}

		int l = 0;
		int r = num - 1;
		int count = 0;
		providerHelper(list, key, map, l, r, count);
		// if the map contains the index number, then the index and the count will be
		// stored into the result array.
		for (int i = 0; i < num; i++) {
			if (map.containsKey(i)) {
				result[i] = map.get(i);
				found = true;
			} else
				result[i] = -1;
		}
		// the result stored in the array will be copied to the table that will show to
		// the user.
		int tableCount = 0;
		if (found == false) {
			binaryMatchedTable[0][0] = "No coupon is found - " + map.get(-1) + "th by BST.";
			tableCount++;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i] != -1) {
					binaryMatchedTable[tableCount][0] = list[i].toString() + " 		found in " + result[i]
							+ "th by BST.";
					System.out.println(result[i]);
					tableCount++;
				}
			}
		}
		return result;
	}

	// the helper method for binary search, this method can recored multiple
	// results.
	// first compare the middle point, then recursively compare the left part and
	// right part.
	void providerHelper(Coupon[] list, String key, HashMap map, int l, int r, int count) {
		int left = l;
		int right = r;
		int m = (left + right) / 2;

		count++;
		if (list[m].getProvider().equals(key)) {
//if matched, only record the result to the map, not return, because there could be more than one matched coupons.
			if (!map.containsKey(m))
				map.put(m, count);
			System.out.println(count);
		}

		// recurssion.
		while (m != left && m != right) {

			providerHelper(list, key, map, m + 1, r, count);
			providerHelper(list, key, map, l, m, count);
			return;
		}

		if (list[r].getProvider().equals(key)) {
			if (!map.containsKey(r))
				map.put(r, count);
		}
		// if not found, then the key (-1) stores the maximum search count.
		map.put(-1, count);
		return;
	}

	// Similar as the sort by provider.
	public Coupon[] sortByProductName(Coupon[] list, int num) {
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j < num - i - 1; j++) {
				if (list[j].getProductName().compareTo(list[j + 1].getProductName()) > 0) {
					Coupon temp = new Coupon();
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		return list;
	}

	// Similar as the linear search by provider.
	public int[] searchByProductNameLinear(Coupon[] list, String key, int num) {
		boolean found = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			if (list[i].getProductName().equals(key)) {
				result.add(i);
				found = true;
			}
		}
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}
		int tableCount = 0;
		if (found == false)
			linearMatchedTable[0][0] = "No coupon is found - " + num + "th by linear search.";
		for (int i = 0; i < rst.length; i++) {
			linearMatchedTable[tableCount][0] = list[rst[i]].toString() + "		found in " + (rst[i] + 1)
					+ "th by linear search.";
			tableCount++;
		}

		return rst;
	}

	// Similar as the binary search by provider.
	public int[] searchByProductNameBinary(Coupon[] list, String key, int num) {
		int[] result = new int[num];
		boolean found = false;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			result[i] = -1;
		}

		int l = 0;
		int r = num - 1;
		int count = 0;
		productNameHelper(list, key, map, l, r, count);
		for (int i = 0; i < num; i++) {
			if (map.containsKey(i)) {
				result[i] = map.get(i);
				found = true;
			} else
				result[i] = -1;
		}
		int tableCount = 0;
		if (found == false) {
			binaryMatchedTable[0][0] = "No coupon is found - " + map.get(-1) + "th by BST.";
			tableCount++;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i] != -1) {
					binaryMatchedTable[tableCount][0] = list[i].toString() + " 		found in " + result[i]
							+ "th by BST.";
					System.out.println(result[i]);
					tableCount++;
				}
			}
		}
		return result;
	}

	void productNameHelper(Coupon[] list, String key, HashMap map, int l, int r, int count) {
		int left = l;
		int right = r;
		int m = (left + right) / 2;

		count++;
		if (list[m].getProductName().equals(key)) {

			if (!map.containsKey(m))
				map.put(m, count);
			System.out.println(count);
		}

		while (m != left && m != right) {

			productNameHelper(list, key, map, m + 1, r, count);
			productNameHelper(list, key, map, l, m, count);
			return;
		}

		if (list[r].getProductName().equals(key)) {
			if (!map.containsKey(r))
				map.put(r, count);
		}
		map.put(-1, count);
		return;
	}

	// Similar as the sort by provider.
	public Coupon[] sortByPrice(Coupon[] list, int num) {
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j < num - i - 1; j++) {
				if (list[j].getPrice() > list[j + 1].getPrice()) {
					Coupon temp = new Coupon();
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		return list;
	}

	// Similar as the linear search by provider.
	public int[] searchByPriceLinear(Coupon[] list, int key, int num) {
		boolean found = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			if (list[i].getPrice() == key) {
				result.add(i);
				found = true;
			}
		}
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}
		int tableCount = 0;
		if (found == false)
			linearMatchedTable[0][0] = "No coupon is found - " + num + "th by linear search.";
		for (int i = 0; i < rst.length; i++) {
			linearMatchedTable[tableCount][0] = list[rst[i]].toString() + "		found in " + (rst[i] + 1)
					+ "th by linear search.";
			tableCount++;
		}

		return rst;
	}

	// Similar as the binary search by provider.
	public int[] searchByPriceBinary(Coupon[] list, int key, int num) {
		int[] result = new int[num];
		boolean found = false;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			result[i] = -1;
		}

		int l = 0;
		int r = num - 1;
		int count = 0;
		priceHelper(list, key, map, l, r, count);
		for (int i = 0; i < num; i++) {
			if (map.containsKey(i)) {
				result[i] = map.get(i);
				found = true;
			} else
				result[i] = -1;
		}
		int tableCount = 0;
		if (found == false) {
			binaryMatchedTable[0][0] = "No coupon is found - " + map.get(-1) + "th by BST.";
			tableCount++;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i] != -1) {
					binaryMatchedTable[tableCount][0] = list[i].toString() + " 		found in " + result[i]
							+ "th by BST.";
					System.out.println(result[i]);
					tableCount++;
				}
			}
		}
		return result;
	}

	void priceHelper(Coupon[] list, int key, HashMap map, int l, int r, int count) {
		int left = l;
		int right = r;
		int m = (left + right) / 2;

		count++;
		if (list[m].getPrice() == key) {

			if (!map.containsKey(m))
				map.put(m, count);
			System.out.println(count);
		}

		while (m != left && m != right) {

			priceHelper(list, key, map, m + 1, r, count);
			priceHelper(list, key, map, l, m, count);
			return;
		}

		if (list[r].getPrice() == key) {
			if (!map.containsKey(r))
				map.put(r, count);
		}
		map.put(-1, count);
		return;
	}

	// Similar as the sort by provider.
	public Coupon[] sortByRate(Coupon[] list, int num) {
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j < num - i - 1; j++) {
				if (list[j].getRate() > list[j + 1].getRate()) {
					Coupon temp = new Coupon();
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		return list;
	}

	// Similar as the linear search by provider.
	public int[] searchByRateLinear(Coupon[] list, int key, int num) {
		boolean found = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			if (list[i].getRate() == key) {
				result.add(i);
				found = true;
			}
		}
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}
		int tableCount = 0;
		if (found == false)
			linearMatchedTable[0][0] = "No coupon is found - " + num + "th by linear search.";
		for (int i = 0; i < rst.length; i++) {
			linearMatchedTable[tableCount][0] = list[rst[i]].toString() + "		found in " + (rst[i] + 1)
					+ "th by linear search.";
			tableCount++;
		}

		return rst;
	}

	// Similar as the binary search by provider.
	public int[] searchByRateBinary(Coupon[] list, int key, int num) {
		int[] result = new int[num];
		boolean found = false;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			result[i] = -1;
		}

		int l = 0;
		int r = num - 1;
		int count = 0;
		rateHelper(list, key, map, l, r, count);
		for (int i = 0; i < num; i++) {
			if (map.containsKey(i)) {
				result[i] = map.get(i);
				found = true;
			} else
				result[i] = -1;
		}
		int tableCount = 0;
		if (found == false) {
			binaryMatchedTable[0][0] = "No coupon is found - " + map.get(-1) + "th by BST.";
			tableCount++;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i] != -1) {
					binaryMatchedTable[tableCount][0] = list[i].toString() + " 		found in " + result[i]
							+ "th by BST.";
					System.out.println(result[i]);
					tableCount++;
				}
			}
		}
		return result;
	}

	void rateHelper(Coupon[] list, int key, HashMap map, int l, int r, int count) {
		int left = l;
		int right = r;
		int m = (left + right) / 2;

		count++;
		if (list[m].getRate() == key) {

			if (!map.containsKey(m))
				map.put(m, count);
			System.out.println(count);
		}

		while (m != left && m != right) {

			rateHelper(list, key, map, m + 1, r, count);
			rateHelper(list, key, map, l, m, count);
			return;
		}

		if (list[r].getRate() == key) {
			if (!map.containsKey(r))
				map.put(r, count);
		}
		map.put(-1, count);
		return;
	}

	// Similar as the sort by provider.
	public Coupon[] sortByExpiration(Coupon[] list, int num) {
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j < num - i - 1; j++) {
				if (list[j].getExpiration() > list[j + 1].getExpiration()) {
					Coupon temp = new Coupon();
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		return list;
	}

	// Similar as the linear search by provider.
	public int[] searchByExpirationLinear(Coupon[] list, int key, int num) {
		boolean found = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			if (list[i].getExpiration() == key) {
				result.add(i);
				found = true;
			}
		}
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}
		int tableCount = 0;
		if (found == false)
			linearMatchedTable[0][0] = "No coupon is found - " + num + "th by linear search.";
		for (int i = 0; i < rst.length; i++) {
			linearMatchedTable[tableCount][0] = list[rst[i]].toString() + "		found in " + (rst[i] + 1)
					+ "th by linear search.";
			tableCount++;
		}

		return rst;
	}

	// Similar as the binary search by provider.
	public int[] searchByExpirationBinary(Coupon[] list, int key, int num) {
		int[] result = new int[num];
		boolean found = false;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			result[i] = -1;
		}

		int l = 0;
		int r = num - 1;
		int count = 0;
		expirationHelper(list, key, map, l, r, count);
		for (int i = 0; i < num; i++) {
			if (map.containsKey(i)) {
				result[i] = map.get(i);
				found = true;
			} else
				result[i] = -1;
		}
		int tableCount = 0;
		if (found == false) {
			binaryMatchedTable[0][0] = "No coupon is found - " + map.get(-1) + "th by BST.";
			tableCount++;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i] != -1) {
					binaryMatchedTable[tableCount][0] = list[i].toString() + " 		found in " + result[i]
							+ "th by BST.";
					System.out.println(result[i]);
					tableCount++;
				}
			}
		}
		return result;
	}

	void expirationHelper(Coupon[] list, int key, HashMap map, int l, int r, int count) {
		int left = l;
		int right = r;
		int m = (left + right) / 2;

		count++;
		if (list[m].getExpiration() == key) {

			if (!map.containsKey(m))
				map.put(m, count);
			System.out.println(count);
		}

		while (m != left && m != right) {

			expirationHelper(list, key, map, m + 1, r, count);
			expirationHelper(list, key, map, l, m, count);
			return;
		}

		if (list[r].getExpiration() == key) {
			if (!map.containsKey(r))
				map.put(r, count);
		}
		map.put(-1, count);
		return;
	}

	// create 2 arrays, one stores for all the unused, another one for all the used.
	// then combine the 2 arrays into one array.
	public Coupon[] sortByStatus(Coupon[] list, int num) {
		Coupon[] listA = new Coupon[num];
		Coupon[] listB = new Coupon[num];
		for (int i = 0; i < num; i++) {
			if (list[i].getStatus() == true)
				listA[i] = list[i];
			else
				listB[i] = list[i];
		}
		// copy from one array with all the unused first, and then copy from another one
		// with all the used.
		Coupon[] listSorted = new Coupon[num];
		int location = 0;
		for (int i = 0; i < num; i++) {
			if (listA[i] != null) {
				listSorted[location] = listA[i];
				location++;
			}
		}
		for (int i = 0; i < num; i++) {
			if (listB[i] != null) {
				listSorted[location] = listB[i];
				location++;
			}
		}
		for (int i = 0; i < num; i++) {
			list[i] = listSorted[i];
		}
		return list;
	}

	// Similar as the linear search by provider.
	public int[] searchByStatusLinear(Coupon[] list, boolean key, int num) {
		boolean found = false;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			if (list[i].getStatus() == key) {
				result.add(i);
				found = true;
			}
		}
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}
		int tableCount = 0;
		if (found == false)
			linearMatchedTable[0][0] = "No coupon is found - " + num + "th by linear search.";
		for (int i = 0; i < rst.length; i++) {
			linearMatchedTable[tableCount][0] = list[rst[i]].toString() + "		found in " + (rst[i] + 1)
					+ "th by linear search.";
			tableCount++;
		}

		return rst;
	}

	// Similar as the binary search by provider.
	public int[] searchByStatusBinary(Coupon[] list, boolean key, int num) {
		int[] result = new int[num];
		boolean found = false;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			result[i] = -1;
		}

		int l = 0;
		int r = num - 1;
		int count = 0;
		statusHelper(list, key, map, l, r, count);
		for (int i = 0; i < num; i++) {
			if (map.containsKey(i)) {
				result[i] = map.get(i);
				found = true;
			} else
				result[i] = -1;
		}
		int tableCount = 0;
		if (found == false) {
			binaryMatchedTable[0][0] = "No coupon is found - " + map.get(-1) + "th by BST.";
			tableCount++;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i] != -1) {
					binaryMatchedTable[tableCount][0] = list[i].toString() + " 		found in " + result[i]
							+ "th by BST.";
					System.out.println(result[i]);
					tableCount++;
				}
			}
		}
		return result;
	}

	void statusHelper(Coupon[] list, boolean key, HashMap map, int l, int r, int count) {
		int left = l;
		int right = r;
		int m = (left + right) / 2;

		count++;
		if (list[m].getStatus() == key) {

			if (!map.containsKey(m))
				map.put(m, count);
			System.out.println(count);
		}

		while (m != left && m != right) {

			statusHelper(list, key, map, m + 1, r, count);
			statusHelper(list, key, map, l, m, count);
			return;
		}

		if (list[r].getStatus() == key) {
			if (!map.containsKey(r))
				map.put(r, count);
		}
		map.put(-1, count);
		return;
	}

	// Similar as the sort by provider.
	public Coupon[] sortByFinalPrice(Coupon[] list, int num) {
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j < num - i - 1; j++) {
				if (list[j].getFinalPrice() > list[j + 1].getFinalPrice()) {
					Coupon temp = new Coupon();
					temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
		return list;
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
