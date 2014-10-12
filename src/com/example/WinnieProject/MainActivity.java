package com.example.WinnieProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.example.WinnieProject.adapters.UserAdapter;
import com.example.WinnieProject.db_protocol.UsersDataSource;
import com.example.WinnieProject.grouplist.Contacts;
import com.example.WinnieProject.grouplist.Favorites;
import com.example.WinnieProject.grouplist.VKFriends;
import com.example.WinnieProject.transfers.ToPhone;
import com.example.WinnieProject.transfers.ToYandexMoney;

import java.util.ArrayList;

/**
 * Created by Юрий on 11.10.2014.
 */
public class MainActivity extends Activity {
    private int numViewSwitch = 0;
    private ArrayList<User> users = new ArrayList<User>();
    private ListView listView_friends;
    private ArrayAdapter<User> arrayAdapter;

	public static UsersDataSource dataSource;

	private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceMain);
        balance.setText(getBalance());
    }

    private ArrayList<User> getAllFriendUsers() {
        //find friends in DB
        ArrayList<User> userArrayList = new ArrayList<User>();
        userArrayList.add(new User("Вася", "vk.com/id123", "4100014255715", "+79012345678", 0, false));
        userArrayList.add(new User("Петя", "vk.com/id123", "4100014255715", "+79012345678", 0, false));
        userArrayList.add(new User("Леха", "vk.com/id123", "4100014255715", "+79012345678", 0, false));
        userArrayList.add(new User("Дима", "vk.com/id123", "4100014255715", "+79117777912", 0, false));
        return userArrayList;
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

    private boolean isPhone(String s) {
        if (s.charAt(0) != '+' || s.length() != 12) return false;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return false;
        return true;
    }

    private boolean isYandexMoney(String s) {
        if (s.length() != 15) return false;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return false;
        return true;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Override BACK

		dataSource = new UsersDataSource(this.getApplicationContext());
		dataSource.open();

		//run database

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


        final EditText editText = (EditText) findViewById(R.id.findId);
        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (editText.getText().toString().length() == 0 && numViewSwitch != 0) {
                    viewSwitcher.showNext();
                }
                if (editText.getText().toString().length() != 0 && numViewSwitch == 0) {
                    viewSwitcher.showNext();
                    show();
                }
                if (editText.getText().toString().length() != 0) {
                    show();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (editText.getText().toString().length() == 0 && numViewSwitch != 0) {
                    viewSwitcher.showNext();
                    numViewSwitch = (numViewSwitch + 1) % 2;
                }
                if (editText.getText().toString().length() != 0 && numViewSwitch == 0) {
                    viewSwitcher.showNext();
                    numViewSwitch = (numViewSwitch + 1) % 2;
                    show();
                }
                if (editText.getText().toString().length() != 0) {
                    show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getText().toString().length() == 0 && numViewSwitch != 0) {
                    viewSwitcher.showNext();
                    numViewSwitch = (numViewSwitch + 1) % 2;
                }
                if (editText.getText().toString().length() != 0 && numViewSwitch == 0) {
                    viewSwitcher.showNext();
                    numViewSwitch = (numViewSwitch + 1) % 2;
                    show();
                }
                if (editText.getText().toString().length() != 0) {
                    show();
                }
            }
        });
        show();
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

        Button buttonSent = (Button) findViewById(R.id.buttonSent);
        buttonSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                if (isPhone(s)) {
                    Intent intent = new Intent();
                    intent.putExtra(RequestToDB.PHONE_NUMBER, s);
                    intent.setClass(getApplicationContext(), ToPhone.class);
                    startActivity(intent);
                }
                if (isYandexMoney(s)) {
                    Intent intent = new Intent();
                    intent.putExtra(RequestToDB.YANDEX_MONEY_NUMBER, s);
                    intent.setClass(getApplicationContext(), ToYandexMoney.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void show() {
        users = getAllFriendUsers();
        EditText editText = (EditText) findViewById(R.id.findId);
        ArrayList<User> trueUsers = getTrueUsers(users, editText.getText().toString());

        arrayAdapter = new UserAdapter(getApplicationContext(), trueUsers);
        listView_friends = (ListView) findViewById(R.id.listViewInSwitcher);
        listView_friends.setAdapter(arrayAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
		dataSource.open();
		refreshBalance();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataSource.close();
	}

}
