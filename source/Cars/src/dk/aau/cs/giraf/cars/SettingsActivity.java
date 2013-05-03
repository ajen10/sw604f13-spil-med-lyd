package dk.aau.cs.giraf.cars;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import dk.aau.cs.giraf.cars.MicTestDialogFragment.DialogListener;
import dk.aau.cs.giraf.cars.R.id;
import dk.aau.cs.giraf.cars.sound.RecorderThread;


public class SettingsActivity extends Activity implements DialogListener {
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

	public void showMicTestDialog(View v) {
		MicTestDialogFragment micTest = new MicTestDialogFragment();

		micTest.setCancelable(false);

		micTest.show(getFragmentManager(), "micTestDialog");


	}

	@Override
	public void pitchResult() {
		// TODO Auto-generated method stub
		MicInputDialogFragment micInput = new MicInputDialogFragment();

		micInput.setCancelable(false);

		micInput.show(getFragmentManager(), "micInputDialog");

	}

	public void changeColors(View view) {
		Dialog color_dialog = new Dialog(this);
		color_dialog.setContentView(R.layout.color_dialog);
		color_dialog.setTitle("Vælg en farve");
		color_dialog.setCanceledOnTouchOutside(false);
		color_dialog.show();

		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor1));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor2));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor3));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor4));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor5));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor6));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor7));
		setOnClick(color_dialog, view, color_dialog.findViewById(id.mcolor8));
	}

	private void setOnClick(final Dialog color_dialog, final View parent, View child) {
		child.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Drawable temp = v.getBackground();
				ColorDrawable temp2 = (ColorDrawable)temp;
				parent.setBackgroundColor(temp2.getColor());
				color_dialog.dismiss();
			}

		}
				);
	}
}

