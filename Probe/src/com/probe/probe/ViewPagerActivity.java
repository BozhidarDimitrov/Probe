package com.probe.probe;

import com.probe.probe.calculator.DatabaseAsyncTaskFragment;
import com.probe.probe.calculator.VariablesListFragment;
import com.probe.probe.calculator.AbstractListFragment.DatabaseAsyncTaskFragmentHolder;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskFragmentCallbacks;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskCallbackHolder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v13.app.FragmentStatePagerAdapter;

public class ViewPagerActivity extends Activity implements 
		DatabaseAsyncTaskFragmentHolder,
		AsyncTaskCallbackHolder {
	
	private static final String TAG = ViewPagerActivity.class.getSimpleName();
	private static final String ASYNC_TASK_FRAGMENT_TAG = "async_task_fragment";
	
	private DatabaseAsyncTaskFragment asyncTaskFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "onCreate() begin");
		
		setContentView(R.layout.user_functions_main_fragment);
		
		MyFragmentStatePagerAdapter adapter = new MyFragmentStatePagerAdapter(getFragmentManager());
		
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		/*
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(
					android.R.id.content,
					new ViewPagerFragment(),
					"view_pager").commit();
		}
		*/
		
		//////////////////////
		// Task Fragment initialization
		//////////////////////
		FragmentManager fm = getFragmentManager();
		asyncTaskFragment = (DatabaseAsyncTaskFragment) fm.findFragmentByTag(ASYNC_TASK_FRAGMENT_TAG);

		if (asyncTaskFragment == null) {
			asyncTaskFragment = new DatabaseAsyncTaskFragment();
			fm.beginTransaction().add(asyncTaskFragment, ASYNC_TASK_FRAGMENT_TAG).commit();
		}
		
		Log.d(TAG, "onCreate() end");
	}
	
	@Override
	public AsyncTaskFragmentCallbacks getCallbacksForAsyncTaskFragment() {
		return null;
	}

	@Override
	public DatabaseAsyncTaskFragment getDatabaseAsyncTaskFragment() {
		return asyncTaskFragment;
	}
	
	/**
	 * Adapter
	 */
	public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

		public MyFragmentStatePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			
			return new VariablesListFragment();
			/*
			Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
            */
		}

		@Override
		public int getCount() {
			return 100; //test
		}
		
		@Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
		
	}
	
	/**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DemoObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.view_pager_adapter_fragment, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(R.id.view_pager_adapter_fragment_tv)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
	
}
