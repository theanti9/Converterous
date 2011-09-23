package com.tectria.converterous;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConverterousDbHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "converterousappdata";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table favorites (_id integer primary key autoincrement, "
			+ "unit1 text not null, unit2 text not null);";
	
	public ConverterousDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS messages");
		onCreate(db);
	}

}
