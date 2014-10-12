package com.example.WinnieProject.db_protocol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.WinnieProject.User;

/**
 * Created by dima on 12.10.14.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

	private static MyDatabaseHelper sInstance;
	private static final int DATABASE_VERSION = 1;

	public static final String DATABASE_NAME = "users_db";

	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_NAME + "(" + User.cID + " integer primary key autoincrement, " +
			User.cPhoneNumber + " text, " +
			User.cvkId + " text, " +
			User.cYandexMoneyNumber + " text, " +
			User.cName + " text" +
			User.cIsFavorite + " integer, " +
			User.cPictureId + " integer" +
			");";


	public static MyDatabaseHelper getInstance(Context context) {

		// Use the application context, which will ensure that you
		// don't accidentally leak an Activity's context.
		// See this article for more information: http://bit.ly/6LRzfx
		if (sInstance == null) {
			sInstance = new MyDatabaseHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	/**
	 * Constructor should be private to prevent direct instantiation.
	 * make call to static method "getInstance()" instead.
	 */
	private MyDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
		onCreate(db);
	}

	public static void closeAll() {
		if (sInstance != null) {
			sInstance.close();
		}
	}
}
