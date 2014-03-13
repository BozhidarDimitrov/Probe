package com.probe.probe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class ViewSwitcherTest extends Activity implements OnClickListener{
	
	private static final int ANIM_DURATION = 1000;
	
	ViewSwitcher viewSwitcher;
	Button change;
	TextView result;

	TranslateAnimation inAnimUp;
	TranslateAnimation outAnimUp;
	TranslateAnimation inAnimDown;
	TranslateAnimation outAnimDown;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_switcher_test);
		
		viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher_test_view_switcher);
		change = (Button) findViewById(R.id.view_switcher_test_button_change);
		change.setOnClickListener(this);
		result = (TextView) findViewById(R.id.view_switcher_test_result);
		
		View text = (View) findViewById(R.id.view_switcher_test_text_view);
		View button1 = (View) findViewById(R.id.view_switcher_test_button_1);
		View button2 = (View) findViewById(R.id.view_switcher_test_button_2);
		
		if (text != null && text.getVisibility() == View.VISIBLE) {
			result.append("text ");
		}
		
		if (button1 != null && button1.getVisibility() == View.VISIBLE) {
			result.append("button1 ");
		}
		
		if (button2 != null && button2.getVisibility() == View.VISIBLE) {
			result.append("button2 ");
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_switcher_test_button_change:
			//viewSwitcher.setAnimation(AnimationUtils.makeInAnimation(this, true));
			/*
			viewSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
			viewSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
			*/
			
			//viewSwitcher.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
			
			/*
			Animation anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
			anim.setDuration(ANIM_DURATION);
			viewSwitcher.setAnimation(anim);
			*/
			
			inAnimUp = new TranslateAnimation(0, 0, viewSwitcher.getHeight(), 0);
			inAnimUp.setDuration(ANIM_DURATION);
			outAnimUp = new TranslateAnimation(0, 0, 0, - viewSwitcher.getHeight());
			outAnimUp.setDuration(ANIM_DURATION);
			
			inAnimDown = new TranslateAnimation(0, 0, - viewSwitcher.getHeight(), 0);
			inAnimDown.setDuration(ANIM_DURATION);
			outAnimDown = new TranslateAnimation(0, 0, 0, viewSwitcher.getHeight());
			outAnimDown.setDuration(ANIM_DURATION);
			
			int currentViewId = viewSwitcher.getCurrentView().getId();
			switch (currentViewId) {
			case R.id.view_switcher_test_frame1:
				viewSwitcher.setInAnimation(inAnimUp);
				viewSwitcher.setOutAnimation(outAnimUp);
				break;
			case R.id.view_switcher_test_frame2:
				viewSwitcher.setInAnimation(inAnimDown);
				viewSwitcher.setOutAnimation(outAnimDown);
				break;
			}
			
			viewSwitcher.showNext();
		}
	}
}
