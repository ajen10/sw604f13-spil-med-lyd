package dk.aau.cs.giraf.cars.gamecode;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;

public class GameRenderer implements Renderer {
	@Override
	public void onDrawFrame(GL10 unused) {
		unused.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onSurfaceChanged(GL10 unused, int width, int height) {
		unused.glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
		unused.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
	}
}
