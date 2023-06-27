package com.example.mhchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.window.SplashScreen;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                }
                catch (Exception e)
                {
                    getAllStackTraces();
                }
                finally {
                    Intent intent=new Intent(splashScreen.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }
}