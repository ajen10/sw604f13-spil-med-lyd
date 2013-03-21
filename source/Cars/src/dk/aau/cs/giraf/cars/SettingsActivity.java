package dk.aau.cs.giraf.cars;

import dk.aau.cs.giraf.cars.sound.RecorderThread;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import dk.aau.cs.giraf.cars.MicTestDialogFragment.DialogListener;

<<<<<<< HEAD

public class SettingsActivity extends Activity implements DialogListener {

=======
public class SettingsActivity extends Activity {
	RecorderThread recorderThread = new RecorderThread();
>>>>>>> dev
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		//recorderThread.start();
		//System.out.println("average frequency = " + recorderThread.getFrequency());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
<<<<<<< HEAD
	
    public void showMicTestDialog(View v) {
    	MicTestDialogFragment micTest = new MicTestDialogFragment(getString(R.string.mic_test_low));
    	
    	micTest.show(getFragmentManager(), "micTestDialog");
    }
=======
	@Override
	public void onStop(){
		super.onStop();
	//	recorderThread.recording = false;
	}  
>>>>>>> dev

	@Override
	public void pitchResult(int pitch) {
		// TODO Auto-generated method stub
		
	}
}
