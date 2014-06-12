package poc.integration;

import poc.domain.Payment;
import poc.exception.IntegrationException;

/**
 * Integrator interface, provides business methods to create operations
 * in system BW
 *  
 * @author nixx
 *
 */
public interface Integrator {
	
	public String processPayment(Payment paymentDetails);
	public void connectCustomerToBW(String customerID) throws IntegrationException;
	
}
