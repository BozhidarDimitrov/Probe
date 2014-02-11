package com.probe.probe.calculator;

import com.probe.probe.calculator.DatabaseAsyncTaskFragment.AsyncTaskFragmentCallbacks;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public abstract class AbstractListFragment extends ListFragment implements 
		LoaderManager.LoaderCallbacks<Cursor>,
		AsyncTaskFragmentCallbacks {
	
	private static final String TAG = AbstractListFragment.class.getSimpleName();
	
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
		mAdapter = getAdapter();
		
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
		//should be implemented by the subclasses
		return super.onOptionsItemSelected(item);
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
		
		/*
		Log.d(TAG,this + ": cursor.getCount() = " + cursor.getCount());
		Log.d(TAG,this + ": cursor.getColumnCount() = " + cursor.getColumnCount());
		for (int i = 0; i < cursor.getColumnCount(); i++) {
			Log.d(TAG,this + ": column " + i + " = " + cursor.getColumnName(i));
		}
		*/
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
	
	protected abstract int getLayoutId();
	protected abstract int getRowLayoutId();
	protected abstract int getOptionsMenuId();
	
	protected abstract Uri getContentUriForTable();
	
	protected abstract String getSelection();
	protected abstract String[] getSelectionArgs();
	
	protected abstract SimpleCursorAdapter getAdapter();
}
