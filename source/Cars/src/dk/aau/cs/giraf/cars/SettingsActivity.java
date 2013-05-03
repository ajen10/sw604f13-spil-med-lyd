package dk.aau.cs.giraf.cars;

import dk.aau.cs.giraf.cars.sound.RecorderThread;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import dk.aau.cs.giraf.cars.MicTestDialogFragment.InputTestDialogListener;
import dk.aau.cs.giraf.cars.MicSetupDialogFragment.DialogListener;


public class SettingsActivity extends Activity implements DialogListener, InputTestDialogListener {
	RecorderThread recorderThread = new RecorderThread();
	
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
	
	@Override
	public void onStop(){
		super.onStop();
	//	recorderThread.recording = false;
	}
	
	
	public void createTestDialog() {
		MicSetupDialogFragment micTest = new MicSetupDialogFragment();
    	
    	micTest.setCancelable(false);
    	
    	micTest.show(getFragmentManager(), "micTestDialog");
    	
	}
    public void showMicTestDialog(View v) {
    	createTestDialog();
    }

	@Override
	public void pitchResult() {
		// TODO Auto-generated method stub
		MicTestDialogFragment micInput = new MicTestDialogFragment();
		
		micInput.setCancelable(false);
		
		micInput.show(getFragmentManager(), "micInputDialog");
		
	}

	@Override
	public void inputTestResult(ResultStates resultState) {
		// TODO Auto-generated method stub
		switch (resultState) {
		case restart:
			createTestDialog();
		case save:
		case cancel:
			break;
		}
		
	}
}
