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
		
		int[] bitmapIds = new int[] { R.drawable.map, R.drawable.garage_backwall, R.drawable.garage_port_aaben, R.drawable.garage_frontwall, R.drawable.garage_tag, R.drawable.ic_launcher, R.drawable.rock,R.drawable.barricade, R.drawable.bump, R.drawable.car, R.drawable.cat};
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
	
	public void AddObjects() { 
		objectList.add(new Car(MapDivider.mapYStart + MapDivider.obstacleSpace + MapDivider.totalObstacleHeight));
		objectList.add(new Bump(1, 3));
		objectList.add(new Cat(2, 1));
		objectList.add(new Barricade(3, 5));
		objectList.add(new Garage(2, 6));
		SetObjects();
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