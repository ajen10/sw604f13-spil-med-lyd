package dk.aau.cs.giraf.cars.objects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;
import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.Car;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;

public class MicCar extends Car {
	private final int mViewWidth;
	private final int mImgWidth;
	private final int mImgHeight;
	private int mStartX;
	private int mStartY;
	private int mEndX;
	private int mEndY;
	
	public MicCar(int viewWidth, int viewHeight, int carWidth, int carHeight) {
		super(0,0);   //Second argument is the speed of the car in the X direction
		mViewWidth = viewWidth;
		mImgWidth = carWidth;
		mImgHeight = carHeight;
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		int x = (mViewWidth / 2) - (mImgWidth / 2);
		
		mStartX = x;
		mStartY = yOffset;
		mEndX = x + (mImgWidth * 2);
		mEndY = (mImgHeight * 2) + yOffset;
		
		spriteBatcher.draw(gl, R.drawable.ic_launcher, new Rect(0, 0, 100, 100), new Rect(mStartX, mStartY , mEndX, mEndY));
	}

	@Override
	public void PerformWork() {
		super.PerformWork();
		//this.xOffset--;
	}

}
