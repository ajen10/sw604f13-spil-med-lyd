package dk.aau.cs.giraf.cars.gamecode;

import android.graphics.Rect;

/**
 * This class divides the screen, to easy get x & y coordinates later.
 *
 */
public final class MapDivider {

	public static int lanes = 3;
	public static int width = 1;
	public static int height = 2;
	public static int totalObstacleWidth;
	public static int totalObstacleHeight;
	public static int obstacleWidth;
	public static int obstacleHeight;
	public static int obstacleSpace;
	public static int mapHeight;
	public static int mapYStart;
	public static int mapYEnd;
	public static int[] laneCenters;
	private static final int CAR_Y_SPEED_FACTOR = 255;
	
	private MapDivider() {}
	/**
	 * divides the map into 7.5 squares
	 * Calculates object width & height. The start & end Y of the map.
	 * finds the center of the lanes.
	 * @param gameView
	 */
	public static void CalculateConstants(GameView gameView) {
		width = gameView.getWidth();
		height = gameView.getHeight();
		
		totalObstacleWidth = (int) (width/7.5);
		totalObstacleHeight = totalObstacleWidth;
		obstacleSpace = (int) (totalObstacleWidth*0.025);
		obstacleWidth = (int) (totalObstacleWidth*0.95);
		obstacleHeight = obstacleWidth;
		mapHeight = totalObstacleWidth*3;
		mapYStart = (height - mapHeight) / 2;
		mapYEnd = mapHeight + mapYStart;
		
		int laneHeight = mapHeight / 3;
		laneCenters = new int[lanes];
		for (int i = 0; i < lanes; i++) {
			laneCenters[i] = mapYStart + laneHeight / 2 + laneHeight * i;
		}
	}
	/**
	 * Specialized case of CalculateConstants(GameView gameView)
	 * @param Height 
	 * @param carHeight
	 */
	public static void CalculateConstants(int Height, int carHeight) {
		mapYEnd = Height;
		obstacleHeight = carHeight;
		int laneHeight = Height / 3;
		laneCenters = new int[lanes];
		for (int i = 0; i < lanes; i++) {
			laneCenters[i] = laneHeight * i + laneHeight / 2;
			//System.out.println("Nr:" + i + " center: " + laneCenters[i]);
		}
	}
	
	/**
	 * 
	 * @param lane - what lane the object should be place on
	 * @param coloumn - what column the object should be place on
	 * @return Start x & y and end x & y coordinate for a rectangle to draw
	 */
	public static Rect CalculateObstacle(int lane, int coloumn){
		int x1;
		int x2;
		int y1;
		int y2;
		y1 = mapYStart + (lane - 1) * totalObstacleHeight + obstacleSpace;
		y2 = y1 + obstacleHeight;
		x1 = (int) (totalObstacleWidth * 1.5 + totalObstacleWidth * (coloumn - 1) + obstacleSpace);
		x2 = x1 + obstacleWidth;
		
		return new Rect(x1, y1, x2, y2);
	}
	
	/**
	 * Calculates the Speed of the car vertically, to allow all screens
	 * @return value that moves the car vertically, on sound input.
	 */
	public static float getCarYSpeed() {
		return mapHeight / CAR_Y_SPEED_FACTOR;
	}
}
