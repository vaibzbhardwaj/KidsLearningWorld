package com.example.alpha.kidslearningworld;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PuzzleLevel2 extends AppCompatActivity {

    TextView ques;
    ImageView a1,a2,a3,a4;
    private Cursor c;
    SQLiteDatabase db;
    int score,qno=0;
    String a, myscore;
    DbPuzzle dbPuzzle;
    private static final String SELECT_SQL = "SELECT * FROM puzzles2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_puzzle_level2);

        ques = (TextView) findViewById(R.id.textView3);
        a1 = (ImageView) findViewById(R.id.imageView3);
        a2 = (ImageView) findViewById(R.id.imageView4);
        a3 = (ImageView) findViewById(R.id.imageView5);
        a4 = (ImageView) findViewById(R.id.imageView6);

        // dbPuzzle = new DbPuzzle(this);
        // db = dbPuzzle.getWritableDatabase();
        createDatabase();
        openDatabase();
        insertIntoDB();
        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showRecords();


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a1"))
                {
                    score++;
                }
                c.moveToNext();
                showRecords();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a2"))
                {
                    score++;
                }
                c.moveToNext();
                showRecords();
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a3"))
                {
                    score++;
                }
                c.moveToNext();
                showRecords();
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a4"))
                {
                    score++;
                }
                c.moveToNext();
                showRecords();
            }
        });
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS puzzles2(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ques VARCHAR, a1 VARCHAR, a2 VARCHAR, a3 VARCHAR, a4 VARCHAR, ans VARCHAR);");
    }
    protected void showRecords() {
        if(qno == 5)
        {


            //Put value of Integer into String and Send Score to Next Activity (Score.class)
            myscore = String.valueOf(score);
            Intent intent = new Intent(PuzzleLevel2.this, Score.class);
            intent.putExtra("myscore",myscore);
            startActivity(intent);
        }
        else {
            String text = c.getString(1);
            String img1 = c.getString(2);
            String img2 = c.getString(3);
            String img3 = c.getString(4);
            String img4 = c.getString(5);
            a = c.getString(6);

            ques.setText(text);

            int id1 = getResources().getIdentifier(img1, "drawable", getPackageName());
            a1.setImageResource(id1);
            int id2 = getResources().getIdentifier(img2, "drawable", getPackageName());
            a2.setImageResource(id2);
            int id3 = getResources().getIdentifier(img3, "drawable", getPackageName());
            a3.setImageResource(id3);
            int id4 = getResources().getIdentifier(img4, "drawable", getPackageName());
            a4.setImageResource(id4);
            qno++;
        }

    }

    protected void insertIntoDB() {


        String query1 = "INSERT INTO puzzles2(ques,a1,a2,a3,a4,ans) VALUES('4','three','four','six','one','a2');";
        String query2 = "INSERT INTO puzzles2(ques,a1,a2,a3,a4,ans) VALUES('7','seven','three','two','four','a1');";
        String query3 = "INSERT INTO puzzles2(ques,a1,a2,a3,a4,ans) VALUES('2','nine','six','five','two','a4');";
        String query4 = "INSERT INTO puzzles2(ques,a1,a2,a3,a4,ans) VALUES('5','three','five','nine','ten','a2');";
        String query5 = "INSERT INTO puzzles2(ques,a1,a2,a3,a4,ans) VALUES('8','six','nine','eight','four','a3');";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
    }

}
