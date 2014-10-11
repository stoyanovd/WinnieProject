package com.example.WinnieProject.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.example.WinnieProject.MainActivity;
import com.example.WinnieProject.R;

/**
 * Created by Юрий on 11.10.2014.
 */
public class VKRegistration extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vk_registration);
        WebView webView = (WebView) findViewById(R.id.webViewVKRegistration);
        //MAGIC FOR VK REGISTRATION

        //AFTER MAGIC
        {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }

}
