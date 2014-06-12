package poc.integration;

import org.apache.log4j.Logger;

import poc.domain.*;
import poc.exception.IntegrationException;
import poc.exception.UpdateException;
import poc.service.BWService;
import poc.service.BWServiceImpl;
import poc.service.dao.BankSystemDAO;
import poc.service.dao.BankSystemDAOImpl;

/**
 * Integrator implementation class, businness methods bodies.
 * Class contains references to BWService and BankSystemDAO objects.
 * 
 * @author nixx
 */
public class ConcreteIntegrator implements Integrator {
	
	private Logger logger = Logger.getLogger( ConcreteIntegrator.class.getName() );
	
	private BWService bwService;
	private BankSystemDAO dao;
	
	public ConcreteIntegrator() {
		this.bwService = new BWServiceImpl();
		this.dao = new BankSystemDAOImpl();
	}

	public ConcreteIntegrator(BWService bwService, BankSystemDAO dao) {
		this.bwService = bwService;
		this.dao = dao;
	}


	@Override
	public String processPayment(Payment payment) {
		
		String paymentReference = dao.getPaymentReferenceNumber(payment.id);
		logger.debug("receive payment reference [" + paymentReference + "]");
		
		String ref = bwService.createPayment(paymentReference, payment);
		logger.debug("payment created, reference [" + ref + "]");
		
		return ref;
	}

	@Override
	public void connectCustomerToBW(String customerID) throws IntegrationException {
		
		try {
			updateCustomerDetails(customerID);
			updateAccounts(customerID);
			updateDeposit(customerID);
		} catch (Exception ex){
			throw new IntegrationException(ex);
		}

	}

	private void updateCustomerDetails(String customerID) throws UpdateException {
		Customer cust = dao.getCustomerDetalis(customerID);
		logger.debug("receive from DAO info about customer [" + cust + "]");
		
		bwService.updateCustomer(cust);
	}

	private void updateAccounts(String customerID) throws UpdateException {

		Account[] accounts = dao.getAccounts(customerID);

		logger.debug("receive from DAO info about [" + accounts.length + "] accounts");
		for (int i = 0; i < accounts.length; i++) {
			bwService.updateAccount(accounts[i]);
		}
	}

	private void updateDeposit(String customerID) throws UpdateException {
		
		Deposit[] deposits = dao.getDeposits(customerID);
		logger.debug("receive from DAO info about [" + deposits.length + "] deposits");
		for (int i = 0; i < deposits.length; i++) {
			bwService.updateDeposit(deposits[i]);
		}
	}


	public void setBwService(BWService bwService) {
		this.bwService = bwService;
	}

	public void setDao(BankSystemDAO dao) {
		this.dao = dao;
	}

	public BWService getBwService() {
		return bwService;
	}

	public BankSystemDAO getDao() {
		return dao;
	}
	
}
