package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;

public class Rock extends GameObject implements IDrawable, IWorkable {
	int x;
	int y;
	
	public Rock(int X, int Y) {
		// TODO Auto-generated constructor stub
		x = X;
		y = Y;
		
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		spriteBatcher.draw(gl, R.drawable.rock, new Rect(0, 0, 100, 100), new Rect(150 + x, 150 + y, 250 + x, 250 + y));

	}

}
