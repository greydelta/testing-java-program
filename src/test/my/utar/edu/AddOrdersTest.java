package my.utar.edu;
/*
 * TC021:To check whether the method is called after user entered all the valid data to the system
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class AddOrdersTest {

	@Test
	public void TestAddOrders()
	{
		IDataStore dmock = mock(DataLists.class);
		Controller c = new Controller(dmock);
		
		ArrayList orderItemID = new ArrayList<String>();
		orderItemID.add("1");
		ArrayList orderItemQuantity = new ArrayList<String>();
		orderItemQuantity.add("1");
		
		Order order = new Order(1, orderItemID, orderItemQuantity, 0.50, 0.30, 0.10, 55.00, false);
		c.addOrders(order);
		
		ArgumentCaptor<Order> argument = ArgumentCaptor.forClass(Order.class);
		verify(dmock).addOrders(argument.capture());
		assertEquals(1, argument.getValue().getOrderID());
		assertArrayEquals(new String[] {"1"}, argument.getValue().getOrderItemsID().toArray());
		assertArrayEquals(new String[] {"1"}, argument.getValue().getOrderItemsQuantity().toArray());
		assertEquals(0.50, argument.getValue().getOrderItemsSubTotal(), 0);
		assertEquals(0.30, argument.getValue().getOrderDeliveryCharges(), 0);
		assertEquals(0.10, argument.getValue().getOrderAdditionalCharges(), 0);
		assertEquals(55.00, argument.getValue().getOrderTotalPrice(), 0);
		assertEquals(false, argument.getValue().getOrderStatus());
	}

}
