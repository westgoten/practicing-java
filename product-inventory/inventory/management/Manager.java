package inventory.management;

import inventory.storage.Product;
import java.nio.file.FileSystem;
import java.nio.file.Paths;

public class Manager {
	/** 
	 * I need to find a way to store/read products' data.
	 * Solution:
	 *   Create an arraylist of objects and store in a file
	 */

	private static Path filepath = Paths.get(System.getProperty("user.dir"), "data", "product_list.txt");
	private static ArrayList<Product>;
	
	public static void main(String[] args) { // Just for test
		/**
		 * Constructor:
		 *  - If the file is empty, create a ArrayList;
		 *  - Else, read the ArrayList in the file.
	}
}