package com.example.mhchatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

 ImageView chatbotImg;
 TextView  chatbotTxt;
 CardView chatbotCard;

 ImageView aboutUsImg;

 ImageView AccountSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        chatbotImg=findViewById(R.id.chatbotImg);
        chatbotCard=findViewById(R.id.chatbotCard);
        aboutUsImg=findViewById(R.id.aboutUsImg);
        AccountSection=findViewById(R.id.accountImg);

        AccountSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Account.class);
                startActivity(intent);
                finish();

            }
        });

        aboutUsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,aboutus.class);
                startActivity(intent);
            }
        });

        chatbotCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,bot.class);
                startActivity(intent);
            }
        });


        chatbotImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,bot.class);
                startActivity(intent);
            }
        });









    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the app
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
