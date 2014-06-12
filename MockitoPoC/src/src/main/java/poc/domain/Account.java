package poc.domain;

import java.math.BigDecimal;

/**
 * Domain object for entity Account
 * 
 * @author nixx
 *
 */

public class Account {

	public String id;
	public String name;
	public BigDecimal balance;
	
	public Account() {
	}
	
	public Account(String id, String name, BigDecimal balance){
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	@Override
	public String toString() { 
		return "account ID [" + id + "] name [" + name + "] balance [" + balance + "]";
	}

}
