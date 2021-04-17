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
 * TC033, TC034: To check validation method for string 
 */
@RunWith(JUnitParamsRunner.class)
public class StringInputValidationTest {

	ConsoleUI ui = new ConsoleUI();
	
	@Before
	public void setupMethod()
	{
		ui.setController(new Controller(new DataLists()));
		//ui.loadAllData();
	}
	
	private Object[] getValuesForStringInputValidationTestValid() {
		return new Object[] {
				new Object[] {"hello","hello"}, // word without whitespace
				new Object[] {"hello world","hello world"}, // word with whitespace
				new Object[] {"2 ;","2 ;"}, // integer and symbols
		};
	}
	
	@Test
	@Parameters(method="getValuesForStringInputValidationTestValid")
	public void stringInputValidationTestValid(String value, String expected)
	{
		String input = value;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		String actual  = ui.stringInputValidation();
		assertEquals(expected, actual);
		
	}
	
	private Object[] getValuesForStringInputValidationTestInvalid() {
		return new Object[] {
				new Object[] {""}, 	// null
				new Object[] {"\n"}, 	// null
				new Object[] {" "}, // whitespace
		};
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters(method="getValuesForStringInputValidationTestInvalid")
	public void stringInputValidationTestInvalid(String value)
	{
		String input = value;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in); // set scanner input
		
		String actual  = ui.stringInputValidation();
		
	}
}
