package com.example.WinnieProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        TextView name = (TextView) findViewById(R.id.userName);
        TextView numberYandexMoney = (TextView) findViewById(R.id.numberYandexMoney);
        TextView numberVK = (TextView) findViewById(R.id.numberVK);
        TextView numberPhone = (TextView) findViewById(R.id.phoneNumber);
        ImageView picture = (ImageView) findViewById(R.id.imageViewInfo);


        name.setText(getIntent().getStringExtra("name"));
        numberYandexMoney.setText(getIntent().getStringExtra("yandexMoneyNum"));
        numberVK.setText(getIntent().getStringExtra("vkId"));
        numberPhone.setText(getIntent().getStringExtra("phoneNum"));

        // /*TODO хз как правильно*/picture.setImageResource(getIntent().getIntExtra("pictureId",0));


        Button buttonTransferToYandexMoney = (Button) findViewById(R.id.buttonTransefToYandexMoney);
        Button buttonTransferToPhone = (Button) findViewById(R.id.buttonTransferToPhone);

        buttonTransferToYandexMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = getIntent().getStringExtra("yandexMoneyNum");
                if (count.equals("Не указан")) {
                    //missing yandexMoney

                } else

                {
                    Intent intent = new Intent();
                    intent.putExtra("yandeMoneyNum", count);
                    intent.setClass(getApplicationContext(), ToYandexMoney.class);
                    startActivity(intent);
                }


            }
        });
        buttonTransferToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = getIntent().getStringExtra("phoneNum");
                if (count.equals("Не указан")) {
                    //missing phoneNum

                } else

                {
                    Intent intent = new Intent();
                    intent.putExtra("phoneNum", count);
                    intent.setClass(getApplicationContext(), ToPhone.class);
                    startActivity(intent);
                }
            }
        });

    }
}
