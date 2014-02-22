package com.probe.probe;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class ResourceManager {
	
	private Activity mActivity;
	
	private TextView tvDefault;
	private TextView tv300;
	private TextView tv600;
	
	public ResourceManager(Activity activity) {
			this.mActivity = activity;
			
			tvDefault = (TextView) activity.findViewById(R.id.resource_manager_tv_default);
			tv300 = (TextView) activity.findViewById(R.id.resource_manager_tv_sw300dp);
			tv600 = (TextView) activity.findViewById(R.id.resource_manager_tv_sw600dp);
	}
	
	public boolean findDefault() {
		return tvDefault != null && tvDefault.getVisibility() == View.VISIBLE;
	}
	
	public boolean find300() {
		return tv300 != null && tv300.getVisibility() == View.VISIBLE;
	}
	
	public boolean find600() {
		return tv600 != null && tv600.getVisibility() == View.VISIBLE;
	}
}
