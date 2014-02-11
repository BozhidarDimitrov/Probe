package com.probe.probe.calculator;

import android.app.Fragment;
import android.os.Bundle;

public class UserFunctionsActivity extends AbstractListFragmentActivity{

	@Override
	protected Fragment getContentFragment() {
		
		/*
		UserFunctionsListFragment fragment = new UserFunctionsListFragment();
		Bundle args = new Bundle();
		args.putString(UserFunctionsListFragment.CATEGORY_ARGUMENT_TAG, "default");
		fragment.setArguments(args);
		return fragment;
		*/
		
		return new UserFunctionsMainFragment();
	}
}
