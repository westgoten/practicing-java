package inventory.management;

import inventory.storage.Product;
import java.util.*;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Manager {

	private static File file = Paths.get(System.getProperty("user.dir"), "inventory", "data", "product_list.txt").toFile();
	private static ArrayList<Product> productList;
	
	static {
		try{
			if (file.length() > 0) {
				getFileData();
			} else {
				productList = new ArrayList<Product>();
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		}
	}
	
	public static ArrayList<Product> getProductList() {
		return productList;
	}
	
	public static boolean hasProduct(String name) {
		name = name.toUpperCase();
		
		for (Product p : productList) {
			if (p.getName().equals(name))
				return true;
		}
		
		return false;
	}
	
	public static boolean hasProduct(int id) {
		if (id <= Product.getNumOfProducts() && id > 0)
			return true;
		else
			return false;
	}
	
	public static boolean isPriceValid(double price) {
		if (price >= 0.0)
			return true;
		else
			return false;
	}
	
	public static boolean isQuantValid(int quantity) {
		if (quantity >= 0)
			return true;
		else
			return false;
	}
	
	public static void addProduct(String name, double price, int quantity) throws FileNotFoundException, IOException {
		name = name.toUpperCase();
		
		Product aProduct = new Product(name, price, quantity);
		productList.add(aProduct);
		setFileData();
		
		return;
	}
	
	public static String removeProduct(int id) throws FileNotFoundException, IOException {
		int listSize = productList.size();
		String removedProductName = "";
		
		for (int i = 0; i < listSize; i++) {
			if (productList.get(i).getID() == id) {
				removedProductName = productList.get(i).getName();
				productList.remove(i);
				productList.trimToSize();
				Product.setNumOfProducts(Product.getNumOfProducts() - 1);
				listSize--;
				
				Product temp;
				for (; i < listSize; i++) {
					temp = productList.get(i);
					temp.setID(temp.getID() - 1);
				}
				temp = null;
				
				setFileData();
				break;
			}
		}
		
		return removedProductName;
	}
	
	public static String modifyProductQuantity(int id, int quantity) throws FileNotFoundException, IOException {
		String productName = "";
		
		for (Product p : productList) {
			if (p.getID() == id) {
				productName = p.getName();
				p.setQuantity(quantity);
				setFileData();
				break;
			}
		}
		
		return productName;
	}
	
	public static void sortProductList(int option) {
		switch(option) {
			case 1:
				Collections.sort(productList, Comparator.comparing(Product::getID));
				break;
			case 2:
				Collections.sort(productList, Comparator.comparing(Product::getName));
				break;
			case 3:
				Collections.sort(productList, Comparator.comparing(Product::getPrice));
				break;
			case 4:
				Collections.sort(productList, Comparator.comparing(Product::getQuantity));
				break;
		}
		
		return;
	}
	
	public static double getInventoryValue() {
		double total = 0.0;
		
		for (Product p : productList)
			total += p.getPrice() * p.getQuantity();
		
		return total;
	}
	
	private static void getFileData() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		productList = (ArrayList<Product>) ois.readObject();
		Product.setNumOfProducts(ois.readInt());
		ois.close();
		
		return;
	}
	
	private static void setFileData() throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(productList);
		oos.writeInt(Product.getNumOfProducts());
		oos.close();
		
		return;
	}
}