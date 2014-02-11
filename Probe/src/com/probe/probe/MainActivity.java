package com.probe.probe;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	private ArrayList<TextView> list = new ArrayList<TextView>();
	private Button b1;
	private Button b2;
	//private TextView tv;
	private LinearLayout lay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
		b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(this);
		
		
		lay = (LinearLayout) findViewById(R.id.MainActivityLynearLayout);
		TextView tv = new TextView(this);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tv.setText("0");
		lay.addView(tv);
		/*
		tv.setId(0);
		tv.setText("Fuck!" + tv.getId());
		lay.addView(tv);
		*/
		
	}
	
	private void write(){
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		/*
		Intent menuOpener = new Intent("android.intent.action.MAIN");
		startActivity(menuOpener);
		*/
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.button1:
			
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			tv.setId(list.size());
			tv.setText("1");
			list.add(tv);
			
			for (int i=0; i < list.size(); i++){
				lay.addView(list.get(i));
			}
			
			break;
		case R.id.button2:
			
			TextView tv2 = new TextView(this);
			tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			tv2.setId(list.size());
			tv2.setText("-");
			list.add(tv2);
			
			for (int i=0; i < list.size(); i++){
				lay.addView(list.get(i));
			}
			
			break;
		}
		
	}

	
}
