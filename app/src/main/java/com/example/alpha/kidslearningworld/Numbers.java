package com.example.alpha.kidslearningworld;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Numbers extends AppCompatActivity  implements TextToSpeech.OnInitListener{

    TextView a1,name;
    ImageView img,home,left,right;
    TextToSpeech tts;
    String s1,s2;
    private Cursor c;
    SQLiteDatabase db;
    private static final String SELECT_SQL = "SELECT * FROM numbers where category = 'numbers'";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_numbers);

        tts = new TextToSpeech(this, this);
        a1 = (TextView) findViewById(R.id.tv1);
        name = (TextView) findViewById(R.id.name);
        img = (ImageView) findViewById(R.id.img1);
        home = (ImageView) findViewById(R.id.home);
        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);


        createDatabase();
        insertIntoDB();
        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showRecords();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Numbers.this,Homescreen.class);
                startActivity(intent);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.moveToPrevious();
                showRecords();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.moveToNext();
                showRecords();
            }
        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut(s1);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut(s1);
            }
        });
    }

    protected void showRecords() {
        String alpha1 = c.getString(1);
        String img1 = c.getString(2);
        String name1 = c.getString(3);
        a1.setText(alpha1);
        name.setText(name1);

        int id = getResources().getIdentifier(img1,"drawable",getPackageName());
        img.setImageResource(id);

        s1 = name1;
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS numbers(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, alphabet VARCHAR, img VARCHAR, name VARCHAR, category VARCHAR);");
    }

    protected void insertIntoDB(){

        String query1 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('1','one','ONE','numbers');";
        String query2 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('2','two','TWO','numbers');";
        String query3 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('3','three','THREE','numbers');";
        String query4 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('4','four','FOUR','numbers');";
        String query5 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('5','five','FIVE','numbers');";
        String query6 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('6','six','SIX','numbers');";
        String query7 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('7','seven','SEVEN','numbers');";
        String query8 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('8','eight','EIGHT','numbers');";
        String query9 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('9','nine','NINE','numbers');";
        String query10 = "INSERT INTO numbers(alphabet,img,name,category) VALUES('10','ten','TEN','numbers');";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);
        db.execSQL(query9);
        db.execSQL(query10);

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
                Toast.makeText(Numbers.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
            } else {

                a1.setEnabled(true);
                img.setEnabled(true);
                speakOut("");
            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    private void speakOut(String text)
    {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}

