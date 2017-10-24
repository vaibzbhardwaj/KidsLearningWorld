package com.example.alpha.kidslearningworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Rhymes extends AppCompatActivity {

    ImageView img1,img2,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rhymes);
        home = (ImageView) findViewById(R.id.homebutton);
        img1 = (ImageView) findViewById(R.id.im1);
        img2 = (ImageView) findViewById(R.id.im2);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Rhymes.this,Rhyme_ttls.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Rhymes.this,Rhymes_bbbs.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Rhymes.this,Homescreen.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
