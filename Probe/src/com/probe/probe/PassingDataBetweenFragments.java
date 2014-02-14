package com.probe.probe;

import com.probe.probe.PassingDataBetweenFragmentsTextFragment.DataSource;
import com.probe.probe.PassingDataBetweenFragmentsWriteFragment.DataSender;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

public class PassingDataBetweenFragments extends Activity implements
		DataSource,
		DataSender {
	
	private static final String CONTENT_FRAGMENT_TAG = "content_fragment";
	private static final String TAG = PassingDataBetweenFragments.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "OnCreate()");
		
		FragmentManager fm = getFragmentManager();
		Fragment contentFragment = (Fragment) fm.findFragmentByTag(CONTENT_FRAGMENT_TAG);
		if (contentFragment == null) {
			contentFragment = new PassingDataBetweenFragmentsTextFragment();
			contentFragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().add(
					android.R.id.content, 
					contentFragment, 
					CONTENT_FRAGMENT_TAG).commit();
		}
		
		Log.d(TAG, "OnCreate() end");
		
	}

	@Override
	public void getData() {
		FragmentManager fm = getFragmentManager();
		Fragment writeFragment = new PassingDataBetweenFragmentsWriteFragment();
		writeFragment.setArguments(getIntent().getExtras());
		fm.beginTransaction().replace(android.R.id.content, writeFragment).commit();
	}

	@Override
	public void sendData(String data) {
		Fragment textFragment = new PassingDataBetweenFragmentsTextFragment();
		Bundle bundle = getIntent().getExtras();
		if (bundle == null) bundle = new Bundle();
		bundle.putString(PassingDataBetweenFragmentsTextFragment.DATA_KEY, data);
		textFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(android.R.id.content, textFragment).commit();
	}
	
}
