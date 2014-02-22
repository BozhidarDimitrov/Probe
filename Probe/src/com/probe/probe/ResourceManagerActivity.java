package com.probe.probe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResourceManagerActivity extends Activity {
	
	private ResourceManager mResManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resource_manager);
		
		mResManager = new ResourceManager(this);
		
		if (mResManager.findDefault() == true) {
			TextView defaultTv = (TextView) findViewById(R.id.resource_manager_tv_default);
			defaultTv.setText(defaultTv.getText() + " - found");
		}
		
		if (mResManager.find300() == true) {
			TextView tv300 = (TextView) findViewById(R.id.resource_manager_tv_sw300dp);
			tv300.setText(tv300.getText() + " - found");
		}
		
		if (mResManager.find600() == true) {
			TextView tv600 = (TextView) findViewById(R.id.resource_manager_tv_sw600dp);
			tv600.setText(tv600.getText() + " - found");
		}
	}
	
}
