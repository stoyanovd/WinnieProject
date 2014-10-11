package com.example.WinnieProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.WinnieProject.grouplist.Contacts;
import com.example.WinnieProject.grouplist.Favorites;
import com.example.WinnieProject.grouplist.VKFriends;

/**
 * Created by Юрий on 11.10.2014.
 */
public class MainActivity extends Activity {

    private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceMain);
        balance.setText(getBalance());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Override BACK

        refreshBalance();

        Button buttonFindInFavorites = (Button) findViewById(R.id.buttonFindInFavorites);
        Button buttonFindInVKFriends = (Button) findViewById(R.id.buttonFindInVK);
        Button buttonFindInContacts = (Button) findViewById(R.id.buttonFindInContacts);

        buttonFindInFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Favorites.class);
                startActivity(intent);
            }
        });
        buttonFindInVKFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), VKFriends.class);
                startActivity(intent);
            }
        });
        buttonFindInContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Contacts.class);
                startActivity(intent);
            }
        });


        Button buttonSentToFriend = (Button) findViewById(R.id.buttonSendToFriend);
        buttonSentToFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText something = (EditText) findViewById(R.id.findId);
                //TODO do something
                //parsing and go for transfer
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
    }

}
