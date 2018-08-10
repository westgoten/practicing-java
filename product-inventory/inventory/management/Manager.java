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
	/** 
	 * I need to find a way to store/read products' data.
	 * Solution:
	 *   Create an arraylist of objects and store in a file
	 */

	private static File file = Paths.get(System.getProperty("user.dir"), "inventory", "data", "product_list.txt").toFile();
	private static ArrayList<Product> product_list;
	
	static {
		if (file.length() > 0) {
			getFileData();
		} else {
			product_list = new ArrayList<Product>();
		}
	}
	
	public static void addProduct(String name, double price, int quantity) throws FileNotFoundException, IOException {
		Product aProduct = new Product(name, price, quantity);
		product_list.add(aProduct);
		setFileData();
		
		return;
	}
	
	public static void removeProduct(int id) throws FileNotFoundException, IOException {
		int listSize = product_list.size();
		
		for (int i = 0; i < listSize; i++) {
			if (product_list.get(i).getID() == id) {
				product_list.remove(i);
				product_list.trimToSize();
				Product.setNumOfProducts(Product.getNumOfProducts() - 1);
				listSize--;
				
				for (; i < listSize; i++) {
					Product temp = product_list.get(i);
					temp.setID(temp.getID() - 1);
					temp = null;
				}
				
				setFileData();
				break;
			}
		}
		
		return;
	}
	
	public static void modifyProductQuantity(int id, int quantity) throws FileNotFoundException, IOException {
		for (Product p : product_list) {
			if (p.getID() == id) {
				p.setQuantity(quantity);
				setFileData();
				break;
			}
		}
		
		return;
	}
	
	public static void sortProductList(int option) {
		switch(option) {
			case 1:
				Collections.sort(product_list, Comparator.comparing(Product::getID));
				break;
			case 2:
				Collections.sort(product_list, Comparator.comparing(Product::getName));
				break;
			case 3:
				Collections.sort(product_list, Comparator.comparing(Product::getPrice));
				break;
			case 4:
				Collections.sort(product_list, Comparator.comparing(Product::getQuantity));
				break;
		}
		
		return;
	}
	
	private static void getFileData() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		product_list = (ArrayList<Product>) ois.readObject();
		Product.setNumOfProducts(ois.readInt());
		ois.close();
		
		return;
	}
	
	private static void setFileData() throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(product_list);
		oos.writeInt(Product.getNumOfProducts());
		oos.close();
		
		return;
	}
}