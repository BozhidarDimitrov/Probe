package com.probe.probe.calculator;

import java.util.ArrayList;
import java.util.Random;

import com.probe.probe.R;
import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskFragmentCallbacks;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public abstract class AbstractListFragment extends ListFragment implements 
		LoaderManager.LoaderCallbacks<Cursor>,
		AsyncTaskFragmentCallbacks, 
		OnCheckedChangeListener {
	
	private static final String TAG = AbstractListFragment.class.getSimpleName();
	
	protected SparseBooleanArray checkboxState = new SparseBooleanArray();
	
	/**
	 * the activity that the fragment is in must contain a method returning
	 * an AsyncTaskFragment doing operations on a database
	 */
	public static interface DatabaseAsyncTaskFragmentHolder {
		public DatabaseAsyncTaskFragment getDatabaseAsyncTaskFragment();
	}
	
	protected SimpleCursorAdapter mAdapter;
	
	protected DatabaseAsyncTaskFragment mDbFragment;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		DatabaseAsyncTaskFragmentHolder mActivity;
		
		try {
			mActivity = (DatabaseAsyncTaskFragmentHolder) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + 
					"does not implement the required interface");
		}
		
		mDbFragment = mActivity.getDatabaseAsyncTaskFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(getLayoutId(), container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//Log.d(TAG,this + ": onActivityCreated()->getAdapter() ");
		mAdapter = new MySimpleCursorAdapter(
				getActivity(),
				getRowLayoutId(),
				null,
				getFromArray(),
				getToArray(),
				0,
				getListener());
		
		//Log.d(TAG,this + ": onActivityCreated()->setListAdapter() ");
		setListAdapter(mAdapter);
		
		//Log.d(TAG,this + ": onActivityCreated()->initLoader() ");
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	/*
	 * MENU
	 */
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(getOptionsMenuId(), menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		//should be implemented by the subclasses
		super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.variables_menu_action_new_variable:
			//TEST
			ContentValues cv = new ContentValues();
			Random r = new Random();
			cv.put(getNameForKeyColumn(), r.nextInt(10));
			mDbFragment.insert(getContentUriForTable(), cv); //test
			return true;
		case R.id.variables_menu_action_delete:
			
			String selection = getNameForKeyColumn() + " = ?";
			String or = " OR ";
			StringBuilder finalSelection = new StringBuilder();
			ArrayList<String> arguments = new ArrayList<String>();
			Cursor c = mAdapter.getCursor();
			
			//an element in the list has the same list, checkboxState, cursor position
			//the list position is not always reachable (covert views), so knowing the
			//checkboxState position we search for the element in the cursor
			for (int i = 0; i < getListView().getCount(); i++) {
				if (checkboxState.get(i) == true) {
					finalSelection.append(selection);
					finalSelection.append(or);
					
					c.moveToPosition(i);
					
					arguments.add(c.getString(c.getColumnIndex(getNameForKeyColumn())));
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

	/*
	 * LOADER
	 */
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		return new CursorLoader(
				getActivity(), 
				getContentUriForTable(), 
				null, 
				getSelection(), 
				getSelectionArgs(), 
				null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		//no need to use changeCursor(), as the framework will close the cursor anyway
		//P.S. it might even crash if closed two times
		mAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
	
	/*
	 * AsyncTaskFragmentCallbacks interface methods
	 */
	
	@Override
	public void onInsertFinished(Uri uri) {
		// b ---------------------------------------------------------------------
		CharSequence text = "onInsertFinished " + uri.toString();
		Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		toast.show();
		// e ---------------------------------------------------------------------
	}
	
	@Override
	public void onUpdateFinished(int rowsUpdated) {
		
	}
	
	@Override
	public void onDeleteFinished(int rowsDeleted) {
		// b ---------------------------------------------------------------------
		CharSequence text = "onDeleteFinished " + rowsDeleted;
		Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		toast.show();
		// e ---------------------------------------------------------------------
	}
	
	@Override
	public void onCheckedChanged(CompoundButton cb, boolean newState) {
		Integer position;
		try {
			position = (Integer) cb.getTag();
		} catch (ClassCastException e) {
			throw new ClassCastException(TAG + 
					": int to Integer cast failed");
		}
		
		checkboxState.put(position, newState);
	}
	
	/**
	 * Custom Simple Cursor adapter implementing getView()
	 */
	private class MySimpleCursorAdapter extends SimpleCursorAdapter {
		
		private Context context;
		private AbstractListFragment listener;

		public MySimpleCursorAdapter(
				Context context, int layout, Cursor c, String[] from, int[] to, int flags, 
				AbstractListFragment listener) {
			super(context, layout, c, from, to, flags);
			this.context = context;
			this.listener = listener;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;
			
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(getRowLayoutId(), null);
				
				holder = getNewViewHolderInstance();
				holder.findViews(convertView);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.setup(position, listener);
			
			return super.getView(position, convertView, parent);
		}
	}
	
	/**
	 * ViewHolder for a layout. Used to avoid calling the findViewById() method
	 */
	public interface ViewHolder {
		public void findViews(View convertView);
		public void setup(int position, AbstractListFragment listener);
	}
	
	protected abstract int getLayoutId();
	protected abstract int getRowLayoutId();
	protected abstract int getOptionsMenuId();
	
	protected abstract String[] getFromArray();
	protected abstract int[] getToArray();
	
	protected abstract Uri getContentUriForTable();
	protected abstract String getNameForKeyColumn();
	
	protected abstract String getSelection();
	protected abstract String[] getSelectionArgs();
	
	protected abstract ViewHolder getNewViewHolderInstance();
	//TODO should the listener be limited to just AbstractListFragmets
	protected abstract AbstractListFragment getListener();
}
