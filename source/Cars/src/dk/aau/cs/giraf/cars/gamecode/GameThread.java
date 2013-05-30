package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;


import android.app.FragmentManager;

import dk.aau.cs.giraf.cars.gamecode.GameObjects.*;

/**
 * Thread responsible for making the game run.
 */
public class GameThread extends Thread {
	final int millisecondsPerTick = 25;
	List<IWorkable> workableObjects;
	List<ICollidable> collidableObjects;
	Car car;
	Boolean running = false;
	private boolean mSettingsView = false;
	public int numberOfClosedGarages;
	private FragmentManager manager;
	
	/**
	 * Standard constructor, used when instantiating the thread in the game.
	 * 
	 * @param gameObjects	Objects of the game. This list is filtered to avoid searching through objects that have no relevance to the GameThread.
	 * @param manager		The fragment manager from the gameActivity, to allow displaying of dialogfragments on top of the game view. 
	 */
	public GameThread(List<GameObject> gameObjects, FragmentManager manager) {
		workableObjects = new ArrayList<IWorkable>();
		collidableObjects = new ArrayList<ICollidable>();
		this.manager = manager;
		
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
	
	/**
	 * Secondary constructor, used only for the Microphone example under the settings.
	 * 
	 * @param car	car object created in the microphone wizard.
	 */
	public GameThread(Car car) {
		this.car = car;
		
		mSettingsView = true;
	}
	
	/**
	 * Method to start the thread.
	 */
	public void run() {
		running = true;
		if (!mSettingsView) {
			gameLogic();
		} else {
			settingsLogic();
		}
	}

	/**
	 * Method for setting objects created after instantiating the thread.
	 * Filters to avoid searching through object irrelevant to the
	 * GameThread at later points in the execution.
	 * 
	 * @param gameObjects	Objects to replace the current objects.
	 */
	public void SetObjects(List<GameObject> gameObjects) {
		workableObjects.clear();
		collidableObjects.clear();
		
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
				object.performWork();
				
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
						Garage garage = (Garage)object;
						
						if (garage.color == car.getColor() && !garage.closed) {
							garage.startClosing();
							car.closeColor();
						}
						else {
							GameInfo.pause = true;
							showDialog();
						}
						car.resetPosition();
					}
					else {
						car.resetPosition();
						GameInfo.pause = true;
						showDialog();
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
			
			car.performWork();
			
			long newTime = System.nanoTime();
			long difference = newTime - currentTime;
			int sleepFor = (int) ((millisecondsPerTick * 1000000 - difference) / 1000000);

			try {
				if (sleepFor > 0)
				Thread.sleep(sleepFor);
			} catch (InterruptedException e) {}
		}
	}
	
	private void showDialog() {
		CarCrashDialogFragment carCrash = new CarCrashDialogFragment();
		
		carCrash.setCancelable(false);
		carCrash.show(manager, "crashdialog");
	}
	
	/**
	 * Called to stop the thread.
	 */
	public void stopRunning() {
		running = false;
	}	
}

