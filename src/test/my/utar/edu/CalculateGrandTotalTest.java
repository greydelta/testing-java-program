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
 * TC018,TC019,TC020
 */
@RunWith(JUnitParamsRunner.class)
public class CalculateGrandTotalTest {
	//TC018
	@Test
	public void GrandTotalTest()
	{
		ConsoleUI cu = new ConsoleUI();
		
		double result = cu.calculateGrandTotal(5, 2.50, 3);
		assertEquals(10.50, result, 0);
	}
	//TC019
	@Test
	public void GrandTotalTestZero()
	{
		ConsoleUI cu = new ConsoleUI();
		
		double result = cu.calculateGrandTotal(0, 0, 0);
		assertEquals(0, result, 0);
	}
	//TC020: Invalid arguments
	@Test(expected=IllegalArgumentException.class)
	public void GrandTotalInvalidTest()
	{
		ConsoleUI cu = new ConsoleUI();
		
		cu.calculateGrandTotal(-1, -1, -1);
	}
}
