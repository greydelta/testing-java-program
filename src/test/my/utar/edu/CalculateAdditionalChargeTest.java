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
/*
 * TC015,TC016,TC017: To check if additional charges are processed and calculated correctly
 */
@RunWith(JUnitParamsRunner.class)
public class CalculateAdditionalChargeTest {
	//TC015
	@Test
	public void testAdditionalChargeforItemTotalInRange()
	{
		ConsoleUI cu = new ConsoleUI();
		
		double result = cu.calculateAdditionalCharge(15);
		assertEquals(3, result, 0);
	}
	//TC016
	@Test
	public void testAdditionalChargeforItemTotalOver25()
	{
		ConsoleUI cu = new ConsoleUI();
		
		double result = cu.calculateAdditionalCharge(30);
		assertEquals(0, result, 0);
	}
	//TC017
	@Test(expected=IllegalArgumentException.class)
	public void testAdditionalChargeforItemTotalNotInRange()
	{
		ConsoleUI cu = new ConsoleUI();
		
		cu.calculateAdditionalCharge(0);
	}
}
