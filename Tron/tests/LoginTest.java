/**@author Simon Lei 
 * @version 2013.11.25
 * Unit testing login logic in Database class.
 */

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


public class LoginTest {
	Database database = new Database();

	@Test
	public void TestValid() {
		File f = null;
		
		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int added = database.add("test", "Testing_123");
		boolean valid = database.verify("test", "Testing_123");
		
		assertEquals(3, added, 0);
		assertTrue(valid);
	}
	
	@Test
	public void TestInvalidUser() {
		File f = null;
		
		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int added = database.add("test", "Testing_123");
		boolean Invalid = database.verify("testtwo", "Testing_123");
		
		assertEquals(3, added, 0);
		assertFalse(Invalid);	
	}
	
	@Test
	public void TestInvalidPassword() {
		File f = null;
		
		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int added = database.add("test", "Testing_123");
		boolean Invalid = database.verify("test", "Testing_12345");
		
		assertEquals(3, added, 0);
		assertFalse(Invalid);	
	}
}
