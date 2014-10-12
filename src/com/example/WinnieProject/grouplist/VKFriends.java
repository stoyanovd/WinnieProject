package com.example.WinnieProject.grouplist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.example.WinnieProject.R;
import com.example.WinnieProject.User;
import com.example.WinnieProject.UserInfo;
import com.example.WinnieProject.adapters.UserAdapter;
import com.example.WinnieProject.db_protocol.DatabaseSafer;

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

    private ArrayList<User> getVKFriendUsers() {
        return new ArrayList<User>(DatabaseSafer.getUsersWithVK());
    }

    private ArrayList<User> getTrueUsers(ArrayList<User> list, String sub) {
        ArrayList<User> ans = new ArrayList<User>();
        for (User user : list) {
            if (user.name.toUpperCase().contains(sub.toUpperCase()) || user.yandexMoneyNumber.toUpperCase().contains(sub.toUpperCase()) || user.phoneNumber.toUpperCase().contains(sub.toUpperCase())) {
                ans.add(user);
            }
        }
        return ans;
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
				users.get(i).putToIntent(intent);

				intent.setClass(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });


        EditText editText = (EditText) findViewById(R.id.findFriendInVKWithSub);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                showFriends();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                showFriends();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showFriends();
            }
        });


    }

    private void showFriends() {


        users = getVKFriendUsers();
        EditText editText = (EditText) findViewById(R.id.findFriendInVKWithSub);
        ArrayList<User> trueUsers = getTrueUsers(users, editText.getText().toString());

        arrayAdapter = new UserAdapter(getApplicationContext(), trueUsers);
        listView_friends = (ListView) findViewById(R.id.listViewVK);
        listView_friends.setAdapter(arrayAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
    }

}
