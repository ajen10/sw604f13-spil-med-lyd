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
import dk.aau.cs.giraf.cars.gamecode.IWorkable;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;

public class Garage extends GameObject implements IDrawable, ICollidable, IWorkable {
	Rect backwall;
	Rect doorOpen;
	Rect doorAnimation1;
	Rect doorAnimation2;
	Rect doorClosed;
	Rect frontwall;
	Rect roof;
	Point[] collisionBox;
	public boolean closed = false;
	boolean closing = false;
	AnimationState animState = AnimationState.Open;
	int animationCounter;
	int coloredBitmapId = -1;

	public Garage(int lane, int coloumn) {
		Rect rectangle = MapDivider.CalculateObstacle(lane, coloumn);

		backwall = new Rect(rectangle.left + (int)(rectangle.width() * 0.05),
							rectangle.top + (int)(rectangle.height() * 0.05),
							rectangle.right - (int)(rectangle.width() * 0.24),
							rectangle.bottom - (int)(rectangle.height() * 0.45));
		
		doorOpen = new Rect(rectangle.left + (int)(rectangle.width() * 0.02),
		 					rectangle.top + (int)(rectangle.height() * 0.07),
		 					rectangle.right - (int)(rectangle.width() * 0.08),
		 					rectangle.bottom - (int)(rectangle.height() * 0.55));
		
		doorAnimation1 = new Rect(rectangle.left - (int)(rectangle.width() * 0.25),
							rectangle.top + (int)(rectangle.height() * 0.07),
							rectangle.left + (int)(rectangle.width() * 0.55),
							rectangle.top + (int)(rectangle.height() * 0.65));
		
		doorAnimation2 = new Rect(rectangle.left - (int)(rectangle.width() * 0.28),
							rectangle.top + (int)(rectangle.height() * 0.07),
							rectangle.left + (int)(rectangle.width() * 0.33),
							rectangle.top + (int)(rectangle.height() * 0.88));
		
		doorClosed = new Rect(rectangle.left + (int)(rectangle.width() * 0.06),
							rectangle.top + (int)(rectangle.height() * 0.05),
							rectangle.left + (int)(rectangle.width() * 0.25),
							rectangle.bottom - (int)(rectangle.height() * 0.05));
		
		
		//05, 05, 19, 05
		
		frontwall = new Rect(rectangle.left + (int)(rectangle.width() * 0.24),
							 rectangle.top + (int)(rectangle.height() * 0.45),
							 rectangle.right - (int)(rectangle.width() * 0.05),
							 rectangle.bottom - (int)(rectangle.height() * 0.05));
		
		roof = new Rect(rectangle.left + (int)(rectangle.width() * 0.05),
				 		rectangle.top + (int)(rectangle.height() * 0.05),
				 		rectangle.right - (int)(rectangle.width() * 0.05),
				 		rectangle.bottom - (int)(rectangle.height() * 0.55));
		
		collisionBox = new Point[4];
		int objectSideCollisionY = rectangle.top + (int) (rectangle.height()*0.70);
		collisionBox[0] = new Point(rectangle.centerX(),rectangle.top);
		collisionBox[1] = new Point(rectangle.left, objectSideCollisionY);
		collisionBox[2] = new Point(rectangle.centerX(),rectangle.bottom);
		collisionBox[3] = new Point(rectangle.right, objectSideCollisionY);
	}
	
	public Garage(int lane, int coloumn, int bitmapId) {
		this(lane, coloumn);
		
		coloredBitmapId = bitmapId;
	}

	@Override
	public void PerformWork() {
		if (closing) {
			animationCounter--;
			if (animationCounter < 0) {
				switch(animState) {
					case Open:
						animState = AnimationState.Step1;
						animationCounter = 50;
						break;
					case Step1:
						animState = AnimationState.Step2;
						animationCounter = 50;
						break;
					case Step2:
						animState = AnimationState.Closed;
						animationCounter = 50;
						break;
					case Closed:
						closing = false;
						closed = true;
						GameInfo.garageClosing = false;
						break;
				}
			}
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
	
	public void startClosing() {
		if (!closing) {
		closing = true;
		GameInfo.garageClosing = true;
		animationCounter = 50;
		}
	}

	@Override
	public void Draw(GL10 gl, GameRenderer spriteBatcher) {
		spriteBatcher.draw(gl, R.drawable.garage_backwall, new Rect(0, 0, 414, 281), backwall);
		
		switch(animState) {
			case Open:
				spriteBatcher.draw(gl, R.drawable.garage_port_aaben, new Rect(0, 0, 511, 149), doorOpen);
				break;
			case Step1:
				spriteBatcher.draw(gl, R.drawable.garage_port_step1, new Rect(0, 50, 365, 251), doorAnimation1);
				break;
			case Step2:
				spriteBatcher.draw(gl, R.drawable.garage_port_step2, new Rect(0, 20, 266, 351), doorAnimation2);
				break;
			case Closed:
				spriteBatcher.draw(gl, R.drawable.garage_port_closed, new Rect(0, 0, 92, 436), doorClosed);
				break;
			default:
				break;
		}
		
		if (coloredBitmapId != -1) {
			spriteBatcher.draw(gl, coloredBitmapId, new Rect(0, 0, 414, 281), frontwall);
		}
		else {
			spriteBatcher.draw(gl, R.drawable.garage_frontwall, new Rect(0, 0, 414, 281), frontwall);
		}
		spriteBatcher.draw(gl, R.drawable.garage_tag, new Rect(0, 0, 524, 164), roof);
	}
	
	private enum AnimationState { Open, Step1, Step2, Closed }
}