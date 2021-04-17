package my.utar.edu;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;
import junitparams.JUnitParamsRunner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*
 * TC030: To check the order list can be updated successfully
 */
@RunWith(JUnitParamsRunner.class)
public class OrderIntegrationTest {
	ConsoleUI ui = new ConsoleUI();

	@Before
	public void setupMethod()
	{
		ui.setController(new Controller(new DataLists()));
		ui.loadAllData();
	}
	@Test
	public void TestOrderIntegration() {
		List<Order> orderList = ui.getController().getAllOrders();
		int orderCountBefore=orderList.size();
		
		double itemTotal=30.0,deliveryCharge=2.5,additionalCharge=0,totalPrice=32.5;
			
		Member loginValid = ui.getController().getAllMembers().get(0);
		System.out.println("Order list count before for member: "+loginValid.getName()+" is "+orderCountBefore);	
		
    	Order order=new Order();
    	order.setOrderItemsSubTotal(itemTotal);
    	order.setOrderDeliveryCharges(deliveryCharge);
    	order.setOrderAdditionalCharges(additionalCharge);
    	order.setOrderTotalPrice(totalPrice);
    	
    	ui.getController().addOrders(order);
		
		int orderCountAfter=orderList.size();
		
		
		assertEquals(1, orderCountAfter);
		System.out.println("Order list count after for member: "+loginValid.getName()+" is "+orderCountAfter);
		
	}
	



}
