package com.example.WinnieProject.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.example.WinnieProject.R;

/**
 * Created by Юрий on 11.10.2014.
 */
public class YandexRegistration extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yandex_registration);
        WebView webView = (WebView) findViewById(R.id.webViewYandexRegistration);
        //MAGIC FOR YA REGISTRATION

        //AFTER MAGIC
        {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), VKRegistration.class);
            startActivity(intent);
        }

    }
}
