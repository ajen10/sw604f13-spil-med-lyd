package dk.aau.cs.giraf.cars.gamecode;

public final class MapDivider {

	
	public static int width = 1;
	public static int height = 2;
	public int laneOne;
	public int laneTwo;
	public int laneThree;
	public int obstacles;
	
	public static void CalculateConstants(GameView gameView) {
		width = gameView.getWidth();
		height = gameView.getHeight();
		
	}
	

//	static int laneOne = 0;
//	static int laneTwo = height/3;
//	static int laneThree = (height/3)*2;
//	static double ratio = width/height;
	
}
