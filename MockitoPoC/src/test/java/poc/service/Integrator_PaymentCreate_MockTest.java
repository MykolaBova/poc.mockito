package poc.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import poc.domain.Payment;
import poc.integration.*;
import poc.service.dao.BankSystemDAO;

import org.apache.log4j.PropertyConfigurator;
import org.junit.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Unit test that test Integration manager functions using BWService and BankSystemDAO mock objects.  
 * 
 * @author nixx
 */

public class Integrator_PaymentCreate_MockTest {
	
	@BeforeClass
	public static void initTestEnvironment(){
		PropertyConfigurator.configure("src/test/resources/logger.properties");
	}

	@Test
	public void paymentCreateWithRealSystemsSuccess() {
		
		// create mock objects
		BWService bwServiceMock = mock(BWService.class);
		BankSystemDAO daoMock = mock(BankSystemDAO.class);
		
		// define behavior for method 'createPayment'
		when(bwServiceMock.createPayment(anyString(), any(Payment.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				String paymentReference = (String) args[0];
				
				return paymentReference + "." + System.currentTimeMillis();
			}
			
		});
		
		// describe expected behavior for mock objects
		when(daoMock.getPaymentReferenceNumber("9999")).thenReturn("pmt9999Reference");
		
		Integrator manager = new ConcreteIntegrator();
		
		// inject mock objects into Integration Manager instance
		((ConcreteIntegrator)manager).setBwService(bwServiceMock);
		((ConcreteIntegrator)manager).setDao(daoMock);
		
		// create payment instance
		Payment payment = new Payment();
		payment.id = "9999";
		payment.currency = "USD";
		payment.sourceAccount = "SourceAccountValue";
		payment.targetAccount = "TargetAccountValue";
		payment.amount = BigDecimal.valueOf(10.56);
		
		final String paymentReference = manager.processPayment(payment);
		
		checkReferenceAndPrint(paymentReference);
		
		// BWServer check: we should check, if method 'createPayment" is called
		verify(bwServiceMock, times(1)).createPayment("pmt9999Reference", payment);
		
		// also, we verify, that we don't call other methods in BWService
		verifyNoMoreInteractions(bwServiceMock);
		
		// BankSystemDAO check
		verify(daoMock, times(1)).getPaymentReferenceNumber("9999");
		verifyNoMoreInteractions(daoMock);
	}
	
	@Test
	public void spyForBWService() {

		// create instance to real implementation. Also, we create "spy" to this instance 
		BWServiceImpl bwServceSpy = spy(new BWServiceImpl());
		
		Integrator manager = new ConcreteIntegrator();
		((ConcreteIntegrator)manager).setBwService(bwServceSpy);
		
		Payment payment = new Payment();
		payment.id = "1001";
		payment.currency = "USD";
		payment.sourceAccount = "SourceAccountValue";
		payment.targetAccount = "TargetAccountValue";
		payment.amount = BigDecimal.valueOf(10.56);
		
		final String paymentReference = manager.processPayment(payment);

		checkReferenceAndPrint(paymentReference);
		
		// we verify that method is called on real object
		verify(bwServceSpy).log(anyString(), any(Payment.class) );	
	}

	private void checkReferenceAndPrint(final String paymentReference) {
		assertNotNull("payment reference", paymentReference);
		assertFalse("payment reference", paymentReference.isEmpty());
		System.out.println("created paymentReference [" + paymentReference + "]");
	}

}
