package com.probe.probe.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DbContract {
	private DbContract() {}
	
	public static final String AUTHORITY = "com.probe.probe.provider.UltCalculator";
	public static final String DB_NAME = "UltCalcDb.db";
	public static final int DB_VERSION = 1;
	public static final String SCHEME = "content://";
	public static final String NULL_COLUMN_HACK = "hack_value";
	public static final String DEFAULT_SORT_ORDER = "modified DESC";
	
	public final static class Variables implements BaseColumns {
		private Variables(){}
		
		public static final String TABLE_NAME = "variables";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_VALUE = "value";
		
		public static final String DEFAULT_NAME = "default_name";
		public static final String DEFAULT_VALUE = "default_value";
		
		public static final String PATH = "/variables";
		public static final String PATH_ID = "/variables/";
		
		public static final int ID_PATH_POSITION = 1;
		
		public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH);
		public static final Uri CONTENT_ID_URI_BASE = 
				Uri.parse(SCHEME + AUTHORITY + PATH_ID);
		public static final Uri CONTENT_ID_URI_PATTERN = 
				Uri.parse(SCHEME + AUTHORITY + PATH_ID + "/#");
		
		public static final String CONTENT_TYPE = 
				"vnd.android.cursor.dir/vnd.com.probe.provider.variables";
		
		public static final String CONTENT_ITEM_TYPE = 
				"vnd.android.cursor.item/vnd.com.probe.provider.variables";
	}
	
	public final static class Memory implements BaseColumns {
		private Memory(){}
		
		public static final String TABLE_NAME = "memory";
		public static final String COLUMN_VALUE = "value";
		
		public static final String DEFAULT_VALUE = "default_value";
		
		public static final String PATH = "/memory";
		public static final String PATH_ID = "/memory/";
		
		public static final int ID_PATH_POSITION = 1;
		
		public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH);
		public static final Uri CONTENT_ID_URI_BASE = 
				Uri.parse(SCHEME + AUTHORITY + PATH_ID);
		public static final Uri CONTENT_ID_URI_PATTERN = 
				Uri.parse(SCHEME + AUTHORITY + PATH_ID + "/#");
		
		public static final String CONTENT_TYPE = 
				"vnd.android.cursor.dir/vnd.com.probe.provider.memory";
		
		public static final String CONTENT_ITEM_TYPE = 
				"vnd.android.cursor.item/vnd.com.probe.provider.memory";
	}
	
	public final static class UserFunctions implements BaseColumns {
		private UserFunctions(){}
		
		public static final String TABLE_NAME = "user_functions";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_VALUE = "value";
		public static final String COLUMN_CATEGORY = "category";
		
		public static final String DEFAULT_NAME = "default_name";
		public static final String DEFAULT_VALUE = "default_value";
		public static final String DEFAULT_CATEGORY = "default";
		
		public static final String PATH = "/user_functions";
		public static final String PATH_ID = "/user_functions/";
		
		public static final int ID_PATH_POSITION = 1;
		
		public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH);
		public static final Uri CONTENT_ID_URI_BASE = 
				Uri.parse(SCHEME + AUTHORITY + PATH_ID);
		public static final Uri CONTENT_ID_URI_PATTERN = 
				Uri.parse(SCHEME + AUTHORITY + PATH_ID + "/#");
		
		public static final String CONTENT_TYPE = 
				"vnd.android.cursor.dir/vnd.com.probe.provider.user_functions";
		
		public static final String CONTENT_ITEM_TYPE = 
				"vnd.android.cursor.item/vnd.com.probe.provider.user_functions";
	}
}
