package gamePlay;

import accountManagement.*;


/**
 * class that controls the winning condition of a single game play
 * @author ZiYang, Simon Lei
 * @version  2013.11.25
 */
public class GamePlay {
    
    public Player player1;
    public Player player2;
    public GridMap gameplayMap;
    
    /** Class constructor
     * 
	 * @param selectedMap imports object of GridMap
	 * @param playerOne imports object of Account
	 * @param playerTwo imports object of Account
	 * 
	 */
    public GamePlay(GridMap selectedMap, Account playerOne, Account playerTwo) {
            // TODO Auto-generated constructor stub
            player1 = new Player(Player.RIGHT, playerOne);
            player2 = new Player(Player.LEFT, playerTwo);
            this.gameplayMap = selectedMap;
    }
    
    /** Determine winning condition for player1
     * 
	 * @param xPlayerCoord that represents the current x coordinate of the opponent
	 * @param yPlayerCoord that represents the current y coordinate of the opponent
	 * @return a boolean which true for player1 wins and false otherwise
	 */
    public boolean player1Wins(int xPlayerCoord, int yPlayerCoord){
        if(gameplayMap.getCoordinateValue(xPlayerCoord, yPlayerCoord) == 1 ||
                xPlayerCoord == 0 || yPlayerCoord == 0 || xPlayerCoord == gameplayMap.xDimension-1 || yPlayerCoord == gameplayMap.yDimension-1 ) {
                     return true;
             }return false;
             
    }
    
    /** Determine winning condition for player2
     * 
	 * @param xPlayerCoord that represents the current x coordinate of the opponent
	 * @param yPlayerCoord that represents the current y coordinate of the opponent
	 * @return a boolean which true for player2 wins and false otherwise
	 */
    public boolean player2Wins(int xPlayerCoord, int yPlayerCoord){
        if(gameplayMap.getCoordinateValue(xPlayerCoord, yPlayerCoord) == 1 ||
                xPlayerCoord == 0 || yPlayerCoord == 0 || xPlayerCoord == gameplayMap.xDimension-1 || yPlayerCoord == gameplayMap.yDimension-1 ) {
                     return true;
             }return false;
    }
            
}
