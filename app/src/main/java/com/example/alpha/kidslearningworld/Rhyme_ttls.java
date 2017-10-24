package com.example.alpha.kidslearningworld;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Rhyme_ttls extends AppCompatActivity {

    ImageView img1,img2,img3,img4,home;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rhyme_ttls);

        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);

        RotateAnimation anim = new RotateAnimation(0f,350f,15f,15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
        img1.startAnimation(anim);

        RotateAnimation anim2 = new RotateAnimation(0f,350f,15f,15f);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.setRepeatCount(Animation.INFINITE);
        anim2.setDuration(1000);
        img2.startAnimation(anim2);

        RotateAnimation anim3 = new RotateAnimation(0f,350f,15f,15f);
        anim3.setInterpolator(new LinearInterpolator());
        anim3.setRepeatCount(Animation.INFINITE);
        anim3.setDuration(1000);
        img3.startAnimation(anim3);

        RotateAnimation anim4 = new RotateAnimation(0f,350f,15f,15f);
        anim4.setInterpolator(new LinearInterpolator());
        anim4.setRepeatCount(Animation.INFINITE);
        anim4.setDuration(1000);
        img4.startAnimation(anim4);

        mp=new MediaPlayer();
        mp=MediaPlayer.create(this,R.raw.ttls);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(true);
        mp.start();

        home = (ImageView) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Rhyme_ttls.this,Rhymes.class);
                startActivity(intent);
                stopMusic(v);
            }
        });
    }
    public void stopMusic(View view)
    {
        mp.stop();
        mp.release();
    }
    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
