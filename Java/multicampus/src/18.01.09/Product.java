package day13;
public class Product implements Comparable<Product>{
	private String productID;
	private String productName;
	private int productPrice;

	Product(String productID, String productName, int productPrice) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public String toString() {
		return productID + "\t" + productName + "\t" + productPrice;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product tmp = (Product) obj;
			return productID.equals(tmp.productID);
		}
		return false;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int hashCode() {
		return productID.hashCode();
	}
	public int compareTo(Product p){
		int result=0;
		if(productPrice>p.productPrice)
			result=1;
		return result;
}
}