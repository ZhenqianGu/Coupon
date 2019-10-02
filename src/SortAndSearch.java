package CS401Project;

// this class is a interface for the sort and search functions.
public interface SortAndSearch {
	public Coupon[] sortByProvider(Coupon[] list, int num);

	public int[] searchByProviderLinear(Coupon[] list, String key, int num);

	public int[] searchByProviderBinary(Coupon[] list, String key, int num);

	public Coupon[] sortByProductName(Coupon[] list, int num);

	public int[] searchByProductNameLinear(Coupon[] list, String key, int num);

	public int[] searchByProductNameBinary(Coupon[] list, String key, int num);

	public Coupon[] sortByPrice(Coupon[] list, int num);

	public int[] searchByPriceLinear(Coupon[] list, int key, int num);

	public int[] searchByPriceBinary(Coupon[] list, int key, int num);

	public Coupon[] sortByRate(Coupon[] list, int num);

	public int[] searchByRateLinear(Coupon[] list, int key, int num);

	public int[] searchByRateBinary(Coupon[] list, int key, int num);

	public Coupon[] sortByExpiration(Coupon[] list, int num);

	public int[] searchByExpirationLinear(Coupon[] list, int key, int num);

	public int[] searchByExpirationBinary(Coupon[] list, int key, int num);

	public Coupon[] sortByStatus(Coupon[] list, int num);

	public Coupon[] sortByFinalPrice(Coupon[] list, int num);

}
