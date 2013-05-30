package dk.aau.cs.giraf.cars.sound;

import java.util.Arrays;


import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.sound.SetupStates;

/**
 * Thread responsible for determining the high and low frequencies when calibrating the microphone
 */
public class MicTestThread extends Thread {
	private boolean mRun = false;
	private int mTmpLowFreq = 0;
	private int mTmpHighFreq = 0;
	private SetupStates mTestType = SetupStates.Low;
	private int arrayIntervals = 50;
	private boolean restartHigh = false;
	private boolean restartLow = false;
	
	/**
	 * Method to start the thread
	 */
	@Override
	public void run() {
		mRun = true;
		collectFrequencies();
	}
	
	/**
	 * @param testType			sets the frequency type currently being calibrated (high or low)
	 */
	public void setType(SetupStates testType) {
		mTestType = testType;
	}
	
	private void collectFrequencies() {
		while(mRun) {
			switch(mTestType) {
			case High: collectHighFreq();
				break;
			case Low: collectLowFreq();
				break;
			}
		}
	}
	
	/**
	 * @return	temporary high frequency
	 */
	public int getTmpHighFreq() {
		return mTmpHighFreq;
	}
	
	/**
	 * @return	temporary low frequency
	 */
	public int getTmpLowFreq() {
		return mTmpLowFreq;
	}
	
	private void collectHighFreq() {
		int firstDimension = (3400-50) / arrayIntervals + 1;
		int[] soundArray = new int[firstDimension];
		int tmpCurrFreq;
		int frequencyRange;
		
		while(mTestType == SetupStates.High) {
			if (restartHigh) {
				Arrays.fill(soundArray, 0);
				restartHigh = false;
			}
			
			tmpCurrFreq = GameInfo.getCurrFreq();
			
			if (tmpCurrFreq > 50) {
				frequencyRange = tmpCurrFreq / arrayIntervals;
			
				if (frequencyRange != 0) {
					soundArray[frequencyRange]++;
				}
			
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
		}
		
		int highestValue = 0;
		for (int i = 1; i < firstDimension; i++) {
			if (soundArray[i] > soundArray[highestValue]) {
				highestValue = i;
			}
		}
		mTmpHighFreq = (highestValue + 1) * arrayIntervals - 100;
	}

	private void collectLowFreq() {
		int firstDimension = (3400-50) / arrayIntervals + 1;
		int[] soundArray = new int[firstDimension];
		int tmpCurrFreq;
		int frequencyRange;
		
		while(mTestType == SetupStates.Low) {
			if (restartLow) {
				Arrays.fill(soundArray, 0);
				restartLow = false;
			}
			
			tmpCurrFreq = GameInfo.getCurrFreq();
			
			if (tmpCurrFreq > 50) {
				frequencyRange = tmpCurrFreq / arrayIntervals;
			
				if (frequencyRange != 0) {
					soundArray[frequencyRange]++;
				}
			
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
		}
		
		int highestValue = 0;
		for (int i = 1; i < firstDimension; i++) {
			if (soundArray[i] > soundArray[highestValue]) {
				highestValue = i;
			}
		}
		mTmpLowFreq = (highestValue + 1) * arrayIntervals + 100;
	}
	
	public void saveFrequencies() {
		GameInfo.setHighFreq(mTmpHighFreq);
		GameInfo.setLowFreq(mTmpLowFreq);
	}

	/**
	 *	called to stop the thread
	 */
	public void stopThread() {
		mRun = false;
	}
	
	/**
	 * restarts the collection of the low frequency threshold
	 */
	public void restartLowFreq() {
		restartLow = true;
	}
	
	/**
	 * restarts the collection of the high frequency threshold
	 */
	public void restartHighFreq() {
		restartHigh = true;
	}
	

}
