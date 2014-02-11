package com.probe.probe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Buttons extends Activity implements OnClickListener{

	Button b1;
	Button b2;
	Button b3;
	TextView tv1;
	Button b4;
	Button b5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buttons);
		
		initialize();
	}

	private void initialize() {
		b1 = (Button) findViewById(R.id.b1Buttons);
		b1.setOnClickListener(this);
		b2 = (Button) findViewById(R.id.b2Buttons);
		b2.setOnClickListener(this);
		b3 = (Button) findViewById(R.id.b3Buttons);
		b3.setOnClickListener(this);
		tv1 = (TextView) findViewById(R.id.tv1Buttons);
		
		b4 = (Button) findViewById(R.id.b4Buttons);
		b5 = (Button) findViewById(R.id.b5Buttons);
		b5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.b1Buttons:
			tv1.setText("1");
			break;
		case R.id.b2Buttons:
			tv1.setText("2");
			break;
		case R.id.b3Buttons:
			tv1.setText("3");
			break;
		case R.id.b5Buttons:
			if (b4.getText().toString().equals("Test")){
				b4.setText("LongText");
			} else {
				b4.setText("Test");
			}
		}
	}
	
}
