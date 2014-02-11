package com.probe.probe.calculator;

public class MemoryActivity extends AbstractListFragmentActivity{

	@Override
	protected AbstractListFragment getContentFragment() {
		return new MemoryListFragment();
	}

}
