package my.utar.edu;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*
 * TC028, TC029: to integrate all the calculations involved in order
 */
@RunWith(JUnitParamsRunner.class)
public class OrderCalculationIntegrationTest {
	ConsoleUI ui = new ConsoleUI();

	@Before
	public void setupMethod()
	{
		ui.setController(new Controller(new DataLists()));
		ui.loadAllData();
	}
	
	
	/*
	 * TC028
	 * Rule 3&4: Different area category, subtotal is 20, so additional charge is considered
	 */
	private Object[] parametersForTestOrderCalculationV1() {
		return new Object[] {
				/*Member */
				new Object[] {true,"MT",25.5}, //Area Category 1: MT is code for Masjid Tanah
				new Object[] {true,"KS",26.0}, //Area Category 2: KS is code for Kuala Sungai
				new Object[] {true,"BR",26.5}, //Area Category 3: BR is code for Bukit Beruang
				new Object[] {true,"JN",27.0}, //Area Category 4: JN is code for Jasin
				new Object[] {true,"UP",27.5}, //Area Category 5: UP is code for Ujong Pasir
				new Object[] {true,"BH",28.0},//Area Category 6: BH is code for Bandar Hilir
				/*Non Member*/
				new Object[] {false,"MT",25.5}, //Area Category 1: MT is code for Masjid Tanah
				new Object[] {false,"KS",26.0}, //Area Category 2: KS is code for Kuala Sungai
				new Object[] {false,"BR",26.5}, //Area Category 3: BR is code for Bukit Beruang
				new Object[] {false,"JN",27.0}, //Area Category 4: JN is code for Jasin
				new Object[] {false,"UP",27.5}, //Area Category 5: UP is code for Ujong Pasir
				new Object[] {false,"BH",28.0}//Area Category 6: BH is code for Bandar Hilir
		};
	}
	
	@Test
	@Parameters(method="parametersForTestOrderCalculationV1")
	public void testOrderCalculationV1(boolean isMember,String areaCode,double expResult) {
		
		//setting member price: 10 X 2 (qty), just to make sure subtotal is 20 (as wanted in decision table)	
		Item anItem=new Item();
		if(!isMember) {
			anItem.setNonMemberPrice(10);
		}
		else {
			anItem.setMemberPrice(10);
		}
		
		
		Member aMember=new Member();
		aMember.setFlag(isMember);
		aMember.setArea(areaCode);
		
		double itemTotal=ui.calculateItemPrice(anItem,2 , aMember);
		
		double delCharge=ui.calculateDeliveryCharge(aMember.getArea());
		double addCharge=ui.calculateAdditionalCharge(itemTotal);//? not sure
		double actualResult=ui.calculateGrandTotal(itemTotal, delCharge, addCharge);
		
		
		assertEquals(expResult,actualResult,0.01);	
	}
	
	
	/*
	 * TC029
	 * Rule 5&6: Registered Member, different area category, subtotal is 30, so additional charge is not considered
	 */
	private Object[] parametersForTestOrderCalculationV2() {
		return new Object[] {
				/*Member */
				new Object[] {true,"MT",32.5}, //Area Category 1: MT is code for Masjid Tanah
				new Object[] {true,"KS",33.0}, //Area Category 2: KS is code for Kuala Sungai
				new Object[] {true,"BR",33.5}, //Area Category 3: BR is code for Bukit Beruang
				new Object[] {true,"JN",34.0}, //Area Category 4: JN is code for Jasin
				new Object[] {true,"UP",34.5}, //Area Category 5: UP is code for Ujong Pasir
				new Object[] {false,"BH",35.0},//Area Category 6: BH is code for Bandar Hilir
				/*Non member*/
				new Object[] {false,"MT",32.5}, //Area Category 1: MT is code for Masjid Tanah
				new Object[] {false,"KS",33.0}, //Area Category 2: KS is code for Kuala Sungai
				new Object[] {false,"BR",33.5}, //Area Category 3: BR is code for Bukit Beruang
				new Object[] {false,"JN",34.0}, //Area Category 4: JN is code for Jasin
				new Object[] {false,"UP",34.5}, //Area Category 5: UP is code for Ujong Pasir
				new Object[] {false,"BH",35.0}//Area Category 6: BH is code for Bandar Hilir
				
		};
	}
	
	@Test
	@Parameters(method="parametersForTestOrderCalculationV2")
	public void testOrderCalculationV2(boolean isMember,String areaCode,double expResult) {
		//setting member price: 15 X 2 (qty), just to get subtotal of 30 (to achieve item total as set in decision table)
		
		Item anItem=new Item();
		if(!isMember) {
			anItem.setNonMemberPrice(15);
		}
		else {
			anItem.setMemberPrice(15);
		}
		
		Member aMember=new Member();
		aMember.setFlag(isMember);
		aMember.setArea(areaCode);
		
		double itemTotal=ui.calculateItemPrice(anItem,2 , aMember);
		
		double delCharge=ui.calculateDeliveryCharge(aMember.getArea());
		double addCharge=ui.calculateAdditionalCharge(itemTotal);
		double actualResult=ui.calculateGrandTotal(itemTotal, delCharge, addCharge);
		
		
		assertEquals(expResult,actualResult,0.01);	
	}
	


}
