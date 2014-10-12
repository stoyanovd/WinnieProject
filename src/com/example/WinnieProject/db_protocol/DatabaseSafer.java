package com.example.WinnieProject.db_protocol;

import com.example.WinnieProject.MainActivity;
import com.example.WinnieProject.User;

import java.util.List;

/**
 * Created by dima on 12.10.14.
 */
public class DatabaseSafer {


	public static long addUser(User user) {
		return MainActivity.dataSource.addUser(user);
	}

	public static List<User> getUsersAll() {
		return MainActivity.dataSource.getUsersWithSelection(null);
	}

	public static List<User> getUsersWithVK() {
		return MainActivity.dataSource.getUsersWithSelection(User.cvkId + " is not null");
	}

	public static List<User> getUsersWithPhones() {
		return MainActivity.dataSource.getUsersWithSelection(User.cPhoneNumber + " is not null");
	}

	public static List<User> getUsersFromFavourites() {
		return MainActivity.dataSource.getUsersWithSelection(User.cIsFavorite + " != 0");
	}
}
