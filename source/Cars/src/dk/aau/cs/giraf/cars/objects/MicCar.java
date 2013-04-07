package dk.aau.cs.giraf.cars.objects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;
import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.Car;
import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;


public class MicCar extends Car {
	private int mOffset = 0;
	private final int mViewWidth;
	private final int mViewHeight;
	private final int mImgWidth;
	private final int mImgHeight;
	private final int mLowFreq;
	private final int mHighFreq;
	private int mStartX;
	private int mStartY;
	private int mEndX;
	private int mEndY;
	
	public MicCar(int viewWidth, int viewHeight, int carWidth, int carHeight) {
		super(0);
		mViewWidth = viewWidth;
		mViewHeight = viewHeight;
		mImgWidth = carWidth;
		mImgHeight = carHeight;
		mLowFreq = GameInfo.getLowFreq();
		mHighFreq = GameInfo.getHighFreq();
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		
		int x = (mViewWidth / 2) - (mImgWidth / 2);
		int y = (mViewHeight / 2) - (mImgHeight / 2);
		
		
		mStartX = x;
		mStartY = y + mOffset;
		mEndX = x + (mImgWidth * 2);
		mEndY = y + (mImgHeight * 2) + mOffset;
		
		spriteBatcher.draw(gl, R.drawable.ic_launcher, new Rect(0, 0, 100, 100), new Rect(mStartX, mStartY , mEndX, mEndY));
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub
		int currFreq = GameInfo.getCurrFreq();
		System.out.println(currFreq + " ---- " + mHighFreq + " ------ " + mLowFreq);
		
		if (currFreq > 0) {
			if (currFreq > mHighFreq) {
				mOffset--;
			} else if (currFreq < mLowFreq) {
				mOffset++;
			}
 		}
	}

}
