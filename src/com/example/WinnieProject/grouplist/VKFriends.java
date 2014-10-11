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
import com.example.WinnieProject.User;
import com.example.WinnieProject.UserInfo;
import com.example.WinnieProject.adapters.UserAdapter;

import java.util.ArrayList;

/**
 * Created by Юрий on 11.10.2014.
 */
public class VKFriends extends Activity {
    private ArrayList<User> users = new ArrayList<User>();
    private ListView listView_friends;
    private ArrayAdapter<User> arrayAdapter;


    private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceListVK);
        balance.setText(getBalance());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_in_vk);

        refreshBalance();
        showFriends();
        listView_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("phoneNum", users.get(i).phoneNumber);
                intent.putExtra("name", users.get(i).name);
                intent.putExtra("vkId", users.get(i).vkId);
                intent.putExtra("yandexMoneyNum", users.get(i).yandexMoneyNumber);
                intent.putExtra("pictureId", users.get(i).pictureId);
                intent.setClass(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });


    }

    private void showFriends() {
        ArrayList<User> userArrayList = new ArrayList<User>();
        //find friends in DB

        userArrayList.add(new User());

        users = userArrayList;
        arrayAdapter = new UserAdapter(getApplicationContext(), users);
        listView_friends = (ListView) findViewById(R.id.listViewVK);
        listView_friends.setAdapter(arrayAdapter);

    }
}
