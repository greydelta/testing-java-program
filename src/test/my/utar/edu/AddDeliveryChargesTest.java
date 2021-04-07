package my.utar.edu;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
/*
 * TC027
 */
public class AddDeliveryChargesTest {

	@Test
	public void AddDeliveryChargesTest()
	{
		IDataStore dmock = mock(DataLists.class);
		Controller c = new Controller(dmock);
		
		c.addDeliveryCharges("AG", "Alor Gajah", 2.50);
		
		ArgumentCaptor<Delivery> argument = ArgumentCaptor.forClass(Delivery.class);
		verify(dmock).addDeliveryCharges(argument.capture());
		assertEquals("AG", argument.getValue().getAreaCode());
		assertEquals("Alor Gajah", argument.getValue().getDeliveryArea());
		assertEquals(2.50, argument.getValue().getDeliveryCharges(),0);
	}
}
