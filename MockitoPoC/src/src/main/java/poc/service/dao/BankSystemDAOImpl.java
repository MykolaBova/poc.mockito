package poc.service.dao;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import poc.domain.*;

/**
 * DAO fake implementation, we just emulate data retrieve from database.  
 * 
 * @author nixx
 */

public class BankSystemDAOImpl implements BankSystemDAO {

	Logger logger = Logger.getLogger( BankSystemDAOImpl.class.getName() );
	
	/**
	 * Fake implementation of method, method just returns values depending from input value 
	 */
	@Override
	public String getPaymentReferenceNumber(String paymentID) {
		String returnValue = null;
		if (paymentID.equals("1000")) {
			returnValue =  "ref1000";
		} else if (paymentID.equals("1001")) {
			returnValue = "ref1001";
		} else if (paymentID.equals("1002")) {
			returnValue = "ref1002";
		}
		
		logger.debug("for paymentID [" + paymentID + "] return reference [" + returnValue + "]"); 

		return returnValue;
	}

	/**
	 * Method returns defined accounts list
	 * 
	 */
	@Override
	public Account[] getAccounts(String customerID) {
		
		if (!customerID.equals("realCustomerID")) {
			return null;
		}
		
		return new Account[]{ 
				new Account("realAccount1", "card account", BigDecimal.valueOf(17.56)),
				new Account("realAaccount2", "current account", BigDecimal.valueOf(12.00)),
				new Account("realAaccount3", "card account", BigDecimal.valueOf(100.00))
		};
	}

	/**
	 * Method returns defined Customer
	 */
	@Override
	public Customer getCustomerDetalis(String customerID) {
		if (!customerID.equals("realCustomerID")) {
			return null;
		}

		return new Customer("realCustomerID", "John", "Smith");
	}
	

	/**
	 * Method returns defined deposit list
	 */
	@Override
	public Deposit[] getDeposits(String customerID) {
		if (!customerID.equals("realCustomerID")) {
			return null;
		}

		return new Deposit[]{
				new Deposit("realDepID1","New Year deposit", BigDecimal.valueOf(1000), BigDecimal.valueOf(15.75) ),
				new Deposit("realDepID2","Simple deposit", BigDecimal.valueOf(2000), BigDecimal.valueOf(10.00) ),
				new Deposit("realDepID3","Easter deposit", BigDecimal.valueOf(2000), BigDecimal.valueOf(1000.00) )
		};
		
	}

}
