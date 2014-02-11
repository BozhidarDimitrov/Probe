package com.probe.probe.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.probe.probe.R;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskFragmentCallbacks;
import com.probe.probe.provider.DbContract;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class UserFunctionsMainFragment extends Fragment implements 
			AsyncTaskFragmentCallbacks {
	
	//private static final String TAG = UserFunctionsMainFragment.class.getSimpleName();
	
	private static final String CATEGORIES_LIST_TAG = "categories_list_tag";
	private static final String CATEGORIES_STRING_SEPARATOR = ",";
	
	private List<String> categories = new ArrayList<String>();
	
	private Activity activity;
	private ViewPager pager;
	private MyFragmentStatePagerAdapter adapter;
	
	private AsyncTaskFragmentCallbacks callbacks = null; //?? = null
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		this.activity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.user_functions_main_fragment, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		getCategoriesFromSharedPreferences();
		
		adapter = new MyFragmentStatePagerAdapter(activity.getFragmentManager());
		
		pager = (ViewPager) activity.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		
	}
	
	/*
	 * Takes the stored categories string in the shared preferences and
	 * fills the categories list
	 */
	private void getCategoriesFromSharedPreferences(){
		SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE); //TODO private?
		String savedCategoriesString = prefs.getString(CATEGORIES_LIST_TAG, 
				DbContract.UserFunctions.DEFAULT_CATEGORY);//?? default
		StringTokenizer st = new StringTokenizer(savedCategoriesString, 
				CATEGORIES_STRING_SEPARATOR);
		
		while (st.hasMoreTokens()) {
			categories.add(st.nextToken());
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		
		SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE); //TODO private?
		StringBuilder sb = new StringBuilder();
		
		for (String str: categories) {
			sb.append(str + CATEGORIES_STRING_SEPARATOR);
		}
		
		prefs.edit().putString(CATEGORIES_LIST_TAG, sb.toString()).commit();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.user_functions_main_fragment_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.user_functions_main_fragment_menu_action_new_category:
			//TEST
			if (categories.contains("new_category") == false) {
				categories.add("new_category");
				adapter = new MyFragmentStatePagerAdapter(activity.getFragmentManager());
				pager.setAdapter(adapter);
			}
			return true;
		case R.id.user_functions_main_fragment_menu_action_delete_category:
			//TEST
			if (categories.contains("new_category") == true) {
				categories.remove("new_category");
				adapter = new MyFragmentStatePagerAdapter(activity.getFragmentManager());
				pager.setAdapter(adapter);
			}
			return true;
		default:
			return false;
		}
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
			UserFunctionsListFragment fragment = new UserFunctionsListFragment();
			Bundle args = new Bundle();
			args.putString(UserFunctionsListFragment.CATEGORY_ARGUMENT_TAG, 
					categories.get(position));
			fragment.setArguments(args);
			callbacks = fragment;
			return fragment;
		}

		@Override
		public int getCount() {
			return categories.size();
		}
		
		@Override
        public CharSequence getPageTitle(int position) {
			return categories.get(position);
        }
	}

	@Override
	public void onInsertFinished(Uri uri) {
		callbacks.onInsertFinished(uri);
	}

	@Override
	public void onUpdateFinished(int rowsUpdated) {
		callbacks.onUpdateFinished(rowsUpdated);
	}

	@Override
	public void onDeleteFinished(int rowsDeleted) {
		callbacks.onDeleteFinished(rowsDeleted);
	}
}
