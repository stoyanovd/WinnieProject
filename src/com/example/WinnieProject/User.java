package com.example.WinnieProject;

import android.content.ContentValues;
import android.content.Intent;

/**
 * Created by Юрий on 11.10.2014.
 */
public class User {
    public static String noFilled = "Не указан";

	public String phoneNumber;
    public String vkId;
    public String yandexMoneyNumber;
    public String name;
    public boolean isFavorite;
    public int pictureId;


	public static final String cID = "_id";

	public static final String cPhoneNumber = "phoneNumber";
	public static final String cvkId = "vkId";
	public static final String cYandexMoneyNumber = "yandexMoneyNumber";
	public static final String cName = "name";
	public static final String cIsFavorite = "isFavorite";
	public static final String cPictureId = "pictureId";

	public static final String[] allFields = {cID, cPhoneNumber, cYandexMoneyNumber, cName, cIsFavorite, cPictureId};

	public User() {
        phoneNumber = noFilled;
        vkId = noFilled;
        name = noFilled;
        yandexMoneyNumber = noFilled;
        pictureId = 0;
        isFavorite = false;
    }

    public User(String name, String vkId, String yandexMoneyNumber, String phoneNumber, int pictureId, boolean isFavorite) {
        this.phoneNumber = phoneNumber;
        this.vkId = vkId;
        this.yandexMoneyNumber = yandexMoneyNumber;
        this.name = name;
        this.pictureId = pictureId;
        this.isFavorite = isFavorite;
    }


	public ContentValues createFullContentValues() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(cPhoneNumber, phoneNumber);
		contentValues.put(cvkId, vkId);
		contentValues.put(cYandexMoneyNumber, yandexMoneyNumber);
		contentValues.put(cName, name);
		contentValues.put(cIsFavorite, isFavorite);
		contentValues.put(cPictureId, pictureId);
		return contentValues;
	}

	public void putToIntent(Intent intent) {
		intent.putExtra(cPhoneNumber, phoneNumber);
		intent.putExtra(cvkId, vkId);
		intent.putExtra(cYandexMoneyNumber, yandexMoneyNumber);
		intent.putExtra(cName, name);
		intent.putExtra(cIsFavorite, isFavorite);
		intent.putExtra(cPictureId, pictureId);
	}

	public static User getFromIntent(Intent intent) {
		return new User(intent.getStringExtra(cName), intent.getStringExtra(cvkId), intent.getStringExtra(cYandexMoneyNumber),
				intent.getStringExtra(cPhoneNumber), intent.getIntExtra(cPictureId, 0), (intent.getIntExtra(cIsFavorite, 0) > 0));
	}
}
