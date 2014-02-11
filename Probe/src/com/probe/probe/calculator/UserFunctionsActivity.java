package com.probe.probe.calculator;

import android.app.Fragment;

public class UserFunctionsActivity extends AbstractListFragmentActivity{

	@Override
	protected Fragment getContentFragment() {
		return new UserFunctionsMainFragment();
	}
}
