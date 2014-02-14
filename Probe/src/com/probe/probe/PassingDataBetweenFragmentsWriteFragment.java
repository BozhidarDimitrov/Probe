package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
	
	//private static final String TAG = PassingDataBetweenFragmentsWriteFragment.class.getSimpleName();
	
	private EditText et;
	private Button b;
	private DataSender sender;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.passing_data_between_fragments_write_fragment,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.passing_data_between_fragments_write_fragment_b:
			sender.sendData(et.getText().toString());
		}
	}

}
