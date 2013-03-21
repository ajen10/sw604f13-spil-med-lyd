package dk.aau.cs.giraf.cars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.objects.Car;

public class MicInputDialogFragment extends DialogFragment {
	private GameView mGameView;
	private GameObject mCar;
	private GameThread mGameThread;
	private static final float GAMEVIEW_WIDTH = 50.0f;
	private static final float GAMEVIEW_HEIGHT = 300.0f;

	
	public MicInputDialogFragment() {
		
	}	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the builder class for convenient dialog construction
        View buttonView = getActivity().getLayoutInflater().inflate(R.layout.dialog_mic_input, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.mic_test);
        
		int[] bitmapIds = new int[] {R.drawable.ic_launcher};
		
		mGameView = new GameView(getActivity(), getResources(), bitmapIds);
		mGameView.setZOrderOnTop(true);
		final float scale = getResources().getDisplayMetrics().density;

		
		int gameViewWidth = (int)(GAMEVIEW_WIDTH * scale + 0.5f);
		int gameViewHeight = (int)(GAMEVIEW_HEIGHT * scale + 0.5f);
				
		LinearLayout.LayoutParams gameViewParams = new LinearLayout.LayoutParams(gameViewWidth, gameViewHeight);
		
        MicInputView micInputView = new MicInputView(this.getActivity());
        
        gameViewParams.gravity = Gravity.CENTER_HORIZONTAL;
                
        micInputView.addView(mGameView, gameViewParams);
        micInputView.addView(buttonView);
        
		builder.setView(micInputView);

		Button nextButton = (Button)micInputView.findViewById(R.id.next_button);
        Button retryButton = (Button)micInputView.findViewById(R.id.retry_button);
        
        nextButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		dismiss();
        	}
        });
        
        retryButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        	}
        });
        
		Drawable car = getResources().getDrawable(R.drawable.ic_launcher);
		
		int carWidth = (int)(car.getIntrinsicWidth() * scale + 0.5f);
		int carHeight = (int)(car.getIntrinsicHeight() * scale + 0.5f);
        
        mCar = new Car(gameViewWidth, gameViewHeight, carWidth, carHeight);
        
		mGameThread = new GameThread(mCar);
		
		SetObjects(mCar);
		
		mGameThread.start();
        
        return builder.create();
    }

	public void SetObjects(GameObject car) {
		mGameThread.SetObjects(car);
		mGameView.SetObjects(car);
	}
	

}
