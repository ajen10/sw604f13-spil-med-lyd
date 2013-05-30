package dk.aau.cs.giraf.cars.gamecode;

import android.graphics.Point;

/**
 * Interface to indicate the object is collidable.
 */
public interface ICollidable {
	public boolean collisionDetection();
	public Point[] calculateCollisionBox();
}
