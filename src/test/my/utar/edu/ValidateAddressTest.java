package my.utar.edu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import junitparams.*;
/*
 * TC001, TC002 to check for valid state (int=4 for Melaka)
 * Test data is read from text files
 */
@RunWith(JUnitParamsRunner.class)
public class ValidateAddressTest {

public static ConsoleUI cu;
	
	private static ArrayList<String[]> linesRead = new ArrayList<String[]>(); 
	/*
	 * Read the data from text file
	 */
	@BeforeClass
	public static void initialiseInterface()
	{
		cu = new ConsoleUI();
		cu.setController(new Controller(new DataLists()));
		
		String fileName = "test_values_for_validate_address.txt";
		Scanner inputStream = null; // constant

		try {
			inputStream = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}

		// store into arraylist
		while (inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split("/");
			linesRead.add(tokens);
		}
	}
	
	/*
	 * TC001 & TC002 Read state as int 
	 *  Valid: 4 - Melaka	
	 *  Invalid: 1- Johor, 2- Kedah, 3- Kelantan, 5- N.Sembilan, 6- Pahang
	 *  	 	7- Penang, 8- Perak, 9- Perlis, 10- Sabah, 11- Sarawak
 			 	12- Selangor, 13- Terengganu	
	 */
	@Test
	public void TestValidateAddress()
	{
		System.out.println("<<Test Results for TestValidateAddress()>>");
		for (String[] strArray : linesRead){
			int expected, actual;
			
			expected = Integer.parseInt(strArray[1].toString());
			actual = cu.validateAddress(Integer.parseInt(strArray[0].toString()));
			
			for (int i = 0; i < strArray.length; i++){
				if (i == 0)
					System.out.print("value to test: " + strArray[i]);
				else if (i== 1) {
					System.out.print("\texpected: " + strArray[i]);
					System.out.print("\tactual: " + actual);
					if(expected == actual)
						System.out.print("\tresult: Pass\n");
					else
						System.out.print("\tresult: Fail\n");
				}
			}
			assertEquals("Fail\n", expected, actual);
		}
	}
	
}
