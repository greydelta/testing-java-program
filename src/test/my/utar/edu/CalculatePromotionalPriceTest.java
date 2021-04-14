package my.utar.edu;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.mockito.ArgumentCaptor;
/*
 * TC025,TC026: To check if promotional price is processed correctly
 */
@RunWith(JUnitParamsRunner.class)
public class CalculatePromotionalPriceTest {
	
	public static ConsoleUI cu;
	
	@BeforeClass
	public static void initialiseInterface()
	{
		cu = new ConsoleUI();
		cu.setController(new Controller(new DataLists()));
		cu.loadAllData();
	}
	//TC025: Member promotional price calculation
	@Test
	public void testCalculateForMember()
	{
		Item imock = mock(Item.class);
		Member mmock = mock(Member.class);
		
		when(mmock.getFlag()).thenReturn(true);
		when(imock.getMemberPrice()).thenReturn(35.00);

		double result = cu.calculatePromotionalPrice(imock, mmock);
		
		assertEquals(33.25, result, 0);
	}
	
	
	//TC025: Non Member promotional price calculation
	@Test
	public void testCalculateForNonMember()
	{
		Item imock = mock(Item.class);
		Member mmock = mock(Member.class);
		
		when(mmock.getFlag()).thenReturn(false);
		when(imock.getNonMemberPrice()).thenReturn(38.00);

		double result = cu.calculatePromotionalPrice(imock, mmock);
		
		assertEquals(36.1, result, 0);
	}
}
