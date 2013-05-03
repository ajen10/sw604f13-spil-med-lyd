package dk.aau.cs.giraf.cars.objects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;
import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.Car;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;

public class MicCar extends Car {
	private final int mViewWidth;
	private final int mViewHeight;
	private final int mImgWidth;
	private final int mImgHeight;
	private int mStartY;
	private int mEndY;
	
	public MicCar(int viewWidth, int viewHeight, int carWidth, int carHeight) {
		super(0, 0, null, null);   //Second argument is the speed of the car in the X direction
		mViewWidth = viewWidth;
		mViewHeight = viewHeight;
		mImgWidth = carWidth;
		mImgHeight = carHeight;
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
	
		mStartY = (mViewHeight / 2) - (mViewWidth / 2) + yOffset;
		mEndY = mStartY + mViewWidth;
		
		spriteBatcher.draw(gl, R.drawable.car, new Rect(0, 0, mImgWidth, mImgHeight), new Rect(0, mStartY , mViewWidth, mEndY));

	}

	@Override
	public void PerformWork() {
		super.PerformWork();
		//this.xOffset--;
	}

}
