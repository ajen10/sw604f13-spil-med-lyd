package dk.aau.cs.giraf.cars.sound;

import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import dk.aau.cs.giraf.cars.sound.TestTypes;

public class MicTestThread extends Thread {
	private boolean mRun = false;
	private int mTmpLowFreq = 0;
	private int mTmpHighFreq = 0;
	private TestTypes mTestType = TestTypes.Low;
	
	public MicTestThread() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		mRun = true;
		collectFrequencies();
	}
	
	public void setType(TestTypes testType) {
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
	
	private void collectHighFreq() {
		int tmpCurrFreq = (int) (GameInfo.getCurrFreq() * 0.75);
		
		if(mTmpHighFreq <= 0) {
			mTmpHighFreq = (int) (GameInfo.getCurrFreq() * 0.75);
		}
		if (tmpCurrFreq > mTmpHighFreq) {
			mTmpHighFreq = tmpCurrFreq;
		}
	}

	private void collectLowFreq() {
		int tmpCurrFreq = (int) (GameInfo.getCurrFreq() * 1.25);
		
		if (mTmpLowFreq <= 0) {
			mTmpLowFreq = (int) (GameInfo.getCurrFreq() * 1.25);
		}
		
		if (tmpCurrFreq < mTmpLowFreq && tmpCurrFreq > 10) {
			mTmpLowFreq = tmpCurrFreq;
		}
		
	}
	
	public void saveFrequencies() {
		GameInfo.setHighFreq(mTmpHighFreq);
		GameInfo.setLowFreq(mTmpLowFreq);
	}

	public void stopThread() {
		mRun = false;
	}
	
	public void restartLowFreq() {
		mTmpLowFreq = 0;
	}
	
	public void restartHighFreq() {
		mTmpHighFreq = 0;
	}
	

}
