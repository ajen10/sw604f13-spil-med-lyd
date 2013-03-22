package dk.aau.cs.giraf.cars.gamecode;

import android.graphics.Rect;

public final class MapDivider {

	
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
	
	public static void CalculateConstants(GameView gameView) {
		width = gameView.getWidth();
		height = gameView.getHeight();
		System.out.println("Width = " + width + " height = " + height);
		
		totalObstacleWidth = (int) (width/7.5);
		totalObstacleHeight = totalObstacleWidth;
		obstacleSpace = (int) (totalObstacleWidth*0.025);
		obstacleWidth = (int) (totalObstacleWidth*0.95);
		obstacleHeight = obstacleWidth;
		mapHeight = totalObstacleWidth*3;
		mapYStart = (height - mapHeight) / 2;
		mapYEnd = mapHeight + mapYStart;
		
	
		
	}
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

//	static int laneOne = 0;
//	static int laneTwo = height/3;
//	static int laneThree = (height/3)*2;
//	static double ratio = width/height;
	
}
