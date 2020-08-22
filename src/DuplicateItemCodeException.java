/**
 * The DuplicateItemCodeException class creates an instance of 
 * DuplicateItemCodeException Exception.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 6 R09
 *
 */
public class DuplicateItemCodeException extends Exception{
	public DuplicateItemCodeException(Item x) {
		super("\n" + x.getItemCode() + ": Cannot add item as item code already "
		  + "exists.\n");
	}
}
