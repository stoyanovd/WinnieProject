package com.example.WinnieProject.transfers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.WinnieProject.R;
import com.example.WinnieProject.RequestToDB;

import java.math.BigDecimal;

/**
 * Created by Юрий on 11.10.2014.
 */
public class ToYandexMoney extends Activity {


    private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceTransferToYandexMoney);
        balance.setText(getBalance());
    }


    private void makeTransfer(BigDecimal sum, String yandexMoney) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_to_yandex_money);
        refreshBalance();

        final TextView yandexMoneyNumber = (TextView) findViewById(R.id.yandexMoneyNumber);
        yandexMoneyNumber.setText(getIntent().getStringExtra(RequestToDB.YANDEX_MONEY_NUMBER));

        ImageView imageViewYandex = (ImageView) findViewById(R.id.imageViewTransferYandexMoney);
        imageViewYandex.setImageResource(this.getResources().getIdentifier("yandex", "drawable", this.getPackageName()));

        final EditText sum = (EditText) findViewById(R.id.sumToYandexMoney);
        Button buttonPay = (Button) findViewById(R.id.buttonTransferToYandexMoneyEnd);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BigDecimal sumTransfer = new BigDecimal(sum.getText().toString());
                    makeTransfer(sumTransfer, yandexMoneyNumber.getText().toString());
                } catch (Exception e) {

                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBalance();
    }
}
