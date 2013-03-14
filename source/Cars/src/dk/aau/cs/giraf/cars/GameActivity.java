package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.Car;

public class GameActivity extends Activity {
	GameView view;
	List<GameObject> objectList;
	GameThread gameThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		objectList = new ArrayList<GameObject>();
		objectList.add(new Car());
		
		int[] bitmapIds = new int[] {R.drawable.ic_launcher};
		view = new GameView(this, getResources(), bitmapIds);
		setContentView(view);
		
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