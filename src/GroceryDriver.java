import java.util.*;
import java.io.*;
import java.util.Hashtable;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.Serializable;
/**
 * The GroceryDriver class creates an instance of GroceryDriver object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 6 R09
 *
 */
public class GroceryDriver {
	
/**
 * A method acts as a test to test all the classes and methods within them	
 * @param args
 */
	public static void main(String[] args) {
		HashedGrocery store = new HashedGrocery();
		try {
			FileInputStream files1 = new FileInputStream("Grocery.obj");
			ObjectInputStream fin  = new ObjectInputStream(files1);        
			store = (HashedGrocery) fin.readObject();
			System.out.println("HashedGrocery is loaded from grocery.obj.\n");
		}catch(ClassNotFoundException ex5) {
			System.out.println("hello");
		}catch(IOException ex6) {
			System.out.println("Grocery.obj does not exist. Creating new "
			  + "HashedGrocery object...\n");
		}
		char option;
		boolean quit = false;
		String file,code,name;
		int qty,sales;
		double price;
		Scanner stdin = new Scanner(System.in);
		System.out.println("Business Day " + store.getDay() + "\n");
		do {
			try {
				System.out.print("Menu : \n\n(L) Load item catalog\n(A) Add items\n(B) "
				  + "Process Sales\n(C) Display all items\n(N) Move to next "
				  + "business day\n(Q) Quit\n");
				System.out.print("\nEnter option: ");
				option = Character.toUpperCase(stdin.next().charAt(0));
				switch(option) {
				case 'L':
					stdin.nextLine();
					System.out.print("\nEnter file to load: ");
					file = stdin.nextLine();
					System.out.println();
					store.addItemCatalog(file);
					System.out.println();
					break;
				case 'A':
					System.out.print("\nEnter item code: ");
					code = stdin.next();
					stdin.nextLine();
					System.out.print("Enter item name: ");
					name = stdin.nextLine();
					System.out.print("Enter Quantity in store: ");
					qty = stdin.nextInt();
					System.out.print("Enter Average sales per day: ");
					sales = stdin.nextInt();
					System.out.print("Enter price: ");
					price = stdin.nextDouble();
					Item x = new Item(code, name,qty,sales,0,price);
					store.addItem(x);
					System.out.println("\n" + x.getItemCode() + ": " + x.
					  getName() + " added to inventory.\n");
					break;
				case'C':
					System.out.println(store.toString());
					store.clearTable();
					break;
				case 'B':
					stdin.nextLine();
					System.out.print("\nEnter filename: ");
					file = stdin.nextLine();
					System.out.println();
					store.processSales(file);
					System.out.println();
					break;
				case'N':
					System.out.println("\nAdvancing business day...");
					System.out.println("Business Day " + (store.getDay() + 1) +"\n");
					store.nextBusinessDay();
					break;
				case'Q':
					System.out.println("\nHashedGrocery is saved in grocery"
					  + ".obj.\n");
					System.out.println("Program terminating normally...\n");
					FileOutputStream files = new FileOutputStream("Grocery"
					  + ".obj");
					ObjectOutputStream fout = new ObjectOutputStream(files);
					fout.writeObject(store);
					fout.close();
					quit = true;
					break;
				default:
					System.out.println("\nEnter a valid choice!\n");
				}	
			}catch(ParseException ex2) {
				System.out.println(ex2.getMessage());
			}catch(IOException ex1) {
				System.out.println("File not found!\n");
			}catch(DuplicateItemCodeException ex3) {
				System.out.println(ex3.getMessage());
			}
		}while(!quit);
		}
	}
