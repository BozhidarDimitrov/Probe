package com.probe.probe;

import com.probe.probe.PassingDataBetweenFragmentsTextFragment.DataSource;
import com.probe.probe.PassingDataBetweenFragmentsWriteFragment.DataSender;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PassingDataBetweenFragmentsRuntimeChanges extends Activity implements 
		DataSource,
		DataSender {
	
	private static final String TAG = PassingDataBetweenFragmentsRuntimeChanges.class.getSimpleName();
	private static final String TEXT_FRAGMENT_TAG = "text_fragment_tag";
	private static final String WRITE_FRAGMENT_TAG = "write_fragment_tag";
	
	private boolean mDualPane;
	
	private String intro = "activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, intro + " onCreate()");
		setContentView(R.layout.passing_data_between_fragments_runtime_changes);
		
		fragmentInitialization();
	}

	private void fragmentInitialization() {
		
		Fragment textFragment = (Fragment) getFragmentManager().findFragmentByTag(TEXT_FRAGMENT_TAG);
		if (textFragment == null) {
			getFragmentManager().beginTransaction().
			replace(R.id.passing_data_between_fragments_runtime_changes_frame1,
					new PassingDataBetweenFragmentsTextFragment(),
					TEXT_FRAGMENT_TAG).commit();
		}
		
		View writeFrame = findViewById(R.id.passing_data_between_fragments_runtime_changes_frame2);
		mDualPane = writeFrame != null && writeFrame.getVisibility() == View.VISIBLE;
		
		if (mDualPane) {
			getFragmentManager().beginTransaction().
			replace(R.id.passing_data_between_fragments_runtime_changes_frame2, 
					new PassingDataBetweenFragmentsWriteFragment(),
					WRITE_FRAGMENT_TAG).commit();
		}
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, intro + " onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, intro + " onResume()");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, intro + " onRestart()");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, intro + " onRestoreInstanceState()");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, intro + " onSaveInstanceState()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, intro + " onPause()");
		
		Fragment writeFragment = (Fragment) getFragmentManager().findFragmentByTag(WRITE_FRAGMENT_TAG);
		if (writeFragment != null) {
			getFragmentManager().beginTransaction().remove(writeFragment).commit();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, intro + " onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, intro + " onDestroy()");
	}

	@Override
	public void getData() {
		if (mDualPane) {
			//do nothing
		} else {
			FragmentManager fm = getFragmentManager();
			Fragment writeFragment = new PassingDataBetweenFragmentsWriteFragment();
			writeFragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().
			replace(R.id.passing_data_between_fragments_runtime_changes_frame1, writeFragment).commit();
		}
	}

	@Override
	public void sendData(String data) {
		Fragment textFragment = new PassingDataBetweenFragmentsTextFragment();
		Bundle bundle = getIntent().getExtras();
		if (bundle == null) bundle = new Bundle();
		bundle.putString(PassingDataBetweenFragmentsTextFragment.DATA_KEY, data);
		textFragment.setArguments(bundle);
		
		getFragmentManager().beginTransaction().
		replace(R.id.passing_data_between_fragments_runtime_changes_frame1, textFragment).commit();
	}


}
