package gamePlay;


/** 
 * @author ZiYang, Simon Lei
 * @version  2013.11.25
 * Class which handles game map logic.
 */
public class GridMap {


	public int[][] coordinate;
	public int[][] obstacles;
	public int xDimension, yDimension;
	/** Class constructor
	 * 
	 * @param xDimension the length in the x direction. 
	 * @param yDimension the length in the y direction.
	 */
	// 10 units amount to the length of 1 block on the map.
	public GridMap(int xDimension, int yDimension){
		coordinate = new int[yDimension][xDimension];
		for(int i = 0; i < yDimension;i++ ){
			for(int j = 0; j < xDimension;j++ ){
				coordinate[i][j] = 0;
			}
		}
		this.xDimension = xDimension;
		this.yDimension = yDimension;
	}
	
	/** Class constructor
	 * 
	 * @param xDimension the length in the x direction. 
	 * @param yDimension the length in the y direction.
	 * @param xObstacle[] the location of the obstacles in the x-axis.
	 * @param xObstacle[] the location of the obstacles in the y-axis.
	 */
	// xObstacle and yObstacle contain the coordinates of the bottom-left and upper-right corners of the obstacles
	public GridMap(int xDimension, int yDimension,int xObstacle[], int yObstacle[]){
		coordinate = new int[yDimension][xDimension];
		for(int i = 0; i < yDimension;i++ ){
			for(int j = 0; j < xDimension;j++ ){
				coordinate[i][j] = 0;
			}
		}
		
		int index = 0;
		while (index < xObstacle.length) {
			for(int i = yObstacle[index]; i < yObstacle[index+1]; i++ ){
				for(int j = xObstacle[index]; j < xObstacle[index+1]; j++ ){
					coordinate[i][j] = 1;
				}
			}
			index = index + 2;
		}
		this.xDimension = xDimension;
		this.yDimension = yDimension;
	}
	
    /** 
    * @param x the x coordinate.
    * @param y the y coordinate.
    * @return whether or not the input coordinate is occupied.
    */
	public int getCoordinateValue(int x, int y) {
		 return coordinate[y][x];
	}
	  
	/** A method to set the input coordinate to occupied.
	 * 
	 * @param xPosition the x-coordinate.
	 * @param yPosition the y-coordinate.
	 */
	public void occupyBlock(int xPosition, int yPosition) {
		 coordinate[yPosition][xPosition] = 1;
	}

}
