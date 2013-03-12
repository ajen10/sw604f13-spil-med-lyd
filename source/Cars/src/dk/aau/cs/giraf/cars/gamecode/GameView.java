package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import dk.aau.cs.giraf.cars.R;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.Car;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;

// TODO Add touch screen registration
public class GameView extends GLSurfaceView implements Drawer {
	List<IDrawable> drawableObjects;
	
	public GameView(Context context, Resources resources, int[] bitmapIds) {
		super(context);
		
		drawableObjects = new ArrayList<IDrawable>();
		setRenderer(new GameRenderer(resources, bitmapIds, this));
	}
	public void SetObjects(List<GameObject> gameObjects) {
		drawableObjects.clear();
		
		for (GameObject object : gameObjects) {
			if (object instanceof IDrawable) {
				drawableObjects.add((IDrawable) object);
			}
		}
	}

	@Override
	public void onDrawFrame(GL10 gl, GameRenderer spriteBatcher) {
		//spriteBatcher.draw(gl, R.drawable.ic_launcher, new Rect(0, 0, 100, 100), new Rect(150, 150, 250, 250));
		
		for (IDrawable object : drawableObjects) {
			object.Draw(gl, spriteBatcher);
		}
	}
}
