package dk.aau.cs.giraf.cars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MicTestDialogFragment extends DialogFragment {

	String mMicText;
	
	public MicTestDialogFragment(String micText) { 
		mMicText = micText;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        View layout = getActivity().getLayoutInflater().inflate(R.layout.dialog_mic_test, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.mic_test);
        builder.setMessage(mMicText);
        builder.setView(layout);
        
        Button nextButton = (Button)layout.findViewById(R.id.next_button);
        Button cancelButton = (Button)layout.findViewById(R.id.cancel_button);
        Button retryButton = (Button)layout.findViewById(R.id.retry_button);
        
        nextButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		DialogListener activity = (DialogListener) getActivity();
        		activity.pitchResult(2);
        		dismiss();
        		
        		if(mMicText.equals(getString(R.string.mic_test_low))) {
        			MicTestDialogFragment micTestHigh = new MicTestDialogFragment(getString(R.string.mic_test_high));
        			micTestHigh.show(getFragmentManager(), "micTestHigh");
        		} else {
        			MicInputDialogFragment micInput = new MicInputDialogFragment();
        			micInput.show(getFragmentManager(), "micInput");
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
        
        return builder.create();
    }
    
    public interface DialogListener {
    	void pitchResult(int pitch);
    }
	
}
