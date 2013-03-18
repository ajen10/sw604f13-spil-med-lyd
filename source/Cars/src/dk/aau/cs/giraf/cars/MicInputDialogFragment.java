package dk.aau.cs.giraf.cars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.objects.Car;

public class MicInputDialogFragment extends DialogFragment {
	GameView gameView;
	GameObject car;
	GameThread gameThread;
	//RelativeLayout viewGrp;
	
	public MicInputDialogFragment() {
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the builder class for convenient dialog construction
        View layout = getActivity().getLayoutInflater().inflate(R.layout.dialog_mic_input, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.mic_test);
        
        
        car = new Car();
		int[] bitmapIds = new int[] {R.drawable.ic_launcher};
		gameView = new GameView(getActivity(), getResources(), bitmapIds);
		
		MicInputView micInputView = new MicInputView(this.getActivity());
        //micInputView.addView(layout);
		
        micInputView.addView(gameView,50,150);
        
		
		builder.setView(micInputView);
		//builder.setView(view);
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
        
        
		gameThread = new GameThread(car);
		
		SetObjects(car);
		
		gameThread.start();
        
        return builder.create();
    }

	public void SetObjects(GameObject car) {
		gameThread.SetObjects(car);
		gameView.SetObjects(car);
	}
}
