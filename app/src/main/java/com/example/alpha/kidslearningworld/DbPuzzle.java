package com.example.alpha.kidslearningworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbPuzzle extends SQLiteOpenHelper{

    SQLiteDatabase db;
    public static final String TABLE_NAME = "puzzle";
    public static final String ID =  "id";
    public static final String QUESTION =  "ques";
    public static final String A1 =  "a1";
    public static final String A2 =  "a2";
    public static final String A3 =  "a3";
    public static final String A4 =  "a4";
    public static final String ANSWER =  "ans";

    public DbPuzzle(Context context) {
        super(context, "KLWDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+QUESTION+" VARCHAR, "+A1+" VARCHAR, "+A2+" VARCHAR, "+A3+" VARCHAR, "+A4+" VARCHAR, "+ANSWER+" VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
