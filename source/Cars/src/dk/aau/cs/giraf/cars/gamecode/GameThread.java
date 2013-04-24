package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;

import dk.aau.cs.giraf.cars.gamecode.GameObjects.*;


public class GameThread extends Thread {
	final int millisecondsPerTick = 25;
	List<IWorkable> workableObjects;
	List<ICollidable> collidableObjects;
	Car car;
	Boolean running;
	private boolean mSettingsView = false;
	public int numberOfClosedGarages;
	
	public GameThread(List<GameObject> gameObjects) {
		workableObjects = new ArrayList<IWorkable>();
		collidableObjects = new ArrayList<ICollidable>();
		
		for (GameObject object : gameObjects) {
			if (object instanceof IWorkable) {
				workableObjects.add((IWorkable) object);
			}
			else if (object instanceof ICollidable) {
				collidableObjects.add((ICollidable) object);
			}
			else if (object instanceof Car) {
				car = (Car) object;
			}
		}
	}
	
	public GameThread(Car car) {
		this.car = car;
		
		mSettingsView = true;
	}
	
	public void run() {
		running = true;
		if (!mSettingsView) {
			gameLogic();
		} else {
			settingsLogic();
		}
	}
	public void SetObjects(List<GameObject> gameObjects) {
		workableObjects.clear();
		
		for (GameObject object : gameObjects) {
			if (object instanceof Car) {
				car = (Car) object;
			}
			if (object instanceof IWorkable) {
				workableObjects.add((IWorkable) object);
			}
			if (object instanceof ICollidable) {
				collidableObjects.add((ICollidable) object);
			}

		}
	}
	
	
	private void gameLogic() {
		while(running){
			
			long currentTime = System.nanoTime();
			numberOfClosedGarages = 0;
			
			for (IWorkable object : workableObjects) {
				object.PerformWork();
				
				if (object instanceof Garage &&
						((Garage)object).closed == true){
					numberOfClosedGarages++;
					
					if (numberOfClosedGarages == 3){
						car.resetPosition();
						GameInfo.win=true;
					}
				}
			}
			for (ICollidable object : collidableObjects) {
				if (car != null &&
					car.CalculateCollisions(object.calculateCollisionBox())) { //�NDRE TIL PASSENDE FORM
					if (object instanceof Garage) {
						((Garage)object).startClosing();
						car.resetPosition();
					}
					else {
						// TODO DISPLAY COLLISION
						car.resetPosition();
						// TODO evt. Gen. ny map
					}
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
	
	private void settingsLogic() {
		while(running){
			long currentTime = System.nanoTime();
			
			car.PerformWork();
			
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