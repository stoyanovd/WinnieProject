package com.example.WinnieProject;

/**
 * Created by Юрий on 11.10.2014.
 */
public class User {
    public String phoneNumber;
    public String vkId;
    public String yandexMoneyNumber;
    public String name;
    public boolean isFavorite;
    public int pictureId;

    public User() {
        phoneNumber = "Не указан";
        vkId = "Не указан";
        name = "Не указан";
        yandexMoneyNumber = "Не указан";
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


}
