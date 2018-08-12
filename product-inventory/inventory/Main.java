package inventory;

import inventory.management.Manager;
import inventory.storage.Product;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Scanner in = new Scanner(System.in).useLocale(Locale.US);
		
		while (true) {
			int option = 0;
			boolean invalid = false;
			
			System.out.println("\nWelcome to the Inventory Manager!");
			System.out.println("\n1) Add a new product"+
								"\n2) Remove a product"+
								"\n3) Modify product quantity"+
								"\n4) List all products");
			
			do {
				System.out.print("\nChoose one of the options above (1-4): ");
			
				option = in.nextInt();
				
				if (option >= 1 && option <= 4)
					invalid = false;
				else {
					System.out.println("\nInvalid option");
					invalid = true;
					waitSeconds(1);
				}
			} while (invalid);
			
			switch (option) {
				case 1:
					addProduct();
					break;
				case 2:
					removeProduct();
					break;
				case 3:
					modifyProductQuantity();
					break;
				case 4:
					listAllProducts();
					break;
			}
		}
	}
	
	private static void addProduct() throws FileNotFoundException, IOException {
		String pName = "";
		double pPrice = 0.0;
		int pQuantity = 0;
		boolean invalid = false;
		
		Scanner in = new Scanner(System.in).useLocale(Locale.US);
		
		System.out.println("\nAdd a new product");
		
		do {
			System.out.print("\nProduct's name: ");
			pName = in.nextLine();
			
			int i = 0;
			int countWhitespace = 0;
			int len = pName.length();
			for (i = 0; i < len; i++) {
				if (!(Character.isAlphabetic(pName.codePointAt(i)) || Character.isWhitespace(pName.codePointAt(i)))) {
					System.out.println("\nInvalid name");
					invalid = true;
					break;
				} else if (Character.isWhitespace(pName.codePointAt(i))) {
					if (++countWhitespace > 1) {
						System.out.println("\nInvalid name");
						invalid = true;
						break;
					}
				}
			}
			if (i == len) {
				invalid = false;
			}
		} while (invalid);
		
		do {
			System.out.print("\nProduct's price: ");
			pPrice = in.nextDouble();
			
			if(Manager.isPriceValid(pPrice)) {
				invalid = false;
			} else {
				System.out.println("\nInvalid price");
				invalid = true;
				waitSeconds(1);
			}
		} while (invalid);
		
		do {
			System.out.print("\nProduct's quantity: ");
			pQuantity = in.nextInt();
			
			if(Manager.isQuantValid(pQuantity)) {
				invalid = false;
			} else {
				System.out.println("\nInvalid quantity");
				invalid = true;
				waitSeconds(1);
			}
		} while (invalid);
		
		if (Manager.hasProduct(pName)) {
			System.out.println("\nA product with that name already exists");
			waitSeconds(3);
		} else {
			Manager.addProduct(pName, pPrice, pQuantity);
			System.out.println("\nAdded '" + pName.toUpperCase() + "' to the inventory");
			waitSeconds(2);
		}
		
		return;
	}
	
	private static void removeProduct() throws FileNotFoundException, IOException {
		int pID = 0;
		String pName = "";
		boolean invalid = false;
		
		Scanner in = new Scanner(System.in).useLocale(Locale.US);
		
		System.out.println("\nRemove a product");
		
		System.out.print("\nProduct's ID number: ");
		pID = in.nextInt();
		
		if (Manager.hasProduct(pID)) {
			pName = Manager.removeProduct(pID);
			System.out.println("\nRemoved '" + pName + "' from the inventory");
			waitSeconds(3);
		} else {
			System.out.println("\nA product with that ID number doesn't exist");
			waitSeconds(3);
		}
		
		return;
	}
	
	private static void modifyProductQuantity() throws FileNotFoundException, IOException {
		int pID = 0, pQuantity = 0;
		String pName = "";
		boolean invalid = false;
		
		Scanner in = new Scanner(System.in).useLocale(Locale.US);
		
		System.out.println("\nModify product quantity");
		
		System.out.print("\nProduct's ID number: ");
		pID = in.nextInt();
		
		do {
			System.out.print("\nProduct's new quantity: ");
			pQuantity = in.nextInt();
			
			if(Manager.isQuantValid(pQuantity)) {
				invalid = false;
			} else {
				System.out.println("\nInvalid quantity");
				invalid = true;
				waitSeconds(1);
			}
		} while (invalid);
		
		if (Manager.hasProduct(pID)) {
			pName = Manager.modifyProductQuantity(pID, pQuantity);
			System.out.println("\nThere is " + pQuantity + " units of '" + pName + "' in the inventory");
			waitSeconds(3);
		} else {
			System.out.println("\nA product with that ID number doesn't exist");
			waitSeconds(3);
		}
		
		return;
	}
	
	private static void listAllProducts() {
		int option = 0;
		boolean invalid = false;
		
		Scanner in = new Scanner(System.in).useLocale(Locale.US);
		
		System.out.println("\nList of products\n\nDo you want to sort the list by:");
		System.out.println("\n1) ID Number"+
							"\n2) Name"+
							"\n3) Price"+
							"\n4) Quantity");
		
		do {
			System.out.print("\nChoose an option (1-4): ");
			
			option = in.nextInt();
				
			if (option >= 1 && option <= 4)
				invalid = false;
			else {
				System.out.println("\nInvalid option");
				invalid = true;
				waitSeconds(1);
			}
		} while (invalid);
		
		Manager.sortProductList(option);
		
		System.out.printf("\n| ID Number  | %-25s | %-12s | Quantity |\n\n", "Name", "Price (U$)");
		
		ArrayList<Product> productList = Manager.getProductList();
		for (Product p : productList) {
			System.out.printf(Locale.US, "|    %04d    | %-25s | %-12.2f | %-8d |\n", p.getID(), p.getName(), p.getPrice(), p.getQuantity());
		}
		System.out.printf(Locale.US, "\n| Inventory Value ------------------------------------- U$ %.2f |\n", Manager.getInventoryValue());
		
		System.out.print("\nPress ENTER to return to Menu...");
		in.nextLine();
		in.nextLine();
		
		return;
	}
	
	private static void waitSeconds(int sec) {
		try{
			TimeUnit.SECONDS.sleep(sec);
			} catch (InterruptedException e) {
				System.err.println("InterruptedException: " + e.getMessage());
			}
			
		return;
	}
}