package com.probe.probe;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NestedFragmentsWorkaroundFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("blq", "onCreateView in fragment");
		return inflater.inflate(R.layout.nested_fragments_workaround_fragment, container, false);
	}
	
	
}
