package dk.aau.cs.giraf.cars.gamecode;

import dk.aau.cs.giraf.cars.R;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class CarCrashDialogFragment extends DialogFragment {
	
	//Empty constructor
	public CarCrashDialogFragment() {

	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int wwidth = displaymetrics.widthPixels;
		
		// Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       GameInfo.pause = false;                   }
               });
        
        
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundColor(Color.WHITE);
        
        
        imageView.setImageResource(R.drawable.car_crash);
        
        builder.setView(imageView);
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
	}
	
}


