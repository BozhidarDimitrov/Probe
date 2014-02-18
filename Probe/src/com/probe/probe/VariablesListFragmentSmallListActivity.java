package com.probe.probe;

import android.app.Fragment;

import com.probe.probe.calculator.AbstractListFragmentActivity;

public class VariablesListFragmentSmallListActivity extends AbstractListFragmentActivity{

	@Override
	protected Fragment getContentFragment() {
		return new VariablesListFragmentSmallList();
	}

}
