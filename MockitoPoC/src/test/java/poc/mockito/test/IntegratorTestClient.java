package mockito.test;

import java.math.BigDecimal;

import poc.domain.Payment;
import poc.exception.IntegrationException;
import poc.integration.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test client class, calls real class implementation. Using this approach
 * we can test our application only using real environment
 *  
 * @author nixx
 *
 */
public class IntegratorTestClient {
	
	@BeforeClass
	public static void initTestEnvironment(){
		PropertyConfigurator.configure("src/test/resources/logger.properties");
	}

	@Test
	public void paymentCreateWithRealSystemsSuccess() {
		
		// create real integrator object instance
		Integrator manager = new ConcreteIntegrator();
		
		// create payment object instance, this instance will be passed to manager to
		// create payment
		Payment payment = new Payment();
		payment.id = "1001";
		payment.currency = "USD";
		payment.sourceAccount = "SourceAccountValue";
		payment.targetAccount = "TargetAccountValue";
		payment.amount = BigDecimal.valueOf(10.56);
		
		// execute integrator and print created payment reference
		final String paymentReference = manager.processPayment(payment);
		System.out.println("created paymentReference [" + paymentReference + "]");
	}
	
	@Test
	public void connectCustomer() throws IntegrationException {
		// create real integrator object instance	
		Integrator manager = new ConcreteIntegrator();
		
		// call method to connect customer to BW, using this approach possible to check
		// what happens on integration only check's log files on target system (for example BW)
		manager.connectCustomerToBW("realCustomerID");
	}
	
	
}
