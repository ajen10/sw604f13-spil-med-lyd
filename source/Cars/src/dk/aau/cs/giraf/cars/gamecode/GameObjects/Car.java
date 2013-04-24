package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;
import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Car extends GameObject implements IWorkable, IDrawable {
	protected float xOffset = -MapDivider.obstacleWidth;
	protected int yOffset = 0;
	private final int mLowFreq;
	private final int mHighFreq;
	Point[] collisionBox;
	private boolean updateCarCollisionBox = true;
	private float carSpeedAsFloat;
	
	public Car(int y, float carSpeed) {
		carSpeedAsFloat = carSpeed;
		this.yOffset = y;

		mLowFreq = GameInfo.getLowFreq();
		mHighFreq = GameInfo.getHighFreq();
		
		collisionBox = new Point[4];
		collisionBox[0] = new Point(0,0);
		collisionBox[1] = new Point(0,0);
		collisionBox[2] = new Point(0,0);
		collisionBox[3] = new Point(0,0);
	}
	
	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		spriteBatcher.draw(gl, R.drawable.car, new Rect(0, 0, 898, 348), new Rect( (int)xOffset, yOffset, MapDivider.obstacleWidth + (int)xOffset, MapDivider.obstacleHeight + yOffset));
	}

	@Override
	public void PerformWork() {
		updateCarCollisionBox = true;
		xOffset = xOffset + carSpeedAsFloat;
		
		int currFreq = GameInfo.getCurrFreq();
		
		if (currFreq > 0) {
			if (currFreq > mHighFreq && yOffset > MapDivider.mapYStart) {
				yOffset--;
				yOffset--;
				//System.out.println("Going up");
			} else if (currFreq < mLowFreq && (yOffset < (MapDivider.mapYEnd - MapDivider.obstacleHeight))) {
				yOffset++;
				yOffset++;
				//System.out.println("Going down");
			}
 		} else {
 			int closestLane = 0;
 			for (int i = 1; i < MapDivider.lanes; i++) {
 				if (Math.abs(MapDivider.laneCenters[i] - yOffset) <
 					Math.abs(MapDivider.laneCenters[closestLane] - yOffset)) {
 					closestLane = i;
 				}
 			}
 			int offset = MapDivider.laneCenters[closestLane] - yOffset;
 			if (offset != 0) {
 				if (offset > 0) {
 					yOffset++;
 				} else if (offset < 0) {
 					yOffset--;
 				}
 			}
 		}
	}
	
	//�NDRE TIL PASSENDE FORM
	public boolean CalculateCollisions(Point[] form) {
		if (updateCarCollisionBox) {
			collisionBox[0].x = (int)xOffset;
			collisionBox[0].y = yOffset;
			collisionBox[1].x = (int)xOffset;
			collisionBox[1].y = MapDivider.obstacleHeight + yOffset;
			collisionBox[2].x = MapDivider.obstacleWidth + (int)xOffset;
			collisionBox[2].y = MapDivider.obstacleHeight + yOffset;
			collisionBox[3].x = MapDivider.obstacleWidth + (int)xOffset;
			collisionBox[3].y = yOffset;
			
			updateCarCollisionBox = false;
		}
		if (collisionBox[0].y > form[2].y || collisionBox[1].y < form[0].y) {
			return false;
		}
		if (collisionBox[0].x > form[3].x || collisionBox[2].x < form[1].x){
			return false;
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (doesLineCrossLine(collisionBox[i], collisionBox[(i + 1) % 4],
									  form[j], form[(j + 1) % 4])) {
					return true;
				}
			}
		}
		
		return false;
	}
	private boolean doesLineCrossLine(Point line1Start, Point line1End,
									  Point line2Start, Point line2End) {
		float crossProduct1 = crossProduct(line1Start, line1End, line2Start);
		float crossProduct2 = crossProduct(line1Start, line1End, line2End);
		if ((crossProduct1 < 0 && crossProduct2 < 0) ||
				(crossProduct1 > 0 && crossProduct2 > 0)) {
			return false;
		}
		else {
			float crossProduct3 = crossProduct(line2Start, line2End, line1Start);
			float crossProduct4 = crossProduct(line2Start, line2End, line1End);
			
			if ((crossProduct3 < 0 && crossProduct4 < 0) ||
					(crossProduct3 > 0 && crossProduct4 > 0)) {
				return false;
			}
		}
		
		return true;
	}
	private float crossProduct(Point line1Start, Point line1End,
							   Point line2Point) {
		return (line1End.x - line1Start.x) * (line2Point.y - line1End.y) -
			   (line1End.y - line1Start.y) * (line2Point.x - line1End.x);
	}
	
	public void resetPosition(){
		yOffset = MapDivider.mapYStart + MapDivider.obstacleSpace + MapDivider.totalObstacleHeight;
		xOffset = -MapDivider.obstacleWidth;
	}
}
