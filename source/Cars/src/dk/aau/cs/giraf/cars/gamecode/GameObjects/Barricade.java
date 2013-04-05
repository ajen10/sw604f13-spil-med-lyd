package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Barricade extends GameObject implements IDrawable {
	Rect rectangle;
	
	public Barricade(int lane, int coloumn) {
		rectangle = MapDivider.CalculateObstacle(lane, coloumn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		spriteBatcher.draw(gl, R.drawable.barricade, new Rect(0, 0, 299, 306), rectangle);
	}

}