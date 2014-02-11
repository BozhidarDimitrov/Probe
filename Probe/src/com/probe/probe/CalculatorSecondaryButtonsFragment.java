package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorSecondaryButtonsFragment extends Fragment implements OnClickListener{

	Button b1;
	Button b2;
	Button b3;
	OnSecondaryButtonClickedListener mOnSecondaryButtonClickedListener;
	TextView state;
	String text;
	
	/*
	 * interface koito da se izpylnqva ot activitito za da priema ot tozi fragment
	 * koi buton e natisnat.
	 */
	public interface OnSecondaryButtonClickedListener{
		public void onSecondaryButtonClicked(Button b);
	}
	
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			mOnSecondaryButtonClickedListener = (OnSecondaryButtonClickedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnSecondaryButtonClickedListener");
		}
		
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		return inflater.inflate(R.layout.secondary_buttons, container, false);
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initialize();
	}

	private void initialize() {
		
		//komentari
		state = (TextView) getActivity().findViewById(R.id.tvSecBut);
		text = "init() -> ";
		state.setText(text);
		
		b1 = (Button) getActivity().findViewById(R.id.secondaryB1);
		b1.setOnClickListener(this);
		b2 = (Button) getActivity().findViewById(R.id.secondaryB2);
		b2.setOnClickListener(this);
		b3 = (Button) getActivity().findViewById(R.id.secondaryB3);
		b3.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		//komentari
		text += "onClick() -> ";
		state.setText(text);
		
		mOnSecondaryButtonClickedListener.onSecondaryButtonClicked((Button) v);
	}



	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	
}