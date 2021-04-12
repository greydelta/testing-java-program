package my.utar.edu;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
/*
 * TC024
 */
public class AddItemTest {

	@Test
	public void AddItemTest()
	{
		IDataStore dmock = mock(DataLists.class);
		Controller c = new Controller(dmock);
		
		c.addItem("Butter Cake", "Cake", 46.00, 46.80, 'N');
		
		ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
		verify(dmock).addItem(argument.capture());
		assertEquals("Butter Cake", argument.getValue().getItemName());
		assertEquals("Cake", argument.getValue().getItemType());
		assertEquals(46.00, argument.getValue().getMemberPrice(), 0);
		assertEquals(46.80, argument.getValue().getNonMemberPrice(), 0);
		assertEquals('N', argument.getValue().getPromotionalItem());
	}

}
