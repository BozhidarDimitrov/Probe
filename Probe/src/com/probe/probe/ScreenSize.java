package com.probe.probe;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class ScreenSize extends Activity{

	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_size);
		
		tv = (TextView) findViewById(R.id.textView1);
		
		//http://stackoverflow.com/questions/15055458/detect-7-inch-and-10-inch-tablet-programmatically
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		addText("");
		addText("width px = " + metrics.widthPixels);
		addText("height px = " + metrics.heightPixels);
		addText("density = " + metrics.density);
		addText("width dp = " + metrics.widthPixels/metrics.density);
		addText("height dp = " + metrics.heightPixels/metrics.density);
		
		double sw = getResources().getConfiguration().smallestScreenWidthDp;
		addText("sw = " + sw);
		
		int orientation = getResources().getConfiguration().orientation;
		
		int mode = 0;
		
		
		if (sw >= 600) {
			if (orientation == Configuration.ORIENTATION_PORTRAIT) {
				mode = 1;
			} else {
				mode = 2;
			}
		} else if (sw >= 330) {
			if (orientation == Configuration.ORIENTATION_PORTRAIT) {
				mode = 3;
			} else {
				mode = 4;
			}
		} else {
			if (orientation == Configuration.ORIENTATION_PORTRAIT) {
				mode = 5;
			} else {
				mode = 6;
			}
		}
		
		switch (mode) {
		case 1:
			addText("sw >= 600 & port");
			break;
		case 2:
			addText("sw >= 600 & land");
			break;
		case 3:
			addText("sw >= 330 & port");
			break;
		case 4:
			addText("sw >= 330 & land");
			break;
		case 5:
			addText("sw > 0 & port");
			break;
		case 6:
			addText("sw > 0 & land");
			break;
		default:
			
		}
	}

	private void addText(String string) {
		this.tv.setText(this.tv.getText() + string + "\n");
	}
	
}