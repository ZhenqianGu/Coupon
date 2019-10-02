package CS401Project;

//This is a coupon class, contains all the attributes of coupons, along with set and get methods.
public class Coupon {
	private String provider;
	private String productName;
	private int price;
	private int rate;
	private int expiration;
	private boolean unused;
	private double finalPrice;

	public Coupon() {

	}

	public Coupon(String provider, String productName, int price, int rate, int expiration, boolean unused) {
		this.provider = provider;
		this.productName = productName;
		this.price = price;
		this.rate = rate;
		this.expiration = expiration;
		this.unused = unused;
	}

	public Coupon(String provider, String productName, int price, int rate, int expiration, boolean unused,
			double finalPrice) {
		this.provider = provider;
		this.productName = productName;
		this.price = price;
		this.rate = rate;
		this.expiration = expiration;
		this.unused = unused;
		this.finalPrice = finalPrice;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProvider() {
		return provider;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public int getExpiration() {
		return expiration;
	}

	public void use() {
		this.unused = false;
	}

	public boolean getStatus() {
		return unused;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String toString() {
		return "Provider: " + getProvider() + ", Product Name: " + getProductName() + ", Price: " + getPrice()
				+ ", Rate: " + getRate() + " %, Expiration: " + getExpiration() + " days, Final Price: "
				+ getFinalPrice();
	}

}
