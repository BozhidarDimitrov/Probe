package com.probe.probe.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.content.UriMatcher;

/**
 * FOR ME:
 * not completed!
 * main functions finished and tested
 * 
 * The ContentProvider for the application
 */
public class UltCalcDbProvider extends ContentProvider{
	
	//Handle to the SQLiteOpenHelper object
	private DbHelper mDbHelper;
	
	/**
	 * UriMatcher instance
	 */
	private static UriMatcher mUriMatcher; // ??? why not final as in sample
	
	/*
	 * Constants used by URI matcher
	 */
	//The incoming URI matches the Variables URI pattern
	private static final int VARIABLES = 1;
	private static final int VARIABLES_ID = 2;
	private static final int MEMORY = 3;
	private static final int MEMORY_ID = 4;
	private static final int USER_FUNCTIONS = 5;
	private static final int USER_FUNCTIONS_ID = 6;
	
	
	/**
	 * A block that initializes and sets the static UriMatcher object
	 */
	static {
		/*
		 * Creates and initializes the URI matcher
		 */
		//Create a new instance
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		//Variables table
		mUriMatcher.addURI(
				DbContract.AUTHORITY, 
				DbContract.Variables.PATH.substring(1), 
				VARIABLES);
		mUriMatcher.addURI(
				DbContract.AUTHORITY, 
				DbContract.Variables.PATH_ID.substring(1) + "#", 
				VARIABLES_ID);
		//Memory table
		mUriMatcher.addURI(
				DbContract.AUTHORITY, 
				DbContract.Memory.PATH.substring(1), 
				MEMORY);
		mUriMatcher.addURI(
				DbContract.AUTHORITY, 
				DbContract.Memory.PATH_ID.substring(1) + "#", 
				MEMORY_ID);
		//UserFunctions table
		mUriMatcher.addURI(
				DbContract.AUTHORITY, 
				DbContract.UserFunctions.PATH.substring(1), 
				USER_FUNCTIONS);
		mUriMatcher.addURI(
				DbContract.AUTHORITY, 
				DbContract.UserFunctions.PATH_ID.substring(1) + "#",
				USER_FUNCTIONS_ID);
	}
	
	@Override
	public boolean onCreate() {
		
		mDbHelper = DbHelper.getInstance(getContext());
		return true;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		
		//match URI with the patterns the provider provide
		int uriMatchValue = mUriMatcher.match(uri);
		
		//Check for a full provider URI. Only the full URIs are allowed for inserts
		switch (uriMatchValue) {
		case VARIABLES:
		case MEMORY:
		case USER_FUNCTIONS:
			break;
		default:
			//TODO how does that work
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		//If no initialValues
		ContentValues values;
		if (initialValues == null) {
			values = new ContentValues();
		} else {
			values = new ContentValues(initialValues);
		}
		
		//saves writing some code
		String tableName = "";
		
		switch (uriMatchValue) {
		case VARIABLES:
			tableName = DbContract.Variables.TABLE_NAME;
			
			//check if missing values
			if (values.containsKey(DbContract.Variables.COLUMN_NAME) == false) {
				values.put(DbContract.Variables.COLUMN_NAME, DbContract.Variables.DEFAULT_NAME);
			}
			
			if (values.containsKey(DbContract.Variables.COLUMN_VALUE) == false) {
				values.put(DbContract.Variables.COLUMN_VALUE, DbContract.Variables.DEFAULT_VALUE);
			}
			break;
		case MEMORY:
			tableName = DbContract.Memory.TABLE_NAME;
			
			//check if missing values
			if (values.containsKey(DbContract.Memory.COLUMN_VALUE) == false) {
				values.put(DbContract.Memory.COLUMN_VALUE, DbContract.Memory.DEFAULT_VALUE);
			}
			break;
		case USER_FUNCTIONS:
			tableName = DbContract.UserFunctions.TABLE_NAME;
			
			//check if missing values
			if (values.containsKey(DbContract.UserFunctions.COLUMN_NAME) == false) {
				values.put(DbContract.UserFunctions.COLUMN_NAME, DbContract.UserFunctions.DEFAULT_NAME);
			}
			
			if (values.containsKey(DbContract.UserFunctions.COLUMN_VALUE) == false) {
				values.put(DbContract.UserFunctions.COLUMN_VALUE, DbContract.UserFunctions.DEFAULT_VALUE);
			}
			
			if (values.containsKey(DbContract.UserFunctions.COLUMN_CATEGORY) == false) {
				values.put(DbContract.UserFunctions.COLUMN_CATEGORY, DbContract.UserFunctions.DEFAULT_CATEGORY);
			}
			break;
		default:
		}
		
		// Opens the database object in "write" mode.
		SQLiteDatabase db;
		try {
			db = mDbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			throw new SQLiteException("Cannot open db" + uri);
		}
		
        // Performs the insert and returns the ID of the new note.
		long rowId = db.insert(
				tableName, 
				DbContract.NULL_COLUMN_HACK, 
				values);
        
		
		//If the insert succeeded, the row ID exists
		if (rowId > 0) {
			Uri rowUri = null;
			
			switch (uriMatchValue) {
			case VARIABLES:
				rowUri = ContentUris.withAppendedId(DbContract.Variables.CONTENT_ID_URI_BASE, rowId);
				break;
			case MEMORY:
				rowUri = ContentUris.withAppendedId(DbContract.Memory.CONTENT_ID_URI_BASE, rowId);
				break;
			case USER_FUNCTIONS:
				rowUri = ContentUris.withAppendedId(DbContract.UserFunctions.CONTENT_ID_URI_BASE, rowId);
				break;
			default:
			}
			
			// Notifies observers registered against this provider that the data changed.
			getContext().getContentResolver().notifyChange(rowUri, null);
			return rowUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		//if an ID is given, we add the corresponding selection clause to the given selection
		String finalSelection = null;
		
		//number of rows deleted
		int count;
		
		//table name (saving some writing)
		String tableName;
		
		switch (mUriMatcher.match(uri)) {
		case VARIABLES_ID:
			finalSelection = 
					DbContract.Variables._ID + 
					" = " + 
					uri.getPathSegments().get(DbContract.Variables.ID_PATH_POSITION);
		case VARIABLES:
			tableName = DbContract.Variables.TABLE_NAME;
			break;
		case MEMORY_ID:
			finalSelection = 
					DbContract.Memory._ID + 
					" = " + 
					uri.getPathSegments().get(DbContract.Memory.ID_PATH_POSITION);
		case MEMORY:
			tableName = DbContract.Memory.TABLE_NAME;
			break;
		case USER_FUNCTIONS_ID:
			finalSelection = 
					DbContract.UserFunctions._ID + 
					" = " + 
					uri.getPathSegments().get(DbContract.UserFunctions.ID_PATH_POSITION);
		case USER_FUNCTIONS:
			tableName = DbContract.UserFunctions.TABLE_NAME;
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		//Opens the database object
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		//if rowId was not given
		if (finalSelection == null) {
			count = db.delete(
					tableName, 
					selection, 
					selectionArgs);
		} else {
			if (selection != null) {
				finalSelection = finalSelection + " AND " + selection;
			}
			
			count = db.delete(
					tableName, 
					finalSelection, 
					selectionArgs);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return count; 
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		
		// if an ID is given, we add the corresponding selection clause to the given selection
		String finalSelection = null;

		// number of rows updated
		int count;

		// table name (saving some writing)
		String tableName;

		switch (mUriMatcher.match(uri)) {
		case VARIABLES_ID:
			finalSelection = DbContract.Variables._ID
					+ " = "
					+ uri.getPathSegments().get(
							DbContract.Variables.ID_PATH_POSITION);
		case VARIABLES:
			tableName = DbContract.Variables.TABLE_NAME;
			break;
		case MEMORY_ID:
			finalSelection = DbContract.Memory._ID
					+ " = "
					+ uri.getPathSegments().get(
							DbContract.Memory.ID_PATH_POSITION);
		case MEMORY:
			tableName = DbContract.Memory.TABLE_NAME;
			break;
		case USER_FUNCTIONS_ID:
			finalSelection = DbContract.UserFunctions._ID
					+ " = "
					+ uri.getPathSegments().get(
							DbContract.UserFunctions.ID_PATH_POSITION);
		case USER_FUNCTIONS:
			tableName = DbContract.UserFunctions.TABLE_NAME;
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// Opens the database object
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		// if rowId was not given
		if (finalSelection == null) {
			count = db.update(
					tableName, 
					values, 
					selection, 
					selectionArgs);
		} else {
			if (selection != null) {
				finalSelection = finalSelection + " AND " + selection;
			}

			count = db.update(
					tableName, 
					values, 
					finalSelection, 
					selectionArgs);
		}

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		switch (mUriMatcher.match(uri)) {
		case VARIABLES_ID:
			qb.appendWhere(
					DbContract.Variables._ID +
					" = " + 
					uri.getPathSegments().get(DbContract.Variables.ID_PATH_POSITION));
		case VARIABLES:
			qb.setTables(DbContract.Variables.TABLE_NAME);
			break;
		case MEMORY_ID:
			qb.appendWhere(
					DbContract.Memory._ID +
					" = " + 
					uri.getPathSegments().get(DbContract.Memory.ID_PATH_POSITION));
		case MEMORY:
			qb.setTables(DbContract.Memory.TABLE_NAME);
			break;
		case USER_FUNCTIONS_ID:
			qb.appendWhere(
					DbContract.UserFunctions._ID +
					" = " + 
					uri.getPathSegments().get(DbContract.UserFunctions.ID_PATH_POSITION));
		case USER_FUNCTIONS:
			qb.setTables(DbContract.UserFunctions.TABLE_NAME);
			break;
		default:
			 // If the URI doesn't match any of the known patterns, throw an exception.
            throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		/*
		String orderBy;
		// If no sort order is specified, uses the default
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = DbContract.DEFAULT_SORT_ORDER;
		} else {
			// otherwise, uses the incoming sort order
			orderBy = sortOrder;
		}
		*/
		
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		Cursor c = qb.query(
				db, 
				projection, 
				selection, 
				selectionArgs, 
				null, 
				null, 
				null);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		
		switch (mUriMatcher.match(uri)) {
		case VARIABLES:
			return DbContract.Variables.CONTENT_TYPE;
		case VARIABLES_ID:
			return DbContract.Variables.CONTENT_ITEM_TYPE;
		case MEMORY:
			return DbContract.Memory.CONTENT_TYPE;
		case MEMORY_ID:
			return DbContract.Memory.CONTENT_ITEM_TYPE;
		case USER_FUNCTIONS:
			return DbContract.UserFunctions.CONTENT_TYPE;
		case USER_FUNCTIONS_ID:
			return DbContract.UserFunctions.CONTENT_ITEM_TYPE;
		default:
			 //Per ContentResolver.getType()'s documentation,
			 //Returns A MIME type for the content, 
			 //or null if the URL is invalid or the type is unknown
			return null;
		}
	}

	public DbHelper getOpenHelperForTest() {
		return mDbHelper;
	}
}
