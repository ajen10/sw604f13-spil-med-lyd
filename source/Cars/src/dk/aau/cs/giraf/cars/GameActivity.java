package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.*;
import dk.aau.cs.giraf.cars.sound.RecorderThread;

public class GameActivity extends Activity {
	GameView view;
	List<GameObject> objectList;
	GameThread gameThread;
	RecorderThread recorderThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		objectList = new ArrayList<GameObject>(); 
		
		int[] bitmapIds = new int[] { R.drawable.map, R.drawable.garage_backwall, R.drawable.garage_port_aaben, R.drawable.garage_port_step1, R.drawable.garage_port_step2, R.drawable.garage_port_closed, R.drawable.garage_frontwall, R.drawable.garage_tag, R.drawable.ic_launcher, R.drawable.rock,R.drawable.barricade, R.drawable.bump, R.drawable.car, R.drawable.cat};
		view = new GameView(this, getResources(), bitmapIds);
		setContentView(view);
		
		gameThread = new GameThread(objectList);
		recorderThread = new RecorderThread();
		
		SetObjects();
		
		gameThread.start();
		recorderThread.start();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		gameThread.stopRunning();
		recorderThread.recording = false;
	}
	
	public void AddObjects() { 
		Random rand = new Random();
		int i;
		int numberOfObjects = 3;    // TODO Indsæt nummer for objekter
		float carSpeed = 0.75F;     // TODO Indsæt hastigheden på bilen her som en float
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
		}
		objectList.add(new Car(MapDivider.mapYStart + MapDivider.obstacleSpace + MapDivider.totalObstacleHeight, carSpeed));
		objectList.add(new Garage(1, 6));
		objectList.add(new Garage(2, 6));
		objectList.add(new Garage(3, 6));
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
