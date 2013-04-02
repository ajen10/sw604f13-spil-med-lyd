package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Car extends GameObject implements IWorkable, IDrawable {
	int offset = 0;
	public int y;
	
	public Car(int y) {
		// TODO Auto-generated constructor stub
		this.y = y;
		
	}
	
	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		spriteBatcher.draw(gl, R.drawable.car, new Rect(0, 0, 898, 348), new Rect( offset, y, MapDivider.obstacleWidth + offset, MapDivider.obstacleHeight + y));
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub
		offset++;
	}

	@Override
	public boolean collisionDetection() {
		// TODO Auto-generated method stub
		return false;
	}

}
