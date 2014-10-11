package com.example.WinnieProject.transfers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.WinnieProject.R;

import java.math.BigDecimal;

/**
 * Created by Юрий on 11.10.2014.
 */
public class ToPhone extends Activity {

    private String getBalance() {

        return "0.00Руб";
    }


    private void refreshBalance() {
        TextView balance = (TextView) findViewById(R.id.MyBalanceTransferToPhone);
        balance.setText(getBalance());
    }

    private void makeTransfer(BigDecimal sum, String phoneNum) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_to_phone);
        refreshBalance();
        final TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        phoneNumber.setText(getIntent().getStringExtra("phoneNum"));

        final EditText sum = (EditText) findViewById(R.id.sumToPhone);
        Button buttonPay = (Button) findViewById(R.id.buttonTransefToPhoneEnd);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BigDecimal sumTransfer = new BigDecimal(sum.getText().toString());
                    makeTransfer(sumTransfer, phoneNumber.getText().toString());
                } catch (Exception e) {

                }
            }
        });

    }
}
