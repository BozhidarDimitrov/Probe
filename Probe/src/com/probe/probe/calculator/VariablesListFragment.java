package com.probe.probe.calculator;

import java.util.ArrayList;
import java.util.Random;

import com.probe.probe.R;
import com.probe.probe.provider.DbContract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SimpleCursorAdapter;

public class VariablesListFragment extends AbstractListFragment implements 
		OnClickListener,
		OnCheckedChangeListener {
	
	//private static final String TAG = VariablesListFragment.class.getSimpleName();
	
	private SparseBooleanArray checkboxState = new SparseBooleanArray();

	private String[] getFromArray() {
		return new String[]{
				DbContract.Variables.COLUMN_NAME,
				DbContract.Variables.COLUMN_VALUE
		};
	}

	private int[] getToArray() {
		return new int[]{
				R.id.variables_row_item_button_name,
				R.id.variables_row_item_button_value
		};
	}

	@Override
	protected int getLayoutId() {
		return R.layout.list_fragment_only_list;
	}

	@Override
	protected int getRowLayoutId() {
		return R.layout.variables_row_item;
	}

	@Override
	protected int getOptionsMenuId() {
		return R.menu.variables_menu;
	}

	@Override
	protected Uri getContentUriForTable() {
		return DbContract.Variables.CONTENT_URI;
	}
	
	@Override
	protected String getSelection() {
		return null;
	}

	@Override
	protected String[] getSelectionArgs() {
		return null;
	}

	@Override
	protected SimpleCursorAdapter getAdapter() {
		return new VariablesSimpleCursorAdapter(
				getActivity(),
				getRowLayoutId(),
				null,
				getFromArray(),
				getToArray(),
				0,
				this,
				this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.variables_menu_action_new_variable:
			ContentValues cv = new ContentValues();
			Random r = new Random();
			cv.put(DbContract.Variables.COLUMN_NAME, r.nextInt(10));
			mDbFragment.insert(getContentUriForTable(), cv); //test
			return true;
		case R.id.variables_menu_action_delete:
			
			String selection = DbContract.Variables.COLUMN_NAME + " = ?";
			String or = " OR ";
			StringBuilder finalSelection = new StringBuilder();
			ArrayList<String> arguments = new ArrayList<String>();
			Cursor c = mAdapter.getCursor();
			for (int i = 0; i < getListView().getCount(); i++) {
				if (checkboxState.get(i) == true) {
					finalSelection.append(selection);
					finalSelection.append(or);
					
					c.moveToPosition(i);
					
					arguments.add(c.getString(c.getColumnIndex(DbContract.Variables.COLUMN_NAME)));
				}
			}
			
			if (arguments.size() > 0) {
				mDbFragment.delete(
						getContentUriForTable(),
						finalSelection.substring(0, finalSelection.length() - or.length()),
						arguments.toArray(new String[arguments.size()]));
				
				checkboxState.clear();
			}
			return true;
		case R.id.variables_menu_action_select_all:
			for (int i = 0; i < getListView().getCount(); i++) {
				checkboxState.put(i, true);
			}
			getListView().invalidateViews();
			return true;
		case R.id.variables_menu_action_unselect_all:
			for (int i = 0; i < getListView().getCount(); i++) {
				checkboxState.put(i, false);
			}
			getListView().invalidateViews();
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public void onClick(View v) {
		Toast toast = Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	@Override
	public void onCheckedChanged(CompoundButton cb, boolean newState) {
		Integer position;
		try {
			position = (Integer) cb.getTag();
		} catch (ClassCastException e) {
			throw new ClassCastException(getActivity().toString() + 
					" int to Integer cast failed");
		}
		
		checkboxState.put(position, newState);
	}
	
	/**
	 * Custom Simple Cursor adapter implementing getView()
	 */
	private class VariablesSimpleCursorAdapter extends SimpleCursorAdapter {
		
		private Context context;
		private OnCheckedChangeListener onCheckedChangeListener;
		private OnClickListener onClickListener;

		public VariablesSimpleCursorAdapter(
				Context context, int layout, Cursor c, String[] from, int[] to, int flags, 
				OnCheckedChangeListener onCheckedChangeListener,
				OnClickListener onClickListener) {
			super(context, layout, c, from, to, flags);
			this.context = context;
			this.onCheckedChangeListener = onCheckedChangeListener;
			this.onClickListener = onClickListener;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;
			
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.variables_row_item, null);
				
				holder = new ViewHolder();
				holder.cb = (CheckBox) convertView.findViewById(R.id.variables_row_item_check_box);
				holder.name = (Button) convertView.findViewById(R.id.variables_row_item_button_name);
				holder.value = (Button) convertView.findViewById(R.id.variables_row_item_button_value);
				holder.edit = (Button) convertView.findViewById(R.id.variables_row_item_button_edit);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			// set check box to correct state
			holder.cb.setOnCheckedChangeListener(onCheckedChangeListener);
			holder.cb.setTag(Integer.valueOf(position));
			holder.cb.setChecked(checkboxState.get(position));
			
			
			// set onClickListener for the buttons
			holder.name.setOnClickListener(onClickListener);
			holder.value.setOnClickListener(onClickListener);
			holder.edit.setOnClickListener(onClickListener);
			
			return super.getView(position, convertView, parent);
		}
	}
	
	
	/**
	 * ViewHolder for the variables layout. Used to avoid calling the findViewById() method
	 */
	private static class ViewHolder {
		CheckBox cb;
		Button name;
		Button value;
		Button edit;
	}
}
