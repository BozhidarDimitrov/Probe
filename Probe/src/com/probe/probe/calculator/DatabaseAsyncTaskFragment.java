package com.probe.probe.calculator;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

public class DatabaseAsyncTaskFragment extends Fragment {

	public static interface AsyncTaskFragmentCallbacks {
		public void onInsertFinished(Uri uri);
		public void onUpdateFinished(int rowsUpdated);
		public void onDeleteFinished(int rowsDeleted);
	}
	
	/**
	 * the activity that the fragment is in must contain a method returning
	 * an AsyncTask dependable Fragment
	 */
	public static interface AsyncTaskCallbackHolder {
		public AsyncTaskFragmentCallbacks getCallbacksForAsyncTaskFragment();
	}
	
	private AsyncTaskCallbackHolder mActivity;
	
	private AsyncTaskFragmentCallbacks mCallbacksFragment;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			mActivity = (AsyncTaskCallbackHolder) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + 
					"does not implement the required interface");
		}
		
		//not sure if this is not going to hold a reference to a detached fragment
		//mCallbacksFragment = mActivity.getCallbacksForAsyncTaskFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRetainInstance(true);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		
		mCallbacksFragment = null;
	}
	
	
	private class InsertTask extends AsyncTask<Void, Cursor, Uri> {
		
		private Uri uri;
		private ContentValues cv;
		
		public InsertTask(Uri uri, ContentValues cv) {
			super();
			this.uri = uri;
			this.cv = cv;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//normally used to setup a progress bar or something
		}

		@Override
		protected Uri doInBackground(Void...values) {
			ContentResolver cr = ((Activity) mActivity).getContentResolver();
			return cr.insert(uri, cv);
		}

		@Override
		protected void onPostExecute(Uri result) {
			super.onPostExecute(result);
			mCallbacksFragment = mActivity.getCallbacksForAsyncTaskFragment();
			if (mCallbacksFragment != null) {
				mCallbacksFragment.onInsertFinished(result);
			}
		}
		
	}
	
	private class UpdateTask extends AsyncTask<Void, Cursor, Integer> {
		
		private Uri uri;
		private ContentValues cv;
		private String selection;
		private String[] selectionArgs;
		
		public UpdateTask(Uri uri, ContentValues cv, String selection, String[] selectionArgs) {
			super();
			this.uri = uri;
			this.cv = cv;
			this.selection = selection;
			this.selectionArgs = selectionArgs;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//normally used to setup a progress bar or something
		}

		@Override
		protected Integer doInBackground(Void...values) {
			ContentResolver cr = ((Activity) mActivity).getContentResolver();
			return cr.update(uri, cv, selection, selectionArgs);
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			mCallbacksFragment = mActivity.getCallbacksForAsyncTaskFragment();
			if (mCallbacksFragment != null) {
				mCallbacksFragment.onUpdateFinished(result != null ? result : 0);
			}
		}
		
	}
	
	private class DeleteTask extends AsyncTask<Void, Cursor, Integer> {
		
		private Uri uri;
		private String selection;
		private String[] selectionArgs;
		
		public DeleteTask(Uri uri, String selection, String[] selectionArgs) {
			super();
			this.uri = uri;
			this.selection = selection;
			this.selectionArgs = selectionArgs;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//normally used to setup a progress bar or something
		}

		@Override
		protected Integer doInBackground(Void...values) {
			ContentResolver cr = ((Activity) mActivity).getContentResolver();
			return cr.delete(uri, selection, selectionArgs);
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			mCallbacksFragment = mActivity.getCallbacksForAsyncTaskFragment();
			if (mCallbacksFragment != null) {
				mCallbacksFragment.onDeleteFinished(result != null ? result : 0);
			}
		}
		
	}
	
	public void insert(Uri uri, ContentValues cv) {
		(new InsertTask(uri, cv)).execute();
	}
	
	public void update(Uri uri, ContentValues cv, String selection, String[] selectionArgs) {
		//the ContentResolver.update() method will return a NullPointerException in this case
		if ((uri == null) || (cv == null)) return;
		
		(new UpdateTask(uri, cv, selection, selectionArgs)).execute();
	}
	
	public void delete(Uri uri, String selection, String[] selectionArgs) {
		(new DeleteTask(uri, selection, selectionArgs)).execute();
	}
	
	
}
