package dk.aau.cs.giraf.cars.gamecode;

import android.content.Context;
import android.opengl.GLSurfaceView;

// TODO Add touch screen registration
public class GameView extends GLSurfaceView {
	public GameView(Context context) {
		super(context);

		setRenderer(new GameRenderer());
	}
}
