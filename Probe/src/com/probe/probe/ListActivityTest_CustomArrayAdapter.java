package com.probe.probe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListActivityTest_CustomArrayAdapter extends ArrayAdapter<String> {
	
	private View v;
	private Context context;
	private OnClickListener listener;
	private String[] rows;

	public ListActivityTest_CustomArrayAdapter(Context context, String[] rows) {
		super(context, R.layout.list_activity_test_row_item, rows);
		
		this.context = context;
		this.rows = rows;
		this.listener = (OnClickListener) context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		this.v = convertView;
		
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        v=vi.inflate(R.layout.list_activity_test_row_item, null);
		}
		
		TextView tv = (TextView) v.findViewById(R.id.ListActivityTestRowItem_TextView);
		tv.setText(rows[position]);
		
		Button b1 = (Button) v.findViewById(R.id.ListActivityTestRowItem_Button1);
		b1.setOnClickListener(listener);
		
		Button b2 = (Button) v.findViewById(R.id.ListActivityTestRowItem_Button2);
		b2.setOnClickListener(listener);
		
		return v;
	}
	
	

}
