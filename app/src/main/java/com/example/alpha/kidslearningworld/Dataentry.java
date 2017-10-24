package com.example.alpha.kidslearningworld;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static com.example.alpha.kidslearningworld.R.id.edalpha;
import static com.example.alpha.kidslearningworld.R.id.edimg;
import static com.example.alpha.kidslearningworld.R.id.edname;

public class Dataentry extends AppCompatActivity {

    Button submit, view, b1;
    EditText id, name, cat, img, alpha;
    ImageView im;
    private Cursor c;
    SQLiteDatabase db;
    private static final String SELECT_SQL = "SELECT * FROM object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dataentry);

        id = (EditText) findViewById(R.id.edid);
        name = (EditText) findViewById(R.id.edname);
        alpha = (EditText) findViewById(edalpha);
        cat = (EditText) findViewById(R.id.edcat);
        img = (EditText) findViewById(R.id.edimg);
        submit = (Button) findViewById(R.id.submit);
        view = (Button) findViewById(R.id.view);
        b1 = (Button) findViewById(R.id.button);
        im = (ImageView) findViewById(R.id.imageView2);

        createDatabase();
        openDatabase();
        c = db.rawQuery(SELECT_SQL, null);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    insertIntoDB();
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.moveToLast();
                showRecords();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dataentry.this,Numbers.class);
                startActivity(i);
            }
        });


    }



    protected void openDatabase() {
        db = openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
    }


    protected void createDatabase(){
        db=openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS object(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, alphabet VARCHAR, img VARCHAR, name VARCHAR, category VARCHAR);");
    }

    protected void insertIntoDB(){
        String alpha1 = alpha.getText().toString().trim();
        String img1 = img.getText().toString().trim();
        String name1 = name.getText().toString().trim();
        String cat1 = cat.getText().toString().trim();

        if(name1.equals("") || alpha1.equals("") || img1.equals("") || cat1.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill all the fields", Toast.LENGTH_LONG).show();
            return;
        }

        String query = "INSERT INTO object(alphabet,img,name,category) VALUES('"+alpha1+"', '"+img1+"' , '"+name1+"' , '"+cat1+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
    }



    protected void showRecords() {
        String alpha1 = c.getString(1);
        String img1 = c.getString(2);
        String name1 = c.getString(3);
        String cat1 = c.getString(4);
        alpha.setText(alpha1);
        img.setText(img1);
        name.setText(name1);
        cat.setText(cat1);


        int id = getResources().getIdentifier(img1,"drawable",getPackageName());
        im.setImageResource(id);
    }




}
