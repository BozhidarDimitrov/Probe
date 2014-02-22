package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class ScrollingButtonsSmall extends Activity {
	
	private static final String FRAGMENT_TAG = "content_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrolling_buttons_small);
		
		FragmentManager fm = getFragmentManager();
		
		//////////////////////
		// Content Fragment initialization
		//////////////////////
		Fragment fragment = (Fragment) fm.findFragmentByTag(FRAGMENT_TAG);
		
		if (fragment == null) {
			fragment = new ScrollingButtonsFragment();
			fragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().replace(
					R.id.scrolling_buttons_frame,
					fragment, 
					FRAGMENT_TAG).commit();
		}
	}

	
}
