package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class ScrollingButtonsBig extends Activity {

	private static final String CONTENT_FRAGMENT_TAG = "content_fragment";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		FragmentManager fm = getFragmentManager();
		
		//////////////////////
		// Content Fragment initialization
		//////////////////////
		Fragment contentFragment = (Fragment) fm.findFragmentByTag(CONTENT_FRAGMENT_TAG);
		
		if (contentFragment == null) {
			contentFragment = new ScrollingButtonsFragment();
			contentFragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().add(
					android.R.id.content, 
					contentFragment, 
					CONTENT_FRAGMENT_TAG).commit();
		}
	}
	
}
