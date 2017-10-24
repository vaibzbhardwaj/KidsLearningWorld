package com.example.alpha.kidslearningworld;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.os.Handler;


public class Homescreen extends AppCompatActivity {

    ImageButton alpha,rhyme,animal,fruit,drawing,numbers,puzzle,veg,colors;
    ImageView log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homescreen);

        alpha = (ImageButton) findViewById(R.id.alphabets);
        rhyme = (ImageButton) findViewById(R.id.rhymes);
        animal = (ImageButton) findViewById(R.id.animals);
        fruit = (ImageButton) findViewById(R.id.fruits);
        drawing = (ImageButton) findViewById(R.id.drawingarea);
        numbers = (ImageButton) findViewById(R.id.numbers);
        puzzle = (ImageButton) findViewById(R.id.puzzles);
        veg = (ImageButton) findViewById(R.id.vegetables);
        colors = (ImageButton) findViewById(R.id.colors);
        log_out = (ImageView) findViewById(R.id.logout);

        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Homescreen.this,Alphabet.class);
                startActivity(intent);

            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Numbers.class);
                startActivity(intent);
            }
        });

        rhyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Rhymes.class);
                startActivity(intent);
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Animals.class);
                startActivity(intent);
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Fruits.class);
                startActivity(intent);
            }
        });

        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Vegetables.class);
                startActivity(intent);
            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Colors.class);
                startActivity(intent);
            }
        });
        drawing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Drawing.class);
                startActivity(intent);
            }
        });

        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,PuzzleLevel.class);
                startActivity(intent);
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(Homescreen.this, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.mymenu, popup.getMenu());


                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        String a = item.getTitle().toString().trim();
                        if(a.equals("Logout"))
                        {
                            logout();
                        }
                        if(a.equals("Exit"))
                        {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            finish();
                            System.exit(0);

                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });

    }

    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are You Sure you want to Logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(LoginConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(LoginConfig.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(LoginConfig.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        finishActivity(0);
                        //Starting login activity
                        Intent intent = new Intent(Homescreen.this, Login.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if(exit)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }
        else
        {
            Toast.makeText(this, "Press Again to Exit",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);
        }
    }
}
