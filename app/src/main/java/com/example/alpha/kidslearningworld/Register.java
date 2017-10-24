package com.example.alpha.kidslearningworld;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name,e_mail,pwd;
    Button insert;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    int errorColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Error Buider API Level Check
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {

            errorColor = ContextCompat.getColor(getApplicationContext(), R.color.errorColor);
        } else {
            errorColor = getResources().getColor(R.color.errorColor);
        }


        openHelper = new SQLiteDBHelper(this);

        name = (EditText) findViewById(R.id.nametxt);
        e_mail = (EditText) findViewById(R.id.emailtxt);
        pwd = (EditText) findViewById(R.id.passtxt);
        insert = (Button) findViewById(R.id.button3);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName()) {
                    return;
                }
                if (!validateEmail()) {
                    return;
                }
                if (!validatePassword()) {
                    return;
                }
                db = openHelper.getWritableDatabase();
                String fullname = name.getText().toString();
                String email = e_mail.getText().toString();
                String pass = pwd.getText().toString();

                //Calling InsertData Method - Defined below
                InsertData(fullname, email, pass);

                //Alert dialog after clicking the Register Account
                final AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setTitle("Thank You");
                builder.setMessage("Your Account is Successfully Created.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Finishing the dialog and removing Activity from stack.
                        dialogInterface.dismiss();
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }});
    }

    //Inserting Data into database - Like INSERT INTO QUERY.
    public void InsertData(String fullName, String email, String password) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_FULLNAME,fullName);
        values.put(SQLiteDBHelper.COLUMN_EMAIL,email);
        values.put(SQLiteDBHelper.COLUMN_PASSWORD,password);
        long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);

    }

    private boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            String errorString = "Please Enter Full Name";  // your error message
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            name.setError(spannableStringBuilder);
            return (false);

        }
        return (true);
    }

    private boolean validateEmail() {
        String mail = e_mail.getText().toString().trim();

        if (mail.isEmpty() || !isValidEmail(mail)) {
            String errorString = "Enter Valid Email Address";  // your error message
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            e_mail.setError(spannableStringBuilder);
            return (false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {
        if (pwd.getText().toString().trim().isEmpty() || pwd.getText().toString().trim().length() < 8) {
            String errorString = "Min. 8 Characters";  // your error message
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
            pwd.setError(spannableStringBuilder);
            return (false);

        }
        return (true);
    }


}
