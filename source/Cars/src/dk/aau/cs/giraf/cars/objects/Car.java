package dk.aau.cs.giraf.cars.objects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;
import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;

public class Car extends GameObject implements IWorkable, IDrawable {
	private int offset = 0;
	private final int mViewWidth;
	private final int mViewHeight;
	private final int mImgWidth;
	private final int mImgHeight;
	
	public Car(int viewWidth, int viewHeight, int carWidth, int carHeight) {
		mViewWidth = viewWidth;
		mViewHeight = viewHeight;
		mImgWidth = carWidth;
		mImgHeight = carHeight;
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		
		int x = (mViewWidth / 2) - (mImgWidth / 2);
		int y = (mViewHeight / 2) - (mImgHeight / 2);
		
		spriteBatcher.draw(gl, R.drawable.ic_launcher, new Rect(0, 0, 100, 100), new Rect(x, y , x + (mImgWidth * 2) + offset, y + (mImgHeight * 2)));
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub
		//offset++;
	}

}
