import java.util.Hashtable;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * The HashedGrocery class creates an instance of HashedGrocery object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 6 R09
 *
 */
public class HashedGrocery implements Serializable{
	private int businessDay = 1;
	private boolean arr;
	private String table = "";
	private String order = "";
	private Hashtable<String,Item> hashTable = new Hashtable<String, Item>();

/**
 * No-arg constructor for the HashedGrocery class
 * Creates an instance of HashedGrocery object
 */
	public HashedGrocery() {
		
	}

/**
 * Adds item to the hash table
 * @param item
 * 		the Item object to be added to the hash table
 * @throws DuplicateItemCodeException
 * 		thrown when an item with the same item code already exists in the hash
 * 		table
 */
	public void addItem(Item item) throws DuplicateItemCodeException {
		if(hashTable.containsKey(item.getItemCode())) {
			throw new DuplicateItemCodeException(item);
		}
		hashTable.put(item.getItemCode(), item);
	}

/**
 * Checks if the hash table is empty
 * @return
 * 		a boolean value indicating whether the hash table is empty, true if
 * 		empty, false otherwise
 */
	public boolean isEmpty() {
		return (hashTable.isEmpty())? true: false;
	}
	
/**
 * Increments businessDay by 1 and checks if any orders have arrived
 * updates the quantity of the arrived orders
 */
	public void nextBusinessDay() {
		arr = false;
		businessDay++;
		hashTable.forEach((k,v) -> { if (v.getDay() == businessDay) {
		  order += v.getItemCode() + ": " + v.getOn() + " units of " + 
		  v.getName() + "\n"; v.setArr(0); v.setQty(-v.getOn());
		  v.setOnOrder(-v.getOn()); arr = true;}});
		if(arr) {
			System.out.println("Orders have arrived for: \n");
			System.out.println(order);
			order = "";
		}else {
			System.out.println("No orders have arrived.\n");
		}
	}

/**
 * Returns the value of businessDay variable
 * @return
 * 		the integer value of businessDay variable
 */
	public int getDay() {
		return businessDay;
	}

/**
 * Changes the qtyInStore amount of item by adjustByQty
 * @param item
 * 		the Item object to be changed
 * @param adjustByQty
 * 		the value to be adjusted to the value of qtyInStore
 */
	public void updateItem(Item item, int adjustByQty) {
		item.setQty(adjustByQty);
		
	}

/**
 * Adds all the items present in the text file
 * @param filename
 * 		the name of the text file to be added to the hash table
 * @throws IOException
 * 		thrown when filename does not exist
 * @throws ParseException
 * 		thrown when failed to parse a string in special format
 */
	public void addItemCatalog(String filename) throws IOException, 
	  ParseException{
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			JSONParser parser = new JSONParser();
			JSONArray objs = (JSONArray) parser.parse(isr);
			for(int i = 0; i < objs.size(); i++) {
				JSONObject obj = (JSONObject) objs.get(i);
				String code = (String) obj.get("itemCode");
				String name = (String) obj.get("itemName");
				int avgSales = Integer.parseInt((String)obj.get("avgSales"));
				int qtyInStore = Integer.parseInt((String)obj.get("qtyInStore"
				  + ""));
				double price = Double.parseDouble((String)obj.get("price"));
				int onOr = Integer.parseInt((String)obj.get("amtOnOrder"));
				Item x = new Item(code,name,qtyInStore,avgSales,onOr,price);
				if(hashTable.containsKey(x.getItemCode())) {
					System.out.println(x.getItemCode() + ": Cannot add item"
					  + " as item code already exists.");
				}else {
					hashTable.put(x.getItemCode(), x);
					System.out.println(x.getItemCode() + ": " + x.getName()
					  + " is added to inventory.");
				}
			}
	}
	
/**
 * Clears the value of table variable
 */
	public void clearTable() {
		table = "";
	}
	
/**
 * Processes filename to see the sold items and adjust accordingly
 * @param filename
 * 		the name of the text file to be read and sold
 * @throws IOException
 * 		thrown when filename does not exist
 * @throws ParseException
 * 		thrown when failed to parse a string in special format
 */
	public void processSales(String filename) throws IOException, 
	  ParseException {
		FileInputStream fis = new FileInputStream(filename);
		InputStreamReader isr = new InputStreamReader(fis);
		JSONParser parser = new JSONParser();
		JSONArray objs = (JSONArray) parser.parse(isr);
		for(int i = 0; i < objs.size(); i++) {
			JSONObject obj = (JSONObject) objs.get(i);
			String code = (String) obj.get("itemCode");
			int qty = Integer.parseInt((String)obj.get("qtySold"));
			if(hashTable.containsKey(code)) {
				if(hashTable.get(code).getQty() > qty) {
					hashTable.get(code).setQty(qty);
					if(hashTable.get(code).getQty() < 3 * hashTable.get(code)
					  .getSales() && hashTable.get(code).getOn() == 0) {
						hashTable.get(code).setOnOrder(2*hashTable.get(code)
						  .getSales());
						hashTable.get(code).setArr(this.businessDay + 3);
						System.out.println(code + ": " + qty + " units of " 
						  + hashTable.get(code).getName() +  " are sold. "
						  + "Order has been placed for " + hashTable.
						  get(code).getOn() + " more units.");
					}else {
						System.out.println(code + ": " + qty + " units of " 
						  + hashTable.get(code).getName() +  " are sold.");
					}
				}else {
					System.out.println(code + ": Not enough stock for sale. "
					  + "Not updated.");
					if(hashTable.get(code).getOn() == 0) {
						if(hashTable.get(code).getQty() < 3 * hashTable.get(code)
						  .getSales() && hashTable.get(code).getOn() == 0) {
							hashTable.get(code).setOnOrder(2*hashTable.get(code).getSales());
							hashTable.get(code).setArr(this.businessDay + 3);
									
						}
					}
				}
			}else {
				System.out.println(code + ": Cannot buy as it is not in the "
				  + "grocery store.");
			}
		}
	}

/**
 * Returns a string representation of HashedGrocery object in a neatly
 * formatted table
 */
	public String toString() {
		System.out.println("\n" + String.format("%-18s%-20s%-5s%-13s%-10s%-"
		  + "10s%-15s", "Item code", "Name", "Qty", "AvgSales", "Price", 
		  "OnOrder", "ArrOnBusDay"));
		System.out.println("-------------------------------------------------"
		  + "--------------------------------------");
		hashTable.forEach((k,v) -> table += v.toString() + "\n");
		return table;
		
	}
}

