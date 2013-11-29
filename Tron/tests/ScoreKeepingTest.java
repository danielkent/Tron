

/**@author Simon Lei 
 * @version 2013.11.25
 * Unit testing login logic in Database class.
 */


import static org.junit.Assert.*;

import org.junit.Test;

import accountManagement.Account;
import accountManagement.Database;

public class ScoreKeepingTest {

	@Test
	public void TestAddWin() {
		Account player = new Account();
		player.addWin();
		assertEquals(1,player.getScore(),0);
	}

	@Test
	public void TestResetScore() {
		Account player = new Account();
		player.addWin();
		assertEquals(1,player.getScore(),0);
		player.resetScore();
		assertEquals(0,player.getScore(),0);
	}

}
