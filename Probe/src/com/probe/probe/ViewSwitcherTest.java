package com.probe.probe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewSwitcher;

public class ViewSwitcherTest extends Activity implements OnClickListener{
	
	ViewSwitcher viewSwitcher;
	Button change;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_switcher_test);
		
		viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher_test_view_switcher);
		change = (Button) findViewById(R.id.view_switcher_test_button_change);
		change.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_switcher_test_button_change:
			viewSwitcher.setAnimation(AnimationUtils.makeInAnimation(this, true));
			viewSwitcher.showNext();
		}
	}
}
