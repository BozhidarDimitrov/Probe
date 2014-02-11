package com.probe.probe;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorLayout extends Activity implements CalculatorSecondaryButtonsFragment.OnSecondaryButtonClickedListener{
	
	private static final String[] secondaryButtons = {"sec1" , "sec2" , "sec3"};
	private static final String[] mainButtons = {"main1" , "main2" , "main3"};
	
	//za komentari
	TextView state;
	String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.calculator_layout);
		
		//komentari
		state = (TextView) findViewById(R.id.textView1);
		text = "onCreate() -> ";
		state.setText(text);
		
		if (savedInstanceState == null){
			fragmentInitialization();
		}
		
	}
	
	private void fragmentInitialization(){
		
		//komentari
		text += "fragInit() -> ";
		state.setText(text);
		
		
		CalculatorDisplayFragment display = new CalculatorDisplayFragment();
		CalculatorSecondaryButtonsFragment secButtons = new CalculatorSecondaryButtonsFragment();
		CalculatorMainButtonsFragment mainButtons = new CalculatorMainButtonsFragment();
		
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		ft.add(R.id.display, display);
		ft.add(R.id.secondaryButtons, secButtons);
		ft.add(R.id.mainButtons, mainButtons);
		ft.commit();
	}

	@Override
	public void onSecondaryButtonClicked(Button b) {
		
		//komentari
		text += "onSecButtClicked() -> ";
		state.setText(text);
		
		int index = 0;
		
		switch (b.getId()){
		case R.id.secondaryB1:
			index = 0;
			break; 
		case R.id.secondaryB2:
			index = 1;
			break;
		case R.id.secondaryB3:
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			
			CalculatorMainButtonsFragment mainButtons = new CalculatorMainButtonsFragment();
			ft.replace(R.id.secondaryButtons, mainButtons);
			//ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			
			ft.commit();
			break;
		}
		
		CalculatorDisplayFragment display = (CalculatorDisplayFragment) getFragmentManager().findFragmentById(R.id.display);
		display.show(secondaryButtons[index]);
	}
	
}
