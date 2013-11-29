/**@author Simon Lei 
 * @version 2013.11.25
 * Unit testing sign up logic in Database class.
 */

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;

public class SignUpTest {
	Database database = new Database();

	@Test
	public void TestUserAlreadyExists() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = database.add("test", "Testing_123");
		int invalid = database.add("test", "Testing_123");

		assertEquals(3, valid, 0);
		assertEquals(2, invalid, 0);
	}
	
	@Test
	public void TestUserOnlyAlphaNum() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int valid = database.add("test", "Testing_123");
		int invalid = database.add("test*bad", "Testing_123");
		
		assertEquals(3, valid, 0);
		assertEquals(6, invalid, 0);
	}
	
	@Test
	public void TestUserNotNull() {	
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int valid = database.add("test", "Testing_123");
		int invalid = database.add("", "Testing_123");
		
		assertEquals(3, valid, 0);
		assertEquals(5, invalid, 0);
	}

	@Test
	public void TestPasswordLength() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = database.add("test", "Testing_123");

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int invalid = database.add("test", "Te_123");

		assertEquals(3, valid, 0);
		assertEquals(4, invalid, 0);

	}
	
	@Test
	public void TestPasswordContainLowerCase() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = database.add("test", "Testing_123");

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int invalid = database.add("test", "TESTING_123");

		assertEquals(3, valid, 0);
		assertEquals(1, invalid, 0);
	}

	@Test
	public void TestPasswordContainUpperCase() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = database.add("test", "Testing_123");

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int invalid = database.add("test", "testing_123");

		assertEquals(3, valid, 0);
		assertEquals(1, invalid, 0);
	}
	
	@Test
	public void TestPasswordContainNonAlpha() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = database.add("test", "Testing_123");

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int invalid = database.add("test", "Testing123");

		assertEquals(3, valid, 0);
		assertEquals(1, invalid, 0);
	}
	
	@Test
	public void TestPasswordContainNumber() {
		File f = null;

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int valid = database.add("test", "Testing_123");

		try {
			f = new File("database.csv");
			f.delete();
			f.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int invalid = database.add("test", "Testing_onetwothree");

		assertEquals(3, valid, 0);
		assertEquals(1, invalid, 0);
	}

}
