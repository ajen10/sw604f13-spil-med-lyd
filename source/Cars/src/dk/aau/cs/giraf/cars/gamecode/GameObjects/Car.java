package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;

public class Car extends GameObject implements IWorkable, IDrawable {
	int offset = 0;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		//spriteBatcher.draw(gl, R.drawable.ic_launcher, new Rect(0, 0, 100, 100), new Rect(150 + offset, 150, 250 + offset, 250));
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub
	}

}
