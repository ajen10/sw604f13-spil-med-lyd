package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;
import android.graphics.Rect;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.gamecode.GameObject;
import dk.aau.cs.giraf.cars.gamecode.GameRenderer;
import dk.aau.cs.giraf.cars.gamecode.ICollidable;
import dk.aau.cs.giraf.cars.gamecode.IDrawable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Barricade extends GameObject implements IDrawable, ICollidable {
	Rect rectangle;
	Point[] collisionBox;
	
	public Barricade(int lane, int coloumn) {
		rectangle = MapDivider.CalculateObstacle(lane, coloumn);
		collisionBox = new Point[4];
		int objectSideCollisionY = rectangle.top + (int) (rectangle.height()*0.70);
		collisionBox[0] = new Point(rectangle.centerX(),rectangle.top);
		collisionBox[1] = new Point(rectangle.left, objectSideCollisionY);
		collisionBox[2] = new Point(rectangle.centerX(),rectangle.bottom);
		collisionBox[3] = new Point(rectangle.right, objectSideCollisionY);
	}

	@Override
	public void draw(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		if (GameInfo.win == false){
		spriteBatcher.draw(gl, R.drawable.barricade, new Rect(0, 0, 299, 306), rectangle);
		}
	}

	@Override
	public boolean collisionDetection() {
		return false;
	}

	@Override
	public Point[] calculateCollisionBox() {
		return collisionBox;
	}

}
