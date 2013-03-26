package dk.aau.cs.giraf.cars;

import dk.aau.cs.giraf.cars.sound.RecorderThread;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MicTestDialogFragment extends DialogFragment {

	private RecorderThread mRecordThread = new RecorderThread();;
	private int mLowFreq;
	private int mHighFreq;
	private enum TestStates { lowFreqStep, highFreqStep };
	private TestStates testState = TestStates.lowFreqStep;
	
	public MicTestDialogFragment() { 
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the Dialog’s UI.
			View view = inflater.inflate(R.layout.dialog_mic_test, container, false);
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
	        			        		
	        		if(testState == TestStates.lowFreqStep) {
	        			
	        			text.setText(getString(R.string.mic_test_high));
	        			img.setImageDrawable(getResources().getDrawable(R.drawable.mus));
	        			
	        			setLowFreq();
	        			
	        			testState = TestStates.highFreqStep;
	        			
	        			restartRecordThread();
	        		} else {
	        			dismiss();

	        			setHighFreq();
	        			activity.pitchResult(mLowFreq, mHighFreq);
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
	        	}
	        });
			
			return view; }
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mRecordThread.start();
	}
	
	public void setLowFreq() {
		mLowFreq = mRecordThread.getLowFrequency();
	}
	
	public void setHighFreq() {
		mHighFreq = mRecordThread.getHighFrequency();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("DESTROYED");
		mRecordThread.interrupt();
	}
	    
    public interface DialogListener {
    	void pitchResult(int lowFreq, int highFreq);
    }
    
    public void restartRecordThread() {
    	mRecordThread.interrupt();
    	
    	mRecordThread = new RecorderThread();
    }
	
}
