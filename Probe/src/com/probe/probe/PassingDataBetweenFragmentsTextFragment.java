package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PassingDataBetweenFragmentsTextFragment extends Fragment implements 
		OnClickListener {
	
	public interface DataSource {
		public void getData();
	}
	
	public static final String DATA_KEY = "data_key";
	private static final String TAG = PassingDataBetweenFragmentsTextFragment.class.getSimpleName();
	
	private TextView tv;
	private Button b;
	private DataSource source;
	
	private boolean debug = true;
	private String intro = this.toString().substring(this.toString().length() - 4) + "-----> text";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (debug) Log.d(TAG, intro + " onAttach()");
		
		try {
			source = (DataSource) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(this + 
					" does not implement the required interface");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (debug) Log.d(TAG, intro + " onCreate()");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (debug) Log.d(TAG, intro + " onCreateView()");
		return inflater.inflate(R.layout.passing_data_between_fragments_text_fragment, 
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (debug) Log.d(TAG, intro + " onActivityCreated()");
		
		initialize();
		
		if (getArguments() != null) {
			if (getArguments().containsKey(DATA_KEY)){
				tv.setText(getArguments().getString(DATA_KEY));
			}
		}
	}

	private void initialize() {
		tv = (TextView) getActivity().
				findViewById(R.id.passing_data_between_fragments_text_fragment_tv);
		b = (Button) getActivity().
				findViewById(R.id.passing_data_between_fragments_text_fragment_b);
		b.setOnClickListener(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (debug) Log.d(TAG, intro + " onStart()");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (debug) Log.d(TAG, intro + " onResume()");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (debug) Log.d(TAG, intro + " onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (debug) Log.d(TAG, intro + " onStop()");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (debug) Log.d(TAG, intro + " onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (debug) Log.d(TAG, intro + " onDestroy()");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (debug) Log.d(TAG, intro + " onDetach()");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.passing_data_between_fragments_text_fragment_b:
			source.getData();
			break;
		}
	}
	
	
}
