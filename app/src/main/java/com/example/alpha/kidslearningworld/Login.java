package com.example.alpha.kidslearningworld;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText eml,pas;
    Button btnlogin;
    TextView reg;
    Cursor cursor;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    int errorColor;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        eml = (EditText) findViewById(R.id.emailhome);
        pas = (EditText) findViewById(R.id.passowrdhome);
        btnlogin = (Button)findViewById(R.id.loginbtn);


        TextView reg = (TextView)findViewById(R.id.regtext);

        //Error Buider API Level Check
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {

            errorColor = ContextCompat.getColor(getApplicationContext(), R.color.errorColor);
        } else {
            errorColor = getResources().getColor(R.color.errorColor);
        }

        //Opening SQLite Pipeline
        dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEmail()) {
                    return;
                }
                if (!validatePassword()) {
                    return;
                }

                String email = eml.getText().toString();
                String pass = pas.getText().toString();

                cursor = db.rawQuery("SELECT *FROM "+SQLiteDBHelper.TABLE_NAME+" WHERE "+SQLiteDBHelper.COLUMN_EMAIL+"=? AND "+SQLiteDBHelper.COLUMN_PASSWORD+"=?",new String[] {email,pass});
                if (cursor != null) {
                    if(cursor.getCount() > 0 ) {

                        cursor.moveToFirst();

                        SharedPreferences sharedPreferences = Login.this.getSharedPreferences(LoginConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //Adding values to editor
                        editor.putBoolean(LoginConfig.LOGGEDIN_SHARED_PREF, true);
                        editor.putString(LoginConfig.EMAIL_SHARED_PREF, email);

                        //Saving values to editor
                        editor.commit();

                        Intent intent = new Intent(Login.this,Homescreen.class);
                        startActivity(intent);
                        //Removing MainActivity[Login Screen] from the stack for preventing back button press.
                        finish();
                    }
                    else {

                        //I am showing Alert Dialog Box here for alerting user about wrong credentials
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setTitle("Login Error");
                        builder.setMessage("Invalid Username & Password");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
                }



            }


        });


    }

    // When User Resumes the App
    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(LoginConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(LoginConfig.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //start the HomeScreen Activity
            Intent intent = new Intent(Login.this, Homescreen.class);
            startActivity(intent);
        }
    }
    //Validations for Email and Password

    private boolean validateEmail() {
        String mail = eml.getText().toString().trim();

        if (mail.isEmpty() || !isValidEmail(mail)) {
            String errorString = "Enter Valid Email Address";  // your error message
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            eml.setError(spannableStringBuilder);
            return (false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {
        if (pas.getText().toString().trim().isEmpty() || pas.getText().toString().trim().length() < 8) {
            String errorString = "Min. 8 Characters";  // your error message
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            pas.setError(spannableStringBuilder);
            return (false);

        }
        return (true);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
