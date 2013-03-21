package dk.aau.cs.giraf.cars;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MicInputView extends LinearLayout{
	public MicInputView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		final float scale = getResources().getDisplayMetrics().density;
		int topPadding = (int) (10.0f * scale + 0.5f);
		int bottomPadding = (int) (10.0f * scale + 0.5f);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setPadding(0, topPadding, 0, bottomPadding);
        //View.inflate(context, R.layout.dialog_mic_input, this);
	}
	public MicInputView(Context context, AttributeSet attrs) {
		this(context, null, 0);
	}
	public MicInputView(Context context) {
		this(context, null);
	}
}
