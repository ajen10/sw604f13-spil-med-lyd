package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
		
		int[] bitmapIds = new int[] { R.drawable.map, R.drawable.ic_launcher, R.drawable.rock,R.drawable.barricade, R.drawable.bump, R.drawable.car, R.drawable.cat};
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
		Random rand = new Random();
		int i;
		int numberOfObjects = 3;    //Indsæt nummer for objekter
		int[][] roadObstacles;
		roadObstacles = ObjectPlacement.objectPlacement(numberOfObjects);
		for(i=0;i<numberOfObjects;i++){
			int obstaclesNumber = Math.abs(rand.nextInt()%4);
			if (obstaclesNumber==0){
				objectList.add(new Bump(roadObstacles[i][0], roadObstacles[i][1]));
			}
			if (obstaclesNumber==1){
				objectList.add(new Cat(roadObstacles[i][0], roadObstacles[i][1]));
			}
			if (obstaclesNumber==2){
				objectList.add(new Barricade(roadObstacles[i][0], roadObstacles[i][1]));
			}
			if (obstaclesNumber==3){
				objectList.add(new Rock(roadObstacles[i][0], roadObstacles[i][1]));
			}
			//System.out.println("Object" + i +" har placering " + roadObstacles[i][0] + roadObstacles[i][1]);
		}
		objectList.add(new Car(MapDivider.mapYStart + MapDivider.obstacleSpace + MapDivider.totalObstacleHeight));
		//objectList.add(new Bump(1, 3));
		//objectList.add(new Cat(2, 1));
		//objectList.add(new Barricade(3, 5));
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