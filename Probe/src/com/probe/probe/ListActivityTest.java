package com.probe.probe;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivityTest extends ListActivity implements OnClickListener{

	String[] rows = {"row1", "row2", "row3"};
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_activity_test);
		
		ListActivityTest_CustomArrayAdapter adapter = new ListActivityTest_CustomArrayAdapter(this, rows);
		
		setListAdapter(adapter);
		
		tv = (TextView) findViewById(R.id.listActivityTestTextView);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		tv.setText(tv.getText() + " onListItemClick() -> ");
		
		switch (v.getId()) {
		case R.id.ListActivityTestRowItem_Button1:
			tv.setText(tv.getText() + "Button1" + "->");
			break;
		case R.id.ListActivityTestRowItem_Button2:
			tv.setText(tv.getText() + "Button2" + "->");
			break;
		case R.id.ListActivityTestRowItem_TextView:
			tv.setText(tv.getText() + "position" + "->");
			break;
		default:
			tv.setText(tv.getText() + "Unknown: " + v.getClass() + " ->");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ListActivityTestRowItem_Button1:
			tv.setText(tv.getText() + "Button1" + "->");
			break;
		case R.id.ListActivityTestRowItem_Button2:
			tv.setText(tv.getText() + "Button2" + "->");
			break;
		case R.id.ListActivityTestRowItem_TextView:
			tv.setText(tv.getText() + "position" + "->");
			break;
		default:
			tv.setText(tv.getText() + "Unknown ->");
		}
	}
	
	
}
