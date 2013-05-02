package dk.aau.cs.giraf.cars;

import dk.aau.cs.giraf.cars.sound.RecorderThread;
import dk.aau.cs.giraf.cars.sound.MicTestThread;
import dk.aau.cs.giraf.cars.sound.TestTypes;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MicSetupDialogFragment extends DialogFragment {

	private RecorderThread mRecordThread = new RecorderThread();
	private MicTestThread mMicThread = new MicTestThread();

	private TestTypes testState = TestTypes.Low;
	
	public MicSetupDialogFragment() { 
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the Dialog’s UI.
			View view = inflater.inflate(R.layout.dialog_mic_setup, container, false);
			// Update the Dialog’s contents.
			final TextView text = (TextView)view.findViewById(R.id.dialog_mic_test_textview); 
			text.setText(getString(R.string.mic_test_low));
			final ImageView img = (ImageView)view.findViewById(R.id.dialog_drawable_to_mimic);

			
			img.setImageDrawable(getResources().getDrawable(R.drawable.bear));
			
			
			Button nextButton = (Button)view.findViewById(R.id.next_button);
	        Button cancelButton = (Button)view.findViewById(R.id.cancel_button);
	        Button retryButton = (Button)view.findViewById(R.id.retry_button);
	                
	        nextButton.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		DialogListener activity = (DialogListener) getActivity();
	        			        		
	        		switch(testState) {
	        		case Low:
	        			text.setText(getString(R.string.mic_test_high));
	        			img.setImageDrawable(getResources().getDrawable(R.drawable.mus));
	        				        			
	        			testState = TestTypes.High;
	        			mMicThread.setType(testState);
	        			break;
	        		case High:
	        			dismiss();
	        			mMicThread.saveFrequencies();
	        			mMicThread.stopThread();
	        			
	        			activity.pitchResult();
	        			break;
	        		}
	        	}
	        });
	        
	        cancelButton.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		getDialog().dismiss();
	        	}
	        });
	        
	        retryButton.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		switch(testState) {
	        		case Low:
	        			mMicThread.restartLowFreq();
	        			Toast.makeText(getActivity(), "Optagelse genstartet", Toast.LENGTH_SHORT).show();
	        			break;
	        		case High:
	        			mMicThread.restartHighFreq();
	        			Toast.makeText(getActivity(), "Optagelse genstartet", Toast.LENGTH_SHORT).show();
	        			
	        			break;
	        		}
	        	}
	        });
		
	        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
			return view; 
		}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mRecordThread.start();
		mMicThread.setType(TestTypes.Low);
		mMicThread.start();
		
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("DESTROYED");
		mRecordThread.recording = false;
	}
	    
    public interface DialogListener {
    	void pitchResult();
    }
	
}
