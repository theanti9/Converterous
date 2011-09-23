package com.tectria.converterous;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class ConverterousDbAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_UNIT1 = "unit1";
	public static final String KEY_UNIT2 = "unit2";
	
	private static final String DATABASE_TABLE = "messages";
	private Context context;
	private SQLiteDatabase database;
	private ConverterousDbHelper dbHelper;
	
	public ConverterousDbAdapter(Context context) {
		this.context = context;
	}
	
	public ConverterousDbAdapter open() {
		dbHelper = new ConverterousDbHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long addFavorite(String unit1, String unit2) {
		ContentValues cv = new ContentValues();
		cv.put("unit1", unit1);
		cv.put("unit2", unit2);
		return database.insert(DATABASE_TABLE, null, cv);
	}
	
	public boolean deleteFavorite(long rowId) {
		// return whether the delete worked by seeing if the return value (num of rows effected)
		// of database.delete() is > 0
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
}
