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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_about_user);

        refreshBalance();

        TextView name = (TextView) findViewById(R.id.userName);
        TextView numberYandexMoney = (TextView) findViewById(R.id.numberYandexMoney);
        TextView numberVK = (TextView) findViewById(R.id.numberVK);
        TextView numberPhone = (TextView) findViewById(R.id.phoneNumber);
        ImageView picture = (ImageView) findViewById(R.id.imageViewInfo);


        name.setText(getIntent().getStringExtra(RequestToDB.NAME));
        numberYandexMoney.setText(getIntent().getStringExtra(RequestToDB.YANDEX_MONEY_NUMBER));
        numberVK.setText(getIntent().getStringExtra(RequestToDB.VK_ID));
        numberPhone.setText(getIntent().getStringExtra(RequestToDB.PHONE_NUMBER));

        // /*TODO хз как правильно*/picture.setImageResource(getIntent().getIntExtra("pictureId",0));


        Button buttonTransferToYandexMoney = (Button) findViewById(R.id.buttonTransefToYandexMoney);
        Button buttonTransferToPhone = (Button) findViewById(R.id.buttonTransferToPhone);

        buttonTransferToYandexMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = getIntent().getStringExtra(RequestToDB.YANDEX_MONEY_NUMBER);
                if (count.equals("Не указан")) {
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
                if (count.equals("Не указан")) {
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

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
    }
}
