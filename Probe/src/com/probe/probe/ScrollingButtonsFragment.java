package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScrollingButtonsFragment extends Fragment {
	
	private static final String TAG = ScrollingButtonsFragment.class.getSimpleName();
	
	private boolean debug = true;
	private String intro = this.toString().substring(this.toString().length() - 4) + "----->";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (debug) Log.d(TAG, intro + " onAttach()");
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (debug) Log.d(TAG, intro + " onCreate()");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (debug) Log.d(TAG, intro + " onCreateView()");
		return inflater.inflate(R.layout.scrolling_buttons_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (debug) Log.d(TAG, intro + " onActivityCreated()");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (debug) Log.d(TAG, intro + " onStart()");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (debug) Log.d(TAG, intro + " onResume()");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (debug) Log.d(TAG, intro + " onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (debug) Log.d(TAG, intro + " onStop()");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (debug) Log.d(TAG, intro + " onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (debug) Log.d(TAG, intro + " onDestroy()");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (debug) Log.d(TAG, intro + " onDetach()");
	}
	
}
