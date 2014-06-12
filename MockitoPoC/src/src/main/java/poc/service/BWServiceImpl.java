package poc.service;

import org.apache.log4j.Logger;

import poc.domain.Account;
import poc.domain.Customer;
import poc.domain.Deposit;
import poc.domain.Payment;

/**
 * BWService implementation, contains methods to update entities on BW side or create active operation - payment
 * 
 * @author nixx
 */

public class BWServiceImpl implements BWService {
	
	Logger logger = Logger.getLogger(BWServiceImpl.class.getName());

	@Override
	public String createPayment(String paymentReference, Payment paymentDetails) {
		log(paymentReference, paymentDetails);
		
		return paymentReference + "." + System.currentTimeMillis();
	}
	
	protected void log(String paymentReference, Payment paymentDetails) {
		logger.debug("create payment, reference [" + paymentReference + "] details:\n" + paymentDetails.toString() );
	}	

	@Override
	public void updateCustomer(Customer customer) {
		logger.debug("customer [" + customer + "] update");
	}

	@Override
	public void updateAccount(Account account) {
		logger.debug("account [" + account + "] update");
	}

	@Override
	public void updateDeposit(Deposit deposit) {
		logger.debug("deposit [" + deposit + "] update");
	}

}
