package inventory.storage;

public class Product {
	private int id;
	private String name;
	private double price;
	private int quantity;
	
	private static int numberOfProducts = 0;
	
	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		
		this.id = ++numberOfProducts;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int newValue) {
		if (newValue >= 0)
			this.quantity = newValue;
		else
			this.quantity = 0;
		
		return;
	}
}