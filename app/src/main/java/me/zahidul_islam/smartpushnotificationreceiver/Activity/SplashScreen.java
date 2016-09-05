package me.zahidul_islam.smartpushnotificationreceiver.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.zahidul_islam.smartpushnotificationreceiver.R;
import me.zahidul_islam.smartpushnotificationreceiver.Receiver.FCMNotification;
import me.zahidul_islam.smartpushnotificationreceiver.Receiver.OnNotificationListener;

/**
 * Created by SSL_ZAHID on 9/4/2016.
 */
public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "SplashScreen";
    private ProgressBar loadingBar;
    private TextView textView;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        init();
    }

    /*Initialize all view*/
    private void init() {
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*Register listener for token*/
        FCMNotification.getInstance().getTokenNotification(new OnNotificationListener() {
            @Override
            public void getMessage(final String message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                /*Successfully Get the token for the user*/
                                textView.setText("Complete Token Registration");
                                textView.setTextColor(Color.GREEN);

                                /*Make the progress bar disable*/
                                loadingBar.setVisibility(View.GONE);
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {e.printStackTrace();}
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).start();
            }
        });

        // Check if already registered
        preferences = getSharedPreferences(getString(R.string.share_pref),MODE_PRIVATE);
        if(!preferences.getString(getString(R.string.token_key),"NULL").equals("NULL")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {e.printStackTrace();}
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).start();
        }
    }
}
