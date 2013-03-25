package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;

import dk.aau.cs.giraf.cars.sound.RecorderThread;

public class GameThread extends Thread {
	final int millisecondsPerTick = 25;
	List<IWorkable> workableObjects;
	Boolean running;
	//private RecorderThread mRecordThread;
	
	public GameThread(List<GameObject> gameObjects, int lowFreq, int highFreq) {
		workableObjects = new ArrayList<IWorkable>();
		
		for (GameObject object : gameObjects) {
			if (object instanceof IWorkable) {
				workableObjects.add((IWorkable) object);
			}
		}
	}
	
	public void run() {
		running = true;
		initializeSound();
		gameLogic();
	}
	public void SetObjects(List<GameObject> gameObjects) {
		workableObjects.clear();
		
		for (GameObject object : gameObjects) {
			if (object instanceof IWorkable) {
				workableObjects.add((IWorkable) object);
			}
		}
	}
	
	public void initializeSound() {
		/*mRecordThread = new RecorderThread();
		
		mRecordThread.start();*/
	}
	
	public void gameLogic() {
		while(running){
			long currentTime = System.nanoTime();
			//int freq = mRecordThread.getFrequency();
			
			for (IWorkable object : workableObjects) {
				if(object.collisionDetection()) {
					object.PerformWork();
				}
			}
		
			long newTime = System.nanoTime();
			long difference = newTime - currentTime;
			int sleepFor = (int) ((millisecondsPerTick * 1000000 - difference) / 1000000);

			try {
				if (sleepFor > 0)
				Thread.sleep(sleepFor);
			} catch (InterruptedException e) {}
		}
	}
	
	public void stopRunning() {
		running = false;
	}
	
}