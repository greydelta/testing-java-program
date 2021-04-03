package my.utar.edu;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.mockito.ArgumentCaptor;

// @@Assumptions@@//
/* The calculation for promotional item will be Item 11 - Absolute Durian
 * The calculation for non-promotional item will be item 1 - Butter Cake
 * The total quantity uses is only 1
 * TC009,TC010,TC011,TC012
 */

@RunWith(JUnitParamsRunner.class)
public class CalculateItemPriceTest {
	
	public static ConsoleUI cu;
	
	@BeforeClass
	public static void initialiseInterface()
	{
		cu = new ConsoleUI();
		cu.setController(new Controller(new DataLists()));
		cu.loadAllData();
	}
	//TC009
	@Test
	public void testCalculateForMemberWithPromotion()
	{
		Item imock = mock(Item.class);
		Member mmock = mock(Member.class);
		
		when(mmock.getFlag()).thenReturn(true);
		when(imock.getMemberPrice()).thenReturn(135.00);
		when(imock.getPromotionalItem()).thenReturn('Y');

		double result = cu.calculateItemPrice(imock, 1, mmock);
		
		assertEquals(128.25, result, 0);
	}
	//TC010
	@Test
	public void testCalculateForMemberWithoutPromotion()
	{
		Item imock = mock(Item.class);
		Member mmock = mock(Member.class);
		
		when(mmock.getFlag()).thenReturn(true);
		when(imock.getMemberPrice()).thenReturn(46.00);
		when(imock.getPromotionalItem()).thenReturn('N');

		double result = cu.calculateItemPrice(imock, 1, mmock);
		
		assertEquals(46.00, result, 0);
	}
	//TC011
	@Test
	public void testCalculateForNonMemberWithPromotion()
	{
		Item imock = mock(Item.class);
		Member mmock = mock(Member.class);
		
		when(mmock.getFlag()).thenReturn(true);
		when(imock.getMemberPrice()).thenReturn(140.00);
		when(imock.getPromotionalItem()).thenReturn('Y');

		double result = cu.calculateItemPrice(imock, 1, mmock);
		
		assertEquals(133, result, 0);
	}
	//TC012
	@Test
	public void testCalculateForNonMemberWithoutPromotion()
	{
		Item imock = mock(Item.class);
		Member mmock = mock(Member.class);
		
		when(mmock.getFlag()).thenReturn(true);
		when(imock.getMemberPrice()).thenReturn(46.80);
		when(imock.getPromotionalItem()).thenReturn('N');

		double result = cu.calculateItemPrice(imock, 1, mmock);
		
		assertEquals(46.80, result, 0);
	}
}
