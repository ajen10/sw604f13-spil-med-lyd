package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;


import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.objects.Car;

public class MicInputDialogFragment extends DialogFragment {
	private GameView mGameView;
	private GameObject mCar;
	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private GameThread mGameThread;
	private static final float GAMEVIEW_WIDTH = 50.0f;
	private static final float GAMEVIEW_HEIGHT = 300.0f;

	
	public MicInputDialogFragment(int lowFreq, int highFreq) {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the Dialog’s UI.
			View buttonView = inflater.inflate(R.layout.dialog_mic_input, container, false);
			
			int[] bitmapIds = new int[] {R.drawable.ic_launcher};
			
			mGameView = new GameView(getActivity(), getResources(), bitmapIds);
			mGameView.setZOrderOnTop(true);
			final float scale = getResources().getDisplayMetrics().density;

			
			int gameViewWidth = (int)(GAMEVIEW_WIDTH * scale + 0.5f);
			int gameViewHeight = (int)(GAMEVIEW_HEIGHT * scale + 0.5f);
					
			LinearLayout.LayoutParams gameViewParams = new LinearLayout.LayoutParams(gameViewWidth, gameViewHeight);
			
	        MicInputView micInputView = new MicInputView(this.getActivity());
	        
	        gameViewParams.bottomMargin = (int) (10.0f * scale + 0.5f);
	        gameViewParams.gravity = Gravity.CENTER_HORIZONTAL;
	                
	        micInputView.addView(mGameView, gameViewParams);
	        micInputView.addView(buttonView);
			
			Button nextButton = (Button)buttonView.findViewById(R.id.next_button);
	        Button retryButton = (Button)buttonView.findViewById(R.id.retry_button);
	                
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
	        
			gameObjects.add(mCar);
	        
			mGameThread = new GameThread(gameObjects);
						
			setObjects();
			
			mGameThread.start();
	        
			
			return micInputView; 
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("DESTROYED");
		mGameThread.stopRunning();
		mGameThread.interrupt();
		
	}
	


	public void setObjects() {
		mGameThread.SetObjects(gameObjects);
		mGameView.SetObjects(gameObjects);
	}
	

}
