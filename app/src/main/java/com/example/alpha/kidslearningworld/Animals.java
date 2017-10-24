package com.example.alpha.kidslearningworld;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class Animals extends AppCompatActivity implements TextToSpeech.OnInitListener{

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,home;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animals);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);
        img1 = (ImageView) findViewById(R.id.im1);
        img2 = (ImageView) findViewById(R.id.im2);
        img3 = (ImageView) findViewById(R.id.im3);
        img4 = (ImageView) findViewById(R.id.im4);
        img5 = (ImageView) findViewById(R.id.im5);
        img6 = (ImageView) findViewById(R.id.im6);
        img7 = (ImageView) findViewById(R.id.im7);
        img8 = (ImageView) findViewById(R.id.im8);
        img9 = (ImageView) findViewById(R.id.im9);
        img10 = (ImageView) findViewById(R.id.im10);
        img11 = (ImageView) findViewById(R.id.im11);
        img12 = (ImageView) findViewById(R.id.im12);

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Animals.this, Homescreen.class);
                startActivity(intent);
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Cow");
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Horse");
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Lion");
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Camel");
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Giraffe");
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Bull");
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Sheep");
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Snake");
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Deer");
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Elephant");
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Rabbit");
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Zebra");
            }
        });



    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(Animals.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
            } else {
                img1.setEnabled(true);
                img2.setEnabled(true);
                img3.setEnabled(true);
                img4.setEnabled(true);
                img5.setEnabled(true);
                img6.setEnabled(true);
                img7.setEnabled(true);
                img8.setEnabled(true);
                img9.setEnabled(true);
                img10.setEnabled(true);
                img11.setEnabled(true);
                img12.setEnabled(true);
                speakOut("");
            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void speakOut(String text)
    {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
