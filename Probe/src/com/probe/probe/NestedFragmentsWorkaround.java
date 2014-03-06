package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class NestedFragmentsWorkaround extends Activity {
	
	private static final String FRAGMENT_TAG = "fragment_tag";
	private static final String FRAME1_TAG = "frame1_tag";
	private static final String FRAME2_TAG = "frame2_tag";

	/* DEBUGGING */
	public static final boolean GLOBAL_DEBUG = true;
	private static final boolean LOCAL_DEBUG = true;
	private static final String TAG = NestedFragmentsWorkaround.class.getSimpleName();
	private final String debug_intro = 
			"..." + this.toString().substring(this.toString().length() - 5) + " ";
	/* END */
	
	private void debug(String text){
		if (GLOBAL_DEBUG && LOCAL_DEBUG) Log.d(TAG, debug_intro + text);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		debug("onCreate()");
		setContentView(R.layout.nested_fragments_workaround);
		
		FragmentManager fm = getFragmentManager();
		Fragment fragment = (Fragment) fm.findFragmentByTag(FRAGMENT_TAG);
		
		if (fragment == null) {
			fm.beginTransaction().replace(
					R.id.nested_fragments_workaround_frame,
					new NestedFragmentsWorkaroundFragment(),
					FRAGMENT_TAG).commit();
		}
		
		boolean containsContainerFrame = false;
		View containerFrame = findViewById(R.id.nested_fragments_workaround_frame);
		containsContainerFrame = containerFrame != null && containerFrame.getVisibility() == View.VISIBLE;
		debug("containsContainerFrame = " + containsContainerFrame);
		
		
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		debug("onRestart()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		debug("onStart()");
		
		boolean containsFrame1 = false;
		View frame1 = findViewById(R.id.nested_fragments_workaround_fragment_frame1);
		containsFrame1 = frame1 != null && frame1.getVisibility() == View.VISIBLE;
		debug("containsFrame1 = " + containsFrame1);
		
		boolean containsFrame2 = false;
		View frame2 = findViewById(R.id.nested_fragments_workaround_fragment_frame2);
		containsFrame2 = frame2 != null && frame2.getVisibility() == View.VISIBLE;
		debug("containsFrame2 = " + containsFrame2);
		
		if (containsFrame1) {
			getFragmentManager().beginTransaction().replace(
					R.id.nested_fragments_workaround_fragment_frame1,
					new ScrollingButtonsFragment()).commit();
		}
		
		if (containsFrame2) {
			getFragmentManager().beginTransaction().replace(
					R.id.nested_fragments_workaround_fragment_frame2,
					new ScrollingButtonsFragment()).commit();
		}
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		debug("onRestoreInstanceState()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		debug("onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		debug("onPause()");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		debug("onSaveInstanceState()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		debug("onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		debug("onDestroy()");
	}
}
