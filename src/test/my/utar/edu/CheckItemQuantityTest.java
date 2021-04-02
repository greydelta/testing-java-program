package my.utar.edu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
/*
 * TC007, TC008: To check if total no of item is within the limit=20
 */
@RunWith(JUnitParamsRunner.class)
public class CheckItemQuantityTest {

	ConsoleUI ui = new ConsoleUI();
	
	@Before
	public void setupMethod()
	{
		ui.setController(new Controller(new DataLists()));
		ui.loadAllData();
	}
	
	private Object[] parametersForTestCheckItemQuantity() {
		return new Object[] {
				/*
				 * ItemQuantity is not exceeding 20
				 */
				new Object[] {10,1,10},//one item in current order 
				new Object[] {11,9,11},//more than one item in current order 
				new Object[] {10,0,10},//dont have any items in current order
				/*
				 * ItemQuantity is exceeding 20
				 */
				new Object[] {1,20,0},//item in current order is already 20
				new Object[] {20,1,0},//sum of item in current order + input quantity> 20
		};
	}
	
	/*
	 * TC007, TC008
	 * MUT: checkItemQuantity
	 */
	@Test
	@Parameters(method="parametersForTestCheckItemQuantity")
	public void testCheckItemQuantity(int quantity,int totalNumOfItems,int expResult) {
		ConsoleUI consMock=mock(ConsoleUI.class);
		when(consMock.promptInputQuantity(anyInt())).thenReturn(quantity);
		
		Order oMock=mock(Order.class);
		when(oMock.getTotalNumOfItems()).thenReturn(totalNumOfItems);

		int actualResult=ui.checkItemQuantity(oMock.getTotalNumOfItems(), consMock.promptInputQuantity(1));
		assertEquals(expResult,actualResult);
		
	}
	


}
