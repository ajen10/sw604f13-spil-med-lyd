package dk.aau.cs.giraf.cars.gamecode.GameObjects;

import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.gamecode.MapDivider;
import junit.framework.TestCase;

public class CarTestUp extends TestCase {

	static Car car;
	protected void setUp() throws Exception {
		super.setUp();
		
		GameInfo.setHighFreq(1000);
		GameInfo.setLowFreq(500);
		GameInfo.setCurrFreq(2000);
	    
	    //The following is needed
	    //Else performwork never does what we want it to do
	    MapDivider.mapYEnd = 1000;
	    MapDivider.mapHeight = 1000;
	    
	    car = new Car(50, 1, null, null);
	    car.xOffset = 100; 
	}

	public void testPerformWork() {
		car.performWork();

		assertTrue(car.getYOffset() < 50);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
		GameInfo.setHighFreq(0);
		GameInfo.setLowFreq(0);
		GameInfo.setCurrFreq(0);
	    MapDivider.mapYEnd = 0;
	    MapDivider.mapHeight = 0;
	    car = null;
	}
}
