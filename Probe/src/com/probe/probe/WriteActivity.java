package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class WriteActivity extends Activity {

	private static final String WRITE_FRAGMENT_TAG = "write_fragment_tag";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Fragment fragment = getFragmentManager().findFragmentByTag(WRITE_FRAGMENT_TAG);
		
		if (fragment == null) {
			getFragmentManager().beginTransaction().replace(
					android.R.id.content,
					new WriteFragment(),
					WRITE_FRAGMENT_TAG).commit();
		}
	}
	
}
