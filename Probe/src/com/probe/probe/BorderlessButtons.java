package com.probe.probe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BorderlessButtons extends Activity implements OnClickListener{
	
	private Button b1;
	private TextView tv1;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.borderless_button);
		
		initialize();
	}

	private void initialize() {
		tv1 = (TextView) findViewById(R.id.tv1BorderlessButton);
		b1 = (Button) findViewById(R.id.b1BorderlessButtons);
		b1.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
		case R.id.b1BorderlessButtons:
			counter++;
			tv1.setText(counter + "");
			break;
		}

	}

	
}
