package inventory.storage;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id, quantity;
	private String name;
	private double price;
	
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
	
	public static int getNumOfProducts() {
		return numberOfProducts;
	}
	
	public void setQuantity(int newValue) {
		this.quantity = newValue;
		
		return;
	}
	
	public void setID(int newValue) {
		this.id = newValue;
		
		return;
	}
	
	public static void setNumOfProducts(int newValue) {
		numberOfProducts = newValue;
		
		return;
	}
}