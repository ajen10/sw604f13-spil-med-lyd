package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Car extends GameObject implements IWorkable, IDrawable {
	protected int xOffset = 0;
	protected int yOffset = 0;
	private final int mLowFreq;
	private final int mHighFreq;
	
	public Car(int y) {
		// TODO Auto-generated constructor stub
		this.yOffset = y;

		mLowFreq = GameInfo.getLowFreq();
		mHighFreq = GameInfo.getHighFreq();
	}
	
	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		spriteBatcher.draw(gl, R.drawable.car, new Rect(0, 0, 898, 348), new Rect( xOffset, yOffset, MapDivider.obstacleWidth + xOffset, MapDivider.obstacleHeight + yOffset));
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub
		xOffset++;
		
		int currFreq = GameInfo.getCurrFreq();
		
		if (currFreq > 0) {
			if (currFreq > mHighFreq && yOffset > 0) {
				yOffset--;
				//System.out.println("Going up");
			} else if (currFreq < mLowFreq && (yOffset < (MapDivider.mapHeight - MapDivider.obstacleHeight))) {
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
	public void CalculateCollisions(int form) {
		
	}
}
