package poc.domain;

import java.math.BigDecimal;

/**
 * Domain object for entity Payment
 * 
 * @author nixx
 *
 */
public class Payment {
	public String id;
	public String sourceAccount;
	public String targetAccount;
	public String currency;
	public BigDecimal amount;
	
	@Override
	public String toString() {
		return "paymentID [" + id + "] source [" + sourceAccount + "] target [" + targetAccount + "] currency [" + currency + "] amount [" + amount + "]";
	}
	
}
