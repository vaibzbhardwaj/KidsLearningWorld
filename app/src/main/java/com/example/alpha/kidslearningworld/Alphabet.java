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

public class Alphabet extends AppCompatActivity  implements TextToSpeech.OnInitListener{

    TextView a1,name;
    ImageView img,home,left,right;
    TextToSpeech tts;
    String s1,s2;
    private Cursor c;
    SQLiteDatabase db;
    private static final String SELECT_SQL = "SELECT * FROM object where category = 'alpha'";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alphabet);

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
                intent.setClass(Alphabet.this,Homescreen.class);
                startActivity(intent);
                finish();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c != null){
                    if(c.getCount()>0){
                        c.moveToPrevious();
                        showRecords();
                    }
                }

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
                speakOut(s2);
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

        s1 = alpha1;
        s2 = name1;
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS object(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, alphabet VARCHAR, img VARCHAR, name VARCHAR, category VARCHAR);");
    }

    protected void insertIntoDB(){

        String query1 = "INSERT INTO object(alphabet,img,name,category) VALUES('A','apple','Apple','alpha');";
        String query2 = "INSERT INTO object(alphabet,img,name,category) VALUES('B','ball','Ball','alpha');";
        String query3 = "INSERT INTO object(alphabet,img,name,category) VALUES('C','car','Car','alpha');" ;
        String query4 = "INSERT INTO object(alphabet,img,name,category) VALUES('D','dog','Dog','alpha');" ;
        String query5 = "INSERT INTO object(alphabet,img,name,category) VALUES('E','elephant','Elephant','alpha');" ;
        String query6 = "INSERT INTO object(alphabet,img,name,category) VALUES('F','fan','Fan','alpha');" ;
        String query7 =  "INSERT INTO object(alphabet,img,name,category) VALUES('G','grapes','Grapes','alpha');" ;
        String query8 =  "INSERT INTO object(alphabet,img,name,category) VALUES('H','hut','Hut','alpha');" ;
        String query9 =  "INSERT INTO object(alphabet,img,name,category) VALUES('I','icecream','Icecream','alpha');" ;
        String query10 = "INSERT INTO object(alphabet,img,name,category) VALUES('J','jug','Jug','alpha');" ;
        String query11 = "INSERT INTO object(alphabet,img,name,category) VALUES('K','kite','Kite','alpha');" ;
        String query12 = "INSERT INTO object(alphabet,img,name,category) VALUES('L','lamp','Lamp','alpha');" ;
        String query13 = "INSERT INTO object(alphabet,img,name,category) VALUES('M','mango','Mango','alpha');" ;
        String query14 = "INSERT INTO object(alphabet,img,name,category) VALUES('N','nest','Nest','alpha');" ;
        String query15 = "INSERT INTO object(alphabet,img,name,category) VALUES('O','orange','Orange','alpha');" ;
        String query16 = "INSERT INTO object(alphabet,img,name,category) VALUES('P','pumpkin','Pumpkin','alpha');" ;
        String query17 = "INSERT INTO object(alphabet,img,name,category) VALUES('Q','quill','Quill','alpha');" ;
        String query18 = "INSERT INTO object(alphabet,img,name,category) VALUES('R','rose','Rose','alpha');" ;
        String query19 = "INSERT INTO object(alphabet,img,name,category) VALUES('S','snake','Snake','alpha');" ;
        String query20 = "INSERT INTO object(alphabet,img,name,category) VALUES('T','tomato','Tomato','alpha');" ;
        String query21 = "INSERT INTO object(alphabet,img,name,category) VALUES('U','umbrella','Umbrella','alpha');" ;
        String query22 = "INSERT INTO object(alphabet,img,name,category) VALUES('V','van','Van','alpha');" ;
        String query23 = "INSERT INTO object(alphabet,img,name,category) VALUES('W','watch','Watch','alpha');" ;
        String query24 = "INSERT INTO object(alphabet,img,name,category) VALUES('X','xylophone','Xylophone','alpha');" ;
        String query25 = "INSERT INTO object(alphabet,img,name,category) VALUES('Y','yacht','Yacht','alpha');" ;
        String query26 = "INSERT INTO object(alphabet,img,name,category) VALUES('Z','zebra','Zebra','alpha');" ;
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
        db.execSQL(query11);
        db.execSQL(query12);
        db.execSQL(query13);
        db.execSQL(query14);
        db.execSQL(query15);
        db.execSQL(query16);
        db.execSQL(query17);
        db.execSQL(query18);
        db.execSQL(query19);
        db.execSQL(query20);
        db.execSQL(query21);
        db.execSQL(query22);
        db.execSQL(query23);
        db.execSQL(query24);
        db.execSQL(query25);
        db.execSQL(query26);

    }



 @Override

    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(Alphabet.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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

