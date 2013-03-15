package dk.aau.cs.giraf.cars;

import dk.aau.cs.giraf.cars.MicTestDialogFragment.DialogListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class SettingsActivity extends Activity implements DialogListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void showMicTestFragment(View view) {
		MicTestDialogFragment micTest = new MicTestDialogFragment(getString(R.string.mic_test_low));
		
		micTest.show(getFragmentManager(), "micTest");
		
	}
	
	public void pitchResult(int test) {
		
		
	}
}
