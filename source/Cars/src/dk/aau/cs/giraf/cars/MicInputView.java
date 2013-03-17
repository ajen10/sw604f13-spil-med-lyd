package dk.aau.cs.giraf.cars;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class MicInputView extends RelativeLayout{
	public MicInputView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        //View.inflate(context, R.layout.dialog_mic_input, this);
	}
	public MicInputView(Context context, AttributeSet attrs) {
		this(context, null, 0);
	}
	public MicInputView(Context context) {
		this(context, null);
	}
}
