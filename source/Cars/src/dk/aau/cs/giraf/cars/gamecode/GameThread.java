package dk.aau.cs.giraf.cars.gamecode;

import java.util.ArrayList;
import java.util.List;

public class GameThread extends Thread {
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
			for (IWorkable object : workableObjects) {
				object.PerformWork();
			}
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
	
}