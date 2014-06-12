package poc.service.dao;

import poc.domain.*;

/**
 * Data Access Object interface for Bank's interface, define methods to access info about entities
 * 
 * @author nixx
 */

public interface BankSystemDAO {
	
	public String getPaymentReferenceNumber(String paymentID);
	
	public Account[] getAccounts(String customerID);
	public Customer getCustomerDetalis(String customerID);
	public Deposit[] getDeposits(String customerID);
	
}
