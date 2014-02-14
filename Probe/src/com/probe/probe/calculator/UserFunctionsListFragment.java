package com.probe.probe.calculator;

import com.probe.probe.R;
import com.probe.probe.provider.DbContract;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class UserFunctionsListFragment extends AbstractListFragment implements
		OnClickListener {
	
	public static final String CATEGORY_ARGUMENT_TAG = "category";
	private static final String TAG = UserFunctionsListFragment.class.getSimpleName();
	
	@Override
	protected String[] getFromArray() {
		return new String[]{
				DbContract.UserFunctions.COLUMN_NAME,
				DbContract.UserFunctions.COLUMN_VALUE
		};
	}
	
	@Override
	protected int[] getToArray() {
		return new int[]{
				R.id.user_functions_row_item_button_name,
				R.id.user_functions_row_item_button_value
		};
	}

	@Override
	protected int getLayoutId() {
		return R.layout.list_fragment_only_list;
	}

	@Override
	protected int getRowLayoutId() {
		return R.layout.user_functions_row_item;
	}

	@Override
	protected int getOptionsMenuId() {
		return R.menu.basic_list_fragment_menu;
	}

	@Override
	protected Uri getContentUriForTable() {
		return DbContract.UserFunctions.CONTENT_URI;
	}
	
	@Override
	protected String getNameForKeyColumn() {
		return DbContract.UserFunctions.COLUMN_NAME;
	}

	@Override
	protected String getSelection() {
		if (getArguments() == null) return null;
		return DbContract.UserFunctions.COLUMN_CATEGORY + " = ?";
	}

	@Override
	protected String[] getSelectionArgs() {
		if (getArguments() == null) return null;
		return new String[]{getArguments().getString(CATEGORY_ARGUMENT_TAG)};
	}
	
	@Override
	protected ViewHolder getNewViewHolderInstance() {
		return new UserFunctionsViewHolder();
	}
	
	@Override
	protected AbstractListFragment getListener() {
		return this;
	}
	
	@Override
	public void onClick(View v) {
		Toast toast = Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	
	private class UserFunctionsViewHolder implements ViewHolder{
		CheckBox cb;
		Button name;
		Button value;
		Button edit;
		
		public void findViews(View convertView) {
			this.cb = (CheckBox) convertView.findViewById(R.id.user_functions_row_item_check_box);
			this.name = (Button) convertView.findViewById(R.id.user_functions_row_item_button_name);
			this.value = (Button) convertView.findViewById(R.id.user_functions_row_item_button_value);
			this.edit = (Button) convertView.findViewById(R.id.user_functions_row_item_button_edit);
		}
		
		public void setup(int position, AbstractListFragment listener) {
			
			OnCheckedChangeListener onCheckedChangeListener;
			try {
				onCheckedChangeListener = (OnCheckedChangeListener) listener;
			} catch (ClassCastException e) {
				throw new ClassCastException(TAG + 
						": listener does not implement the required interface");
			}
			
			// set check box to correct state
			this.cb.setOnCheckedChangeListener(onCheckedChangeListener);
			this.cb.setTag(Integer.valueOf(position));
			this.cb.setChecked(checkboxState.get(position));

			OnClickListener onClickListener;
			try {
				onClickListener = (OnClickListener) listener;
			} catch (ClassCastException e) {
				throw new ClassCastException(TAG + 
						": listener does not implement the required interface");
			}
			
			// set onClickListener for the buttons
			this.name.setOnClickListener(onClickListener);
			this.value.setOnClickListener(onClickListener);
			this.edit.setOnClickListener(onClickListener);
		}
	}
}
