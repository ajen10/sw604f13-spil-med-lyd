package dk.aau.cs.giraf.cars;


import android.app.DialogFragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import dk.aau.cs.giraf.cars.gamecode.*;
import dk.aau.cs.giraf.cars.gamecode.GameObjects.Car;
import dk.aau.cs.giraf.cars.objects.MicCar;
import dk.aau.cs.giraf.cars.sound.RecorderThread;
import dk.aau.cs.giraf.cars.ResultStates;

public class MicTestDialogFragment extends DialogFragment {
	private GameView mGameView;
	private GameObject mCar;
	private GameThread mGameThread;
	private static final float GAMEVIEW_WIDTH = 50.0f;
	private static final float GAMEVIEW_HEIGHT = 300.0f;
	private RecorderThread mRecordThread = new RecorderThread();

	
	public MicTestDialogFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			// Inflate the Dialog’s UI.
			View buttonView = inflater.inflate(R.layout.dialog_mic_test, container, false);
			
			int[] bitmapIds = new int[] {R.drawable.car};
			
			mGameView = new GameView(getActivity(), getResources(), bitmapIds, true);
			mGameView.setZOrderOnTop(true);
			final float scale = getResources().getDisplayMetrics().density;

			
			int gameViewWidth = (int)(GAMEVIEW_WIDTH * scale + 0.5f);
			int gameViewHeight = (int)(GAMEVIEW_HEIGHT * scale + 0.5f);
					
			LinearLayout.LayoutParams gameViewParams = new LinearLayout.LayoutParams(gameViewWidth, gameViewHeight);
			
	        
	        gameViewParams.bottomMargin = (int) (10.0f * scale + 0.5f);
	        gameViewParams.gravity = Gravity.CENTER_HORIZONTAL;
	        
	       	LinearLayout openglLayout = (LinearLayout) buttonView.findViewById(R.id.opengl_layout);
			openglLayout.addView(mGameView, gameViewParams);
			

			Button nextButton = (Button)buttonView.findViewById(R.id.next_button);
	        Button retryButton = (Button)buttonView.findViewById(R.id.retry_button);
	        
	        
	       final InputTestDialogListener activity = (InputTestDialogListener) getActivity();
	        
	        nextButton.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		dismiss();
	        	}
	        });
	       
	        
	        retryButton.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		dismiss();
	        		activity.inputTestResult(ResultStates.restart);
	        	}
	        });
	        
	        Drawable car = getResources().getDrawable(R.drawable.car);
			
			int carWidth = (int)(car.getIntrinsicWidth());
			int carHeight = (int)(car.getIntrinsicHeight());
			
			System.out.println(carWidth);
			System.out.println(carHeight);
			MapDivider.CalculateConstants(gameViewHeight, carHeight);
	        
	        mCar = new MicCar(gameViewWidth, gameViewHeight, carWidth, carHeight);
	        
			mGameThread = new GameThread((Car)mCar);
						
			mGameView.SetObjects(mCar);
			
			mGameThread.start();
	      
	        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
			return buttonView; 
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mGameThread.stopRunning();	
		mRecordThread.recording = false;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mRecordThread.start();
	}
	
	interface InputTestDialogListener {
		void inputTestResult(ResultStates resultState);
	}

}
