package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.*;

public class GameActivity extends Activity {
	GameView view;
	List<GameObject> objectList;
	GameThread gameThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		objectList = new ArrayList<GameObject>();

		int[] bitmapIds = new int[] {R.drawable.ic_launcher, R.drawable.rock,R.drawable.barricade, R.drawable.bump, R.drawable.car, R.drawable.cat, R.drawable.map};
		view = new GameView(this, getResources(), bitmapIds);
		setContentView(view);
		
			objectList.add(new Car());
			objectList.add(new Rock());
			objectList.add(new Bump());
			objectList.add(new Cat());
			objectList.add(new Barricade());
		
		gameThread = new GameThread(objectList);
		
		SetObjects();
		
		gameThread.start();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("DESTROYED");
		gameThread.stopRunning();
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