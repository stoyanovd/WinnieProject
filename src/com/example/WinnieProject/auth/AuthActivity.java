package com.example.WinnieProject.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.WinnieProject.MainActivity;
import com.example.WinnieProject.R;

public class AuthActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    //String==null, if Pin not exists
    private String getPin() {

        return null;
    }

    private String getVK() {

        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_pin);
        if (getPin() == null) {
            //create new Login
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }

        final Button button_add = (Button) findViewById(R.id.buttonIn);
        final EditText editText_pin = (EditText) findViewById(R.id.pin);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getPin() == null) return;
                if (getPin().equals(editText_pin.getText().toString())) {
                    //go to MainView
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}



