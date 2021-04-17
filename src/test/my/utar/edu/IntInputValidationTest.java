package my.utar.edu;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
/*
 * TC031,TC032: To check validation for integer
 */
@RunWith(JUnitParamsRunner.class)
public class IntInputValidationTest {

	ConsoleUI ui = new ConsoleUI();
	
	@Before
	public void setupMethod()
	{
		ui.setController(new Controller(new DataLists()));
		//ui.loadAllData();
	}
	
	private Object[] getValuesForIntInputValidationTestValid() {
		return new Object[] {
				new Object[] {1,1,"1",1},
				new Object[] {1,5,"1",1},
				new Object[] {1,5,"2",2},
				new Object[] {1,5,"3",3},
				new Object[] {1,5,"4",4},
				new Object[] {1,5,"5",5},
				new Object[] {0,5,"0",0},
		};
	}
	
	@Test
	@Parameters(method="getValuesForIntInputValidationTestValid")
	public void intInputValidationTestValid(int min, int max, String value, int expected)
	{
		String input = value;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		int actual  = ui.intInputValidation(min, max);
		assertEquals(expected, actual);
	}
	
	private Object[] getValuesForIntInputValidationTestInvalid() {
		return new Object[] {
				new Object[] {1,5,""}, 	// null
				new Object[] {1,5,"\n"}, // newline
				new Object[] {1,5," "}, // whitespace
				new Object[] {1,5,";"}, // symbols
				new Object[] {1,5,"s"}, // characters
				new Object[] {1,5,"9d"}, // Integer + string
				new Object[] {1,5,"-1"}, // lower than range
				new Object[] {1,5,"6"}, // higher than range
				new Object[] {1,5,"0.5"}, // decimal
				new Object[] {1,5," 1"}, // whitespace with Integer
				new Object[] {1,5,"1 "}, // Integer with whitespace
				new Object[] {1,1,"2"}, // range is same but enter outside that range
		};
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters(method="getValuesForIntInputValidationTestInvalid")
	public void intInputValidationTestInvalid(int min, int max, String value)
	{
		String input = value;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in); // set scanner input
		
		int actual  = ui.intInputValidation(min, max);
	}
}
