package com.probe.probe;

import com.probe.probe.calculator.AbstractListFragment.DatabaseAsyncTaskFragmentHolder;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskFragmentCallbacks;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskCallbackHolder;
import com.probe.probe.calculator.UserFunctionsMainFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class ViewPagerActivityNestedFragments extends Activity implements 
		DatabaseAsyncTaskFragmentHolder,
		AsyncTaskCallbackHolder {
	
	private static final String ASYNC_TASK_FRAGMENT_TAG = "async_task_fragment";
	
	private DatabaseAsyncTaskFragment asyncTaskFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState == null) {
			getFragmentManager()
					.beginTransaction()
					.add(android.R.id.content, new UserFunctionsMainFragment(),
							"view_pager").commit();
		}

		//////////////////////
		// Task Fragment initialization
		//////////////////////
		FragmentManager fm = getFragmentManager();
		asyncTaskFragment = (DatabaseAsyncTaskFragment) fm
				.findFragmentByTag(ASYNC_TASK_FRAGMENT_TAG);

		if (asyncTaskFragment == null) {
			asyncTaskFragment = new DatabaseAsyncTaskFragment();
			fm.beginTransaction()
					.add(asyncTaskFragment, ASYNC_TASK_FRAGMENT_TAG).commit();
		}
	}

	@Override
	public AsyncTaskFragmentCallbacks getCallbacksForAsyncTaskFragment() {
		return null;
	}

	@Override
	public DatabaseAsyncTaskFragment getDatabaseAsyncTaskFragment() {
		return asyncTaskFragment;
	}
	
}
