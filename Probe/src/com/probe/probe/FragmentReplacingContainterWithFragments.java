package com.probe.probe;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentReplacingContainterWithFragments extends Activity implements 
		OnClickListener {
	
	private static final String BIG_FRAGMENT_TAG = "content_fragment";
	private static final String SMALL_FRAGMENT_TAG_1 = "small_fragment_tag_1";
	private static final String SMALL_FRAGMENT_TAG_2 = "small_fragment_tag_2";
	
	private Button replace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_replacing_container_with_fragments);
		
		
		replace = (Button) findViewById(R.id.fragment_replacing_container_with_fragments_button_replace);
		replace.setOnClickListener(this);
		
		FragmentManager fm = getFragmentManager();
		
		//////////////////////
		// Content Fragment initialization
		//////////////////////
		Fragment contentFragment = (Fragment) fm.findFragmentByTag(BIG_FRAGMENT_TAG);
		Log.d("blq", "initialization");
		if (contentFragment == null) {
			contentFragment = new ScrollingButtonsFragment();
			contentFragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().replace(
					R.id.fragment_replacing_container_with_fragments_container, 
					contentFragment, 
					BIG_FRAGMENT_TAG).commit();
		}
	}

	@Override
	public void onClick(View v) {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = (Fragment) fm.findFragmentByTag(BIG_FRAGMENT_TAG);
		
		if (fragment == null) {
			Log.d("blq", "case 1");
			
			fragment = (Fragment) fm.findFragmentByTag(SMALL_FRAGMENT_TAG_1);
			fm.beginTransaction().remove(fragment).commit();
			
			fragment = (Fragment) fm.findFragmentByTag(SMALL_FRAGMENT_TAG_2);
			fm.beginTransaction().remove(fragment).commit();
			
			fragment = new ScrollingButtonsFragment();
			fragment.setArguments(getIntent().getExtras());
			fm.beginTransaction().replace(
					R.id.fragment_replacing_container_with_fragments_container, 
					fragment, 
					BIG_FRAGMENT_TAG).commit();
			
		} else {
			Log.d("blq", "case 2");
			
			fm.beginTransaction().remove(fragment).commit();
			
			/*
			FrameLayout view = (FrameLayout) findViewById(R.id.blqblq);
			if (view != null) ((ViewManager) view.getParent()).removeView(view);
			??? In the newer SDKs its (ViewGroup) :) –  Ron Nov 13 '12 at 9:45
			*/
			
			fm.beginTransaction().replace(
					R.id.fragment_replacing_container_with_fragments_frame1, 
					new ScrollingButtonsFragment(), 
					SMALL_FRAGMENT_TAG_1).commit();
			
			fm.beginTransaction().replace(
					R.id.fragment_replacing_container_with_fragments_frame2, 
					new ScrollingButtonsFragment(), 
					SMALL_FRAGMENT_TAG_2).commit();
					
		}
		
	}

}
