package com.example.kunalsingh.entreprise.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kunalsingh.entreprise.R;

public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 2000;
    private static final String MY_FILE = "my_file";
    private int selector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(selector==1) {
                    intent = new Intent(SplashScreen.this, ClientMainActivity.class);
                }else if(selector==2){
                    intent = new Intent(SplashScreen.this,SellerMainActivity.class);
                }else{
                    intent = new Intent(SplashScreen.this,SelectorActivity.class);
                }

                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);

        SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE,MODE_PRIVATE);
        selector = sharedPreferences.getInt("selector",0);

    }
}
