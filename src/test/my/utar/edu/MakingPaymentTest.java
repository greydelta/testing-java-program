package my.utar.edu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
/*
 * TC022 To check if valid payment choice can be processed accordingly
 * Note: Choice is 1 for Online Banking, 2 for Credit Card
 */
@RunWith(JUnitParamsRunner.class)
public class MakingPaymentTest {

	ConsoleUI ui = new ConsoleUI();
	
	@Before
	public void setupMethod()
	{
		ui.setController(new Controller(new DataLists()));
		ui.loadAllData();
	}
	
	private Object[] parametersForTestMakingPayment() {
		return new Object[] {
				new Object[] {"Online Banking","Fail",1,100.5,"Successful"},
				new Object[] {"Credit Card","Fail",2,100.5,"Successful"}

		};
	}
	//TC022: Test for valid arguments
	@Test
	@Parameters(method="parametersForTestMakingPayment")
	public void testMakingPayment(String paymentMethod,String paymentStatus,int choice,double totalPrice,String expResult) {
		
		Payment paymentMock = mock(Payment.class);
		when(paymentMock.makingPayment(paymentMethod, paymentStatus,totalPrice)).thenReturn("Successful");	
		
		String actualPaymentStatus=ui.makingPayment(choice, totalPrice, paymentMock);
		assertEquals(expResult,actualPaymentStatus);
		
	}
	
	private Object[] parametersForTestMakingPaymentError() {
		return new Object[] {
				new Object[] {0,100.5},
				new Object[] {4,100.5},

		};
	}
	
	}
}
