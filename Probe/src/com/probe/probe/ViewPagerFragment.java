package com.probe.probe;

import com.probe.probe.calculator.VariablesListFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerFragment extends Fragment {
	
	private static final String TAG = ViewPagerFragment.class.getSimpleName();
	
	Activity activity;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.user_functions_main_fragment, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//Log.d(TAG, "onActivityCreated(): begin");
		MyFragmentStatePagerAdapter adapter = new MyFragmentStatePagerAdapter(activity.getFragmentManager());
		
		ViewPager pager = (ViewPager) activity.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		//Log.d(TAG, "onActivityCreated(): end");
	}

	/**
	 * Adapter
	 */
	public class MyFragmentStatePagerAdapter extends 
			android.support.v13.app.FragmentStatePagerAdapter {

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
			return 10; //test
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
