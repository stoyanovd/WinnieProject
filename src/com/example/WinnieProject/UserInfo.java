package com.example.WinnieProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.WinnieProject.transfers.ToPhone;
import com.example.WinnieProject.transfers.ToYandexMoney;

/**
 * Created by Юрий on 11.10.2014.
 */
public class UserInfo extends Activity {

    private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceUserInfo);
        balance.setText(getBalance());
    }

    private void changeFlagOnUser(User user) {
        ///add this and delete opposite
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_about_user);

        refreshBalance();

        ImageView imageViewVK = (ImageView) findViewById(R.id.imageViewInfoVK);
        imageViewVK.setImageResource(this.getResources().getIdentifier("vk", "drawable", this.getPackageName()));

        ImageView imageViewYandex = (ImageView) findViewById(R.id.imageViewInfoYandex);
        imageViewYandex.setImageResource(this.getResources().getIdentifier("yandex", "drawable", this.getPackageName()));

        ImageView imageViewContact = (ImageView) findViewById(R.id.imageViewInfoContact);
        imageViewContact.setImageResource(this.getResources().getIdentifier("contact", "drawable", this.getPackageName()));

        TextView name = (TextView) findViewById(R.id.userName);
        TextView numberYandexMoney = (TextView) findViewById(R.id.numberYandexMoney);
        TextView numberVK = (TextView) findViewById(R.id.numberVK);
        TextView numberPhone = (TextView) findViewById(R.id.phoneNumber);
        ImageView picture = (ImageView) findViewById(R.id.imageViewInfo);


        name.setText(getIntent().getStringExtra(RequestToDB.NAME));
        numberYandexMoney.setText(getIntent().getStringExtra(RequestToDB.YANDEX_MONEY_NUMBER));
        numberVK.setText(getIntent().getStringExtra(RequestToDB.VK_ID));
        numberPhone.setText(getIntent().getStringExtra(RequestToDB.PHONE_NUMBER));

        picture.setImageResource(this.getResources().getIdentifier("p" + getIntent().getIntExtra(RequestToDB.PICTURE_ID, 0), "drawable", this.getPackageName()));

        Button buttonTransferToYandexMoney = (Button) findViewById(R.id.buttonTransefToYandexMoney);
        Button buttonTransferToPhone = (Button) findViewById(R.id.buttonTransferToPhone);

        buttonTransferToYandexMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = getIntent().getStringExtra(RequestToDB.YANDEX_MONEY_NUMBER);
                if (count.equals(User.noFilled)) {
                    //missing yandexMoney

                } else

                {
                    Intent intent = new Intent();
                    intent.putExtra(RequestToDB.YANDEX_MONEY_NUMBER, count);
                    intent.setClass(getApplicationContext(), ToYandexMoney.class);
                    startActivity(intent);
                }


            }
        });
        buttonTransferToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = getIntent().getStringExtra(RequestToDB.PHONE_NUMBER);
                if (count.equals(User.noFilled)) {
                    //missing phoneNum

                } else

                {
                    Intent intent = new Intent();
                    intent.putExtra(RequestToDB.PHONE_NUMBER, count);
                    intent.setClass(getApplicationContext(), ToPhone.class);
                    startActivity(intent);
                }
            }
        });

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setChecked(getIntent().getBooleanExtra(RequestToDB.IS_FAVORITE, false));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFlagOnUser(new User(getIntent().getStringExtra(RequestToDB.NAME), getIntent().getStringExtra(RequestToDB.VK_ID), getIntent().getStringExtra(RequestToDB.YANDEX_MONEY_NUMBER), getIntent().getStringExtra(RequestToDB.PHONE_NUMBER), getIntent().getIntExtra(RequestToDB.PICTURE_ID, 0), checkBox.isChecked()));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
    }
}
