package com.probe.probe;

import com.gwbo.customappadds.AppPromoter;
import com.gwbo.customappadds.AppPromoter.AppPromoterLayoutConfiguration;
import com.gwbo.customappadds.AppRater;
import com.gwbo.customappadds.CustomAddsManager;
import com.gwbo.customappadds.AppRater.AppRaterLayoutConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AppAdds extends Activity implements OnClickListener{

	CustomAddsManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.app_adds);
		
		Button show = (Button) findViewById(R.id.app_adds_button_show);
		
		/*
		LinearLayout container = (LinearLayout) findViewById(R.id.app_adds_container);
		container.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
		*/
		
		// APP RATER
		AppRater rater = new AppRater(
				this, 
				"com.gwbo.selfiephotoeditor", 
				"sunflower28");
		rater.setLauchRate(2);
		
		AppRaterLayoutConfiguration raterConfig = rater.new AppRaterLayoutConfiguration();
		raterConfig
			.backgroundColor(android.R.color.darker_gray)
			.buttonOkTextColor(android.R.color.holo_blue_bright)
			.buttonLaterTextColor(android.R.color.holo_green_light)
			.buttonOtherAppsTextColor(android.R.color.holo_green_light)
			.dividerColor(android.R.color.holo_red_light);
		
		rater.setLayoutConfiguration(raterConfig);
		
		// APP PROMOTER
		AppPromoter promoter = new AppPromoter(
				this, 
				"sunflower28");
		promoter
			.setMode(AppPromoter.MODE_PROBABILITIES);
		
		AppPromoterLayoutConfiguration promoterConfig = promoter.new AppPromoterLayoutConfiguration();
		promoterConfig
				.backgroundColor(android.R.color.darker_gray)
				.buttonYesTextColor(android.R.color.holo_blue_bright)
				.descriptionColor(android.R.color.holo_green_light);
		
		promoter.setLayoutConfiguration(promoterConfig);
		
		promoter
			.promote(AppPromoter.CODE_GWBO_VOLUMEBOOSTERPRO, 20)
			.promote(AppPromoter.CODE_GWBO_SELFIEPHOTOEDITOR, 80);
		
		manager = new CustomAddsManager(this);
		manager.setGlobalLauchRate(2);
		manager.add(rater);
		manager.add(promoter);
		
		show.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.app_adds_button_show:
			
			manager.requestAdd();
			
			break;
		}
	}
	
	
	
}
