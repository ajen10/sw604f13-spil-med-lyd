package dk.aau.cs.giraf.cars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MicInputDialogFragment extends DialogFragment {
	
	public MicInputDialogFragment() {
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        View layout = getActivity().getLayoutInflater().inflate(R.layout.dialog_mic_input, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.mic_test);
        builder.setView(layout);
        
        Button nextButton = (Button)layout.findViewById(R.id.next_button);
        Button retryButton = (Button)layout.findViewById(R.id.retry_button);
        
        nextButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		dismiss();
        	}
        });
        
        retryButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        	}
        });
        
        return builder.create();
    }
}
