package com.probe.probe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CalculatorDisplayFragment extends Fragment{
	
	TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		
		return inflater.inflate(R.layout.display, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initialize();
	}

	private void initialize() {
		tv = (TextView) getActivity().findViewById(R.id.tvDisplay);
	}
	
	public void show(String str){
		tv.setText(str);
	}
}
