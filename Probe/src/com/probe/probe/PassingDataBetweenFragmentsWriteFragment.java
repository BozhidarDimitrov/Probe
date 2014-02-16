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
import android.widget.EditText;

public class PassingDataBetweenFragmentsWriteFragment extends Fragment implements 
		OnClickListener {
	
	public interface DataSender {
		public void sendData(String data);
	}
	
	private static final String TAG = PassingDataBetweenFragmentsWriteFragment.class.getSimpleName();
	
	private EditText et;
	private Button b;
	private DataSender sender;
	
	private boolean debug = true;
	private String intro = this.toString().substring(this.toString().length() - 4) + "#####> write";
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (debug) Log.d(TAG, intro + " onAttach()");
		
		try {
			sender = (DataSender) activity;
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
		return inflater.inflate(R.layout.passing_data_between_fragments_write_fragment,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (debug) Log.d(TAG, intro + " onActivityCreated()");
		
		initialize();
	}

	private void initialize() {
		et = (EditText) getActivity().
				findViewById(R.id.passing_data_between_fragments_write_fragment_et);
		b = (Button) getActivity().
				findViewById(R.id.passing_data_between_fragments_write_fragment_b);
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
		case R.id.passing_data_between_fragments_write_fragment_b:
			sender.sendData(et.getText().toString());
		}
	}

}
