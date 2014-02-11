package com.probe.probe.calculator;

import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskFragmentCallbacks;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskCallbackHolder;
import com.probe.probe.calculator.AbstractListFragment.DatabaseAsyncTaskFragmentHolder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;

public abstract class AbstractListFragmentActivity extends Activity implements 
		DatabaseAsyncTaskFragmentHolder,
		AsyncTaskCallbackHolder {
	
	private static final String ASYNC_TASK_FRAGMENT_TAG = "async_task_fragment";
	private static final String CONTENT_FRAGMENT_TAG = "content_fragment";
	
	private AsyncTaskFragmentCallbacks content;
	private DatabaseAsyncTaskFragment asyncTaskFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		
		FragmentManager fm = getFragmentManager();
		
		//////////////////////
		// Content Fragment initialization
		//////////////////////
		Fragment contentFragment = (Fragment) fm.findFragmentByTag(CONTENT_FRAGMENT_TAG);
		
		if (contentFragment == null) {
			contentFragment = getContentFragment();
			contentFragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().add(
					android.R.id.content, 
					contentFragment, 
					CONTENT_FRAGMENT_TAG).commit();
		}
		
		//check if the content fragment implements the interface AsyncTaskFragmentCallbacks
		try {
			content = (AsyncTaskFragmentCallbacks) contentFragment;
		} catch (ClassCastException e) {
			throw new ClassCastException(this + 
					"contentFragment does not implement the required interface");
		}
		
		//////////////////////
		// Task Fragment initialization
		//////////////////////
		asyncTaskFragment = (DatabaseAsyncTaskFragment) fm.findFragmentByTag(ASYNC_TASK_FRAGMENT_TAG);
		
		if (asyncTaskFragment == null) {
			asyncTaskFragment = new DatabaseAsyncTaskFragment();
			fm.beginTransaction().add(asyncTaskFragment, ASYNC_TASK_FRAGMENT_TAG).commit();
		}
		
	}

	@Override
	public AsyncTaskFragmentCallbacks getCallbacksForAsyncTaskFragment() {
		return content;
	}

	@Override
	public DatabaseAsyncTaskFragment getDatabaseAsyncTaskFragment() {
		return asyncTaskFragment;
	}
	
	protected abstract Fragment getContentFragment();

}
