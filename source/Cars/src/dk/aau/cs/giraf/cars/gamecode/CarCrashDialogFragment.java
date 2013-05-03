package dk.aau.cs.giraf.cars.gamecode;

import dk.aau.cs.giraf.cars.R;
import android.os.Bundle;
import android.app.DialogFragment;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CarCrashDialogFragment extends DialogFragment {
	
	//Empty constructor
	public CarCrashDialogFragment() {

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_car_crash, container, false);
		
		Button nextButton = (Button) view.findViewById(R.id.next_button);
		
		nextButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		GameInfo.pause = false;
        		dismiss();
        	}
        });
		
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view.setBackgroundColor(Color.WHITE);
        
		return view;

	}
	
	/*@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		
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
        
	}*/
	
}


