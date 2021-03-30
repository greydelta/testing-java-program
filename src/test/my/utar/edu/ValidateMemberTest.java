package my.utar.edu;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
/*
 * TC003, TC004, TC005 to check whether member is valid
 */
@RunWith(JUnitParamsRunner.class)
public class ValidateMemberTest {
	
	public static ConsoleUI cu;
	
	@BeforeClass
	public static void initialiseInterface()
	{
		cu = new ConsoleUI();
		cu.setController(new Controller(new DataLists()));
		cu.loadAllData();
	}
	/*
	 * TC003 with valid username and password
	 */
	@Test
	@Parameters({"aw, aw, 0"})
	public void testLoginValid(String name, String pass, int index)
	{
		List<Member> members = cu.getController().getAllMembers();
		Member expected = members.get(index);
		Member actual = cu.validateMember(name, pass);
		assertEquals(expected, actual);
	}
	/*
	 * TC004 with valid username but wrong password
	 * TC005 with invalid username and invalid password
	 */
	@Test
	@Parameters({"aw, ewdaw", "a, b"})
	public void testLoginInvalid(String name, String pass)
	{
		Member actual = cu.validateMember(name, pass);
		assertFalse(actual.getFlag());
	}
}
