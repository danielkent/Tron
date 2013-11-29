package system;
import accountManagement.Account;


/**
 * @author ZiYang, Simon Lei
 * @version 2013.11.28
 * This class contains the main method.
 */

public class MainSystem {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		Account playerOne = new Account();
		Account playerTwo = new Account();
		
		GameMenu menu = new GameMenu(playerOne, playerTwo);
	}
}
