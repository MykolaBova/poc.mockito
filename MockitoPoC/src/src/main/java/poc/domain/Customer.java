package poc.domain;

/**
 * Domain object for entity Customer
 * 
 * @author nixx
 *
 */

public class Customer {
	
	public String customerID;
	public String name;
	public String surname;
	
	public Customer(){
	}
	
	public Customer(String customerID, String name, String surname){
		this.customerID = customerID;
		this.name = name;
		this.surname = surname;
	}
	
	@Override
	public String toString() { 
		return "customerID [" + customerID + "] name [" + name + "] surname [" + surname + "]";
	}
	
}
