package com.probe.probe;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextProbe extends Activity implements OnClickListener{

	EditText et;
	TextView tv1;
	Button write;
	Button edit;
	
	MyEditText met;
	TextView tv2;
	Button write2;
	Button edit2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.edit_text_probe);
		
		initialize();
	}

	private void initialize() {
		et = (EditText) findViewById(R.id.etEditText);
		tv1 = (TextView) findViewById(R.id.tv1EditText);
		write = (Button) findViewById(R.id.writeEditText);
		write.setOnClickListener(this);
		edit = (Button) findViewById(R.id.editEditText);
		edit.setOnClickListener(this);
		
		//no soft keyboard	
		//InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		//imm.hideSoftInputFromWindow(et.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		//et.setInputType(InputType.TYPE_NULL);
		//et.setRawInputType(InputType.TYPE_CLASS_TEXT);
		//et.setTextIsSelectable(true);
		disableSoftKeyboard(et);
		
		met = (MyEditText) findViewById(R.id.myEditText_EditTextProbe);
		tv2 = (TextView) findViewById(R.id.tv2EditText);
		write2 = (Button) findViewById(R.id.write2EditText);
		write2.setOnClickListener(this);
		edit2 = (Button) findViewById(R.id.edit2EditText);
		edit2.setOnClickListener(this);
	}
	
	private static void disableSoftKeyboard(EditText et){
		if (Build.VERSION.SDK_INT >= 11) {
			et.setRawInputType(InputType.TYPE_CLASS_TEXT);
			et.setTextIsSelectable(true);
		} else {
			et.setRawInputType(InputType.TYPE_NULL);
			et.setTextIsSelectable(true);
		}
	}
	
	/*
	 * nov class za probi
	 */
	public static class MyEditText extends EditText {
		private Rect mRect;
		private Paint mPaint;
		
		public MyEditText(Context context, AttributeSet attrs) {
			super(context, attrs);
			
			// Creates a Rect and a Paint object, and sets the style and color of the Paint object.
            mRect = new Rect();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(0x800000FF);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			
			// Gets the number of lines of text in the View.
            int count = getLineCount();

            // Gets the global Rect and Paint objects
            Rect r = mRect;
            Paint paint = mPaint;

            /*
             * Draws one line in the rectangle for every line of text in the EditText
             */
            for (int i = 0; i < count; i++) {

                // Gets the baseline coordinates for the current line of text
                int baseline = getLineBounds(i, r);

                /*
                 * Draws a line in the background from the left of the rectangle to the right,
                 * at a vertical position one dip below the baseline, using the "paint" object
                 * for details.
                 */
                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
                canvas.drawText("bojo uspq", r.left, baseline + 1, paint);
            }

            // Finishes up by calling the parent method
            super.onDraw(canvas);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.writeEditText:
			
			tv1.setText(et.getText());
			//no soft keyboard
			InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(et.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			break;
		case R.id.editEditText:
			
			int start = et.getSelectionStart();
			int end = et.getSelectionEnd();
			
			String str = et.getText().toString();
			
			SpannableStringBuilder ssb = new SpannableStringBuilder(str);
			ssb.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			et.setText(ssb);
			break;
		case R.id.edit2EditText:
			
			int start2 = met.getSelectionStart();
			int end2 = met.getSelectionEnd();
			
			String str2 = met.getText().toString();
			
			SpannableStringBuilder ssb2 = new SpannableStringBuilder(str2);
			ssb2.setSpan(new ForegroundColorSpan(Color.BLUE), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			met.setText(ssb2);
			break;
		case R.id.write2EditText:
			
			tv2.setText(met.getText());
			break;
		}
	}

	
}
