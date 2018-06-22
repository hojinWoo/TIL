package day7;
class Product {
	String name;
	int balance;
	int price;
	Product() {
		this("듀크인형",5,10000);
	}
	Product(String name, int balance, int price) {
		this.name = name;
		this.balance = balance;
		this.price = price;
	}
	String getName() {
		return name;
	}
	int getBalance() {
		return balance;
	}
	int getPrice() {
		return price;
	}
}
public class ProductTest {
	public static void main(String[] args) {
		Product[] pro = new Product[5];
		pro[0] = new Product("수모", 10, 15000);
		pro[1] = new Product("수경", 15, 15000);
		pro[2] = new Product("수영복", 5, 50000);
		pro[3] = new Product("수영가방", 20, 20000);
		pro[4] = new Product();
		for (int i = 0; i < pro.length; i++) {
			System.out.printf("상품명: %s, 재고량: %s, 가격: %,d원\n", 
			       pro[i].getName(), pro[i].getBalance(), pro[i].getPrice());
		}
		for (Product p : pro) {
			System.out.printf("상품명: %s, 재고량: %s, 가격: %,d원\n", 
			       p.getName(), p.getBalance(), p.getPrice());
		}
	}
}




