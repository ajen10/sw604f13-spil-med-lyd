package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.ICollidable;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Rock extends GameObject implements IDrawable, ICollidable {
	Rect rectangle;
	
	public Rock(int lane, int coloumn) {
		rectangle = MapDivider.CalculateObstacle(lane, coloumn);
		
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		spriteBatcher.draw(gl, R.drawable.rock, new Rect(0, 0, 383, 278), rectangle);

	}

	@Override
	public boolean collisionDetection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int calculateCollisionBox() {
		// TODO Auto-generated method stub
		return 0;
	}

}
