package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.IWorkable;

public class Barricade extends GameObject implements IWorkable, IDrawable {
	
	
	public Barricade() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		spriteBatcher.draw(gl, R.drawable.barricade, new Rect(0, 0, 100, 100), new Rect(150, 150, 250, 250));
	}

	@Override
	public void PerformWork() {
		// TODO Auto-generated method stub
		
	}

}
