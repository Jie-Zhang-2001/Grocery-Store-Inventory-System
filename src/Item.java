import java.io.*;
/**
 * The Item class creates an instance of Item object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 6 R09
 *
 */
public class Item implements Serializable{
	private String itemCode;
	private String name;
	private int qtyInStore;
	private int averageSalesPerDay;
	private int onOrder;
	private int arrivalDay;
	private double price;

/**
 * No-arg constructor for the Item class
 * Creates an instance of Item object
 */
	public Item() {
		
	}
	
/**
 * Creates an instance of Item object
 * @param Code
 * 		The item code of the Item object
 * @param name
 * 		The name of the Item object
 * @param qty
 * 		The quantity of the Item object
 * @param sales
 * 		The average sales per day of the Item object
 * @param onorder
 * 		The quantity of on order of the Item object
 * @param price
 * 		The price of the Item object
 */
	public Item(String Code, String name, int qty, int sales, int onorder, 
	  double price) {
		itemCode = Code;
		this.name = name;
		qtyInStore = qty;
		averageSalesPerDay = sales;
		onOrder = onorder;
		this.price = price;
	}
	
/**
 * Decreases the value of qtyInStore by num
 * @param num
 * 		The integer to be subtracted from qtyInStore variable
 */
	public void setQty(int num) {
		this.qtyInStore = qtyInStore - num;
	}
	
/**
 * Returns the String representation of itemCode variable
 * @return
 * 		the String representation of itemCode variable
 */
	public String getItemCode() {
		return itemCode;
	}
	
/**
 * Returns the String representation of name variable
 * @return
 * 		the String representation of name variable
 */
	public String getName() {
		return name;
	}
	
/**
 * Sets the value of arrivalDay variable to num
 * @param num
 * 		The integer to be set as the value of arrivalDay variable
 */
	public void setArr(int num) {
		arrivalDay = num;
	}

/**
 * Increases the value of onOrder by num
 * @param num
 * 		the integer to be added to the value of onOrder variable
 */
	public void setOnOrder(int num) {
		this.onOrder = onOrder + num;
	}

/**
 * Returns the value of qtyInStore variable
 * @return
 * 		the integer value of qtyInStore variable
 */
	public int getQty() {
		return qtyInStore;
	}

/**
 * Returns the value of averageSalesPerDay variable
 * @return
 * 		the integer value of averageSalesPerDay variable
 */
	public int getSales() {
		return averageSalesPerDay;
	}
	
/**
 * Returns the value of onOrder variable
 * @return
 * 		the integer value of onOrder variable
 */
	public int getOn() {
		return onOrder;
	}

/**
 * Returns the value of arrivalDay variable
 * @return
 * 		the integer value of arrivalDay variable
 */
	public int getDay() {
		return arrivalDay;
	}

/**
 * Returns the value of price variable
 * @return
 * 		the integer value of price variable
 */
	public double getPrice() {
		return price;
	}

/**
 * Returns a string representation of Item object
 */
	public String toString() {
		return String.format("%-18s%-20s%3s%9s%11s%10s%15s", this.itemCode, 
		  this.name, this.qtyInStore, this.averageSalesPerDay, this.price, 
		  this.onOrder,this.arrivalDay);
	}
}
