package dk.aau.cs.giraf.cars.gamecode;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;

// TODO Add touch screen registration
public class GameView extends GLSurfaceView implements Drawer {
	public GameView(Context context, Resources resources, int[] bitmapIds) {
		super(context);

		setRenderer(new GameRenderer(resources, bitmapIds, this));
	}

	@Override
	public void onDrawFrame(GL10 gl, GameRenderer spriteBatcher) {
		// TODO Auto-generated method stub
		
	}
}
