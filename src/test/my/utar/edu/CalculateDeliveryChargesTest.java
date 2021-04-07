package my.utar.edu;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.mockito.ArgumentCaptor;
/*
 * TC013,TC014: To check if the delivery charges is correct
 *	We designed our system the variable area is an abbreviation of each delivery area 
 *	Eg: Area value for Ayer Keroh is AK
 * Please refer to delivery_charges for the complete list of area and its corresponding abbreviation
 */
@RunWith(JUnitParamsRunner.class)
public class CalculateDeliveryChargesTest {
	
	public static ConsoleUI cu;
	
	@BeforeClass
	public static void initialiseInterface()
	{
		cu = new ConsoleUI();
		cu.setController(new Controller(new DataLists()));
		cu.loadAllData();
	}
	
	// TC013
	@Test
	@FileParameters("delivery_charges_forTest.txt") //read data from text file
	public void TestDeliveryCharges(String area, double expected)
	{
		double result = cu.calculateDeliveryCharge(area);
		assertEquals(expected, result, 0);
	}
	
	//TC014
	@Test(expected=IllegalArgumentException.class)
	@FileParameters("delivery_charges_forIlllegalTest.txt") //read data from text file
	public void TestDeliveryFailCharges(String area)
	{
		cu.calculateDeliveryCharge(area);
	}
}
