package poc.domain;

import java.math.BigDecimal;

/**
 * Domain object for entity Deposit
 * 
 * @author nixx
 *
 */
public class Deposit {
	
	public String id;
	public String name;
	public BigDecimal amount;
	public BigDecimal interestRate;
	
	public Deposit(){
	}
	
	public Deposit(String id, String name, BigDecimal amount, BigDecimal interestRate){
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.interestRate = interestRate;
	}
	
	@Override
	public String toString() { 
		return "depositID [" + id + "] name [" + name + "] amount [" + amount + "] interestRate [" + interestRate + "]";
	}

	
}
