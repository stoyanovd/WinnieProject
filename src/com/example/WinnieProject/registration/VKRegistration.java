package com.example.WinnieProject.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.example.WinnieProject.MainActivity;
import com.example.WinnieProject.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;
import com.vk.sdk.dialogs.VKCaptchaDialog;


/**
 * Created by Юрий on 11.10.2014.
 */
public class VKRegistration extends Activity {
    private static String[] scopes = new String[]{VKScopes.FRIENDS};
    private static String sTokenKey = "VnUQRvqLth7IYevCuPZh";
    private static String ID_APP = "4585864";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vk_registration);
        //String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        VKUIHelper.onCreate(this);
        WebView webView = (WebView) findViewById(R.id.webViewVKRegistration);
        //MAGIC FOR VK REGISTRATION

        VKSdkListener vkSdkListener = new VKSdkListener() {
            @Override
            public void onCaptchaError(VKError captchaError) {
                new VKCaptchaDialog(captchaError).show();
            }

            @Override
            public void onTokenExpired(VKAccessToken expiredToken) {
                VKSdk.authorize(scopes);
            }

            @Override
            public void onAccessDenied(VKError authorizationError) {
                new AlertDialog.Builder(getApplicationContext()).setMessage(authorizationError.errorMessage).show();
            }

            @Override
            public void onReceiveNewToken(VKAccessToken newToken) {
                newToken.saveTokenToSharedPreferences(getApplicationContext(), sTokenKey);
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAcceptUserToken(VKAccessToken token) {
                super.onAcceptUserToken(token);
            }
        };

        VKSdk.initialize(vkSdkListener, ID_APP, VKAccessToken.tokenFromSharedPreferences(getApplicationContext(), sTokenKey));
        VKSdk.authorize(scopes);
        //AFTER MAGIC
    }

    @Override
    public void onResume() {
        super.onResume();
        VKUIHelper.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VKUIHelper.onActivityResult(requestCode, resultCode, data);
    }

}
