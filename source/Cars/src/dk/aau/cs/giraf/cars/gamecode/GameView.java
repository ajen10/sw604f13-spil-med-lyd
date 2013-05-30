package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import dk.aau.cs.giraf.cars.GameActivity;
import dk.aau.cs.giraf.cars.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;

/**
 * View that is shown during the gameplay.
 * This class is responsible for everything visual during the game.
 */
@SuppressLint("ViewConstructor")
public class GameView extends GLSurfaceView implements Drawer {
	List<IDrawable> drawableObjects;
	GameActivity parent;
	private boolean mSettingsView = false;
	
	/**
	 * Standard constuctor, used in the game.
	 * 
	 * @param context	Context of the related activity. Can be obtained with "getContext()".
	 * @param resources	Reference to the android resources. Can be obtained from "getResources()".
	 */
	public GameView(Context context, Resources resources) {
		super(context);
		parent = (GameActivity) context;
		
		drawableObjects = new ArrayList<IDrawable>();
		setRenderer(new GameRenderer(resources, this));
	}
	/**
	 * Secondary constructor, used in the microphone wizard.
	 * 
	 * @param context		Context of the related activity. Can be obtained with "getContext()".
	 * @param resources		Reference to the android resources. Can be obtained from "getResources()".
	 * @param settingsView	Boolean indicating that it is the "settings" version of the GameView.
	 */
	public GameView(Context context, Resources resources, boolean settingsView) {
		super(context);
		mSettingsView = settingsView;
		drawableObjects = new ArrayList<IDrawable>();
		setRenderer(new GameRenderer(resources, this));
	}

	/**
	 * Replaces the objects of the GameView.
	 * Filters the list for relevant objects to avoid overhead later.
	 * 
	 * @param gameObjects	List of objects to replace current objects.
	 */
	public void SetObjects(List<GameObject> gameObjects) {
		drawableObjects.clear();

		for (GameObject object : gameObjects) {
			if (object instanceof IDrawable) {
				drawableObjects.add((IDrawable) object);
			}
		}
	}

	/**
	 * Same as SetObjects(List<GameObject> gameObjects), except with a single object.
	 * 
	 * @param gameObject	GameObject to replace current objects.
	 */
	public void SetObjects(GameObject gameObject) {
		drawableObjects.clear();

		if (gameObject instanceof IDrawable) {
			drawableObjects.add((IDrawable) gameObject);
		}

	}

	/**
	 * Method called whenever a screen refresh is necessary.
	 */
	@Override
	public void onDrawFrame(GL10 gl, GameRenderer spriteBatcher) {
		//spriteBatcher.draw(gl, R.drawable.ic_launcher, new Rect(0, 0, 100, 100), new Rect(150, 150, 250, 250));
		if (!mSettingsView) {
			spriteBatcher.draw(gl, R.drawable.map, new Rect(0, 0, 1947, 1122), new Rect(0, MapDivider.mapYStart, spriteBatcher.getViewWidth(), MapDivider.mapYEnd));
			spriteBatcher.draw(gl, R.drawable.grass, new Rect(0, 0, 100, 100), new Rect(0, 0, spriteBatcher.getViewWidth(), MapDivider.mapYStart));
			spriteBatcher.draw(gl, R.drawable.grass, new Rect(0, 0, 100, 100), new Rect(0, MapDivider.mapYEnd, spriteBatcher.getViewWidth(), spriteBatcher.getViewHeight()));
			if (GameInfo.win){
				spriteBatcher.draw(gl, R.drawable.trophy, new Rect(0, 0, 1090, 951), new Rect((int)(MapDivider.obstacleWidth*3), MapDivider.mapYStart+(int)(MapDivider.obstacleHeight*0.25), (int)(MapDivider.obstacleWidth*3)+(int)(MapDivider.obstacleWidth*2), MapDivider.mapYEnd-(int)(MapDivider.obstacleHeight*0.25)));
			}
		} else {
			gl.glClearColor(255, 255, 255, 0);
		}



		for (IDrawable object : drawableObjects) {
			object.draw(gl, spriteBatcher);
		}
	}

	/**
	 * Method called whenever the view changes dimensions.
	 * Calls AddObjects on GameActivity as this is the earliest point,
	 * the view has correct dimension, hence the objects cannot be
	 * placed properly prior to this point.
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		if(!mSettingsView) {
			MapDivider.CalculateConstants(this);
			parent.AddObjects();
		}
	}
}
