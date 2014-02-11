package com.probe.probe;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{

	String[] classes = 
		{"MainActivity" , "FragmentLayout" , "CalculatorLayout", "EditTextProbe", "BorderlessButtons", 
			"Buttons", "ScreenSize", "ActionBarTest", "ListActivityTest", "calculator.VariablesActivity",
			"calculator.MemoryActivity", "calculator.UserFunctionsActivity", "ViewPagerActivity", "ViewPagerActivityNestedFragments"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String strClass = classes[position];

		try {
			Class clickedClass = Class.forName("com.probe.probe." + strClass); //--------------------

			Intent intent = new Intent(Menu.this, clickedClass);
			startActivity(intent);
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		//finish();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}
	
	
}

