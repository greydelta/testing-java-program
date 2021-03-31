package my.utar.edu;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
/*
 * TC006: To check if member can be added to datalist, with the correct values
 */
public class AddMemberTest {

	@Test
	public void TestAddMember()
	{	
		IDataStore dmock = mock(DataLists.class);
		Controller c = new Controller(dmock);
		
		c.addMember("Fai", "123456", "0123456789", true, "No 17", "Jalan Woo", "TI", "District", "70521", 3);
		
		ArgumentCaptor<Member> argument = ArgumentCaptor.forClass(Member.class);
		verify(dmock).addMember(argument.capture());
		assertEquals("Fai", argument.getValue().getName());
		assertEquals("123456", argument.getValue().getPassword());
		assertEquals("0123456789", argument.getValue().getPhoneNumber());
		assertEquals(true, argument.getValue().getFlag());
		assertEquals("No 17", argument.getValue().getUnitNumber());
		assertEquals("Jalan Woo", argument.getValue().getStreetName());
		assertEquals("TI", argument.getValue().getArea());
		assertEquals("District", argument.getValue().getDistrict());
		assertEquals("70521", argument.getValue().getPostalCode());
		assertEquals(3, argument.getValue().getState());
	}
	

}
