package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import dk.aau.cs.giraf.cars.gamecode.*;

public class GameActivity extends Activity {
	GameView view;
	List<GameObject> objectList;
	GameThread gameThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		objectList = new ArrayList<GameObject>();
		
		int[] bitmapIds = new int[0];
		view = new GameView(this, getResources(), bitmapIds);
		setContentView(view);
		
		gameThread = new GameThread(objectList);
		gameThread.start();
	}
	
	public void SetObjects() {
		gameThread.SetObjects(objectList);
		view.SetObjects(objectList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
}