package dk.aau.cs.giraf.cars.gamecode;


//Static class containing game information
public class GameInfo {
	private static int mLowFreq = -1;
	private static int mHighFreq = -1;
	private static int mCurrFreq = -1;
	
	private GameInfo() { /*empty */ }
	
	public static void setHighFreq(int highFreq) {
		mHighFreq = highFreq;
	}
	
	public static void setLowFreq(int lowFreq) {
		mLowFreq = lowFreq;
	}
	
	public static void setCurrFreq(int currFreq) {
		mCurrFreq = currFreq;
	}
	
	public static void setFrequencies(int lowFreq, int highFreq) {
		mLowFreq = lowFreq;
		mHighFreq = highFreq;
	}
	
	public static int getHighFreq() {
		return mHighFreq;
	}
	
	public static int getLowFreq() {
		return mLowFreq;
	}
	
	public static int getCurrFreq() {
		return mCurrFreq;
	}
	
}
