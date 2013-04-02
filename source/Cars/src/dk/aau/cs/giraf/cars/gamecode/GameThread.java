package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;


public class GameThread extends Thread {
	final int millisecondsPerTick = 25;
	List<IWorkable> workableObjects;
	Boolean running;
	
	public GameThread(List<GameObject> gameObjects) {
		workableObjects = new ArrayList<IWorkable>();
		
		for (GameObject object : gameObjects) {
			if (object instanceof IWorkable) {
				workableObjects.add((IWorkable) object);
			}
		}
	}
	
	public void run() {
		running = true;
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
	
	
	public void gameLogic() {
		while(running){
			long currentTime = System.nanoTime();
			
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