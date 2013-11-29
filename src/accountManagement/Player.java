package accountManagement;

import java.lang.String;

/**
 * @author ZiYang
 * @version  2013.11.25
 * Class that represent players in game play
 *	
 */
public class Player {


	public Account account;
	public int orientation;
	public final static int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	/** Class constructor
     * 
	 * @param orientation indicates the initial orientation of the player
	 * @param player imports object of Account
	 * 
	 */
	public Player(int orientation, Account player) {
		// TODO Auto-generated constructor stub
		this.orientation = orientation;
		this.account = player;
	}
	/** Modify the current orientation of a player
     * 
	 * @param orientation indicates the initial orientation of the player
	 * 
	 */
	public void setOrientation(int orientation){
		this.orientation = orientation;
	}
	
	/** Getter method for the name of the account
     * 
	 * @return The String of the name of the account
	 * 
	 */
	public String getName() {
		return account.getName();
	}
	
}
