package accountManagement;

/** 
 * @author Simon Lei, Daniel Dominic
 * @version  2013.11.25
 * Class which contains the necessary account information of the players
 */

import java.lang.String;


public class Account {
	
	private String name;
	private int score = 0;
	
	/**
	 * Get method.
	 * @return returns the username
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set method which changes the username stored as a property of the class.
	 * @param user
	 */
	public void setName(String user) {
		this.name = user;
	}
	
	/**
	 * Get method.
	 * @return the user's score for the current match
	 */

	public int getScore() {
		return this.score;
	}
	
	/**
	 * Increments the user's score in the current match.
	 */
	public void addWin() {
		this.score++;
	}
	
	/**
	 * Resets the user's score after the match is over.
	 */
	public void resetScore() {
		this.score = 0;
	}
	

}