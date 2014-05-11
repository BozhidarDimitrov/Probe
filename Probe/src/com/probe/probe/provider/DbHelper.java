package com.probe.probe.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * FOR ME:
 * finished, tested.
 * Needs work on the onUpgrade method
 * 
 * DbHelper is a SQLiteOpenHelper implemented as a 
 * singleton with lazy initialization using double locking mechanism
 */
public class DbHelper extends SQLiteOpenHelper{

	private static DbHelper instance = null;
	
	private DbHelper(Context context) {
		super (context, DbContract.DB_NAME, null, DbContract.DB_VERSION);
	}
	
	/*
	 * The first time instance is being initialized I use as an argument
	 * context.getApplicationContext(), because holding a reference to a
	 * potential Activity context may cause leaks
	 */
	public static DbHelper getInstance(Context context) {
		if (instance == null) {
			synchronized(DbHelper.class) {
				if (instance == null) {
					instance = new DbHelper(context.getApplicationContext());
				}
			}
		}
		
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Variables table
		db.execSQL("CREATE TABLE " + DbContract.Variables.TABLE_NAME + " (" +
				DbContract.Variables._ID + " INTEGER PRIMARY KEY, " + 
				DbContract.Variables.COLUMN_NAME + " TEXT NOT NULL, " + 
				DbContract.Variables.COLUMN_VALUE + " TEXT NOT NULL" + ");");
		
		//Memory table
		db.execSQL("CREATE TABLE " + DbContract.Memory.TABLE_NAME + " (" +
				DbContract.Memory._ID + " INTEGER PRIMARY KEY, " + 
				DbContract.Memory.COLUMN_VALUE + " TEXT NOT NULL" + ");");
		
		//UserFunctions table
		db.execSQL("CREATE TABLE " + DbContract.UserFunctions.TABLE_NAME + " (" +
				DbContract.UserFunctions._ID + " INTEGER PRIMARY KEY, " + 
				DbContract.UserFunctions.COLUMN_NAME + " TEXT NOT NULL, " + 
				DbContract.UserFunctions.COLUMN_VALUE + " TEXT NOT NULL, " + 
				DbContract.UserFunctions.COLUMN_CATEGORY + " TEXT NOT NULL" + ");");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO before uploading on the market. TEST (i've found a good stack overflow post, use it)
		db.execSQL("DROP TABLE IF EXISTS " + DbContract.Variables.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DbContract.Memory.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DbContract.UserFunctions.TABLE_NAME);
		
		onCreate(db);
	}

	public void dropTablesForTest() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + DbContract.Variables.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DbContract.Memory.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DbContract.UserFunctions.TABLE_NAME);
		
		onCreate(db);
	}
}
