package com.tectria.converterous;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class FontResizingEditText extends EditText {
	
	float initialTextSize;
	
	public FontResizingEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public FontResizingEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public FontResizingEditText(Context context) {
		super(context);
	}
	
	protected void initialize() {
		this.initialTextSize = this.getTextSize();
	}

	@Override
	protected void onTextChanged(CharSequence text, int start, int before, int after) {
		//If string length increased
		if((before - start) < (after - start)) {
			//Decrease font size until it only spans one line
			if(this.getLineCount() > 1) {
				this.setTextSize(this.getTextSize()/2.2f);
			}
		}
		super.onTextChanged(text, start, before, after);
	}
}
