package com.example.WinnieProject.grouplist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.WinnieProject.R;
import com.example.WinnieProject.RequestToDB;
import com.example.WinnieProject.User;
import com.example.WinnieProject.UserInfo;
import com.example.WinnieProject.adapters.UserAdapter;

import java.util.ArrayList;

/**
 * Created by Юрий on 11.10.2014.
 */
public class Contacts extends Activity {
    private ArrayList<User> users = new ArrayList<User>();
    private ListView listView_friends;
    private ArrayAdapter<User> arrayAdapter;


    private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceListContacts);
        balance.setText(getBalance());
    }

    private ArrayList<User> getContactsUsers() {
        //find friends in DB
        ArrayList<User> userArrayList = new ArrayList<User>();
        userArrayList.add(new User("Вася", "vk.com/id123", "4100014255715", "+79012345678", 0, false));
        userArrayList.add(new User("Петя", "vk.com/id123", "4100014255715", "+79012345678", 0, false));
        userArrayList.add(new User("Леха", "vk.com/id123", "4100014255715", "+79012345678", 0, false));
        return userArrayList;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_in_contacts);
        refreshBalance();
        showFriends();
        listView_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(RequestToDB.PHONE_NUMBER, users.get(i).phoneNumber);
                intent.putExtra(RequestToDB.NAME, users.get(i).name);
                intent.putExtra(RequestToDB.VK_ID, users.get(i).vkId);
                intent.putExtra(RequestToDB.YANDEX_MONEY_NUMBER, users.get(i).yandexMoneyNumber);
                intent.putExtra(RequestToDB.PICTURE_ID, users.get(i).pictureId);
                intent.putExtra(RequestToDB.IS_FAVORITE, users.get(i).isFavorite);

                intent.setClass(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });


    }

    private void showFriends() {
        users = getContactsUsers();
        arrayAdapter = new UserAdapter(getApplicationContext(), users);
        listView_friends = (ListView) findViewById(R.id.listViewContacts);
        listView_friends.setAdapter(arrayAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
    }

}
