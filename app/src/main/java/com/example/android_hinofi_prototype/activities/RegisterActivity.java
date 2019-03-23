package com.example.android_hinofi_prototype.activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.adapters.DatabaseAdapter;

public class RegisterActivity extends AppCompatActivity {

    Context context = this;
    private EditText textEditUsername;// allows to connect to the text entry for username in register
    private EditText textEditEmail;// allows to connect to the text entry for email address in register
    private EditText textEditPassword;// allows to connect to the text entry for password in register
    private EditText textEditDOB;// allows to connect to the text entry for date of birth in register
    private EditText textEditConifrmPassword;// allows to connect to the text entry for password in register

    private String userName;
    private String emailAddress;
    private String password;
    private String dateOfBirth;
    private String confirmPassword;
    String receiveOk;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get instance of the databases adapter
        //init DB
        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter = databaseAdapter.open();
        textEditEmail = findViewById(R.id.textEditEmail);
        textEditPassword = findViewById(R.id.textEditPassword);
        textEditConifrmPassword = findViewById(R.id.textEditConfirmPassword);
        textEditUsername = findViewById(R.id.textEditUsername);
        textEditDOB = findViewById(R.id.textEditDOB);
    }



    public void OK(View view) {

        userName = textEditUsername.getText().toString();
        emailAddress = textEditEmail.getText().toString();
        password = textEditPassword.getText().toString();
        dateOfBirth = textEditDOB.getText().toString();
        confirmPassword = textEditConifrmPassword.toString();


        if ((userName.equals("")) || (emailAddress.equals("")) || (password.equals("")) || (dateOfBirth.equals(""))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("ALERT!");
            alertDialog.setMessage("Fill All Fields");
            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }


        else {


            if (!textEditPassword.getText().toString().equals(textEditConifrmPassword.getText().toString()))
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("ALERT!");
                alertDialog.setMessage("Passwords don't match");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
            else
            {
                //Save the data in the databases
                receiveOk= databaseAdapter.insertUser(userName,emailAddress,password, dateOfBirth);

                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
                alertDialog.setTitle("SUCCESS!");
                alertDialog.setMessage("You've signed up");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    }
                });
                alertDialog.show();
                finish();
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        databaseAdapter.close();
    }
}



