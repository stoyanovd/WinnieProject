package com.example.WinnieProject.db_protocol;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.WinnieProject.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 12.10.14.
 */
public class UsersDataSource {


	private SQLiteDatabase database;
	private MyDatabaseHelper dbHelper;

	public UsersDataSource(Context context) {
		dbHelper = MyDatabaseHelper.getInstance(context);
	}

	public void open() throws SQLException {
		if (dbHelper != null) {
			database = dbHelper.getWritableDatabase();
		} else {
			database = null;
		}
	}

	public void close() {
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

	public long addUser(User user) {
		return database.insert(MyDatabaseHelper.DATABASE_NAME, null, user.createFullContentValues());
	}

	@Deprecated
	public void deleteUser(User user) {
		//do nothing
	}

	public List<User> getUsersWithSelection(String selection) {
		List<User> users = new ArrayList<User>();
		Cursor cursor = database.query(MyDatabaseHelper.DATABASE_NAME,
				User.allFields, selection, null, null, null, null);
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		cursor.close();
		return users;

	}

	public List<User> getUsersAll() {
		return getUsersWithSelection(null);
	}

	public List<User> getUsersVK() {
		return getUsersWithSelection(User.cvkId + " is not null");
	}

	public List<User> getUsersPhones() {
		return getUsersWithSelection(User.cPhoneNumber + " is not null");
	}

	public List<User> getUsersFavourites() {
		return getUsersWithSelection(User.cIsFavorite + " != 0");
	}

	private User cursorToUser(Cursor cursor) {
		User user = new User(
				cursor.getString(cursor.getColumnIndex(User.cName)),
				cursor.getString(cursor.getColumnIndex(User.cvkId)),
				cursor.getString(cursor.getColumnIndex(User.cYandexMoneyNumber)),
				cursor.getString(cursor.getColumnIndex(User.cPhoneNumber)),
				cursor.getInt(cursor.getColumnIndex(User.cPictureId)),
				(cursor.getInt(cursor.getColumnIndex(User.cIsFavorite)) > 0));
		return user;
	}

}
